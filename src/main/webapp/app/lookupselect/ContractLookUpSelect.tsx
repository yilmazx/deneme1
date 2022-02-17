import { useAppDispatch, useAppSelector } from 'app/config/store';
import React, { useEffect } from 'react';
import { Translate, translate } from 'react-jhipster';
import { Col, Input, Row } from 'reactstrap';
import { getCities, getCountries } from './contract-lookup-select.reducer';

export const ContractLookUpSelect = () => {
  const dispatch = useAppDispatch();

  const countryList = useAppSelector(state => state.contractlookupselect.entities);
  const cityList = useAppSelector(state => state.contractlookupselect.entities2);
  const loading = useAppSelector(state => state.contractlookupselect.loading);

  useEffect(() => {
    dispatch(getCountries({}));
  }, []);

  const handleCountryChange = event => {
    dispatch(getCities(event.target.value));
  };

  return (
    <div className="contract-lookup-select-container">
      <Row>
        <Col md="12" className="homeCol">
          <h4>
            <Translate contentKey="contractselect.contractlookup.title">
              WilmaDoc is available on several markets. Please choose below.
            </Translate>
          </h4>
        </Col>
        <Col md="12" className="homeCol">
          <Input
            name="country"
            data-cy="country"
            label={translate('mayaApp.contract.country')}
            onChange={event => handleCountryChange(event)}
            type="select"
          >
            <option value="" key="0" />
            {countryList
              ? countryList.map(otherEntity => (
                  <option value={otherEntity.id} key={otherEntity.id}>
                    {otherEntity.name}
                  </option>
                ))
              : null}
          </Input>

          <Input name="city" data-cy="city" label={translate('mayaApp.contract.city')} type="select">
            <option value="" key="0" />
            {cityList
              ? cityList.map(otherEntity => (
                  <option value={otherEntity.id} key={otherEntity.id}>
                    {otherEntity.name}
                  </option>
                ))
              : null}
          </Input>
        </Col>
      </Row>
    </div>
  );
};
