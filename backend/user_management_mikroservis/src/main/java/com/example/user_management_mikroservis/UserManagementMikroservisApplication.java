package com.example.user_management_mikroservis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class UserManagementMikroservisApplication{
	public static void main(String[] args) {
		SpringApplication.run(UserManagementMikroservisApplication.class, args);
	}

}
