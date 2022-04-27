package com.mbaraujo.analyst.transaction.entity;

import lombok.*;

import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Table(schema = "transaction_register")
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class TransactionsRegister {

    private LocalDate date;
    private LocalDateTime importDate;
}
