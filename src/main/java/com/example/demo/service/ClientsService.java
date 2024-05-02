package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.model.ClientsModel;

@Service
public interface ClientsService {
	
	ClientsModel createClient(ClientsModel newClient);

	List<ClientsModel> getAllClients();

	ClientsModel searchBySharedKey(String sharedKey);

	List<ClientsModel> searchAdvanced(ClientsModel searchParams);

}
