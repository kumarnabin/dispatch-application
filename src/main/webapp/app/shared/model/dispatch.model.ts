import dayjs from 'dayjs';
import { ITeam } from 'app/shared/model/team.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IDispatch {
  id?: number;
  voice?: string | null;
  data?: string | null;
  iptv?: string | null;
  customerName?: string | null;
  contactNo?: string | null;
  oltPort?: string | null;
  regDate?: string | null;
  fapPort?: string | null;
  cpeSn?: string | null;
  cpeRx?: string | null;
  complain?: string | null;
  remark?: string | null;
  status?: keyof typeof Status | null;
  location?: string | null;
  printDate?: dayjs.Dayjs | null;
  publicationDate?: dayjs.Dayjs | null;
  team?: ITeam | null;
}

export const defaultValue: Readonly<IDispatch> = {};
