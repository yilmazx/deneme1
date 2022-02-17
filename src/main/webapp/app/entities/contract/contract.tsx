import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './contract.reducer';
import { IContract } from 'app/shared/model/contract.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Contract = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const contractList = useAppSelector(state => state.contract.entities);
  const loading = useAppSelector(state => state.contract.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="contract-heading" data-cy="ContractHeading">
        <Translate contentKey="mayaApp.contract.home.title">Contracts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="mayaApp.contract.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="mayaApp.contract.home.createLabel">Create new Contract</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {contractList && contractList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="mayaApp.contract.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.contractname">Contractname</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.contractpath">Contractpath</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.uid">Uid</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.user">User</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.isdeleted">Isdeleted</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.isenabled">Isenabled</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.updateDate">Update Date</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.createBy">Create By</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.updateBy">Update By</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.city">City</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.country">Country</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.legalarea">Legalarea</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.industry">Industry</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contract.contracttype">Contracttype</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {contractList.map((contract, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${contract.id}`} color="link" size="sm">
                      {contract.id}
                    </Button>
                  </td>
                  <td>{contract.contractname}</td>
                  <td>{contract.contractpath}</td>
                  <td>{contract.uid}</td>
                  <td>{contract.user}</td>
                  <td>{contract.isdeleted ? 'true' : 'false'}</td>
                  <td>{contract.isenabled ? 'true' : 'false'}</td>
                  <td>{contract.updateDate ? <TextFormat type="date" value={contract.updateDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{contract.createDate ? <TextFormat type="date" value={contract.createDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{contract.createBy}</td>
                  <td>{contract.updateBy}</td>
                  <td>{contract.city ? <Link to={`lookup/${contract.city.id}`}>{contract.city.name}</Link> : ''}</td>
                  <td>{contract.country ? <Link to={`lookup/${contract.country.id}`}>{contract.country.name}</Link> : ''}</td>
                  <td>{contract.legalarea ? <Link to={`lookup/${contract.legalarea.id}`}>{contract.legalarea.name}</Link> : ''}</td>
                  <td>{contract.industry ? <Link to={`lookup/${contract.industry.id}`}>{contract.industry.name}</Link> : ''}</td>
                  <td>
                    {contract.contracttype ? <Link to={`lookup/${contract.contracttype.id}`}>{contract.contracttype.name}</Link> : ''}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${contract.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${contract.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${contract.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="mayaApp.contract.home.notFound">No Contracts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Contract;
