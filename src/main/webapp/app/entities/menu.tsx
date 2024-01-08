import React from 'react';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/excel-data">
        Excel Data
      </MenuItem>
      <MenuItem icon="asterisk" to="/service-provider">
        Service Provider
      </MenuItem>
      <MenuItem icon="asterisk" to="/branch">
        Branch
      </MenuItem>
      <MenuItem icon="asterisk" to="/area">
        Area
      </MenuItem>
      <MenuItem icon="asterisk" to="/olt">
        Olt
      </MenuItem>
      <MenuItem icon="asterisk" to="/team">
        Team
      </MenuItem>
      <MenuItem icon="asterisk" to="/dispatch">
        Dispatch
      </MenuItem>
      <MenuItem icon="asterisk" to="/employee">
        Employee
      </MenuItem>
      <MenuItem icon="asterisk" to="/document">
        Document
      </MenuItem>
      <MenuItem icon="asterisk" to="/employee-area">
        Employee Area
      </MenuItem>
      <MenuItem icon="asterisk" to="/attendance">
        Attendance
      </MenuItem>
      <MenuItem icon="asterisk" to="/dispatch-record">
        Dispatch Record
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
