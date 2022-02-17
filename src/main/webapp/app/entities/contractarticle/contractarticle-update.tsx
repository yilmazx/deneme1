import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IContract } from 'app/shared/model/contract.model';
import { getEntities as getContracts } from 'app/entities/contract/contract.reducer';
import { getEntities as getContractarticles } from 'app/entities/contractarticle/contractarticle.reducer';
import { getEntity, updateEntity, createEntity, reset } from './contractarticle.reducer';
import { IContractarticle } from 'app/shared/model/contractarticle.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ContractarticleUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const contracts = useAppSelector(state => state.contract.entities);
  const contractarticles = useAppSelector(state => state.contractarticle.entities);
  const contractarticleEntity = useAppSelector(state => state.contractarticle.entity);
  const loading = useAppSelector(state => state.contractarticle.loading);
  const updating = useAppSelector(state => state.contractarticle.updating);
  const updateSuccess = useAppSelector(state => state.contractarticle.updateSuccess);
  const handleClose = () => {
    props.history.push('/contractarticle');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getContracts({}));
    dispatch(getContractarticles({}));
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
      ...contractarticleEntity,
      ...values,
      contract: contracts.find(it => it.id.toString() === values.contract.toString()),
      parent: contractarticles.find(it => it.id.toString() === values.parent.toString()),
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
          ...contractarticleEntity,
          updateDate: convertDateTimeFromServer(contractarticleEntity.updateDate),
          createDate: convertDateTimeFromServer(contractarticleEntity.createDate),
          contract: contractarticleEntity?.contract?.id,
          parent: contractarticleEntity?.parent?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="mayaApp.contractarticle.home.createOrEditLabel" data-cy="ContractarticleCreateUpdateHeading">
            <Translate contentKey="mayaApp.contractarticle.home.createOrEditLabel">Create or edit a Contractarticle</Translate>
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
                  id="contractarticle-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('mayaApp.contractarticle.articlename')}
                id="contractarticle-articlename"
                name="articlename"
                data-cy="articlename"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.contractarticle.detail')}
                id="contractarticle-detail"
                name="detail"
                data-cy="detail"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.contractarticle.uid')}
                id="contractarticle-uid"
                name="uid"
                data-cy="uid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.contractarticle.isdeleted')}
                id="contractarticle-isdeleted"
                name="isdeleted"
                data-cy="isdeleted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('mayaApp.contractarticle.isenabled')}
                id="contractarticle-isenabled"
                name="isenabled"
                data-cy="isenabled"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('mayaApp.contractarticle.updateDate')}
                id="contractarticle-updateDate"
                name="updateDate"
                data-cy="updateDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.contractarticle.createDate')}
                id="contractarticle-createDate"
                name="createDate"
                data-cy="createDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.contractarticle.createBy')}
                id="contractarticle-createBy"
                name="createBy"
                data-cy="createBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.contractarticle.updateBy')}
                id="contractarticle-updateBy"
                name="updateBy"
                data-cy="updateBy"
                type="text"
              />
              <ValidatedField
                id="contractarticle-contract"
                name="contract"
                data-cy="contract"
                label={translate('mayaApp.contractarticle.contract')}
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
                id="contractarticle-parent"
                name="parent"
                data-cy="parent"
                label={translate('mayaApp.contractarticle.parent')}
                type="select"
              >
                <option value="" key="0" />
                {contractarticles
                  ? contractarticles.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.articlename}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contractarticle" replace color="info">
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

export default ContractarticleUpdate;
