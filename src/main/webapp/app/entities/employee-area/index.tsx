import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import EmployeeArea from './employee-area';
import EmployeeAreaDetail from './employee-area-detail';
import EmployeeAreaUpdate from './employee-area-update';
import EmployeeAreaDeleteDialog from './employee-area-delete-dialog';

const EmployeeAreaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<EmployeeArea />} />
    <Route path="new" element={<EmployeeAreaUpdate />} />
    <Route path=":id">
      <Route index element={<EmployeeAreaDetail />} />
      <Route path="edit" element={<EmployeeAreaUpdate />} />
      <Route path="delete" element={<EmployeeAreaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default EmployeeAreaRoutes;
