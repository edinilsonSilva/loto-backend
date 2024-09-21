"use client";
import axios from "axios";
import { LoginService } from "../LoginService";

export class ServiceBase {
	constructor(urlBase) {
		this.url = urlBase;
		this.axiosInstance = axios.create({
			baseURL: process.env.NEXT_PUBLIC_URL_API_V1,
		});
		this.initAxios();
	}

	initAxios() {
		this.axiosInstance.interceptors.request.use(
			(config) => {
				const token = new LoginService().getToken();

				if (token) {
					config.headers.Authorization = `Bearer ${token}`;
				}

				return config;
			},
			(error) => Promise.reject(error)
		);

		this.handlerError401();
	}

	handlerError401() {
		this.axiosInstance.interceptors.response.use(
			(response) => response,
			(error) => {
				if (
					error.response &&
					error.response.status === 401 &&
					typeof window !== "undefined"
				) {

					let response = JSON.parse(error.request.response);
					
					if (response.status === 401) {
						new LoginService().doLogout();
					}
				}
				return Promise.reject(error);
			}
		);
	}

	listarTodos() {
		return this.axiosInstance.get(this.url);
	}

	buscarId(id) {
		return this.axiosInstance.get(`${this.url}${id}`);
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
