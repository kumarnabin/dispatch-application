import serviceProvider from 'app/entities/service-provider/service-provider.reducer';
import branch from 'app/entities/branch/branch.reducer';
import masterCircuit from 'app/entities/master-circuit/master-circuit.reducer';
import branchCircuit from 'app/entities/branch-circuit/branch-circuit.reducer';
import excelData from 'app/entities/excel-data/excel-data.reducer';
import team from 'app/entities/team/team.reducer';
import customer from 'app/entities/customer/customer.reducer';
import area from 'app/entities/area/area.reducer';
import dispatch from 'app/entities/dispatch/dispatch.reducer';
import employee from 'app/entities/employee/employee.reducer';
import employeeArea from 'app/entities/employee-area/employee-area.reducer';
import attendance from 'app/entities/attendance/attendance.reducer';
import document from 'app/entities/document/document.reducer';
import dispatchRecord from 'app/entities/dispatch-record/dispatch-record.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  serviceProvider,
  branch,
  masterCircuit,
  branchCircuit,
  excelData,
  team,
  customer,
  area,
  dispatch,
  employee,
  employeeArea,
  attendance,
  document,
  dispatchRecord,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
