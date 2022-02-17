import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './documentarticle.reducer';
import { IDocumentarticle } from 'app/shared/model/documentarticle.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Documentarticle = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const documentarticleList = useAppSelector(state => state.documentarticle.entities);
  const loading = useAppSelector(state => state.documentarticle.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="documentarticle-heading" data-cy="DocumentarticleHeading">
        <Translate contentKey="mayaApp.documentarticle.home.title">Documentarticles</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="mayaApp.documentarticle.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="mayaApp.documentarticle.home.createLabel">Create new Documentarticle</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {documentarticleList && documentarticleList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="mayaApp.documentarticle.id">Id</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.documentarticle.documentId">Document Id</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.documentarticle.contractarticleId">Contractarticle Id</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.documentarticle.is_deleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.documentarticle.updateDate">Update Date</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.documentarticle.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.documentarticle.createBy">Create By</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.documentarticle.updateBy">Update By</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.documentarticle.contract">Contract</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.documentarticle.parent">Parent</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {documentarticleList.map((documentarticle, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${documentarticle.id}`} color="link" size="sm">
                      {documentarticle.id}
                    </Button>
                  </td>
                  <td>{documentarticle.documentId}</td>
                  <td>{documentarticle.contractarticleId}</td>
                  <td>{documentarticle.is_deleted ? 'true' : 'false'}</td>
                  <td>
                    {documentarticle.updateDate ? (
                      <TextFormat type="date" value={documentarticle.updateDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {documentarticle.createDate ? (
                      <TextFormat type="date" value={documentarticle.createDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{documentarticle.createBy}</td>
                  <td>{documentarticle.updateBy}</td>
                  <td>
                    {documentarticle.contract ? (
                      <Link to={`contract/${documentarticle.contract.id}`}>{documentarticle.contract.contractname}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {documentarticle.parent ? (
                      <Link to={`contract/${documentarticle.parent.id}`}>{documentarticle.parent.contractname}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${documentarticle.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${documentarticle.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${documentarticle.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
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
              <Translate contentKey="mayaApp.documentarticle.home.notFound">No Documentarticles found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Documentarticle;
