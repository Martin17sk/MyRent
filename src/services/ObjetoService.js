import axios from 'axios'
class ObjetoService {
  async getAllObjetos() {
    const response = await axios.get('http://localhost:3000/objetos')
    const objetos = response.data
    return objetos
  }

  async getObjetosByName(name) {
    const response = await axios.get(
      `http://localhost:3000/objetos?nombre=${name}`,
    )
    return response.data
  }

  async getObjetoById(id) {
    const response = await axios.get(`http://localhost:3000/objetos?id=${id}`)
    return response.data
  }

  async addObjeto(objeto) {
    const objetoExists = await this.getObjetosByName(objeto.nombre)
    if (objetoExists.length > 0) {
      console.log(objeto.nombre + ' already exists')
    } else {
      const response = await axios.post('http://localhost:3000/objetos', objeto)
      return response.data
    }
    return {}
  }

  async addInventario(inventario) {
    const response = await axios.post(
      'http://localhost:3000/inventarios',
      inventario,
    )
    return response.data
  }

  async updateObjeto(objeto) {
    const response = await axios.put(
      `http://localhost:3000/objetos/${objeto.id}`,
      objeto,
    )
    return response.data
  }

  async deleteObjeto(id) {
    const response = await axios.delete(`http://localhost:3000/objetos/${id}`)
    return response.data
  }
}
export default ObjetoService
