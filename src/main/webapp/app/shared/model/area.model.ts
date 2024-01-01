import dayjs from 'dayjs';
import { IEmployee } from 'app/shared/model/employee.model';

export interface IArea {
  id?: number;
  code?: string | null;
  detail?: string | null;
  publicationDate?: dayjs.Dayjs | null;
  employees?: IEmployee[] | null;
}

export const defaultValue: Readonly<IArea> = {};
