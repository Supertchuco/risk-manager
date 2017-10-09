package com.risk.riskmanager.controller;

import com.risk.riskmanager.entity.Client;
import com.risk.riskmanager.entity.Risk;
import com.risk.riskmanager.service.ClientService;
import com.risk.riskmanager.service.RiskService;
import com.risk.riskmanager.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    ClientService clientService;

    @Autowired
    RiskService riskService;

    @RequestMapping(value = "/client/", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> listAllClients() {
        List<Client> clients = clientService.findAllClients();
        if (clients.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getClient(@PathVariable("id") long id) {
        logger.info("Fetching User with id {}", id);
        Client client = clientService.findById(id);
        if (client == null) {
            logger.error("Client with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Client with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @RequestMapping(value = "/client/", method = RequestMethod.POST)
    public ResponseEntity<?> createClient(@RequestBody Client client, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Client : {}", client);

        if (clientService.isClientExist(client)) {
            logger.error("Unable to create. A Client with name {} already exist", client.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Client with name " +
                    client.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        clientService.saveClient(client);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/client/{id}").buildAndExpand(client.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateClient(@PathVariable("id") long id, @RequestBody Client client) {
        logger.info("Updating Client with id {}", id);

        Client currentClient = clientService.findById(id);

        if (currentClient == null) {
            logger.error("Unable to update. Client with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Client with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentClient.setName(client.getName());
        currentClient.setCreditLimit(client.getCreditLimit());
      //  currentClient.setRisk(client.getRisk());

        clientService.updateClient(currentClient);
        return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClient(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Client with id {}", id);

        Client client = clientService.findById(id);
        if (client == null) {
            logger.error("Unable to delete. Client with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Client with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        clientService.deleteClientById(id);
        return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/client/", method = RequestMethod.DELETE)
    public ResponseEntity<Client> deleteAllClients() {
        logger.info("Deleting All Clients");
        clientService.deleteAllClients();
        return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/risk/", method = RequestMethod.GET)
    public ResponseEntity<List<Risk>> listAllRiskts() {
        List<Risk> risks = riskService.findAlRisks();
        if (risks.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Risk>>(risks, HttpStatus.OK);
    }
}
