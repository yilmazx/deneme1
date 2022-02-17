import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Contractarticle from './contractarticle';
import ContractarticleDetail from './contractarticle-detail';
import ContractarticleUpdate from './contractarticle-update';
import ContractarticleDeleteDialog from './contractarticle-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContractarticleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContractarticleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContractarticleDetail} />
      <ErrorBoundaryRoute path={match.url} component={Contractarticle} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ContractarticleDeleteDialog} />
  </>
);

export default Routes;
