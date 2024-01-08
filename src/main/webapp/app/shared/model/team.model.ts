export interface ITeam {
  id?: number;
  teamNo?: string | null;
  supervisor?: string | null;
  supervisorPhoneNo?: string | null;
  teamLeader?: string | null;
  teamLeaderPhone?: string | null;
}

export const defaultValue: Readonly<ITeam> = {};
