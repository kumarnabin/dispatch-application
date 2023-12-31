import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ServiceProvider from './service-provider';
import Branch from './branch';
import MasterCircuit from './master-circuit';
import BranchCircuit from './branch-circuit';
import ExcelData from './excel-data';
import Team from './team';
import Customer from './customer';
import Area from './area';
import Dispatch from './dispatch';
import Employee from './employee';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="service-provider/*" element={<ServiceProvider />} />
        <Route path="branch/*" element={<Branch />} />
        <Route path="master-circuit/*" element={<MasterCircuit />} />
        <Route path="branch-circuit/*" element={<BranchCircuit />} />
        <Route path="excel-data/*" element={<ExcelData />} />
        <Route path="team/*" element={<Team />} />
        <Route path="customer/*" element={<Customer />} />
        <Route path="area/*" element={<Area />} />
        <Route path="dispatch/*" element={<Dispatch />} />
        <Route path="employee/*" element={<Employee />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
