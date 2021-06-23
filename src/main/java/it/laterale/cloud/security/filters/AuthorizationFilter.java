package it.laterale.cloud.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import it.laterale.cloud.config.SecurityConfig;
import it.laterale.cloud.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * The type Authorization filter.
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {

    /**
     * Instantiates a new Authorization filter.
     *
     * @param authenticationManager the authentication manager
     */
    @Autowired
    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConfig.param);
        if (header != null && header.startsWith(SecurityConfig.prefix)) {
            DecodedJWT decoded = JwtProvider.verifyJwt(header.replace(SecurityConfig.prefix, ""));
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(decoded.getSubject(), null, Collections.emptyList())
            );
        }
        chain.doFilter(request, response);
    }
}
