import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class ContractInputUpdatePage {
  pageTitle: ElementFinder = element(by.id('mayaApp.contractInput.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  inputnameInput: ElementFinder = element(by.css('input#contract-input-inputname'));
  inputvalueInput: ElementFinder = element(by.css('input#contract-input-inputvalue'));
  uidInput: ElementFinder = element(by.css('input#contract-input-uid'));
  isdeletedInput: ElementFinder = element(by.css('input#contract-input-isdeleted'));
  isenabledInput: ElementFinder = element(by.css('input#contract-input-isenabled'));
  updateDateInput: ElementFinder = element(by.css('input#contract-input-updateDate'));
  createDateInput: ElementFinder = element(by.css('input#contract-input-createDate'));
  createByInput: ElementFinder = element(by.css('input#contract-input-createBy'));
  updateByInput: ElementFinder = element(by.css('input#contract-input-updateBy'));
  contractSelect: ElementFinder = element(by.css('select#contract-input-contract'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setInputnameInput(inputname) {
    await this.inputnameInput.sendKeys(inputname);
  }

  async getInputnameInput() {
    return this.inputnameInput.getAttribute('value');
  }

  async setInputvalueInput(inputvalue) {
    await this.inputvalueInput.sendKeys(inputvalue);
  }

  async getInputvalueInput() {
    return this.inputvalueInput.getAttribute('value');
  }

  async setUidInput(uid) {
    await this.uidInput.sendKeys(uid);
  }

  async getUidInput() {
    return this.uidInput.getAttribute('value');
  }

  getIsdeletedInput() {
    return this.isdeletedInput;
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
    await this.setInputnameInput('inputname');
    await waitUntilDisplayed(this.saveButton);
    await this.setInputvalueInput('inputvalue');
    await waitUntilDisplayed(this.saveButton);
    await this.setUidInput('uid');
    await waitUntilDisplayed(this.saveButton);
    const selectedIsdeleted = await this.getIsdeletedInput().isSelected();
    if (selectedIsdeleted) {
      await this.getIsdeletedInput().click();
    } else {
      await this.getIsdeletedInput().click();
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
