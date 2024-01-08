import { IServiceProvider } from 'app/shared/model/service-provider.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IBranch {
  id?: number;
  name?: string | null;
  code?: string | null;
  status?: keyof typeof Status | null;
  serviceProvider?: IServiceProvider | null;
}

export const defaultValue: Readonly<IBranch> = {};
