package com.mbaraujo.analyst.transaction.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "transaction_register")
@Table(name = "transaction_register")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TransactionsRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "register_id")
    @SequenceGenerator(name = "register_id", sequenceName = "register_id", allocationSize = 1)
    private Long id;
    private LocalDate date;
    private LocalDateTime importDate;

    public TransactionsRegister(LocalDate date, LocalDateTime importDate) {
        this.date = date;
        this.importDate = importDate;
    }

    public String getFormatDate() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getFormatImportDate() {
        return importDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"));
    }
}
