import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    data-cy="entity"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="asterisk" to="/lookup">
      <Translate contentKey="global.menu.entities.lookup" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/contract">
      <Translate contentKey="global.menu.entities.contract" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/contract-input">
      <Translate contentKey="global.menu.entities.contractInput" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/contractarticle">
      <Translate contentKey="global.menu.entities.contractarticle" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/document">
      <Translate contentKey="global.menu.entities.document" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/documentarticle">
      <Translate contentKey="global.menu.entities.documentarticle" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
