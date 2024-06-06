package io.chagchagchag.oauth2client.oauth2_client_example.jwt;

import io.chagchagchag.oauth2client.oauth2_client_example.member.security.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JwtSupportTest {

  @DisplayName("JWT 생성 분해")
  @Test
  public void TEST_JWT_생성_분해_테스트(){
    // given
    String userId = "John Deer";
    String email = "abc@email.com";
    Key key = SecurityProperties.key;

    // when
    String jwt = Jwts.builder()
        .setSubject(userId)
        .setExpiration(new Date(System.currentTimeMillis() + 864000000))
        .claim("userId", userId)
        .claim("email", email)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();

    // then
    // JWT 분해
    JwtParser parser = Jwts.parserBuilder()
        .setSigningKey(key)
        .build();

    Jws<Claims> claimsJws = parser.parseClaimsJws(jwt);
    assert claimsJws.getBody().get("email", String.class).equals(email);
    assert claimsJws.getBody().get("userId", String.class).equals(userId);
  }
}
