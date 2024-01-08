import { Status } from 'app/shared/model/enumerations/status.model';

export interface IServiceProvider {
  id?: number;
  name?: string | null;
  code?: string | null;
  phone?: string | null;
  address?: string | null;
  status?: keyof typeof Status | null;
}

export const defaultValue: Readonly<IServiceProvider> = {};
