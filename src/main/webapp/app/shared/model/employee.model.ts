import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';

export interface IEmployee {
  id?: number;
  name?: string | null;
  detail?: string | null;
  publicationDate?: dayjs.Dayjs | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IEmployee> = {};
