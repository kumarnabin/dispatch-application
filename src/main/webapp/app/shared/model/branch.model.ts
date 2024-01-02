import { IServiceProvider } from 'app/shared/model/service-provider.model';
import { IBranchCircuit } from 'app/shared/model/branch-circuit.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IBranch {
  id?: number;
  name?: string | null;
  code?: string | null;
  status?: keyof typeof Status | null;
  serviceProvider?: IServiceProvider | null;
  branchCircuits?: IBranchCircuit[] | null;
}

export const defaultValue: Readonly<IBranch> = {};
