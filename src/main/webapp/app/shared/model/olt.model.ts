import { IBranch } from 'app/shared/model/branch.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IOlt {
  id?: number;
  name?: string | null;
  detail?: string | null;
  status?: keyof typeof Status | null;
  branch?: IBranch | null;
}

export const defaultValue: Readonly<IOlt> = {};
