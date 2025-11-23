package com.devMeet.DeveloperMeetWeb.config;

import com.devMeet.DeveloperMeetWeb.model.User;
import com.devMeet.DeveloperMeetWeb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                User user = new User();                         // 👈 no-arg constructor
                user.setName("Kishan");                         // optional if you have name field
                user.setEmail("test@example.com");
                user.setPassword(encoder.encode("123456"));     // default password

                userRepository.save(user);
                System.out.println("Default user created");
            }
        };
    }
}
