package com.example.workflow.mvc.service;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.repository.ClientRepository;
import com.example.workflow.mvc.repository.DebtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DebtRepository debtRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }


}
