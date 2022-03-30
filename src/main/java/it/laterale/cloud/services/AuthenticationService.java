package it.laterale.cloud.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.laterale.cloud.model.User;
import it.laterale.cloud.repositories.UserRepository;
import it.laterale.cloud.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String password) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
        ObjectNode userNode = new ObjectMapper().convertValue(user, ObjectNode.class);
        userNode.remove("password");
        Map<String, Object> claimMap = new HashMap<>(0);
        claimMap.put("user", userNode);
        return JwtProvider.createJwt(email, claimMap);
    }
}
