import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/service-provider">
        <Translate contentKey="global.menu.entities.serviceProvider" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/branch">
        <Translate contentKey="global.menu.entities.branch" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/excel-data">
        <Translate contentKey="global.menu.entities.excelData" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/team">
        <Translate contentKey="global.menu.entities.team" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/customer">
        <Translate contentKey="global.menu.entities.customer" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/area">
        <Translate contentKey="global.menu.entities.area" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/dispatch">
        <Translate contentKey="global.menu.entities.dispatch" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/employee">
        <Translate contentKey="global.menu.entities.employee" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/employee-area">
        <Translate contentKey="global.menu.entities.employeeArea" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/attendance">
        <Translate contentKey="global.menu.entities.attendance" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/document">
        <Translate contentKey="global.menu.entities.document" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/dispatch-record">
        <Translate contentKey="global.menu.entities.dispatchRecord" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/olt">
        <Translate contentKey="global.menu.entities.olt" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
