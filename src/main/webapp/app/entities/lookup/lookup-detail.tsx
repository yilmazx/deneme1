import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './lookup.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const LookupDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const lookupEntity = useAppSelector(state => state.lookup.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="lookupDetailsHeading">
          <Translate contentKey="mayaApp.lookup.detail.title">Lookup</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{lookupEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="mayaApp.lookup.name">Name</Translate>
            </span>
          </dt>
          <dd>{lookupEntity.name}</dd>
          <dt>
            <span id="lang">
              <Translate contentKey="mayaApp.lookup.lang">Lang</Translate>
            </span>
          </dt>
          <dd>{lookupEntity.lang}</dd>
          <dt>
            <span id="uid">
              <Translate contentKey="mayaApp.lookup.uid">Uid</Translate>
            </span>
          </dt>
          <dd>{lookupEntity.uid}</dd>
          <dt>
            <span id="value">
              <Translate contentKey="mayaApp.lookup.value">Value</Translate>
            </span>
          </dt>
          <dd>{lookupEntity.value}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="mayaApp.lookup.description">Description</Translate>
            </span>
          </dt>
          <dd>{lookupEntity.description}</dd>
          <dt>
            <Translate contentKey="mayaApp.lookup.parent">Parent</Translate>
          </dt>
          <dd>{lookupEntity.parent ? lookupEntity.parent.uid : ''}</dd>
        </dl>
        <Button tag={Link} to="/lookup" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/lookup/${lookupEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LookupDetail;
