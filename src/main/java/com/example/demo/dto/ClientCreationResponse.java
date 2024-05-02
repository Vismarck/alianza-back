package com.example.demo.dto;

import com.example.demo.model.ClientsModel;

import java.util.List;

public class ClientCreationResponse {

    private String message;
    private List<ClientsModel> clients;

    public ClientCreationResponse() {
    }

    public ClientCreationResponse(String message, List<ClientsModel> clients) {
        this.message = message;
        this.clients = clients;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClientsModel> getClients() {
        return clients;
    }

    public void setClients(List<ClientsModel> clients) {
        this.clients = clients;
    }
}