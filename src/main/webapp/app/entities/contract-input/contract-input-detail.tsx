import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './contract-input.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ContractInputDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contractInputEntity = useAppSelector(state => state.contractInput.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contractInputDetailsHeading">
          <Translate contentKey="mayaApp.contractInput.detail.title">ContractInput</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{contractInputEntity.id}</dd>
          <dt>
            <span id="inputname">
              <Translate contentKey="mayaApp.contractInput.inputname">Inputname</Translate>
            </span>
          </dt>
          <dd>{contractInputEntity.inputname}</dd>
          <dt>
            <span id="inputvalue">
              <Translate contentKey="mayaApp.contractInput.inputvalue">Inputvalue</Translate>
            </span>
          </dt>
          <dd>{contractInputEntity.inputvalue}</dd>
          <dt>
            <span id="uid">
              <Translate contentKey="mayaApp.contractInput.uid">Uid</Translate>
            </span>
          </dt>
          <dd>{contractInputEntity.uid}</dd>
          <dt>
            <span id="isdeleted">
              <Translate contentKey="mayaApp.contractInput.isdeleted">Isdeleted</Translate>
            </span>
          </dt>
          <dd>{contractInputEntity.isdeleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="isenabled">
              <Translate contentKey="mayaApp.contractInput.isenabled">Isenabled</Translate>
            </span>
          </dt>
          <dd>{contractInputEntity.isenabled ? 'true' : 'false'}</dd>
          <dt>
            <span id="updateDate">
              <Translate contentKey="mayaApp.contractInput.updateDate">Update Date</Translate>
            </span>
          </dt>
          <dd>
            {contractInputEntity.updateDate ? (
              <TextFormat value={contractInputEntity.updateDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createDate">
              <Translate contentKey="mayaApp.contractInput.createDate">Create Date</Translate>
            </span>
          </dt>
          <dd>
            {contractInputEntity.createDate ? (
              <TextFormat value={contractInputEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createBy">
              <Translate contentKey="mayaApp.contractInput.createBy">Create By</Translate>
            </span>
          </dt>
          <dd>{contractInputEntity.createBy}</dd>
          <dt>
            <span id="updateBy">
              <Translate contentKey="mayaApp.contractInput.updateBy">Update By</Translate>
            </span>
          </dt>
          <dd>{contractInputEntity.updateBy}</dd>
          <dt>
            <Translate contentKey="mayaApp.contractInput.contract">Contract</Translate>
          </dt>
          <dd>{contractInputEntity.contract ? contractInputEntity.contract.contractname : ''}</dd>
        </dl>
        <Button tag={Link} to="/contract-input" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contract-input/${contractInputEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContractInputDetail;
