import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { IContractarticle, defaultValue } from 'app/shared/model/contractarticle.model';

const initialState: EntityState<IContractarticle> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

const apiUrl = 'api/contractarticleselect';

export const getContractarticles = createAsyncThunk(
  'contractarticleselect/fetch_contactarticles_list',
  async (contactId: string | number) => {
    const requestUrl = `${apiUrl}/contactarticles/${contactId}`;
    return axios.get<IContractarticle[]>(requestUrl);
  }
);

export const ContractLookupSelectSlice = createEntitySlice({
  name: 'contractarticleselect',
  initialState,
  extraReducers(builder) {
    builder
      .addMatcher(isFulfilled(getContractarticles), (state, action) => {
        const { data } = action.payload;
        return {
          ...state,
          loading: false,
          entities: data,
        };
      })
      .addMatcher(isPending(getContractarticles), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      });
  },
});

export const { reset } = ContractLookupSelectSlice.actions;

// Reducer
export default ContractLookupSelectSlice.reducer;

// export const getContracts = createAsyncThunk(
//   'contractcreate/fetch_contract_list',
//   async (name: string) => {
//     const requestUrl = `${apiUrl}/contacts/${name}`;
//     return axios.get<ILookup>(requestUrl);
//   },
//   { serializeError: serializeAxiosError }
// );
