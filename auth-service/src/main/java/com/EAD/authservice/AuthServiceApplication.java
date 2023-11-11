package com.ead.authservice;

import com.ead.authservice.User.Role;
import com.ead.authservice.auth.AuthenticationService;
import com.ead.authservice.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	){
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("Admin@gmail.com")
					.password("password")
					.role(Role.ADMIN)
					.build();
			System.out.println("Admin token:" + service.register(admin).getAccessToken());

			var customer = RegisterRequest.builder()
					.firstname("Customer")
					.lastname("Customer")
					.email("Customer@gmail.com")
					.password("cpassword")
					.role(Role.CUSTOMER)
					.build();
			System.out.println("Customer token:" + service.register(customer).getAccessToken());
		};
	}
}
