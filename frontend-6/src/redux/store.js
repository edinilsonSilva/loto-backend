import { configureStore } from '@reduxjs/toolkit';
import { combineReducers } from 'redux';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage'; // Usar localStorage como padrão
import accountReducer from './accountSlice'

const persistConfig = {
  key: 'account-current',
  storage, // Define o localStorage como armazenamento padrão
  whitelist: ['account'], // Nomes dos reducers que você quer persistir
};

const rootReducer = combineReducers({
  account: accountReducer,
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false, // Necessário para lidar com a persistência no localStorage
    }),
});

export const persistor = persistStore(store);