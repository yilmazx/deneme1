import dayjs from 'dayjs';
import { ILookup } from 'app/shared/model/lookup.model';

export interface IContract {
  id?: number;
  contractname?: string;
  contractpath?: string;
  uid?: string;
  user?: string;
  isdeleted?: boolean;
  isenabled?: boolean;
  updateDate?: string;
  createDate?: string;
  createBy?: string;
  updateBy?: string | null;
  city?: ILookup | null;
  country?: ILookup | null;
  legalarea?: ILookup | null;
  industry?: ILookup | null;
  contracttype?: ILookup | null;
}

export const defaultValue: Readonly<IContract> = {
  isdeleted: false,
  isenabled: false,
};
