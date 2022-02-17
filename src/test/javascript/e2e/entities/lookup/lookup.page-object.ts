import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import LookupUpdatePage from './lookup-update.page-object';

const expect = chai.expect;
export class LookupDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('mayaApp.lookup.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-lookup'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class LookupComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('lookup-heading'));
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
    await navBarPage.getEntityPage('lookup');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateLookup() {
    await this.createButton.click();
    return new LookupUpdatePage();
  }

  async deleteLookup() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const lookupDeleteDialog = new LookupDeleteDialog();
    await waitUntilDisplayed(lookupDeleteDialog.deleteModal);
    expect(await lookupDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/mayaApp.lookup.delete.question/);
    await lookupDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(lookupDeleteDialog.deleteModal);

    expect(await isVisible(lookupDeleteDialog.deleteModal)).to.be.false;
  }
}
