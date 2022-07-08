package com.example.workflow.mvc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String amount;
    private String currency;
    private String amountOfInstallament;
    @Column(name = "client_id")
    private Long clientId;
}
