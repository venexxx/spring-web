package com.resellerapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{
    @Column(nullable = false)
    @Min(2)
    @Max(50)
    private String description;

    @Column(nullable = false)
    @Positive
    private BigDecimal price;


    @ManyToOne
    private Condition condition;
}
