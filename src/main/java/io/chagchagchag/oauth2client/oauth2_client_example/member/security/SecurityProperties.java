package io.chagchagchag.oauth2client.oauth2_client_example.member.security;

import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class SecurityProperties {
  public static final String SECRET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMLOPQRTTTTTTTTT";
  public static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
}
