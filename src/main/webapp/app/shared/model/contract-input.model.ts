import dayjs from 'dayjs';
import { IContract } from 'app/shared/model/contract.model';

export interface IContractInput {
  id?: number;
  inputname?: string;
  inputvalue?: string;
  uid?: string;
  isdeleted?: boolean;
  isenabled?: boolean;
  updateDate?: string;
  createDate?: string;
  createBy?: string;
  updateBy?: string | null;
  contract?: IContract | null;
}

export const defaultValue: Readonly<IContractInput> = {
  isdeleted: false,
  isenabled: false,
};
