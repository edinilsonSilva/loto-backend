import { ServiceBase } from './util/ServiceBase';

export class ContestService extends ServiceBase {

    constructor() {
        super("/contests");
    }

    async postCreateContest(request) {
        return this.axiosInstance.post(`${this.url}`, request)
            .then(res => res.data)
    }

    async getSearch() {
        return this.axiosInstance.get(`${this.url}/search`)
            .then(res => res.data)
    }
}