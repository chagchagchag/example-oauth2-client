package io.chagchagchag.oauth2client.oauth2_client_example.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class JwtSupport {
  public String generateToken(Key key, String userId, String username, String email){
    return Jwts.builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + 864000000))
        .claim("id", userId)
        .claim("name", username)
        .claim("email", email)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public void addJwtAtResponseHeader(String jwt, ServerHttpResponse response){
    response.getHeaders().add("Authorization", String.format("Bearer %s", jwt));
  }

  public Jwt degenerateToken(Key key, String token){
    JwtParser parser = Jwts.parserBuilder()
        .setSigningKey(key)
        .build();

    Jws<Claims> claimsJws = parser.parseClaimsJws(token);

    return new Jwt(
        claimsJws.getBody().get("id", String.class),
        claimsJws.getBody().get("name", String.class),
        claimsJws.getBody().get("email", String.class),
        claimsJws.getBody().getExpiration()
    );
  }

  public Boolean checkContainsBearer(String header){
    var len = "Bearer ".length();
    return header.substring(0, len).equalsIgnoreCase("Bearer");
  }

  // 만료되면 true
  // 만료되지 않았으면 false
  public Boolean checkIfNotExpired(Date expiration){
    return expiration.after(new Date());
  }
}
