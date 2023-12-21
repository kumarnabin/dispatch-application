import dayjs from 'dayjs';

export interface IExcelData {
  id?: number;
  column1?: string | null;
  column2?: string | null;
  column3?: string | null;
  column4?: string | null;
  column5?: string | null;
  column6?: string | null;
  column7?: string | null;
  column8?: string | null;
  column9?: string | null;
  column10?: string | null;
  column11?: string | null;
  column12?: string | null;
  column13?: string | null;
  column14?: string | null;
  column15?: string | null;
  column16?: string | null;
  column17?: string | null;
  column18?: string | null;
  column19?: string | null;
  column20?: string | null;
  column21?: string | null;
  column22?: string | null;
  column23?: string | null;
  column24?: string | null;
  column25?: string | null;
  column26?: string | null;
  column27?: string | null;
  publicationDate?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IExcelData> = {};
