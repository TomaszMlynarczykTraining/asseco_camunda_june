package com.example.workflow.mvc.service;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientByIdg4(Long clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    public Client getClientById(long id) {
        return clientRepository.getById(id);
    }
}
