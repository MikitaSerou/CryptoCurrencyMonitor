package com.serov.cryptocurrencymonitor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "crypto_currencies")
public class CryptoCurrency {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "symbol")
    private String symbol;

}