import http from "../http-common"

class ManagerDataService {

    getTablaPos(idCampeonato) {
        return http.get(`/getTablaPos?idCampeonato=${idCampeonato}`);
    }

    getCampeonatos(){
        return http.get("/getCampeonatos");
    }
}

export default new ManagerDataService();