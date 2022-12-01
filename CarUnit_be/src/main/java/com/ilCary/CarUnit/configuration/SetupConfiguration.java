package com.ilCary.CarUnit.configuration;

import com.ilCary.CarUnit.models.Role;
import com.ilCary.CarUnit.models.RoleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SetupConfiguration {

    @Bean(name="roleAdmin")
    @Scope("singleton")
    public Role roleAdmin() {
        return Role.builder().roleType(RoleType.ROLE_ADMIN)
                .build();
    }


}
