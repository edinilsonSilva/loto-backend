import { message } from 'antd';
import { ServiceBase } from './util/ServiceBase';

import { setAccount } from '../redux/accountSlice';

export class LoginService extends ServiceBase {

    KEY_TOKEN = "@tkloto";

    constructor(dispatch, getState) {
        super("/auth");
        this.dispatch = dispatch;
        this.getState = getState;
    }

    doLogin(request) {

        this.axiosInstance.post(`${this.url}login`, request).then(res => {

            const accountData = {
                name: res.data?.content?.name,
                email: res.data?.content?.contact,
                avatar: '/avatar.jpg',
            };

            this.dispatch(setAccount(accountData)); // Despacha a ação para armazenar o usuário

            window.localStorage.setItem(this.KEY_TOKEN, res.data.token);
            window.location.href = "/";
        }).catch(error => {
            const errorMessage = error.response?.data?.message || 'Erro desconhecido';
            this.showMessageError(errorMessage);
        });
    }

    showMessageError(erro) {
        message.error(erro);
    }

    autenticado() {
        return this.getToken() !== null;
    }

    doLogout() {
        if (typeof window !== "undefined") {
            window.localStorage.removeItem(this.KEY_TOKEN);
            window.location.href = "/";
        }
    }

    getToken() {
        if (typeof window !== "undefined") {
            return window.localStorage.getItem(this.KEY_TOKEN);
        }
        return null;
    }
}