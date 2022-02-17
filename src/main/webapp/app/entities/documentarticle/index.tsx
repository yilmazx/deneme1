import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Documentarticle from './documentarticle';
import DocumentarticleDetail from './documentarticle-detail';
import DocumentarticleUpdate from './documentarticle-update';
import DocumentarticleDeleteDialog from './documentarticle-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DocumentarticleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DocumentarticleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DocumentarticleDetail} />
      <ErrorBoundaryRoute path={match.url} component={Documentarticle} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DocumentarticleDeleteDialog} />
  </>
);

export default Routes;
