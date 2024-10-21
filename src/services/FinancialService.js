import axios from 'axios';
class FinancialService {
    async getAllDesempenios() {
        const response = await axios.get('http://localhost:3000/desempenios');
        const desempenios = response.data;
        return desempenios;
    }

    async getDesempenioById(id) {
        const response = await axios.get(`http://localhost:3000/desempenios?id=${id}`);
        return response.data;
    }

    async getDesempeniosByPerfilId(id) {
        const response = await axios.get(`http://localhost:3000/desempenios?perfil_id=${id}}`);
        return response.data;
    }

    async getAllDesempeniosByPropiedadId(id) {
        const response = await axios.get(`http://localhost:3000/desempenios?propiedad_id=${id}`);
        return response.data;
    }

    async getAllGastos() {
        const response = await axios.get('http://localhost:3000/gastos');
        return response.data;
    }

    async getAllIngresos() {
        const response = await axios.get('http://localhost:3000/ingresos');
        return response.data;
    }

    async getAllCostes() {
        const response = await axios.get('http://localhost:3000/costes');
        return response.data;
    }
}

export default FinancialService;