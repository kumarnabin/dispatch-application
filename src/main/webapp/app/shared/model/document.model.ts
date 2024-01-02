import dayjs from 'dayjs';
import { IEmployee } from 'app/shared/model/employee.model';

export interface IDocument {
  id?: number;
  name?: string | null;
  fileContentType?: string | null;
  file?: string | null;
  publicationDate?: dayjs.Dayjs | null;
  employee?: IEmployee | null;
}

export const defaultValue: Readonly<IDocument> = {};
