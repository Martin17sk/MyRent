import axios from 'axios'
class UsuarioService {
  async getUsuarios() {
    const response = await axios.get('http://localhost:3000/users')
    return response.data
  }
  
   async getUsuarioById(id) {
        const response = await axios.get(`http://localhost:3000/users/${id}`);
        return response.data;
   }


  async getUsuarioByEmail(email) {
    const response = await axios.get(
      `http://localhost:3000/users?correo=${email}`,
    )
    return response.data
  }

  async addUsuario(usuario) {
    const response = await axios.post('http://localhost:3000/users', usuario)
    return response.data
  }

  async updateUsuario(usuario) {
    const response = await axios.put(
      `http://localhost:3000/users/${usuario.id}`,
      usuario,
    )
    return response.data
  }

  async deleteUsuario(id) {
    const response = await axios.delete(`http://localhost:3000/users/${id}`)
    return response.data
  }
}

export default UsuarioService
