import { IEmployee } from 'app/shared/model/employee.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IArea {
  id?: number;
  name?: string | null;
  code?: string | null;
  detail?: string | null;
  status?: keyof typeof Status | null;
  employee?: IEmployee | null;
}

export const defaultValue: Readonly<IArea> = {};
