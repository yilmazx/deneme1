import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IContract } from 'app/shared/model/contract.model';
import { getEntities as getContracts } from 'app/entities/contract/contract.reducer';
import { getEntity, updateEntity, createEntity, reset } from './contract-input.reducer';
import { IContractInput } from 'app/shared/model/contract-input.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ContractInputUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const contracts = useAppSelector(state => state.contract.entities);
  const contractInputEntity = useAppSelector(state => state.contractInput.entity);
  const loading = useAppSelector(state => state.contractInput.loading);
  const updating = useAppSelector(state => state.contractInput.updating);
  const updateSuccess = useAppSelector(state => state.contractInput.updateSuccess);
  const handleClose = () => {
    props.history.push('/contract-input');
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
      ...contractInputEntity,
      ...values,
      contract: contracts.find(it => it.id.toString() === values.contract.toString()),
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
          ...contractInputEntity,
          updateDate: convertDateTimeFromServer(contractInputEntity.updateDate),
          createDate: convertDateTimeFromServer(contractInputEntity.createDate),
          contract: contractInputEntity?.contract?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="mayaApp.contractInput.home.createOrEditLabel" data-cy="ContractInputCreateUpdateHeading">
            <Translate contentKey="mayaApp.contractInput.home.createOrEditLabel">Create or edit a ContractInput</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <input name="id" id="contract-input-id" type="hidden" /> : null}
              <ValidatedField
                label={translate('mayaApp.contractInput.inputname')}
                id="contract-input-inputname"
                name="inputname"
                data-cy="inputname"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.contractInput.inputvalue')}
                id="contract-input-inputvalue"
                name="inputvalue"
                data-cy="inputvalue"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <input id="contract-input-uid" name="uid" data-cy="uid" type="hidden" />
              <ValidatedField
                label={translate('mayaApp.contractInput.isenabled')}
                id="contract-input-isenabled"
                name="isenabled"
                data-cy="isenabled"
                check
                type="checkbox"
              />
              <ValidatedField
                id="contract-input-contract"
                name="contract"
                data-cy="contract"
                label={translate('mayaApp.contractInput.contract')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contract-input" replace color="info">
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

export default ContractInputUpdate;
