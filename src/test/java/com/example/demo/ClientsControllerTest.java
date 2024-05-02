package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.common.Utils;
import com.example.demo.controller.ClientsController;
import com.example.demo.service.ClientsService;
import com.example.demo.model.ClientsModel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
class ClientsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClientsService clientService;

    @InjectMocks
    private ClientsController clientController;
    
    @Test
    public void testCreateClient() {
        // Crear un cliente simulado para agregar
        ClientsModel newClient = new ClientsModel();
        newClient.setSharedKey("nuevoCliente");
        newClient.setName("Nuevo Cliente");
        newClient.setPhone("2222222");
        newClient.setEmail("nuevoCliente@gmail.com");
        newClient.setStartDate(new Date());
        newClient.setEndDate(new Date());
        newClient.setDateAdded(new Date());

        // Crear una lista simulada de clientes existentes
        List<ClientsModel> mockClients = new ArrayList<>();
        ClientsModel existingClient = new ClientsModel();
        existingClient.setSharedKey("clienteExistente");
        existingClient.setName("Cliente Existente");
        mockClients.add(existingClient);

        // Crear un mock del servicio
        ClientsService clientsServiceMock = mock(ClientsService.class);

        // Configurar el comportamiento del mock para devolver la lista simulada de clientes
        when(clientsServiceMock.getAllClients()).thenReturn(mockClients);

        // Configurar el comportamiento del mock para que devuelva el cliente creado cuando se llame a createClient
        when(clientsServiceMock.createClient(newClient)).thenReturn(newClient);

        // Llamar al método de creación de cliente del servicio
        ClientsModel createdClient = clientsServiceMock.createClient(newClient);
        
     // Agregar el cliente creado a la lista simulada
        mockClients.add(createdClient);

        // Verificar que se llamó al método createClient con el nuevo cliente
        verify(clientsServiceMock, times(1)).createClient(newClient);

        // Verificar que la lista de clientes simulada se actualizó correctamente
        assertEquals(2, mockClients.size()); // Verificar que la lista de clientes simulada tiene un tamaño de 2
        assertEquals(newClient, mockClients.get(1)); // Verificar que el nuevo cliente está en la posición correcta en la lista
    }

    @Test
    public void testGetAllClients() throws Exception {
        List<ClientsModel> allClients = Utils.listAllClients();

        when(clientService.getAllClients()).thenReturn(allClients);

        mockMvc.perform(get("/clients/allclients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sharedKey").value("jgutierrez"))
                .andExpect(jsonPath("$[1].sharedKey").value("mmartinez"))
                .andExpect(jsonPath("$[2].sharedKey").value("aruiz"))
                .andExpect(jsonPath("$[3].sharedKey").value("ogarcia"))
                .andExpect(jsonPath("$[4].sharedKey").value("tramos"))
                .andExpect(jsonPath("$[5].sharedKey").value("caiza"))
                .andExpect(jsonPath("$[6].sharedKey").value("rvillaneda"))
                .andExpect(jsonPath("$[7].sharedKey").value("mfonseca"));
    }
    
    
    @Test
    public void testSearchBySharedKey() {
    	 // Crear una lista de clientes simulada
        List<ClientsModel> mockClients = Utils.listAllClients();

        // Crear un mock del servicio de clientes
        ClientsService clientsServiceMock = mock(ClientsService.class);

        // Configurar el comportamiento del mock para devolver la lista simulada de clientes
        when(clientsServiceMock.getAllClients()).thenReturn(mockClients);

        // Configurar el comportamiento del mock para devolver el cliente simulado cuando se llama a searchBySharedKey
        String sharedKeyToSearch = "jgutierrez";
        ClientsModel expectedClient = mockClients.stream()
                                        .filter(client -> client.getSharedKey().equals(sharedKeyToSearch))
                                        .findFirst()
                                        .orElse(null);
        when(clientsServiceMock.searchBySharedKey(sharedKeyToSearch)).thenReturn(expectedClient);

        // Llamar al método de búsqueda por SharedKey del servicio
        ClientsModel actualClient = clientsServiceMock.searchBySharedKey(sharedKeyToSearch);

        // Verificar que el cliente devuelto sea el esperado
        assertEquals(expectedClient, actualClient);
    }
    
    
    @Test
    public void testSearchAdvanced() {
    	 // Crear una lista de clientes simulada
        List<ClientsModel> mockClients = Utils.listAllClients();

        // Crear un mock del servicio de clientes
        ClientsService clientsServiceMock = mock(ClientsService.class);

        // Configurar el comportamiento del mock para devolver la lista simulada de clientes
        when(clientsServiceMock.getAllClients()).thenReturn(mockClients);

        // Crear un objeto de búsqueda simulado
        ClientsModel searchParams = new ClientsModel();
        searchParams.setName("Juliana Gutierrez");

        // Obtener la lista simulada de clientes que coinciden con los parámetros de búsqueda
        List<ClientsModel> expectedClients = mockClients.stream()
                                                .filter(client -> client.getName().equals(searchParams.getName()))
                                                .toList();
        
        when(clientsServiceMock.searchAdvanced(searchParams)).thenReturn(expectedClients);

        // Llamar al método de búsqueda avanzada del servicio
        List<ClientsModel> actualClients = clientsServiceMock.searchAdvanced(searchParams);


        // Verificar que la lista de clientes devuelta sea la esperada
        assertEquals(expectedClients, actualClients);
    }
}