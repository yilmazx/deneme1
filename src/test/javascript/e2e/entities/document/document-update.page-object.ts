import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class DocumentUpdatePage {
  pageTitle: ElementFinder = element(by.id('mayaApp.document.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  documentnameInput: ElementFinder = element(by.css('input#document-documentname'));
  descriptionInput: ElementFinder = element(by.css('input#document-description'));
  uidInput: ElementFinder = element(by.css('input#document-uid'));
  pathInput: ElementFinder = element(by.css('input#document-path'));
  contactIdInput: ElementFinder = element(by.css('input#document-contactId'));
  inputsInput: ElementFinder = element(by.css('input#document-inputs'));
  is_deletedInput: ElementFinder = element(by.css('input#document-is_deleted'));
  isenabledInput: ElementFinder = element(by.css('input#document-isenabled'));
  updateDateInput: ElementFinder = element(by.css('input#document-updateDate'));
  createDateInput: ElementFinder = element(by.css('input#document-createDate'));
  createByInput: ElementFinder = element(by.css('input#document-createBy'));
  updateByInput: ElementFinder = element(by.css('input#document-updateBy'));
  contractSelect: ElementFinder = element(by.css('select#document-contract'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setDocumentnameInput(documentname) {
    await this.documentnameInput.sendKeys(documentname);
  }

  async getDocumentnameInput() {
    return this.documentnameInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return this.descriptionInput.getAttribute('value');
  }

  async setUidInput(uid) {
    await this.uidInput.sendKeys(uid);
  }

  async getUidInput() {
    return this.uidInput.getAttribute('value');
  }

  async setPathInput(path) {
    await this.pathInput.sendKeys(path);
  }

  async getPathInput() {
    return this.pathInput.getAttribute('value');
  }

  async setContactIdInput(contactId) {
    await this.contactIdInput.sendKeys(contactId);
  }

  async getContactIdInput() {
    return this.contactIdInput.getAttribute('value');
  }

  async setInputsInput(inputs) {
    await this.inputsInput.sendKeys(inputs);
  }

  async getInputsInput() {
    return this.inputsInput.getAttribute('value');
  }

  getIs_deletedInput() {
    return this.is_deletedInput;
  }
  getIsenabledInput() {
    return this.isenabledInput;
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
    await this.setDocumentnameInput('documentname');
    await waitUntilDisplayed(this.saveButton);
    await this.setDescriptionInput('description');
    await waitUntilDisplayed(this.saveButton);
    await this.setUidInput('uid');
    await waitUntilDisplayed(this.saveButton);
    await this.setPathInput('path');
    await waitUntilDisplayed(this.saveButton);
    await this.setContactIdInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setInputsInput('inputs');
    await waitUntilDisplayed(this.saveButton);
    const selectedIs_deleted = await this.getIs_deletedInput().isSelected();
    if (selectedIs_deleted) {
      await this.getIs_deletedInput().click();
    } else {
      await this.getIs_deletedInput().click();
    }
    await waitUntilDisplayed(this.saveButton);
    const selectedIsenabled = await this.getIsenabledInput().isSelected();
    if (selectedIsenabled) {
      await this.getIsenabledInput().click();
    } else {
      await this.getIsenabledInput().click();
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
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
