package com.risk.riskmanager.repositories;

import com.risk.riskmanager.entity.Client;
import com.risk.riskmanager.entity.Risk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ClientRepository clientRepository;

    private final RiskRepository riskRepository;

    @Autowired
    public DatabaseLoader(ClientRepository clientRep, RiskRepository riskRep) {
        this.clientRepository = clientRep;
        this.riskRepository = riskRep;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.clientRepository.save(new Client("oldman", 99));
        this.riskRepository.save(new Risk("a", 5));
        this.riskRepository.save(new Risk("b", 10));
        this.riskRepository.save(new Risk("c", 20));
    }
}
