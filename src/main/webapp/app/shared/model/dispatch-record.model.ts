import dayjs from 'dayjs';
import { IEmployee } from 'app/shared/model/employee.model';
import { IArea } from 'app/shared/model/area.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IDispatchRecord {
  id?: number;
  status?: keyof typeof Status | null;
  publicationDate?: dayjs.Dayjs | null;
  employee?: IEmployee | null;
  area?: IArea | null;
}

export const defaultValue: Readonly<IDispatchRecord> = {};