import React from 'react';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/service-provider">
        Service Provider
      </MenuItem>
      <MenuItem icon="asterisk" to="/branch">
        Branch
      </MenuItem>
      <MenuItem icon="asterisk" to="/master-circuit">
        Master Circuit
      </MenuItem>
      <MenuItem icon="asterisk" to="/branch-circuit">
        Branch Circuit
      </MenuItem>
      <MenuItem icon="asterisk" to="/excel-data">
        Excel Data
      </MenuItem>
      <MenuItem icon="asterisk" to="/team">
        Team
      </MenuItem>
      <MenuItem icon="asterisk" to="/customer">
        Customer
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
