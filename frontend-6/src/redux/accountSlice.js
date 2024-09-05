import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    name: '',
    email: '',
    avatar: '',
    isAuthenticated: false,
};

const accountSlice = createSlice({
    name: 'account',
    initialState,
    reducers: {
        setAccount: (state, action) => action.payload,
        clearAccount: () => null,
    },
});

export const { setAccount, clearAccount } = accountSlice.actions;
export default accountSlice.reducer;