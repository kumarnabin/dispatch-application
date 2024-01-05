import dayjs from 'dayjs';
import { IEmployee } from 'app/shared/model/employee.model';
import { IDispatch } from 'app/shared/model/dispatch.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IDispatchRecord {
  id?: number;
  status?: keyof typeof Status | null;
  publicationDate?: dayjs.Dayjs | null;
  employee?: IEmployee | null;
  dispatch?: IDispatch | null;
}

export const defaultValue: Readonly<IDispatchRecord> = {};
