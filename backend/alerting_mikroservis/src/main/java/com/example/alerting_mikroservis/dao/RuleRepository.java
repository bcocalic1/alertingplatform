package com.example.alerting_mikroservis.dao;

import com.example.alerting_mikroservis.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("rules")
public interface RuleRepository extends JpaRepository<Rule, Integer> {
    @Query(value = "SELECT * FROM rules WHERE service = ?1", nativeQuery = true)
    Rule findByService(String service);

    @Query(value = "SELECT * FROM rules WHERE severity = ?1", nativeQuery = true)
    List<Rule> findBySeverity(String severity);

    @Modifying
    @Query(value = "UPDATE rules SET (name, limit, time_period, time_unit, in_a_row) = (?1, ?2, ?3, ?4, ?5) WHERE severity = ?5 AND service = ?6", nativeQuery = true)
    void updateRule(String name, Double limit, Double time_period, String time_unit, Integer inARow, String severity, String service);

}