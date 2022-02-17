import dayjs from 'dayjs';
import { IContract } from 'app/shared/model/contract.model';

export interface IContractarticle {
  id?: number;
  articlename?: string;
  detail?: string;
  uid?: string;
  isdeleted?: boolean;
  isenabled?: boolean;
  updateDate?: string;
  createDate?: string;
  createBy?: string;
  updateBy?: string | null;
  contract?: IContract | null;
  parent?: IContractarticle | null;
}

export const defaultValue: Readonly<IContractarticle> = {
  isdeleted: false,
  isenabled: false,
};
