import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './contract-input.reducer';
import { IContractInput } from 'app/shared/model/contract-input.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ContractInput = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const contractInputList = useAppSelector(state => state.contractInput.entities);
  const loading = useAppSelector(state => state.contractInput.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="contract-input-heading" data-cy="ContractInputHeading">
        <Translate contentKey="mayaApp.contractInput.home.title">Contract Inputs</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="mayaApp.contractInput.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="mayaApp.contractInput.home.createLabel">Create new Contract Input</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {contractInputList && contractInputList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="mayaApp.contractInput.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractInput.inputname">Inputname</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractInput.inputvalue">Inputvalue</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractInput.uid">Uid</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractInput.isdeleted">Isdeleted</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractInput.isenabled">Isenabled</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractInput.updateDate">Update Date</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractInput.createDate">Create Date</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractInput.createBy">Create By</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractInput.updateBy">Update By</Translate>
                </th>
                <th>
                  <Translate contentKey="mayaApp.contractInput.contract">Contract</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {contractInputList.map((contractInput, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${contractInput.id}`} color="link" size="sm">
                      {contractInput.id}
                    </Button>
                  </td>
                  <td>{contractInput.inputname}</td>
                  <td>{contractInput.inputvalue}</td>
                  <td>{contractInput.uid}</td>
                  <td>{contractInput.isdeleted ? 'true' : 'false'}</td>
                  <td>{contractInput.isenabled ? 'true' : 'false'}</td>
                  <td>
                    {contractInput.updateDate ? <TextFormat type="date" value={contractInput.updateDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {contractInput.createDate ? <TextFormat type="date" value={contractInput.createDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{contractInput.createBy}</td>
                  <td>{contractInput.updateBy}</td>
                  <td>
                    {contractInput.contract ? (
                      <Link to={`contract/${contractInput.contract.id}`}>{contractInput.contract.contractname}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${contractInput.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${contractInput.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${contractInput.id}/delete`}
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
              <Translate contentKey="mayaApp.contractInput.home.notFound">No Contract Inputs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default ContractInput;
