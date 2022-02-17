import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { IContract, defaultValue } from 'app/shared/model/contract.model';

const initialState: EntityState<IContract> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

const apiUrl = 'api/contractselect';

export const getContracts = createAsyncThunk('contractselect/fetch_contract_list', async (name: string) => {
  const requestUrl = `${apiUrl}/contacts/${name}`;
  return axios.get<IContract[]>(requestUrl);
});

export const ContractSelectSlice = createEntitySlice({
  name: 'contractselect',
  initialState,
  extraReducers(builder) {
    builder
      .addMatcher(isFulfilled(getContracts), (state, action) => {
        const { data } = action.payload;
        return {
          ...state,
          loading: false,
          entities: data,
        };
      })
      .addMatcher(isPending(getContracts), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      });
  },
});

export const { reset } = ContractSelectSlice.actions;

// Reducer
export default ContractSelectSlice.reducer;
