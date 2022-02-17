import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ContractInput from './contract-input';
import ContractInputDetail from './contract-input-detail';
import ContractInputUpdate from './contract-input-update';
import ContractInputDeleteDialog from './contract-input-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContractInputUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContractInputUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContractInputDetail} />
      <ErrorBoundaryRoute path={match.url} component={ContractInput} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ContractInputDeleteDialog} />
  </>
);

export default Routes;
