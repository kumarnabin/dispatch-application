import dayjs from 'dayjs';
import { IArea } from 'app/shared/model/area.model';
import { IEmployee } from 'app/shared/model/employee.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IEmployeeArea {
  id?: number;
  status?: keyof typeof Status | null;
  publicationDate?: dayjs.Dayjs | null;
  area?: IArea | null;
  employee?: IEmployee | null;
}

export const defaultValue: Readonly<IEmployeeArea> = {};
