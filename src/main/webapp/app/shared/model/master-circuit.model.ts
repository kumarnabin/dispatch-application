import { IServiceProvider } from 'app/shared/model/service-provider.model';

export interface IMasterCircuit {
  id?: number;
  name?: string | null;
  address?: string | null;
  serviceProviders?: IServiceProvider[] | null;
}

export const defaultValue: Readonly<IMasterCircuit> = {};
