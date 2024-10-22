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

    async getDesempenioByPropiedadIdAndMes(id, mes) {
        const response = await axios.get(`http://localhost:3000/desempenios?propiedad_id=${id}`);
        const fechaActual = new Date();
        const fechaLimite = new Date();
        fechaLimite.setFullYear(fechaActual.getFullYear() - 1);
        const desempenios = response.data.filter(desempenio => {
            const fechaDesempenio = new Date(desempenio.fecha);
            return fechaDesempenio > fechaLimite;
        });
        const desempenio = desempenios.find(desempenio => {
            const fechaDesempenio = new Date(desempenio.fecha);
            return fechaDesempenio.getMonth() === mes;
        });
        return desempenio;
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