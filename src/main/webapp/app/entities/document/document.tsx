import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './document.reducer';
import { IDocument } from 'app/shared/model/document.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Document = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const documentList = useAppSelector(state => state.document.entities);
  const loading = useAppSelector(state => state.document.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="document-heading" data-cy="DocumentHeading">
        <Translate contentKey="mayaApp.document.home.title">Documents</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="mayaApp.document.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="mayaApp.document.home.createLabel">Create new Document</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {documentList && documentList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="mayaApp.document.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.documentname">Documentname</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.uid">Uid</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.path">Path</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.contactId">Contact Id</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.inputs">Inputs</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.is_deleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.isenabled">Isenabled</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.updateDate">Update Date</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.createBy">Create By</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.updateBy">Update By</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.document.contract">Contract</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {documentList.map((document, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${document.id}`} color="link" size="sm">
                      {document.id}
                    </Button>
                  </td>
                  <td>{document.documentname}</td>
                  <td>{document.description}</td>
                  <td>{document.uid}</td>
                  <td>{document.path}</td>
                  <td>{document.contactId}</td>
                  <td>{document.inputs}</td>
                  <td>{document.is_deleted ? 'true' : 'false'}</td>
                  <td>{document.isenabled ? 'true' : 'false'}</td>
                  <td>{document.updateDate ? <TextFormat type="date" value={document.updateDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{document.createDate ? <TextFormat type="date" value={document.createDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{document.createBy}</td>
                  <td>{document.updateBy}</td>
                  <td>{document.contract ? <Link to={`contract/${document.contract.id}`}>{document.contract.contractname}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${document.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${document.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${document.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="mayaApp.document.home.notFound">No Documents found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Document;
