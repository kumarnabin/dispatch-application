import serviceProvider from 'app/entities/service-provider/service-provider.reducer';
import branch from 'app/entities/branch/branch.reducer';
import masterCircuit from 'app/entities/master-circuit/master-circuit.reducer';
import branchCircuit from 'app/entities/branch-circuit/branch-circuit.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  serviceProvider,
  branch,
  masterCircuit,
  branchCircuit,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
