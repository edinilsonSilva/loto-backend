import { ServiceBase } from './util/ServiceBase';

export class AccountPublicService extends ServiceBase {

    constructor() {
        super("/public/accounts");
    }

    async postCreateAccountPb(request) {
        return this.axiosInstance.post(`${this.url}/create-account`, request)
            .then(res => res.data)
    }

    async postResetPassword(cpf) {
        return this.axiosInstance.post(`${this.url}/reset-password`, { cpf: cpf })
            .then(res => res.data)
    }

    async uploadImagens(obj) {
        const formData = new FormData();
        formData.append('idProduto', obj.idProduto);
        formData.append('file', obj.file);
        const config = {
            headers: {
                'content-type': 'multipart/form-data'
            }
        }
        return this.axiosInstance.post(this.url, formData, config);
    }
}