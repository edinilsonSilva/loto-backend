import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  name: null,
  email: null,
  cpf: null,
  phone: null,
  wallets: [],
  isAuthenticated: false,
};

const accountSlice = createSlice({
  name: 'account',
  initialState,
  reducers: {
    setAccount: (state, action) => {
      //console.log("Payload recebido:", action.payload);  // Para depurar o payload recebido
      state.name = action.payload.name;
      state.email = action.payload.email;
      state.cpf = action.payload.cpf;
      state.phone = action.payload.phone;
      state.wallets = action.payload.wallets;
      state.isAuthenticated = true;  // Marcar o usuário como autenticado
    },
    clearAccount: (state) => {
      return { ...initialState };
    },
  },
});

export const { setAccount, clearAccount } = accountSlice.actions;
export default accountSlice.reducer;