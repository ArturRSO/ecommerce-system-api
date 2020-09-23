package ecommerce.system.api.tools;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtHandler {

    @Value("${jwt.expiration.miliseconds}")
    private long expirationMiliseconds;

    @Value("${jwt.secret}")
    private String secret;

    public boolean checkToken(String subject, String token) {

        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        final String tokenSubject = claims.getSubject();

        return (tokenSubject.equals(subject) && !isTokenExpired(claims.getExpiration()));
    }

    public LocalDateTime getExpirationFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        Date expirationDate = claims.getExpiration();

        return LocalDateTime.ofInstant(expirationDate.toInstant(), ZoneId.systemDefault());
    }

    public String getToken(String subject) {

        String jwt = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMiliseconds))
                .signWith(SignatureAlgorithm.HS512, secret)
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

    public String getTokenSubject(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    private boolean isTokenExpired(Date expiration) {

        return expiration.before(new Date());
    }
}
