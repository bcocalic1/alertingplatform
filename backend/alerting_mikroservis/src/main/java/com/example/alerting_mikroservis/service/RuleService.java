package com.example.alerting_mikroservis.service;

import com.example.alerting_mikroservis.dao.RuleRepository;
import com.example.alerting_mikroservis.model.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RuleService {
    private final RuleRepository ruleRepository;

    @Autowired
    public RuleService(@Qualifier("rules") RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public void addRule(Rule rule){
        System.out.println(this.ruleRepository.findByService(rule.getService()));
        if((Objects.isNull(this.ruleRepository.findByService(rule.getService())))) {
            this.ruleRepository.save(rule);
        }else{
            throw new RuntimeException("Theres already a " + rule.getService() + " rule" );
        }
    }

    public Rule getRuleByService(String service){
        return this.ruleRepository.findByService(service);
    }

    public List<Rule> getAllRules(){
        return this.ruleRepository.findAll();
    }

    public void updateRule(Rule rule){
        if(Objects.isNull(this.ruleRepository.findBySeverity(rule.getSeverity())) && (Objects.isNull(this.ruleRepository.findByService(rule.getService())))) {
            throw new RuntimeException("No such rule");
        }
        this.ruleRepository.updateRule(rule.getName(), rule.getLimit(), rule.getTimePeriod(), rule.getTimeUnit(), rule.getInARow(), rule.getSeverity(), rule.getService());
    }

    public void deleteRule(String service){

        this.ruleRepository.deleteRule(service);
    }
}
