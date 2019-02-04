package com.movedigital;

import com.movedigital.controller.IContactRepo;
import com.movedigital.impl.Car;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan
@Configuration
@EntityScan("com.movedigital.entities")
public class ConfAuto {

//    @Bean
//    public IContactRepo repositoryContact() {
//        return new IContactRepo();
//    }

    @Bean
    public Car voiture() {
        return new Car("Mercedes");
    }

}

