import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import DocumentarticleComponentsPage from './documentarticle.page-object';
import DocumentarticleUpdatePage from './documentarticle-update.page-object';
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

describe('Documentarticle e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let documentarticleComponentsPage: DocumentarticleComponentsPage;
  let documentarticleUpdatePage: DocumentarticleUpdatePage;
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
    documentarticleComponentsPage = new DocumentarticleComponentsPage();
    documentarticleComponentsPage = await documentarticleComponentsPage.goToPage(navBarPage);
  });

  it('should load Documentarticles', async () => {
    expect(await documentarticleComponentsPage.title.getText()).to.match(/Documentarticles/);
    expect(await documentarticleComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Documentarticles', async () => {
    const beforeRecordsCount = (await isVisible(documentarticleComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(documentarticleComponentsPage.table);
    documentarticleUpdatePage = await documentarticleComponentsPage.goToCreateDocumentarticle();
    await documentarticleUpdatePage.enterData();
    expect(await isVisible(documentarticleUpdatePage.saveButton)).to.be.false;

    expect(await documentarticleComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(documentarticleComponentsPage.table);
    await waitUntilCount(documentarticleComponentsPage.records, beforeRecordsCount + 1);
    expect(await documentarticleComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await documentarticleComponentsPage.deleteDocumentarticle();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(documentarticleComponentsPage.records, beforeRecordsCount);
      expect(await documentarticleComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(documentarticleComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
