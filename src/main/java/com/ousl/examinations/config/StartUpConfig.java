package com.ousl.examinations.config;

import com.ousl.examinations.model.ERole;
import com.ousl.examinations.model.Role;
import com.ousl.examinations.model.User;
import com.ousl.examinations.repository.RoleRepository;
import com.ousl.examinations.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

//For creating hard coded elements
@Component
public class StartUpConfig {


    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public StartUpConfig(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public boolean createHardcodedUsers() {

        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);

        if(adminRole == null) {
            adminRole = new Role();
            adminRole.setName(ERole.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }

        System.out.println(adminRole);


        User adminUser = userRepository.findByUsername("admin");
        if(adminUser == null) {
            adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setNameWithInitials("Administrator");
            adminUser.setEmail("admin@ousl.lk");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            adminUser.setPassword(encoder.encode("admin"));
            adminUser.setEmail("admin@ousl.lk");
            adminUser.setRoles(Set.of(adminRole));
            adminUser.setStatus(true);
            userRepository.save(adminUser);
        }

        System.out.println(adminUser);


        return true;
    }

}
