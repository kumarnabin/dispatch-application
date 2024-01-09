import { IOlt } from 'app/shared/model/olt.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IArea {
  id?: number;
  name?: string | null;
  code?: string | null;
  detail?: string | null;
  status?: keyof typeof Status | null;
  olt?: IOlt | null;
}

export const defaultValue: Readonly<IArea> = {};
