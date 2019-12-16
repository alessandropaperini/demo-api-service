package it.laterale.cloud.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The type Jwt provider.
 */
@Component
@Slf4j
public class JwtProvider {

    @Value("${security.secret}")
    private String secret;

    @Value("${security.prefix}")
    private String prefix;

    /**
     * Create jwt string.
     *
     * @param id the user id
     * @return the string
     */
    public String createJwt(Long id) {
        return JWT.create()
                .withSubject("subject")
                .withIssuer("issuer")
                .withIssuedAt(DateTime.now().toDate())
                .withClaim("userId", id)
                .withExpiresAt(DateTime.now().plusMonths(1).toDate())
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * Decode jwt decoded jwt.
     *
     * @param jwt the jwt
     * @return the decoded jwt
     */
    public DecodedJWT decodeJwt(String jwt) {
        try {
            jwt = jwt.replace(prefix, "").trim();
            return JWT.require(Algorithm.HMAC256(secret)).build().verify(jwt);
        } catch (Exception e) {
            log.error("Invalid JWT", e);
        }
        return null;
    }
}
