package com.example.workflow.mvc.service;

import com.example.workflow.mvc.entity.Client;

import java.util.List;

public interface IClientService {
    List<Client> getClients();

    Client getClientById(long id);
}
