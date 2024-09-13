import { ServiceBase } from './util/ServiceBase';

export class PoolService extends ServiceBase {

    constructor() {
        super("/pools");
    }

    async postCreatePool(request) {
        return this.axiosInstance.post(`${this.url}`, request)
            .then(res => res.data)
    }

    async getSearch() {
        return this.axiosInstance.get(`${this.url}/search`)
            .then(res => res.data)
    }
}