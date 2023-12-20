import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import BranchCircuit from './branch-circuit';
import BranchCircuitDetail from './branch-circuit-detail';
import BranchCircuitUpdate from './branch-circuit-update';
import BranchCircuitDeleteDialog from './branch-circuit-delete-dialog';

const BranchCircuitRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<BranchCircuit />} />
    <Route path="new" element={<BranchCircuitUpdate />} />
    <Route path=":id">
      <Route index element={<BranchCircuitDetail />} />
      <Route path="edit" element={<BranchCircuitUpdate />} />
      <Route path="delete" element={<BranchCircuitDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default BranchCircuitRoutes;
