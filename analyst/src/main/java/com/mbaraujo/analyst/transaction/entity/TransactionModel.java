package com.mbaraujo.analyst.transaction.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "transaction_id")
    @SequenceGenerator(name = "transaction_id", sequenceName = "transaction_id", allocationSize = 1)
    private Long id;
    private String originBank;
    private String orignAgency;
    private String originAccount;
    private String destinationBank;
    private String destinationAgency;
    private String destinationAccount;
    private BigDecimal amount;
    private LocalDateTime dateTime;

    public TransactionModel(String originBank,
                            String orignAgency,
                            String originAccount,
                            String destinationBank,
                            String destinationAgency,
                            String destinationAccount,
                            BigDecimal amount,
                            LocalDateTime dateTime) {
        this.originBank = originBank;
        this.orignAgency = orignAgency;
        this.originAccount = originAccount;
        this.destinationBank = destinationBank;
        this.destinationAgency = destinationAgency;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TransactionModel that = (TransactionModel) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
