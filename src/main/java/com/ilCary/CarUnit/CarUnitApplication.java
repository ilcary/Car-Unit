package com.ilCary.CarUnit;

import com.ilCary.CarUnit.configuration.SetupConfiguration;
import com.ilCary.CarUnit.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.ilCary.CarUnit.services.RoleService;

@SpringBootApplication
public class CarUnitApplication {


	public static void main(String[] args) {
		SpringApplication.run(CarUnitApplication.class, args);

	}

	}
