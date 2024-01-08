import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Dispatch from './dispatch';
import DispatchDetail from './dispatch-detail';
import DispatchUpdate from './dispatch-update';
import DispatchDeleteDialog from './dispatch-delete-dialog';

const DispatchRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Dispatch />} />
    <Route path="new" element={<DispatchUpdate />} />
    <Route path=":id">
      <Route index element={<DispatchDetail />} />
      <Route path="edit" element={<DispatchUpdate />} />
      <Route path="delete" element={<DispatchDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DispatchRoutes;
