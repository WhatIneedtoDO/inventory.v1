package com.invent.first;


import com.invent.first.Repository.UserRepository;
import com.invent.first.Service.AuthenticationService;
import com.invent.first.request.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.invent.first.Entity.Enum.Role.ADMIN;
import static com.invent.first.Entity.Enum.Role.MANAGER;

@SpringBootApplication

public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service,
			UserRepository userRepository
	) {
		return args -> {
			String adminUsername = "admin";
			String managerUsername = "manager";

			if (userRepository.findByUsername(adminUsername).isEmpty()) {
				// Создать и зарегистрировать пользователя admin
				var admin = RegisterRequest.builder()
						.firstname("Admin")
						.lastname("Admin")
						.username(adminUsername)
						.password("password")
						.role(ADMIN)
						.build();
				System.out.println("Admin token: " + service.register(admin).getAccessToken());
			}

			if (userRepository.findByUsername(managerUsername).isEmpty()) {
				// Создать и зарегистрировать пользователя manager
				var manager = RegisterRequest.builder()
						.firstname("Manager")
						.lastname("Manager")
						.username(managerUsername)
						.password("password")
						.role(MANAGER)
						.build();
				System.out.println("Manager token: " + service.register(manager).getAccessToken());
			}
		};
	}
}