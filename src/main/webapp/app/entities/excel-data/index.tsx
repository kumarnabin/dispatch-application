import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ExcelData from './excel-data';
import ExcelDataDetail from './excel-data-detail';
import ExcelDataUpdate from './excel-data-update';
import ExcelDataDeleteDialog from './excel-data-delete-dialog';

const ExcelDataRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ExcelData />} />
    <Route path="new" element={<ExcelDataUpdate />} />
    <Route path=":id">
      <Route index element={<ExcelDataDetail />} />
      <Route path="edit" element={<ExcelDataUpdate />} />
      <Route path="delete" element={<ExcelDataDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ExcelDataRoutes;
