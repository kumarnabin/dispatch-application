import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Olt from './olt';
import OltDetail from './olt-detail';
import OltUpdate from './olt-update';
import OltDeleteDialog from './olt-delete-dialog';

const OltRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Olt />} />
    <Route path="new" element={<OltUpdate />} />
    <Route path=":id">
      <Route index element={<OltDetail />} />
      <Route path="edit" element={<OltUpdate />} />
      <Route path="delete" element={<OltDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default OltRoutes;
