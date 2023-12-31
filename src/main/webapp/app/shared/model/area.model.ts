import dayjs from 'dayjs';

export interface IArea {
  id?: number;
  code?: string | null;
  detail?: string | null;
  publicationDate?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IArea> = {};
