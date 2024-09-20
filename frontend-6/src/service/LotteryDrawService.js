import { ServiceBase } from './util/ServiceBase';

export class LotteryDrawService extends ServiceBase {

    constructor() {
        super("/lotteries");
    }

    async postCreateContest(request) {
        return this.axiosInstance.post(`${this.url}`, request)
            .then(res => res.data)
    }

    async findAllByParams() {
        return this.axiosInstance.get(`${this.url}/search`)
            .then(res => res.data)
    }

    async findAllReduced01() {
        return this.axiosInstance.get(`${this.url}/reduced-01`)
            .then(res => res.data)
    }
}