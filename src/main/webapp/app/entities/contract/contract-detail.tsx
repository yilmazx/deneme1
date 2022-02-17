import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './contract.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ContractDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contractEntity = useAppSelector(state => state.contract.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contractDetailsHeading">
          <Translate contentKey="mayaApp.contract.detail.title">Contract</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{contractEntity.id}</dd>
          <dt>
            <span id="contractname">
              <Translate contentKey="mayaApp.contract.contractname">Contractname</Translate>
            </span>
          </dt>
          <dd>{contractEntity.contractname}</dd>
          <dt>
            <span id="contractpath">
              <Translate contentKey="mayaApp.contract.contractpath">Contractpath</Translate>
            </span>
          </dt>
          <dd>{contractEntity.contractpath}</dd>
          <dt>
            <span id="uid">
              <Translate contentKey="mayaApp.contract.uid">Uid</Translate>
            </span>
          </dt>
          <dd>{contractEntity.uid}</dd>
          <dt>
            <span id="user">
              <Translate contentKey="mayaApp.contract.user">User</Translate>
            </span>
          </dt>
          <dd>{contractEntity.user}</dd>
          <dt>
            <span id="isdeleted">
              <Translate contentKey="mayaApp.contract.isdeleted">Isdeleted</Translate>
            </span>
          </dt>
          <dd>{contractEntity.isdeleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="isenabled">
              <Translate contentKey="mayaApp.contract.isenabled">Isenabled</Translate>
            </span>
          </dt>
          <dd>{contractEntity.isenabled ? 'true' : 'false'}</dd>
          <dt>
            <span id="updateDate">
              <Translate contentKey="mayaApp.contract.updateDate">Update Date</Translate>
            </span>
          </dt>
          <dd>
            {contractEntity.updateDate ? <TextFormat value={contractEntity.updateDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createDate">
              <Translate contentKey="mayaApp.contract.createDate">Create Date</Translate>
            </span>
          </dt>
          <dd>
            {contractEntity.createDate ? <TextFormat value={contractEntity.createDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createBy">
              <Translate contentKey="mayaApp.contract.createBy">Create By</Translate>
            </span>
          </dt>
          <dd>{contractEntity.createBy}</dd>
          <dt>
            <span id="updateBy">
              <Translate contentKey="mayaApp.contract.updateBy">Update By</Translate>
            </span>
          </dt>
          <dd>{contractEntity.updateBy}</dd>
          <dt>
            <Translate contentKey="mayaApp.contract.city">City</Translate>
          </dt>
          <dd>{contractEntity.city ? contractEntity.city.name : ''}</dd>
          <dt>
            <Translate contentKey="mayaApp.contract.country">Country</Translate>
          </dt>
          <dd>{contractEntity.country ? contractEntity.country.name : ''}</dd>
          <dt>
            <Translate contentKey="mayaApp.contract.legalarea">Legalarea</Translate>
          </dt>
          <dd>{contractEntity.legalarea ? contractEntity.legalarea.name : ''}</dd>
          <dt>
            <Translate contentKey="mayaApp.contract.industry">Industry</Translate>
          </dt>
          <dd>{contractEntity.industry ? contractEntity.industry.name : ''}</dd>
          <dt>
            <Translate contentKey="mayaApp.contract.contracttype">Contracttype</Translate>
          </dt>
          <dd>{contractEntity.contracttype ? contractEntity.contracttype.name : ''}</dd>
        </dl>
        <Button tag={Link} to="/contract" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contract/${contractEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContractDetail;
