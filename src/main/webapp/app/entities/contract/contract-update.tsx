import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ILookup } from 'app/shared/model/lookup.model';
import { getEntities as getLookups } from 'app/entities/lookup/lookup.reducer';
import { getEntity, updateEntity, createEntity, reset } from './contract.reducer';
import { IContract } from 'app/shared/model/contract.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ContractUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const lookups = useAppSelector(state => state.lookup.entities);
  const contractEntity = useAppSelector(state => state.contract.entity);
  const loading = useAppSelector(state => state.contract.loading);
  const updating = useAppSelector(state => state.contract.updating);
  const updateSuccess = useAppSelector(state => state.contract.updateSuccess);
  const handleClose = () => {
    props.history.push('/contract');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getLookups({}));
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
      ...contractEntity,
      ...values,
      city: lookups.find(it => it.id.toString() === values.city.toString()),
      country: lookups.find(it => it.id.toString() === values.country.toString()),
      legalarea: lookups.find(it => it.id.toString() === values.legalarea.toString()),
      industry: lookups.find(it => it.id.toString() === values.industry.toString()),
      contracttype: lookups.find(it => it.id.toString() === values.contracttype.toString()),
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
          ...contractEntity,
          updateDate: convertDateTimeFromServer(contractEntity.updateDate),
          createDate: convertDateTimeFromServer(contractEntity.createDate),
          city: contractEntity?.city?.id,
          country: contractEntity?.country?.id,
          legalarea: contractEntity?.legalarea?.id,
          industry: contractEntity?.industry?.id,
          contracttype: contractEntity?.contracttype?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="mayaApp.contract.home.createOrEditLabel" data-cy="ContractCreateUpdateHeading">
            <Translate contentKey="mayaApp.contract.home.createOrEditLabel">Create or edit a Contract</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <input name="id" id="contract-id" type="hidden" /> : null}
              <ValidatedField
                label={translate('mayaApp.contract.contractname')}
                id="contract-contractname"
                name="contractname"
                data-cy="contractname"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.contract.contractpath')}
                id="contract-contractpath"
                name="contractpath"
                data-cy="contractpath"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <input id="contract-uid" name="uid" data-cy="uid" type="hidden" />
              <ValidatedField
                label={translate('mayaApp.contract.user')}
                id="contract-user"
                name="user"
                data-cy="user"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('mayaApp.contract.isenabled')}
                id="contract-isenabled"
                name="isenabled"
                data-cy="isenabled"
                check
                type="checkbox"
              />
              <ValidatedField id="contract-city" name="city" data-cy="city" label={translate('mayaApp.contract.city')} type="select">
                <option value="" key="0" />
                {lookups
                  ? lookups.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="contract-country"
                name="country"
                data-cy="country"
                label={translate('mayaApp.contract.country')}
                type="select"
              >
                <option value="" key="0" />
                {lookups
                  ? lookups.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="contract-legalarea"
                name="legalarea"
                data-cy="legalarea"
                label={translate('mayaApp.contract.legalarea')}
                type="select"
              >
                <option value="" key="0" />
                {lookups
                  ? lookups.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="contract-industry"
                name="industry"
                data-cy="industry"
                label={translate('mayaApp.contract.industry')}
                type="select"
              >
                <option value="" key="0" />
                {lookups
                  ? lookups.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="contract-contracttype"
                name="contracttype"
                data-cy="contracttype"
                label={translate('mayaApp.contract.contracttype')}
                type="select"
              >
                <option value="" key="0" />
                {lookups
                  ? lookups.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/contract" replace color="info">
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

export default ContractUpdate;
