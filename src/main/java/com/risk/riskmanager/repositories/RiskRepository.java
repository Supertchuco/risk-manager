package com.risk.riskmanager.repositories;

import com.risk.riskmanager.entity.Risk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskRepository extends JpaRepository<Risk, Long> {

    Risk findByRiskName(String riskName);
}
