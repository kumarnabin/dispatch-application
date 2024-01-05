import dayjs from 'dayjs';

export interface IExcelData {
  id?: number;
  column1?: string | null;
}

export const defaultValue: Readonly<IExcelData> = {};
