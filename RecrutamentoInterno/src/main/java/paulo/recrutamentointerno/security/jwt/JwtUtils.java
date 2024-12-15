package paulo.recrutamentointerno.security.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import paulo.recrutamentointerno.service.UserDetailsImpl;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwtKey}")
    private String jwtKey;

    @Value("${jwtExpirationTime}")
    private int jwtExpirationTime;

    public String gerarTokenFromUserDetailsImpl(UserDetailsImpl userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // Username como subject
                .claim("id", userDetails.getId())      // Adiciona o ID do usuário nos claims
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationTime))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtKey));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Token JWT inválido: {} " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("O token JWT expirou:: {} " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("O token JWT não é compatível: {} " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT a string de reivindicações está vazia: {} " + e.getMessage());
        }

        return false;
    }

    public Key getSigninKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtKey));
        return key;
    }

    public String getUsernameToken(String token) {
        return Jwts.parser().setSigningKey(getSigninKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
