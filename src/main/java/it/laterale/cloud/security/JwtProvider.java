package it.laterale.cloud.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import it.laterale.cloud.config.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.Map;

/**
 * The type Jwt provider.
 */
@Slf4j
public class JwtProvider {

    private static final String issuer = "demo-service";

    /**
     * Create jwt string.
     *
     * @param subject       the subject
     * @param payloadClaims the payload claims
     * @return the JWT string
     */
    public static String createJwt(String subject, Map<String, Object> payloadClaims) {
        JWTCreator.Builder builder =  JWT.create()
                .withSubject(subject)
                .withIssuer(issuer)
                .withIssuedAt(DateTime.now().toDate())
                .withExpiresAt(DateTime.now().plusMonths(1).toDate());

        if (payloadClaims != null && !payloadClaims.isEmpty()) {
            for (Map.Entry<String, Object> entry : payloadClaims.entrySet()) {
                builder.withClaim(entry.getKey(), entry.getValue().toString());
            }
        } else {
            log.warn("You are building a JWT without header claims!");
        }
        return builder.sign(Algorithm.HMAC256(SecurityConfig.secret));
    }

    /**
     * Verify jwt decoded.
     *
     * @param jwt the JWT string
     * @return the decoded JWT
     */
    public static DecodedJWT verifyJwt(String jwt) {
        return JWT.require(Algorithm.HMAC256(SecurityConfig.secret)).build().verify(jwt);
    }

    private JwtProvider() {}
}
