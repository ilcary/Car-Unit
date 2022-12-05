package com.ilCary.CarUnit.configuration;

import com.ilCary.CarUnit.models.Role;
import com.ilCary.CarUnit.models.RoleType;
import com.ilCary.CarUnit.models.User;
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

    @Bean(name="popi")
    @Scope("singleton")
    public User popi() {
        return User.builder()
                .email("lemiedita@gmail.com")
                .name("lemiedita")
                .lastname("deipiedi")
                .password("1234")
                .username("alluce")
                .active(true)
                .build();
    }


}
