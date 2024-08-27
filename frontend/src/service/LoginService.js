import { ServiceBase } from './util/ServiceBase';

export class LoginService extends ServiceBase {

    KEY_TOKEN = "@tkloto";

    constructor(){
        super("/auth");
    }

    login(username, password, mensagemErro){
        this.axiosInstance.post(this.url+"login",{'username':username, 'password':password}).then(res=>{
            localStorage.setItem(this.KEY_TOKEN, res.data.token);
            window.location.href = "/";
        }).catch(error=>{
            mensagemErro(error.response.data.message);
        });
    }

    autenticado(){
        return this.getToken()!=null;
    }

    sair(){
        localStorage.removeItem(this.KEY_TOKEN);
    }

    getToken(){
        return localStorage.getItem(this.KEY_TOKEN);
    }
}