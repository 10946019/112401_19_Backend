package com.javaguides.arduino;

import com.javaguides.arduino.dao.UserDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArduinoBackendApplication implements CommandLineRunner {

	private final UserDAO userDAO;

	public ArduinoBackendApplication(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public static void main(String[] args) {
		SpringApplication.run(ArduinoBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setAccount("test0511");
//		user.setPassword("test0511");
//		userRepository.save(user);
	}
}
