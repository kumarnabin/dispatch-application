import dayjs from 'dayjs';

export interface ITeam {
  id?: number;
  teamNo?: string | null;
  supervisor?: string | null;
  supervisorPhoneNo?: string | null;
  teamLeader?: string | null;
  teamLeaderPhone?: string | null;
  publicationDate?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<ITeam> = {};
