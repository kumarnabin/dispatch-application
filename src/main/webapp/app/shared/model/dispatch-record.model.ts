import dayjs from 'dayjs';
import { IDispatch } from 'app/shared/model/dispatch.model';
import { IEmployee } from 'app/shared/model/employee.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IDispatchRecord {
  id?: number;
  remark?: string | null;
  status?: keyof typeof Status | null;
  publicationDate?: dayjs.Dayjs | null;
  dispatch?: IDispatch | null;
  employee?: IEmployee | null;
}

export const defaultValue: Readonly<IDispatchRecord> = {};
