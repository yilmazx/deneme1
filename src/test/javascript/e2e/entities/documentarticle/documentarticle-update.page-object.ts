import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class DocumentarticleUpdatePage {
  pageTitle: ElementFinder = element(by.id('mayaApp.documentarticle.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  documentIdInput: ElementFinder = element(by.css('input#documentarticle-documentId'));
  contractarticleIdInput: ElementFinder = element(by.css('input#documentarticle-contractarticleId'));
  is_deletedInput: ElementFinder = element(by.css('input#documentarticle-is_deleted'));
  updateDateInput: ElementFinder = element(by.css('input#documentarticle-updateDate'));
  createDateInput: ElementFinder = element(by.css('input#documentarticle-createDate'));
  createByInput: ElementFinder = element(by.css('input#documentarticle-createBy'));
  updateByInput: ElementFinder = element(by.css('input#documentarticle-updateBy'));
  contractSelect: ElementFinder = element(by.css('select#documentarticle-contract'));
  parentSelect: ElementFinder = element(by.css('select#documentarticle-parent'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setDocumentIdInput(documentId) {
    await this.documentIdInput.sendKeys(documentId);
  }

  async getDocumentIdInput() {
    return this.documentIdInput.getAttribute('value');
  }

  async setContractarticleIdInput(contractarticleId) {
    await this.contractarticleIdInput.sendKeys(contractarticleId);
  }

  async getContractarticleIdInput() {
    return this.contractarticleIdInput.getAttribute('value');
  }

  getIs_deletedInput() {
    return this.is_deletedInput;
  }
  async setUpdateDateInput(updateDate) {
    await this.updateDateInput.sendKeys(updateDate);
  }

  async getUpdateDateInput() {
    return this.updateDateInput.getAttribute('value');
  }

  async setCreateDateInput(createDate) {
    await this.createDateInput.sendKeys(createDate);
  }

  async getCreateDateInput() {
    return this.createDateInput.getAttribute('value');
  }

  async setCreateByInput(createBy) {
    await this.createByInput.sendKeys(createBy);
  }

  async getCreateByInput() {
    return this.createByInput.getAttribute('value');
  }

  async setUpdateByInput(updateBy) {
    await this.updateByInput.sendKeys(updateBy);
  }

  async getUpdateByInput() {
    return this.updateByInput.getAttribute('value');
  }

  async contractSelectLastOption() {
    await this.contractSelect.all(by.tagName('option')).last().click();
  }

  async contractSelectOption(option) {
    await this.contractSelect.sendKeys(option);
  }

  getContractSelect() {
    return this.contractSelect;
  }

  async getContractSelectedOption() {
    return this.contractSelect.element(by.css('option:checked')).getText();
  }

  async parentSelectLastOption() {
    await this.parentSelect.all(by.tagName('option')).last().click();
  }

  async parentSelectOption(option) {
    await this.parentSelect.sendKeys(option);
  }

  getParentSelect() {
    return this.parentSelect;
  }

  async getParentSelectedOption() {
    return this.parentSelect.element(by.css('option:checked')).getText();
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }

  async enterData() {
    await waitUntilDisplayed(this.saveButton);
    await this.setDocumentIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setContractarticleIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    const selectedIs_deleted = await this.getIs_deletedInput().isSelected();
    if (selectedIs_deleted) {
      await this.getIs_deletedInput().click();
    } else {
      await this.getIs_deletedInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    await this.setUpdateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setCreateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setCreateByInput('createBy');
    await waitUntilDisplayed(this.saveButton);
    await this.setUpdateByInput('updateBy');
    await this.contractSelectLastOption();
    await this.parentSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
