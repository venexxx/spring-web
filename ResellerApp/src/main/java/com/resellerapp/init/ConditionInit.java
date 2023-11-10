package com.resellerapp.init;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ConditionInit implements CommandLineRunner {

    private  final ConditionRepository conditionRepository;

    public ConditionInit(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Condition> conditions = new ArrayList<>();


        if (!(conditionRepository.count() > 0)){
            Arrays.stream(ConditionName.values()).forEach(c -> {
                Condition condition = new Condition(c);
                conditions.add(condition);

            });
            conditionRepository.saveAll(conditions);
        }


    }
}
