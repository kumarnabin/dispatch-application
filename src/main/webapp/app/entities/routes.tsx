import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ExcelData from './excel-data';
import ServiceProvider from './service-provider';
import Branch from './branch';
import Area from './area';
import Olt from './olt';
import Team from './team';
import Dispatch from './dispatch';
import Employee from './employee';
import Document from './document';
import EmployeeArea from './employee-area';
import Attendance from './attendance';
import DispatchRecord from './dispatch-record';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="excel-data/*" element={<ExcelData />} />
        <Route path="service-provider/*" element={<ServiceProvider />} />
        <Route path="branch/*" element={<Branch />} />
        <Route path="area/*" element={<Area />} />
        <Route path="olt/*" element={<Olt />} />
        <Route path="team/*" element={<Team />} />
        <Route path="dispatch/*" element={<Dispatch />} />
        <Route path="employee/*" element={<Employee />} />
        <Route path="document/*" element={<Document />} />
        <Route path="employee-area/*" element={<EmployeeArea />} />
        <Route path="attendance/*" element={<Attendance />} />
        <Route path="dispatch-record/*" element={<DispatchRecord />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
