import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './contractarticle.reducer';
import { IContractarticle } from 'app/shared/model/contractarticle.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Contractarticle = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const contractarticleList = useAppSelector(state => state.contractarticle.entities);
  const loading = useAppSelector(state => state.contractarticle.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="contractarticle-heading" data-cy="ContractarticleHeading">
        <Translate contentKey="mayaApp.contractarticle.home.title">Contractarticles</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="mayaApp.contractarticle.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="mayaApp.contractarticle.home.createLabel">Create new Contractarticle</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {contractarticleList && contractarticleList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.articlename">Articlename</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.detail">Detail</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.uid">Uid</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.isdeleted">Isdeleted</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.isenabled">Isenabled</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.updateDate">Update Date</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.createBy">Create By</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.updateBy">Update By</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.contract">Contract</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractarticle.parent">Parent</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {contractarticleList.map((contractarticle, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${contractarticle.id}`} color="link" size="sm">
                      {contractarticle.id}
                    </Button>
                  </td>
                  <td>{contractarticle.articlename}</td>
                  <td>{contractarticle.detail}</td>
                  <td>{contractarticle.uid}</td>
                  <td>{contractarticle.isdeleted ? 'true' : 'false'}</td>
                  <td>{contractarticle.isenabled ? 'true' : 'false'}</td>
                  <td>
                    {contractarticle.updateDate ? (
                      <TextFormat type="date" value={contractarticle.updateDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {contractarticle.createDate ? (
                      <TextFormat type="date" value={contractarticle.createDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{contractarticle.createBy}</td>
                  <td>{contractarticle.updateBy}</td>
                  <td>
                    {contractarticle.contract ? (
                      <Link to={`contract/${contractarticle.contract.id}`}>{contractarticle.contract.contractname}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {contractarticle.parent ? (
                      <Link to={`contractarticle/${contractarticle.parent.id}`}>{contractarticle.parent.articlename}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${contractarticle.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${contractarticle.id}/edit`}
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
                        to={`${match.url}/${contractarticle.id}/delete`}
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
              <Translate contentKey="mayaApp.contractarticle.home.notFound">No Contractarticles found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Contractarticle;
