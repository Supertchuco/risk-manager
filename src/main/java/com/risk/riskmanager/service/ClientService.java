package com.risk.riskmanager.service;

import com.risk.riskmanager.entity.Client;
import com.risk.riskmanager.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client findById(Long id) {
        return clientRepository.findOne(id);
    }

    public Client findByName(String name) {
        return clientRepository.findByName(name);
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public void updateClient(Client client){
        saveClient(client);
    }

    public void deleteClientById(Long id){
        clientRepository.delete(id);
    }

    public void deleteAllClients(){
        clientRepository.deleteAll();
    }

    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }

    public boolean isClientExist(Client client) {
        return findByName(client.getName()) != null;
    }
}
