import dayjs from 'dayjs';
import { IEmployee } from 'app/shared/model/employee.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IAttendance {
  id?: number;
  status?: keyof typeof Status | null;
  meterPics?: string | null;
  publicationDate?: dayjs.Dayjs | null;
  employee?: IEmployee | null;
}

export const defaultValue: Readonly<IAttendance> = {};
