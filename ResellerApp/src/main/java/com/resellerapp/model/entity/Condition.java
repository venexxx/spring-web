package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table(name = "conditions")
@Entity
public class Condition extends BaseEntity {

    public Condition(ConditionName conditionName) {
        setConditionName(conditionName);
        setId(UUID.randomUUID());
    }

    public Condition() {
    }

    @Column(nullable = false,unique = true,name = "condition_name")
    @Enumerated(EnumType.STRING)
    private ConditionName conditionName;

    @NotNull
    private String description;


    public ConditionName getConditionName() {
        return conditionName;
    }

    public void setConditionName(ConditionName conditionName) {
        this.conditionName = conditionName;
        setDescription(conditionName.toString());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        switch (name){
            case "EXCELLENT":
                this.description = "In perfect condition";
                break;
            case "GOOD":
                this.description = "Some signs of wear and tear or minor defects";
                break;
            case "ACCEPTABLE":
                this.description = "The item is fairly worn but continues to function properly";
                break;
        }
    }
}
