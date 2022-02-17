import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IContract } from 'app/shared/model/contract.model';
import { getEntities as getContracts } from 'app/entities/contract/contract.reducer';
import { getEntity, updateEntity, createEntity, reset } from './documentarticle.reducer';
import { IDocumentarticle } from 'app/shared/model/documentarticle.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DocumentarticleUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const contracts = useAppSelector(state => state.contract.entities);
  const documentarticleEntity = useAppSelector(state => state.documentarticle.entity);
  const loading = useAppSelector(state => state.documentarticle.loading);
  const updating = useAppSelector(state => state.documentarticle.updating);
  const updateSuccess = useAppSelector(state => state.documentarticle.updateSuccess);
  const handleClose = () => {
    props.history.push('/documentarticle');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getContracts({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.updateDate = convertDateTimeToServer(values.updateDate);
    values.createDate = convertDateTimeToServer(values.createDate);

    const entity = {
      ...documentarticleEntity,
      ...values,
      contract: contracts.find(it => it.id.toString() === values.contract.toString()),
      parent: contracts.find(it => it.id.toString() === values.parent.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          updateDate: displayDefaultDateTime(),
          createDate: displayDefaultDateTime(),
        }
      : {
          ...documentarticleEntity,
          updateDate: convertDateTimeFromServer(documentarticleEntity.updateDate),
          createDate: convertDateTimeFromServer(documentarticleEntity.createDate),
          contract: documentarticleEntity?.contract?.id,
          parent: documentarticleEntity?.parent?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="mayaApp.documentarticle.home.createOrEditLabel" data-cy="DocumentarticleCreateUpdateHeading">
            <Translate contentKey="mayaApp.documentarticle.home.createOrEditLabel">Create or edit a Documentarticle</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="documentarticle-id"
                  label={translate('mayaApp.documentarticle.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('mayaApp.documentarticle.documentId')}
                id="documentarticle-documentId"
                name="documentId"
                data-cy="documentId"
                type="text"
              />
              <ValidatedField
                label={translate('mayaApp.documentarticle.contractarticleId')}
                id="documentarticle-contractarticleId"
                name="contractarticleId"
                data-cy="contractarticleId"
                type="text"
              />
              <ValidatedField
                label={translate('mayaApp.documentarticle.is_deleted')}
                id="documentarticle-is_deleted"
                name="is_deleted"
                data-cy="is_deleted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('mayaApp.documentarticle.updateDate')}
                id="documentarticle-updateDate"
                name="updateDate"
                data-cy="updateDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.documentarticle.createDate')}
                id="documentarticle-createDate"
                name="createDate"
                data-cy="createDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.documentarticle.createBy')}
                id="documentarticle-createBy"
                name="createBy"
                data-cy="createBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.documentarticle.updateBy')}
                id="documentarticle-updateBy"
                name="updateBy"
                data-cy="updateBy"
                type="text"
              />
              <ValidatedField
                id="documentarticle-contract"
                name="contract"
                data-cy="contract"
                label={translate('mayaApp.documentarticle.contract')}
                type="select"
              >
                <option value="" key="0" />
                {contracts
                  ? contracts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.contractname}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="documentarticle-parent"
                name="parent"
                data-cy="parent"
                label={translate('mayaApp.documentarticle.parent')}
                type="select"
              >
                <option value="" key="0" />
                {contracts
                  ? contracts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.contractname}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/documentarticle" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default DocumentarticleUpdate;
