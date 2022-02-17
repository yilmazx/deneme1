import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale from './locale';
import authentication from './authentication';
import applicationProfile from './application-profile';

import administration from 'app/modules/administration/administration.reducer';
import userManagement from 'app/modules/administration/user-management/user-management.reducer';
import register from 'app/modules/account/register/register.reducer';
import activate from 'app/modules/account/activate/activate.reducer';
import password from 'app/modules/account/password/password.reducer';
import settings from 'app/modules/account/settings/settings.reducer';
import passwordReset from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import lookup from 'app/entities/lookup/lookup.reducer';
// prettier-ignore
import contract from 'app/entities/contract/contract.reducer';
// prettier-ignore
import contractInput from 'app/entities/contract-input/contract-input.reducer';
// prettier-ignore
import contractarticle from 'app/entities/contractarticle/contractarticle.reducer';
// prettier-ignore
import document from 'app/entities/document/document.reducer';
// prettier-ignore
import documentarticle from 'app/entities/documentarticle/documentarticle.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

import contractlookupselect from 'app/lookupselect/contract-lookup-select.reducer';
import contractselect from 'app/contractselect/contract-select/contract-select.reducer';
import contractinputselect from 'app/contractselect/contract-input-select/contract-input-select.reducer';
import contractarticleselect from 'app/contractselect/contract-article-select/contract-article-select.reducer';

const rootReducer = {
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  lookup,
  contract,
  contractInput,
  contractarticle,
  document,
  documentarticle,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
  contractlookupselect,
  contractselect,
  contractinputselect,
  contractarticleselect,
};

export default rootReducer;
