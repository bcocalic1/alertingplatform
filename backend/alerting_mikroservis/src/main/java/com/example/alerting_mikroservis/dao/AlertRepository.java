package com.example.alerting_mikroservis.dao;

import com.example.alerting_mikroservis.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("alerts")
public interface AlertRepository extends JpaRepository<Alert, Integer> {
    @Query(value = "SELECT * FROM alerts WHERE severity = ?1", nativeQuery = true)
    List<Alert> findBySeverity(String severity);

    @Query(value = "SELECT * FROM alerts WHERE service = ?1", nativeQuery = true)
    List<Alert> findByService(String service);
}
