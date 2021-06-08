package com.example.alerting_mikroservis.dao;

import com.example.alerting_mikroservis.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("rules")
public class RulesDao {
    private Map<String, Rule> rules;

    public RulesDao() {
        this.rules = new HashMap<>();
    }

    public void addRule(Rule rule){
        this.rules.put(rule.getService(), rule);
    }

    public Rule getCPURule(){
        return rules.get("CPU");
    }

    public List<Rule> getAllRules(){
        List<Rule> rulesList = new ArrayList<>(this.rules.values());

        return rulesList;
    }
}
