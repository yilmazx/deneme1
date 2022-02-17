import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Lookup from './lookup';
import Contract from './contract';
import ContractInput from './contract-input';
import Contractarticle from './contractarticle';
import Document from './document';
import Documentarticle from './documentarticle';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}lookup`} component={Lookup} />
      <ErrorBoundaryRoute path={`${match.url}contract`} component={Contract} />
      <ErrorBoundaryRoute path={`${match.url}contract-input`} component={ContractInput} />
      <ErrorBoundaryRoute path={`${match.url}contractarticle`} component={Contractarticle} />
      <ErrorBoundaryRoute path={`${match.url}document`} component={Document} />
      <ErrorBoundaryRoute path={`${match.url}documentarticle`} component={Documentarticle} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
