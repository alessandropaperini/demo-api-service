package it.laterale.cloud.services;

import it.laterale.cloud.model.User;
import it.laterale.cloud.model.UserRole;
import it.laterale.cloud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "admin")
public class StartUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    private String email;
    private String password;

    @PostConstruct
    public void initAdmin() {
        if (this.userRepository.findByEmail(this.email).isEmpty()) {
            User adminUser = new User();
            adminUser.setEmail(this.email);
            adminUser.setName("Admin");
            adminUser.setSurname("Admin");
            adminUser.setPassword(encoder.encode(this.password));
            adminUser.getRoles().add(UserRole.ADMIN);
            this.userRepository.save(adminUser);
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
