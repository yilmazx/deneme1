import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './contractarticle.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ContractarticleDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contractarticleEntity = useAppSelector(state => state.contractarticle.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contractarticleDetailsHeading">
          <Translate contentKey="mayaApp.contractarticle.detail.title">Contractarticle</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{contractarticleEntity.id}</dd>
          <dt>
            <span id="articlename">
              <Translate contentKey="mayaApp.contractarticle.articlename">Articlename</Translate>
            </span>
          </dt>
          <dd>{contractarticleEntity.articlename}</dd>
          <dt>
            <span id="detail">
              <Translate contentKey="mayaApp.contractarticle.detail">Detail</Translate>
            </span>
          </dt>
          <dd>{contractarticleEntity.detail}</dd>
          <dt>
            <span id="uid">
              <Translate contentKey="mayaApp.contractarticle.uid">Uid</Translate>
            </span>
          </dt>
          <dd>{contractarticleEntity.uid}</dd>
          <dt>
            <span id="isdeleted">
              <Translate contentKey="mayaApp.contractarticle.isdeleted">Isdeleted</Translate>
            </span>
          </dt>
          <dd>{contractarticleEntity.isdeleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="isenabled">
              <Translate contentKey="mayaApp.contractarticle.isenabled">Isenabled</Translate>
            </span>
          </dt>
          <dd>{contractarticleEntity.isenabled ? 'true' : 'false'}</dd>
          <dt>
            <span id="updateDate">
              <Translate contentKey="mayaApp.contractarticle.updateDate">Update Date</Translate>
            </span>
          </dt>
          <dd>
            {contractarticleEntity.updateDate ? (
              <TextFormat value={contractarticleEntity.updateDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createDate">
              <Translate contentKey="mayaApp.contractarticle.createDate">Create Date</Translate>
            </span>
          </dt>
          <dd>
            {contractarticleEntity.createDate ? (
              <TextFormat value={contractarticleEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createBy">
              <Translate contentKey="mayaApp.contractarticle.createBy">Create By</Translate>
            </span>
          </dt>
          <dd>{contractarticleEntity.createBy}</dd>
          <dt>
            <span id="updateBy">
              <Translate contentKey="mayaApp.contractarticle.updateBy">Update By</Translate>
            </span>
          </dt>
          <dd>{contractarticleEntity.updateBy}</dd>
          <dt>
            <Translate contentKey="mayaApp.contractarticle.contract">Contract</Translate>
          </dt>
          <dd>{contractarticleEntity.contract ? contractarticleEntity.contract.contractname : ''}</dd>
          <dt>
            <Translate contentKey="mayaApp.contractarticle.parent">Parent</Translate>
          </dt>
          <dd>{contractarticleEntity.parent ? contractarticleEntity.parent.articlename : ''}</dd>
        </dl>
        <Button tag={Link} to="/contractarticle" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contractarticle/${contractarticleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContractarticleDetail;
