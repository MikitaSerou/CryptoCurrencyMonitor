package com.serov.cryptocurrencymonitor.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "subscriptions")
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "subscribed_price")
    private Double subscribedPrice;

    @Column(name = "username")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crypto_currency_id")
    @ToString.Exclude
    private CryptoCurrency currency;

    public Subscription() {
    }

    public Subscription(@NonNull Double subscribedPrice, @NonNull String username, CryptoCurrency currency) {
        this.subscribedPrice = subscribedPrice;
        this.username = username;
        this.currency = currency;
    }
}