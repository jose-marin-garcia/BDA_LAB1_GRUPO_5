package Laboratorio1BdaGrupo5.BackendLab1.service;

import Laboratorio1BdaGrupo5.BackendLab1.models.Cliente;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ClienteRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteRepositoryImp clienteRepository;

    public Cliente getClienteById(Integer idCliente) {
        try {
            Cliente cliente = clienteRepository.getClienteById(idCliente);
            if (cliente != null) {
                return cliente;
            } else {
                throw new RuntimeException("Cliente no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el Cliente", e);
        }
    }

    public void createCliente(Cliente cliente) {
        try {
            clienteRepository.createCliente(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el Cliente", e);
        }
    }

    public void updateCliente(Cliente cliente) {
        try {
            if (getClienteById(cliente.getId_cliente()) != null) {
                clienteRepository.updateCliente(cliente);
            } else {
                throw new RuntimeException("El cliente no existe en la base de datos");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el Cliente", e);
        }
    }

    public void deleteCliente(Integer idCliente) {
        try {
            if (getClienteById(idCliente) != null) {
                clienteRepository.deleteCliente(idCliente);
            } else {
                throw new RuntimeException("El cliente no existe en la base de datos");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto", e);
        }
    }
}
