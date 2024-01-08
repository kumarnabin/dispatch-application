import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DispatchRecord from './dispatch-record';
import DispatchRecordDetail from './dispatch-record-detail';
import DispatchRecordUpdate from './dispatch-record-update';
import DispatchRecordDeleteDialog from './dispatch-record-delete-dialog';

const DispatchRecordRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DispatchRecord />} />
    <Route path="new" element={<DispatchRecordUpdate />} />
    <Route path=":id">
      <Route index element={<DispatchRecordDetail />} />
      <Route path="edit" element={<DispatchRecordUpdate />} />
      <Route path="delete" element={<DispatchRecordDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DispatchRecordRoutes;
