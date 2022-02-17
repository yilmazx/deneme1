import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class ContractUpdatePage {
  pageTitle: ElementFinder = element(by.id('mayaApp.contract.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  contractnameInput: ElementFinder = element(by.css('input#contract-contractname'));
  contractpathInput: ElementFinder = element(by.css('input#contract-contractpath'));
  uidInput: ElementFinder = element(by.css('input#contract-uid'));
  userInput: ElementFinder = element(by.css('input#contract-user'));
  isdeletedInput: ElementFinder = element(by.css('input#contract-isdeleted'));
  isenabledInput: ElementFinder = element(by.css('input#contract-isenabled'));
  updateDateInput: ElementFinder = element(by.css('input#contract-updateDate'));
  createDateInput: ElementFinder = element(by.css('input#contract-createDate'));
  createByInput: ElementFinder = element(by.css('input#contract-createBy'));
  updateByInput: ElementFinder = element(by.css('input#contract-updateBy'));
  citySelect: ElementFinder = element(by.css('select#contract-city'));
  countrySelect: ElementFinder = element(by.css('select#contract-country'));
  legalareaSelect: ElementFinder = element(by.css('select#contract-legalarea'));
  industrySelect: ElementFinder = element(by.css('select#contract-industry'));
  contracttypeSelect: ElementFinder = element(by.css('select#contract-contracttype'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setContractnameInput(contractname) {
    await this.contractnameInput.sendKeys(contractname);
  }

  async getContractnameInput() {
    return this.contractnameInput.getAttribute('value');
  }

  async setContractpathInput(contractpath) {
    await this.contractpathInput.sendKeys(contractpath);
  }

  async getContractpathInput() {
    return this.contractpathInput.getAttribute('value');
  }

  async setUidInput(uid) {
    await this.uidInput.sendKeys(uid);
  }

  async getUidInput() {
    return this.uidInput.getAttribute('value');
  }

  async setUserInput(user) {
    await this.userInput.sendKeys(user);
  }

  async getUserInput() {
    return this.userInput.getAttribute('value');
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

  async citySelectLastOption() {
    await this.citySelect.all(by.tagName('option')).last().click();
  }

  async citySelectOption(option) {
    await this.citySelect.sendKeys(option);
  }

  getCitySelect() {
    return this.citySelect;
  }

  async getCitySelectedOption() {
    return this.citySelect.element(by.css('option:checked')).getText();
  }

  async countrySelectLastOption() {
    await this.countrySelect.all(by.tagName('option')).last().click();
  }

  async countrySelectOption(option) {
    await this.countrySelect.sendKeys(option);
  }

  getCountrySelect() {
    return this.countrySelect;
  }

  async getCountrySelectedOption() {
    return this.countrySelect.element(by.css('option:checked')).getText();
  }

  async legalareaSelectLastOption() {
    await this.legalareaSelect.all(by.tagName('option')).last().click();
  }

  async legalareaSelectOption(option) {
    await this.legalareaSelect.sendKeys(option);
  }

  getLegalareaSelect() {
    return this.legalareaSelect;
  }

  async getLegalareaSelectedOption() {
    return this.legalareaSelect.element(by.css('option:checked')).getText();
  }

  async industrySelectLastOption() {
    await this.industrySelect.all(by.tagName('option')).last().click();
  }

  async industrySelectOption(option) {
    await this.industrySelect.sendKeys(option);
  }

  getIndustrySelect() {
    return this.industrySelect;
  }

  async getIndustrySelectedOption() {
    return this.industrySelect.element(by.css('option:checked')).getText();
  }

  async contracttypeSelectLastOption() {
    await this.contracttypeSelect.all(by.tagName('option')).last().click();
  }

  async contracttypeSelectOption(option) {
    await this.contracttypeSelect.sendKeys(option);
  }

  getContracttypeSelect() {
    return this.contracttypeSelect;
  }

  async getContracttypeSelectedOption() {
    return this.contracttypeSelect.element(by.css('option:checked')).getText();
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
    await this.setContractnameInput('contractname');
    await waitUntilDisplayed(this.saveButton);
    await this.setContractpathInput('contractpath');
    await waitUntilDisplayed(this.saveButton);
    await this.setUidInput('uid');
    await waitUntilDisplayed(this.saveButton);
    await this.setUserInput('user');
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
    await this.citySelectLastOption();
    await this.countrySelectLastOption();
    await this.legalareaSelectLastOption();
    await this.industrySelectLastOption();
    await this.contracttypeSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
