package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.demo.model.ClientsModel;
import com.example.demo.service.ClientsService;
import com.example.demo.common.Utils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientsServiceImpl implements ClientsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientsServiceImpl.class);

	private final List<ClientsModel> allClients;

	public ClientsServiceImpl() {
		// Obtener la lista de todos los clientes utilizando el método listAllClients de
		// Utils
		this.allClients = Utils.listAllClients();
	}

	@Override
	public ClientsModel createClient(ClientsModel newClient) {
		
		List<ClientsModel> allClients = new ArrayList<>();
		allClients.addAll(this.allClients);
		
		LOGGER.info("Obteniendo todos los clientes: {}", allClients);
		
		
		// Verificar si el cliente ya existe en la lista
		boolean clientExists = allClients.stream()
				.anyMatch(existingClient -> existingClient.getSharedKey().equals(newClient.getSharedKey()));

		if (clientExists) {
			// Si el cliente ya existe, lanzar una excepción o retornar un valor que indique
			// que la operación falló
			LOGGER.info("El cliente ya existe");
			throw new RuntimeException("El cliente ya existe");
		} else {
			// Retornar el cliente agregado
			LOGGER.info("Cliente creado exitosamente: {}", newClient);
			return newClient;
		}
	}

	// Método para obtener todos los clientes
	@Override
	public List<ClientsModel> getAllClients() {
		List<ClientsModel> allClients = this.allClients;
		LOGGER.info("Obteniendo todos los clientes: {}", allClients);
		return allClients;
	}

	// Método para buscar un cliente por su SharedKey
	@Override
	public ClientsModel searchBySharedKey(String sharedKey) {
		 LOGGER.info("Iniciando la búsqueda de cliente por SharedKey");
		// Iterar sobre la lista de clientes y buscar el cliente con la SharedKey dada
		for (ClientsModel client : allClients) {
			if (client.getSharedKey().equals(sharedKey)) {
				LOGGER.info("Cliente encontrado con SharedKey: {}", sharedKey);
				return client; // Devolver el cliente si se encuentra
			}
		}
		LOGGER.info("No se encontró ningún cliente con la SharedKey: {}", sharedKey);
		return null; // Retornar null si no se encuentra ningún cliente con la SharedKey dada
	}

	@Override
	public List<ClientsModel> searchAdvanced(ClientsModel searchParams) {
		
		 LOGGER.info("Iniciando búsqueda avanzada de clientes con parámetros: {}", searchParams.toString());
		List<ClientsModel> allClients = this.allClients;

		// Inicializar una lista para almacenar los resultados de la búsqueda
		List<ClientsModel> searchResults = new ArrayList<>();

		// Filtrar la lista de clientes según los parámetros de búsqueda especificados
		// en searchParams
		for (ClientsModel client : allClients) {
			boolean matchesCriteria = true;

			// Verificar el criterio del nombre
			if (searchParams.getName() != null && !searchParams.getName().isEmpty()) {
				if (!client.getName().toLowerCase().contains(searchParams.getName().toLowerCase())) {
					matchesCriteria = false;
				}
			}

			// Verificar el criterio de sharedkey
			if (searchParams.getSharedKey() != null && !searchParams.getSharedKey().isEmpty()) {
				if (!client.getSharedKey().toLowerCase().contains(searchParams.getSharedKey().toLowerCase())) {
					matchesCriteria = false;
				}
			}

			// Verificar el criterio del telefono
			if (searchParams.getPhone() != null && !searchParams.getPhone().isEmpty()) {
				if (!client.getPhone().toLowerCase().contains(searchParams.getPhone().toLowerCase())) {
					matchesCriteria = false;
				}
			}

			// Verificar el criterio del email
			if (searchParams.getEmail() != null && !searchParams.getEmail().isEmpty()) {
				if (!client.getEmail().toLowerCase().contains(searchParams.getEmail().toLowerCase())) {
					matchesCriteria = false;
				}
			}

			// Verificar el criterio de fecha de inicio
			if (searchParams.getStartDate() != null && client.getStartDate() != null) {
				if (client.getStartDate().before(searchParams.getStartDate())) {
					matchesCriteria = false;
				}
			}

			// Verificar el criterio de fecha de final
			if (searchParams.getEndDate() != null && client.getEndDate() != null) {
				if (client.getEndDate().before(searchParams.getEndDate())) {
					matchesCriteria = false;
				}
			}

			// Si el cliente cumple con todos los criterios de búsqueda, agregarlo a la
			// lista de resultados
			if (matchesCriteria) {
				searchResults.add(client);
			}
		}
		LOGGER.info("Búsqueda avanzada completada. Se encontraron {} clientes que coinciden con los criterios de búsqueda.", searchResults.size());
		return searchResults;
	}
}
