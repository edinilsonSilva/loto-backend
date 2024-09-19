import { ServiceBase } from './util/ServiceBase';

export class LotteryDrawPublicService extends ServiceBase {

    constructor() {
        super("/public/lotteries");
    }

    async getSearch() {
        return this.axiosInstance.get(`${this.url}/search`)
            .then(res => res.data)
    }
}