package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.demo.dto.ClientCreationResponse;
import com.example.demo.model.ClientsModel;
import com.example.demo.service.ClientsService;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/clients")
public class ClientsController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(ClientsController.class);

	    private final ClientsService clientsService;

	    public ClientsController(ClientsService clientsService) {
	        this.clientsService = clientsService;
	    }
	    
	    // Endpoint para crear un cliente
	    @PostMapping("/create")
	    public ResponseEntity<ClientCreationResponse> createClient(@RequestBody ClientsModel client) {
	    	LOGGER.info("Iniciando creación de cliente...");
	        ClientCreationResponse response = new ClientCreationResponse();
	        HttpStatus status;
	        String message;
	        List<ClientsModel> clients = new ArrayList<>();

	        ClientsModel createdClient = clientsService.createClient(client);

	        if (createdClient != null) {
	            message = "Cliente creado exitosamente";
	            status = HttpStatus.CREATED;
	        } else {
	            message = "No se pudo crear el cliente";
	            status = HttpStatus.BAD_REQUEST;
	        }

	        clients.addAll(clientsService.getAllClients());

	        if (createdClient != null) {
	            clients.add(createdClient);
	        }

	        response.setMessage(message);
	        response.setClients(clients);
	        
	        LOGGER.info("Creación de cliente completada.");
	        return ResponseEntity.status(status).body(response);
	    }
	    

	    // Endpoint para obtener todos los clientes
	    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
	    @GetMapping("/allclients")
	    public ResponseEntity<List<ClientsModel>> getAllClients() {
	        LOGGER.info("Obteniendo todos los clientes");
	        List<ClientsModel> clients = clientsService.getAllClients();

	        if (clients.isEmpty()) {
	            LOGGER.info("No se encontraron clientes");
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            LOGGER.info("Se encontraron {} clientes", clients.size());
	            return new ResponseEntity<>(clients, HttpStatus.OK);
	        }
	    }
	    
	    // Endpoint para buscar un cliente por su SharedKey
	    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
	    @GetMapping("/search/{sharedKey}")
	    public ResponseEntity<ClientsModel> searchBySharedKey(@PathVariable String sharedKey) {
	    	LOGGER.info("Iniciando búsqueda por SharedKey: {}", sharedKey);
	        ClientsModel client = clientsService.searchBySharedKey(sharedKey);
	        if (client != null) {
	        	LOGGER.info("Cliente encontrado para SharedKey {}: {}", sharedKey, client.toString());
	            return ResponseEntity.ok(client);
	        } else {
	        	 LOGGER.info("No se encontró ningún cliente para SharedKey: {}", sharedKey);
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	    // Endpoint para la búsqueda avanzada
	    @PostMapping("/advancedsearch")
	    public ResponseEntity<List<ClientsModel>> advancedSearch(@RequestBody ClientsModel searchParams) {
	    	LOGGER.info("Iniciando búsqueda avanzada con parámetros: {}", searchParams.toString());
	        List<ClientsModel> searchResult = clientsService.searchAdvanced(searchParams);
	        
	        if (searchResult.isEmpty()) {
	        	LOGGER.info("No se encontraron resultados para la búsqueda avanzada con parámetros: {}", searchParams.toString());
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	        	LOGGER.info("Búsqueda avanzada completada con éxito. Resultados encontrados: {}", searchResult.size());
	            return new ResponseEntity<>(searchResult, HttpStatus.OK);
	        }
	    }

}
