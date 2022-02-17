import dayjs from 'dayjs';
import { IContract } from 'app/shared/model/contract.model';

export interface IDocumentarticle {
  id?: number;
  documentId?: number | null;
  contractarticleId?: number | null;
  is_deleted?: boolean;
  updateDate?: string;
  createDate?: string;
  createBy?: string;
  updateBy?: string | null;
  contract?: IContract | null;
  parent?: IContract | null;
}

export const defaultValue: Readonly<IDocumentarticle> = {
  is_deleted: false,
};
