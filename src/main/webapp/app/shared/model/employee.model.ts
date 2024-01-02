import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IEmployee {
  id?: number;
  fullName?: string | null;
  dob?: dayjs.Dayjs | null;
  gender?: string | null;
  mobile?: string | null;
  photoContentType?: string | null;
  photo?: string | null;
  citizenshipNo?: string | null;
  panNo?: string | null;
  category?: string | null;
  detail?: string | null;
  status?: keyof typeof Status | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IEmployee> = {};
