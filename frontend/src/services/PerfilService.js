import axios from 'axios'
class PerfilService {
  async getAllPerfiles() {
    const response = await axios.get('http://localhost:3000/perfiles')
    const perfiles = response.data
    console.log(perfiles)
    return perfiles
  }

  async getPerfilById(id) {
    const response = await axios.get(`http://localhost:3000/perfiles?id=${id}`)
    return response.data
  }

  async getPerfilesByUserId(id) {
    const response = await axios.get(
      `http://localhost:8005/api/perfiles/${id}`,
    )
    return response.data
  }
  async addPerfil(object) {
    const response = await axios.post(
      `http://localhost:3000/perfiles`, object
    )
    return response.data
  }
}

export default PerfilService
