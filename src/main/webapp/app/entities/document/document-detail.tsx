import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './document.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DocumentDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const documentEntity = useAppSelector(state => state.document.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="documentDetailsHeading">
          <Translate contentKey="mayaApp.document.detail.title">Document</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{documentEntity.id}</dd>
          <dt>
            <span id="documentname">
              <Translate contentKey="mayaApp.document.documentname">Documentname</Translate>
            </span>
          </dt>
          <dd>{documentEntity.documentname}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="mayaApp.document.description">Description</Translate>
            </span>
          </dt>
          <dd>{documentEntity.description}</dd>
          <dt>
            <span id="uid">
              <Translate contentKey="mayaApp.document.uid">Uid</Translate>
            </span>
          </dt>
          <dd>{documentEntity.uid}</dd>
          <dt>
            <span id="path">
              <Translate contentKey="mayaApp.document.path">Path</Translate>
            </span>
          </dt>
          <dd>{documentEntity.path}</dd>
          <dt>
            <span id="contactId">
              <Translate contentKey="mayaApp.document.contactId">Contact Id</Translate>
            </span>
          </dt>
          <dd>{documentEntity.contactId}</dd>
          <dt>
            <span id="inputs">
              <Translate contentKey="mayaApp.document.inputs">Inputs</Translate>
            </span>
          </dt>
          <dd>{documentEntity.inputs}</dd>
          <dt>
            <span id="is_deleted">
              <Translate contentKey="mayaApp.document.is_deleted">Is Deleted</Translate>
            </span>
          </dt>
          <dd>{documentEntity.is_deleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="isenabled">
              <Translate contentKey="mayaApp.document.isenabled">Isenabled</Translate>
            </span>
          </dt>
          <dd>{documentEntity.isenabled ? 'true' : 'false'}</dd>
          <dt>
            <span id="updateDate">
              <Translate contentKey="mayaApp.document.updateDate">Update Date</Translate>
            </span>
          </dt>
          <dd>
            {documentEntity.updateDate ? <TextFormat value={documentEntity.updateDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createDate">
              <Translate contentKey="mayaApp.document.createDate">Create Date</Translate>
            </span>
          </dt>
          <dd>
            {documentEntity.createDate ? <TextFormat value={documentEntity.createDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createBy">
              <Translate contentKey="mayaApp.document.createBy">Create By</Translate>
            </span>
          </dt>
          <dd>{documentEntity.createBy}</dd>
          <dt>
            <span id="updateBy">
              <Translate contentKey="mayaApp.document.updateBy">Update By</Translate>
            </span>
          </dt>
          <dd>{documentEntity.updateBy}</dd>
          <dt>
            <Translate contentKey="mayaApp.document.contract">Contract</Translate>
          </dt>
          <dd>{documentEntity.contract ? documentEntity.contract.contractname : ''}</dd>
        </dl>
        <Button tag={Link} to="/document" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/document/${documentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DocumentDetail;
