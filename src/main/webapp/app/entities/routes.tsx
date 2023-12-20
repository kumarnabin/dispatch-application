import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ServiceProvider from './service-provider';
import Branch from './branch';
import MasterCircuit from './master-circuit';
import BranchCircuit from './branch-circuit';
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
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
