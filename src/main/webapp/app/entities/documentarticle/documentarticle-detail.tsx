import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './documentarticle.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DocumentarticleDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const documentarticleEntity = useAppSelector(state => state.documentarticle.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="documentarticleDetailsHeading">
          <Translate contentKey="mayaApp.documentarticle.detail.title">Documentarticle</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="mayaApp.documentarticle.id">Id</Translate>
            </span>
          </dt>
          <dd>{documentarticleEntity.id}</dd>
          <dt>
            <span id="documentId">
              <Translate contentKey="mayaApp.documentarticle.documentId">Document Id</Translate>
            </span>
          </dt>
          <dd>{documentarticleEntity.documentId}</dd>
          <dt>
            <span id="contractarticleId">
              <Translate contentKey="mayaApp.documentarticle.contractarticleId">Contractarticle Id</Translate>
            </span>
          </dt>
          <dd>{documentarticleEntity.contractarticleId}</dd>
          <dt>
            <span id="is_deleted">
              <Translate contentKey="mayaApp.documentarticle.is_deleted">Is Deleted</Translate>
            </span>
          </dt>
          <dd>{documentarticleEntity.is_deleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="updateDate">
              <Translate contentKey="mayaApp.documentarticle.updateDate">Update Date</Translate>
            </span>
          </dt>
          <dd>
            {documentarticleEntity.updateDate ? (
              <TextFormat value={documentarticleEntity.updateDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createDate">
              <Translate contentKey="mayaApp.documentarticle.createDate">Create Date</Translate>
            </span>
          </dt>
          <dd>
            {documentarticleEntity.createDate ? (
              <TextFormat value={documentarticleEntity.createDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createBy">
              <Translate contentKey="mayaApp.documentarticle.createBy">Create By</Translate>
            </span>
          </dt>
          <dd>{documentarticleEntity.createBy}</dd>
          <dt>
            <span id="updateBy">
              <Translate contentKey="mayaApp.documentarticle.updateBy">Update By</Translate>
            </span>
          </dt>
          <dd>{documentarticleEntity.updateBy}</dd>
          <dt>
            <Translate contentKey="mayaApp.documentarticle.contract">Contract</Translate>
          </dt>
          <dd>{documentarticleEntity.contract ? documentarticleEntity.contract.contractname : ''}</dd>
          <dt>
            <Translate contentKey="mayaApp.documentarticle.parent">Parent</Translate>
          </dt>
          <dd>{documentarticleEntity.parent ? documentarticleEntity.parent.contractname : ''}</dd>
        </dl>
        <Button tag={Link} to="/documentarticle" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/documentarticle/${documentarticleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DocumentarticleDetail;
