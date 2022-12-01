package com.ilCary.CarUnit.configuration;

import com.ilCary.CarUnit.models.Role;
import com.ilCary.CarUnit.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SetupRunner implements CommandLineRunner {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SetupConfiguration.class);
    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        createData();
    }

    private void createData() {

        System.out.println("popi");

        roleService.save(ctx.getBean("roleAdmin", Role.class));
    }

}
