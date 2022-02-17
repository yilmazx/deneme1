import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import ContractInputComponentsPage from './contract-input.page-object';
import ContractInputUpdatePage from './contract-input-update.page-object';
import {
  waitUntilDisplayed,
  waitUntilAnyDisplayed,
  click,
  getRecordsCount,
  waitUntilHidden,
  waitUntilCount,
  isVisible,
} from '../../util/utils';

const expect = chai.expect;

describe('ContractInput e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let contractInputComponentsPage: ContractInputComponentsPage;
  let contractInputUpdatePage: ContractInputUpdatePage;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();
    await signInPage.username.sendKeys(username);
    await signInPage.password.sendKeys(password);
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();
    await waitUntilDisplayed(navBarPage.entityMenu);
    await waitUntilDisplayed(navBarPage.adminMenu);
    await waitUntilDisplayed(navBarPage.accountMenu);
  });

  beforeEach(async () => {
    await browser.get('/');
    await waitUntilDisplayed(navBarPage.entityMenu);
    contractInputComponentsPage = new ContractInputComponentsPage();
    contractInputComponentsPage = await contractInputComponentsPage.goToPage(navBarPage);
  });

  it('should load ContractInputs', async () => {
    expect(await contractInputComponentsPage.title.getText()).to.match(/Contract Inputs/);
    expect(await contractInputComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete ContractInputs', async () => {
    const beforeRecordsCount = (await isVisible(contractInputComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(contractInputComponentsPage.table);
    contractInputUpdatePage = await contractInputComponentsPage.goToCreateContractInput();
    await contractInputUpdatePage.enterData();
    expect(await isVisible(contractInputUpdatePage.saveButton)).to.be.false;

    expect(await contractInputComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(contractInputComponentsPage.table);
    await waitUntilCount(contractInputComponentsPage.records, beforeRecordsCount + 1);
    expect(await contractInputComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await contractInputComponentsPage.deleteContractInput();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(contractInputComponentsPage.records, beforeRecordsCount);
      expect(await contractInputComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(contractInputComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
