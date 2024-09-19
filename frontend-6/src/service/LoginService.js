"use client";
import { message } from "antd";
import { ServiceBase } from "./util/ServiceBase";

import { useSelector } from "react-redux";
import { setAccount } from "../redux/accountSlice";

export class LoginService extends ServiceBase {
	
	KEY_TOKEN = "@tkloto";
	KEY_PERSIST_ACCOUNT_CURRENT = "persist:account-current";

	constructor() {
		super("/auth");
	}

	getAccount() {
		return useSelector((state) => state.account);
	}

	doLogin(request, dispatch, router) {
		this.axiosInstance
			.post(`${this.url}/login`, request)
			.then((res) => {
				const accountData = {
					name: res.data?.content?.name,
					email: res.data?.content?.email,
					phone: res.data?.content?.phone,
					cpf: res.data?.content?.cpf,
					wallets: res.data?.content?.wallets,
				};

				dispatch(setAccount(accountData));

				window.localStorage.setItem(this.KEY_TOKEN, res.data.token);
				router.push("/");
			})
			.catch((error) => {
				const errorMessage =
					error.response?.data?.message || "Erro desconhecido";
				this.showMessageError(errorMessage);
			});
	}

	showMessageError(erro) {
		message.error(erro);
	}

	autenticado() {
		return this.getToken() !== null;
	}

	doLogout(router) {
		if (typeof window !== "undefined") {
			window.localStorage.removeItem(this.KEY_TOKEN);
			window.localStorage.removeItem(this.KEY_PERSIST_ACCOUNT_CURRENT);
			router.push("/");
		}
	}

	getToken() {
		if (typeof window !== "undefined") {
			let token = window.localStorage.getItem(this.KEY_TOKEN);
			return token;
		}
		return null;
	}
}
