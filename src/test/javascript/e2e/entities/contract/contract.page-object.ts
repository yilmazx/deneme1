import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import ContractUpdatePage from './contract-update.page-object';

const expect = chai.expect;
export class ContractDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('mayaApp.contract.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-contract'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class ContractComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('contract-heading'));
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
    await navBarPage.getEntityPage('contract');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateContract() {
    await this.createButton.click();
    return new ContractUpdatePage();
  }

  async deleteContract() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const contractDeleteDialog = new ContractDeleteDialog();
    await waitUntilDisplayed(contractDeleteDialog.deleteModal);
    expect(await contractDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/mayaApp.contract.delete.question/);
    await contractDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(contractDeleteDialog.deleteModal);

    expect(await isVisible(contractDeleteDialog.deleteModal)).to.be.false;
  }
}
