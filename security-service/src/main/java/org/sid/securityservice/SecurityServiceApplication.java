package org.sid.securityservice;

import org.sid.securityservice.entities.AppRole;
import org.sid.securityservice.repositories.AppUserRepository;
import org.sid.securityservice.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class SecurityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService, AppUserRepository appUserRepository){
        return args -> {
            accountService.saveAppRole(new AppRole(null, "USER"));
            accountService.saveAppRole(new AppRole(null, "ADMIN"));

            Stream.of("user1", "user2", "user3", "admin").forEach(u ->{
                accountService.saveUser(u, "1234", "1234");
            });
            accountService.addRoleToUser("admin", "ADMIN");
            appUserRepository.findAll().forEach(u->{
                System.out.println("Name:" + " " + u.getUsername() + " " + "Roles" + " " + u.getRoles());
            });
        };
    }

    @Bean
    BCryptPasswordEncoder getBPCE(){
        return new BCryptPasswordEncoder();
    }
}
