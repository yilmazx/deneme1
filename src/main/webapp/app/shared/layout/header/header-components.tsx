import React from 'react';
import { Translate } from 'react-jhipster';

import { NavItem, NavLink, NavbarBrand, Button } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { PrimaryButton } from 'app/shared/components/PrimaryButton';

export const BrandIcon = props => (
  <div {...props} className="brand-icon">
    {/* {Logo Alanı} */}
  </div>
);

export const HeaderButtons = () => (
  <div className="ms-header-Buttons-container">
    <PrimaryButton
      className="header-buttons"
      text="How it works?"
      to="/"
      translateKey="global.menu.howitworks"
      style={{ color: 'white' }}
    />
    <PrimaryButton className="header-buttons" text="Pricing" to="/" translateKey="global.menu.pricing" />
    <PrimaryButton className="header-buttons" text="About Us" to="/" translateKey="global.menu.aboutus" />
    <PrimaryButton className="header-buttons" text="Resources" to="/" translateKey="global.menu.resources" />
  </div>
);

export const Home = () => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span>
        <Translate contentKey="global.menu.home">Home</Translate>
      </span>
    </NavLink>
  </NavItem>
);

export const ContractSelect = () => (
  <NavItem>
    <NavLink tag={Link} to="/contractlookupselect" className="d-flex align-items-center">
      <span>Contract Select</span>
    </NavLink>
  </NavItem>
);
