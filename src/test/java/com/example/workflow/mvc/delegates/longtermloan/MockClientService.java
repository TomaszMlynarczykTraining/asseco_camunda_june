package com.example.workflow.mvc.delegates.longtermloan;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.entity.Debt;
import com.example.workflow.mvc.service.IClientService;

import java.util.ArrayList;
import java.util.List;

public class MockClientService implements IClientService {

    private final  List<Client> clients;

    public MockClientService(Long clientId, Long debtId) {
        clients = new ArrayList<>();
        clients.add(createClient(1L, "Jan", "Nowak", debtId, "100"));
    }

    @Override
    public List<Client> getClients() {
       return clients;
    }

    @Override
    public Client getClientById(long id) {
        return clients.get((int) id);
    }

    private Client createClient(Long id, String name, String surname, Long debtId, String amount){
        Client cl = new Client();
        cl.setFirstName(name);
        cl.setLastName(surname);
        cl.setId(id);
        Debt debt = new Debt();
        debt.setId(debtId);
        debt.setAmount(amount);
        cl.setDebt(debt);
        return cl;
    }


}
