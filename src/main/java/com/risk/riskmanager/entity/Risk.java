package com.risk.riskmanager.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "RISK")
@Data
public class Risk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "RISK_NAME", nullable = false)
    private String riskName;

    @Column(name = "TAX_RISK", nullable = false)
    private double taxRisk;

    public Risk(String riskName, double taxRisk){
        this.riskName = riskName;
        this.taxRisk = taxRisk;
    }


    public Risk(Long id, String riskName, double taxRisk){
        this.id = id;
        this.riskName = riskName;
        this.taxRisk = taxRisk;
    }

    public Risk(){}
}
