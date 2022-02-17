export interface ILookup {
  id?: number;
  name?: string;
  lang?: string;
  uid?: string;
  value?: string;
  description?: string | null;
  parent?: ILookup | null;
}

export const defaultValue: Readonly<ILookup> = {};
