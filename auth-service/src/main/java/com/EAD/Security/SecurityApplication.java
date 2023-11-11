package com.EAD.Security;

import com.EAD.Security.User.Role;
import com.EAD.Security.auth.AuthenticationService;
import com.EAD.Security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
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
