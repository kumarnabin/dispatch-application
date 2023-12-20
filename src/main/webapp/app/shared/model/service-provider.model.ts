import { IMasterCircuit } from 'app/shared/model/master-circuit.model';

export interface IServiceProvider {
  id?: number;
  name?: string | null;
  address?: string | null;
  masterCircuits?: IMasterCircuit[] | null;
}

export const defaultValue: Readonly<IServiceProvider> = {};
