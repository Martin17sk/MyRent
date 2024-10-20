import axios from 'axios';
class PropiedadesService {
    async getPropiedades() {
        const response = await axios.get('http://localhost:3000/propiedades');
        return response.data;
    }

    async getPropiedadesByProfile(profileId) {
        const response = await axios.get(`http://localhost:3000/propiedades?perfil_id=${profileId}`);
        return response.data;
    }

    async getPropiedadById(id) {
        const response = await axios.get(`http://localhost:3000/propiedades/${id}`);
        return response.data;
    }

    async createPropiedad(propiedad) {
        const response = await axios.post('http://localhost:3000/propiedades', propiedad);
        return response.data;
    }

    async updatePropiedad(id, propiedad) {
        const response = await axios.put(`http://localhost:3000/propiedades/${id}`, propiedad);
        return response.data;
    }

    async deletePropiedad(id) {
        const response = await axios.delete(`http://localhost:3000/propiedades/${id}`);
        return response.data;
    }
}

export default PropiedadesService;