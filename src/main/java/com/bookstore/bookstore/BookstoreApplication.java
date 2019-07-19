package com.bookstore.bookstore;

import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.security.Role;
import com.bookstore.bookstore.security.UserRole;
import com.bookstore.bookstore.services.UserService;
import com.bookstore.bookstore.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public BookstoreApplication( UserService userService) {

		this.userService=userService;
	}

	public static void main(String[] args)
	{
		SpringApplication.run(BookstoreApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		User user1 =new User();
		user1.setFirstName("Jhon");
		user1.setLastName("adams");
		user1.setUsername("j");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("JAdams@gmail.com");
		Set<UserRole> userRoles= new HashSet<>();
		Role role1=new Role();
		role1.setRoleId(1);
		role1.setName("Role_User");
		userRoles.add(new UserRole(user1, role1));
		userService.createUser(user1, userRoles);
	}


	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}
}
