import { IMasterCircuit } from 'app/shared/model/master-circuit.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IServiceProvider {
  id?: number;
  name?: string | null;
  code?: string | null;
  phone?: string | null;
  address?: string | null;
  status?: keyof typeof Status | null;
  masterCircuits?: IMasterCircuit[] | null;
}

export const defaultValue: Readonly<IServiceProvider> = {};
