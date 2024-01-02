import dayjs from 'dayjs';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IEmployeeArea {
  id?: number;
  status?: keyof typeof Status | null;
  publicationDate?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IEmployeeArea> = {};
