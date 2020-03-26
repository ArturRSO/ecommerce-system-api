package ecommerce.system.api.tools;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtHandler {

    @Value("${jwt.expiration.miliseconds}")
    static long expirationMiliseconds;

    @Value("${jwt.header}")
    static String header;

    @Value("${jwt.token.prefix}")
    static String tokenPrefix;

    @Value("${jwt.secret}")
    static String secret;

    public boolean checkToken(String subject, String token) {

        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        final String tokenSubject = claims.getSubject();

        return (tokenSubject.equals(subject) && !isTokenExpired(claims.getExpiration()));
    }

    public String getToken(String subject) {

        String jwt = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMiliseconds))
                .signWith(SignatureAlgorithm.ES512, secret)
                .compact();

        return jwt;
    }

    public String getTokenFromRequest(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");

            return token;
        }

        return null;
    }

    private boolean isTokenExpired(Date expiration) {

        return expiration.before(new Date());
    }
}
