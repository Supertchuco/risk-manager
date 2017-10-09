package com.risk.riskmanager.service;

import com.risk.riskmanager.entity.Risk;
import com.risk.riskmanager.repositories.RiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskService {

    @Autowired
    private RiskRepository riskRepository;

    public List<Risk> findAlRisks() {
        return riskRepository.findAll();
    }

    public Risk findByRiskName(String riskName) {
        return riskRepository.findByRiskName(riskName);
    }
}
