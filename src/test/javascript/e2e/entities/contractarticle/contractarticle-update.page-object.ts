import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class ContractarticleUpdatePage {
  pageTitle: ElementFinder = element(by.id('mayaApp.contractarticle.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  articlenameInput: ElementFinder = element(by.css('input#contractarticle-articlename'));
  detailInput: ElementFinder = element(by.css('input#contractarticle-detail'));
  uidInput: ElementFinder = element(by.css('input#contractarticle-uid'));
  isdeletedInput: ElementFinder = element(by.css('input#contractarticle-isdeleted'));
  isenabledInput: ElementFinder = element(by.css('input#contractarticle-isenabled'));
  updateDateInput: ElementFinder = element(by.css('input#contractarticle-updateDate'));
  createDateInput: ElementFinder = element(by.css('input#contractarticle-createDate'));
  createByInput: ElementFinder = element(by.css('input#contractarticle-createBy'));
  updateByInput: ElementFinder = element(by.css('input#contractarticle-updateBy'));
  contractSelect: ElementFinder = element(by.css('select#contractarticle-contract'));
  parentSelect: ElementFinder = element(by.css('select#contractarticle-parent'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setArticlenameInput(articlename) {
    await this.articlenameInput.sendKeys(articlename);
  }

  async getArticlenameInput() {
    return this.articlenameInput.getAttribute('value');
  }

  async setDetailInput(detail) {
    await this.detailInput.sendKeys(detail);
  }

  async getDetailInput() {
    return this.detailInput.getAttribute('value');
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
    await this.setArticlenameInput('articlename');
    await waitUntilDisplayed(this.saveButton);
    await this.setDetailInput('detail');
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
    await this.parentSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
