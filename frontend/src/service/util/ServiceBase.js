import axios from 'axios';
import { config } from 'react-transition-group';
import { LoginService } from '../LoginService';

export class ServiceBase {

    constructor(urlBase) {
        this.url = urlBase + '/';
        this.initAxios();
        this.handlerError401();
    }

    initAxios() {
        this.axiosInstance = axios.create({
            baseURL: process.env.REACT_APP_URL_API_V1,
        });

        this.axiosInstance.interceptors.request.use((config) => {
            const token = new LoginService().getToken();
            const authRequestToken = token ? `Bearer ${token}` : '';
            config.headers.common['Authorization'] = authRequestToken;
            return config;
        },
            (error) => Promise.reject(error)
        );
    }

    handlerError401() {
        this.axiosInstance.interceptors.response.use((response) => {
            return response;
        }, (erro) => {
            console.log(erro.response.status);
            if (erro.response.status == 401) {
                if (!erro.request.response.includes("auth/login")) {
                    new LoginService().sair();
                    window.location.href = "/";
                }
            }
            return Promise.reject(erro);
        });
    }

    listarTodos() {
        return this.axiosInstance.get(this.url);
    }

    buscarId(id) {
        return this.axiosInstance.get(this.url + id);
    }

    inserir(objeto) {
        return this.axiosInstance.post(this.url, objeto);
    }

    alterar(objeto) {
        return this.axiosInstance.put(this.url, objeto);
    }

    excluir(id) {
        return this.axiosInstance.delete(this.url + id);
    }
}