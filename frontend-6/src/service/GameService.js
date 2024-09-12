import { ServiceBase } from './util/ServiceBase';

export class GameService extends ServiceBase {

    constructor() {
        super("/games");
    }

    async postCreateGame(request) {
        return this.axiosInstance.post(`${this.url}`, request)
            .then(res => res.data)
    }

    async getSearch() {
        return this.axiosInstance.get(`${this.url}/search`)
            .then(res => res.data)
    }
}