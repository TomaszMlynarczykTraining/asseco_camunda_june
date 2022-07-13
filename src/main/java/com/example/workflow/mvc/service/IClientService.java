package com.example.workflow.mvc.service;

import com.example.workflow.mvc.entity.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    List<Client> getClients();
    Client getClientById(long id);

    Optional<Client> getClientByIdg4(Long id);
}
