import dayjs from 'dayjs';
import { IServiceProvider } from 'app/shared/model/service-provider.model';
import { IBranchCircuit } from 'app/shared/model/branch-circuit.model';

export interface IBranch {
  id?: number;
  name?: string | null;
  publicationDate?: dayjs.Dayjs | null;
  serviceProvider?: IServiceProvider | null;
  branchCircuits?: IBranchCircuit[] | null;
}

export const defaultValue: Readonly<IBranch> = {};
