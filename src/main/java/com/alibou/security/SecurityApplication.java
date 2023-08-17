package com.alibou.security;

import com.alibou.security.Service.AuthenticationService;
import com.alibou.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.alibou.security.Entity.Enum.Role.ADMIN;
import static com.alibou.security.Entity.Enum.Role.MANAGER;

@SpringBootApplication

public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.username("admin")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var danil = RegisterRequest.builder()
					.firstname("danil")
					.lastname("shevelev")
					.username("danil")
					.password("password111")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(danil).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.username("manager")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}
}
