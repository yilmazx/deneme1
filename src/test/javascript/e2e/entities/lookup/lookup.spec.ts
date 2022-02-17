import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import LookupComponentsPage from './lookup.page-object';
import LookupUpdatePage from './lookup-update.page-object';
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

describe('Lookup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let lookupComponentsPage: LookupComponentsPage;
  let lookupUpdatePage: LookupUpdatePage;
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
    lookupComponentsPage = new LookupComponentsPage();
    lookupComponentsPage = await lookupComponentsPage.goToPage(navBarPage);
  });

  it('should load Lookups', async () => {
    expect(await lookupComponentsPage.title.getText()).to.match(/Lookups/);
    expect(await lookupComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Lookups', async () => {
    const beforeRecordsCount = (await isVisible(lookupComponentsPage.noRecords)) ? 0 : await getRecordsCount(lookupComponentsPage.table);
    lookupUpdatePage = await lookupComponentsPage.goToCreateLookup();
    await lookupUpdatePage.enterData();
    expect(await isVisible(lookupUpdatePage.saveButton)).to.be.false;

    expect(await lookupComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(lookupComponentsPage.table);
    await waitUntilCount(lookupComponentsPage.records, beforeRecordsCount + 1);
    expect(await lookupComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await lookupComponentsPage.deleteLookup();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(lookupComponentsPage.records, beforeRecordsCount);
      expect(await lookupComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(lookupComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
