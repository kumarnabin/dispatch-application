import excelData from 'app/entities/excel-data/excel-data.reducer';
import serviceProvider from 'app/entities/service-provider/service-provider.reducer';
import branch from 'app/entities/branch/branch.reducer';
import area from 'app/entities/area/area.reducer';
import olt from 'app/entities/olt/olt.reducer';
import team from 'app/entities/team/team.reducer';
import dispatch from 'app/entities/dispatch/dispatch.reducer';
import employee from 'app/entities/employee/employee.reducer';
import document from 'app/entities/document/document.reducer';
import employeeArea from 'app/entities/employee-area/employee-area.reducer';
import attendance from 'app/entities/attendance/attendance.reducer';
import dispatchRecord from 'app/entities/dispatch-record/dispatch-record.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  excelData,
  serviceProvider,
  branch,
  area,
  olt,
  team,
  dispatch,
  employee,
  document,
  employeeArea,
  attendance,
  dispatchRecord,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
