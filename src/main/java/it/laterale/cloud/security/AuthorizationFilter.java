package it.laterale.cloud.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.laterale.cloud.model.User;
import it.laterale.cloud.model.UserRole;
import it.laterale.cloud.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    public AuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String header = request.getHeader(JwtProvider.headerParam);
        if (header != null && header.startsWith(JwtProvider.prefix)) {
            final DecodedJWT decoded = JwtProvider.verifyJwt(header.replace(JwtProvider.prefix, ""));
            final ObjectNode userNode = this.mapper.readValue(decoded.getClaim("user").asString(), ObjectNode.class);
            final User user = this.mapper.convertValue(userNode, User.class);
            this.userRepository.findById(user.getId()).ifPresent(entity -> {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, entity.getRoles().stream().map(UserRole::name).map(SimpleGrantedAuthority::new).collect(Collectors.toSet())));
            });
        }
        chain.doFilter(request, response);
    }
}
