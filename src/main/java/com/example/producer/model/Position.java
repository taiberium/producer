package com.example.producer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    private String id;
    @Indexed
    @NotNull
    private String account;
    @NotNull
    private String isin;
    private BigDecimal t0;
    @NotNull
    private BigDecimal tn;

}
