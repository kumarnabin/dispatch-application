import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import MasterCircuit from './master-circuit';
import MasterCircuitDetail from './master-circuit-detail';
import MasterCircuitUpdate from './master-circuit-update';
import MasterCircuitDeleteDialog from './master-circuit-delete-dialog';

const MasterCircuitRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<MasterCircuit />} />
    <Route path="new" element={<MasterCircuitUpdate />} />
    <Route path=":id">
      <Route index element={<MasterCircuitDetail />} />
      <Route path="edit" element={<MasterCircuitUpdate />} />
      <Route path="delete" element={<MasterCircuitDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default MasterCircuitRoutes;
