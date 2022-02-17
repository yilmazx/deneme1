import dayjs from 'dayjs';
import { IContract } from 'app/shared/model/contract.model';

export interface IDocument {
  id?: number;
  documentname?: string;
  description?: string;
  uid?: string;
  path?: string | null;
  contactId?: number | null;
  inputs?: string | null;
  is_deleted?: boolean;
  isenabled?: boolean;
  updateDate?: string;
  createDate?: string;
  createBy?: string;
  updateBy?: string | null;
  contract?: IContract | null;
}

export const defaultValue: Readonly<IDocument> = {
  is_deleted: false,
  isenabled: false,
};
