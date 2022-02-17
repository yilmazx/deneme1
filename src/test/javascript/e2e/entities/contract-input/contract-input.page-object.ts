import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import ContractInputUpdatePage from './contract-input-update.page-object';

const expect = chai.expect;
export class ContractInputDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('mayaApp.contractInput.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-contractInput'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class ContractInputComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('contract-input-heading'));
  noRecords: ElementFinder = element(by.css('#app-view-container .table-responsive div.alert.alert-warning'));
  table: ElementFinder = element(by.css('#app-view-container div.table-responsive > table'));

  records: ElementArrayFinder = this.table.all(by.css('tbody tr'));

  getDetailsButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-info.btn-sm'));
  }

  getEditButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-primary.btn-sm'));
  }

  getDeleteButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-danger.btn-sm'));
  }

  async goToPage(navBarPage: NavBarPage) {
    await navBarPage.getEntityPage('contract-input');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateContractInput() {
    await this.createButton.click();
    return new ContractInputUpdatePage();
  }

  async deleteContractInput() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const contractInputDeleteDialog = new ContractInputDeleteDialog();
    await waitUntilDisplayed(contractInputDeleteDialog.deleteModal);
    expect(await contractInputDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/mayaApp.contractInput.delete.question/);
    await contractInputDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(contractInputDeleteDialog.deleteModal);

    expect(await isVisible(contractInputDeleteDialog.deleteModal)).to.be.false;
  }
}
