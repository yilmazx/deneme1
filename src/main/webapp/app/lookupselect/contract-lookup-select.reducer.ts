import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending } from '@reduxjs/toolkit';

import { IQueryParams, createEntitySlice, EntityState } from 'app/shared/reducers/reducer.utils';
import { ILookup, defaultValue } from 'app/shared/model/lookup.model';

const initialState: EntityState<ILookup> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
  entities2: [],
};

const apiUrl = 'api/contractlookupselect';

export const getCountries = createAsyncThunk('contractlookupselect/fetch_country_list', async ({ page, size, sort }: IQueryParams) => {
  const requestUrl = `${apiUrl}/countries`;
  return axios.get<ILookup[]>(requestUrl);
});

export const getCities = createAsyncThunk('contractlookupselect/fetch_city_list', async (id: string | number) => {
  const requestUrl = `${apiUrl}/cities/${id}`;
  return axios.get<ILookup[]>(requestUrl);
});

export const ContractLookupSelectSlice = createEntitySlice({
  name: 'contractlookupselect',
  initialState,
  extraReducers(builder) {
    builder
      .addMatcher(isFulfilled(getCountries), (state, action) => {
        const { data } = action.payload;
        return {
          ...state,
          loading: false,
          entities: data,
        };
      })
      .addMatcher(isFulfilled(getCities), (state, action) => {
        const { data } = action.payload;
        return {
          ...state,
          loading: false,
          entities2: data,
        };
      })
      .addMatcher(isPending(getCountries, getCities), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      });
  },
});

export const { reset } = ContractLookupSelectSlice.actions;

// Reducer
export default ContractLookupSelectSlice.reducer;
