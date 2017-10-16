package com.risk.riskmanager.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CREDIT_LIMIT", nullable = false)
    private double creditLimit;

//    @OneToOne
//    @JoinTable(name="ClientRisk",
//            joinColumns=@JoinColumn(name="RISK_NAME"))
//    private Risk risk;
/*
    public Client(String name, double creditLimit, Risk risk){
        this.name = name;
        this.creditLimit = creditLimit;
        this.risk = risk;
    }*/

    public Client(String name, double creditLimit){
        this.name = name;
        this.creditLimit = creditLimit;
    }

    public Client(){
    }
}
