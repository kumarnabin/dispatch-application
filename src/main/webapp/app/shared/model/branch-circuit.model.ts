import dayjs from 'dayjs';
import { IMasterCircuit } from 'app/shared/model/master-circuit.model';
import { IBranch } from 'app/shared/model/branch.model';

export interface IBranchCircuit {
  id?: number;
  title?: string | null;
  publicationDate?: dayjs.Dayjs | null;
  masterCircuit?: IMasterCircuit | null;
  branches?: IBranch[] | null;
}

export const defaultValue: Readonly<IBranchCircuit> = {};
