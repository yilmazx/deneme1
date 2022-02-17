import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './lookup.reducer';
import { ILookup } from 'app/shared/model/lookup.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Lookup = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const lookupList = useAppSelector(state => state.lookup.entities);
  const loading = useAppSelector(state => state.lookup.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="lookup-heading" data-cy="LookupHeading">
        <Translate contentKey="mayaApp.lookup.home.title">Lookups</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="mayaApp.lookup.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="mayaApp.lookup.home.createLabel">Create new Lookup</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {lookupList && lookupList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="mayaApp.lookup.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.lookup.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.lookup.lang">Lang</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.lookup.uid">Uid</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.lookup.value">Value</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.lookup.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.lookup.parent">Parent</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {lookupList.map((lookup, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${lookup.id}`} color="link" size="sm">
                      {lookup.id}
                    </Button>
                  </td>
                  <td>{lookup.name}</td>
                  <td>{lookup.lang}</td>
                  <td>{lookup.uid}</td>
                  <td>{lookup.value}</td>
                  <td>{lookup.description}</td>
                  <td>{lookup.parent ? <Link to={`lookup/${lookup.parent.id}`}>{lookup.parent.uid}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${lookup.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${lookup.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${lookup.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="mayaApp.lookup.home.notFound">No Lookups found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Lookup;
