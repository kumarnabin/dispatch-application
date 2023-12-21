import serviceProvider from 'app/entities/service-provider/service-provider.reducer';
import branch from 'app/entities/branch/branch.reducer';
import masterCircuit from 'app/entities/master-circuit/master-circuit.reducer';
import branchCircuit from 'app/entities/branch-circuit/branch-circuit.reducer';
import excelData from 'app/entities/excel-data/excel-data.reducer';
import team from 'app/entities/team/team.reducer';
import customer from 'app/entities/customer/customer.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  serviceProvider,
  branch,
  masterCircuit,
  branchCircuit,
  excelData,
  team,
  customer,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
