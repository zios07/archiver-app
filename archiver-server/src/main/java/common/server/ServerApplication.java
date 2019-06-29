package common.server;

import common.server.domain.Account;
import common.server.domain.Role;
import common.server.domain.User;
import common.server.repository.RoleRepository;
import common.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {

		if (userRepository.count() == 0) {
			Role adminRole = roleRepository.save(new Role("ADMIN", "Administrator"));
			Role userRole = roleRepository.save(new Role("USER", "User"));

			Account account = new Account("admin@demo.com", encoder.encode("demo"));
			Account account2 = new Account("user@demo.com", encoder.encode("demo"));

			User admin = new User();
			admin.setRole(adminRole);
			admin.setAccount(account);

			User user = new User();
			user.setRole(userRole);
			user.setAccount(account2);

			userRepository.save(admin);
			userRepository.save(user);
		}


	}
}
