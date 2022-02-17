import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { IContractInput, defaultValue } from 'app/shared/model/contract-input.model';

const initialState: EntityState<IContractInput> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

const apiUrl = 'api/contractinputselect';

export const getContractInputs = createAsyncThunk('contractinputselect/fetch_contractinput_list', async (contactId: string | number) => {
  const requestUrl = `${apiUrl}/contactinputs/${contactId}`;
  return axios.get<IContractInput[]>(requestUrl);
});

export const ContractInputSelectSlice = createEntitySlice({
  name: 'contractinputselect',
  initialState,
  extraReducers(builder) {
    builder
      .addMatcher(isFulfilled(getContractInputs), (state, action) => {
        const { data } = action.payload;
        return {
          ...state,
          loading: false,
          entities: data,
        };
      })
      .addMatcher(isPending(getContractInputs), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      });
  },
});

export const { reset } = ContractInputSelectSlice.actions;

// Reducer
export default ContractInputSelectSlice.reducer;
