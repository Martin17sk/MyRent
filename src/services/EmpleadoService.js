import axios from 'axios'
class EmpleadoService {
  async getEmpleados() {
    const response = await axios.get('http://localhost:3000/empleados')
    return response.data
  }

  async getEmpleadoById(id) {
    const response = await axios.get(`http://localhost:3000/empleados/${id}`)
    return response.data
  }

  async getEmpleadosByProfileId(profileId) {
    const response = await axios.get(
      `http://localhost:3000/empleados?perfil_id=${profileId}`,
    )
    return response.data
  }

  async getEmpleadosByPropiedadId(propiedadId) {
    const response = await axios.get(
      `http://localhost:3000/empleado_propiedad?propiedad_id=${propiedadId}`,
    )
    return response.data
  }

  async addEmpleadoPropiedad(empleadoPropiedad) {
    const response = await axios.post(
      'http://localhost:3000/empleado_propiedad',
      empleadoPropiedad,
    )
    return response.data
  }

  async createEmpleado(empleado) {
    const response = await axios.post(
      'http://localhost:3000/empleados',
      empleado,
    )
    return response.data
  }

  async updateEmpleado(id, empleado) {
    const response = await axios.put(
      `http://localhost:3000/empleados/${id}`,
      empleado,
    )
    return response.data
  }

  async deleteEmpleado(id) {
    const response = await axios.delete(`http://localhost:3000/empleados/${id}`)
    return response.data
  }
}
export default EmpleadoService
