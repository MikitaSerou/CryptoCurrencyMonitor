package com.serov.cryptocurrencymonitor.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "crypto_currencies")
public class CryptoCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "symbol")
    private String symbol;

}