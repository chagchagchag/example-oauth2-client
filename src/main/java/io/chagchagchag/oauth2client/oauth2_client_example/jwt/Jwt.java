package io.chagchagchag.oauth2client.oauth2_client_example.jwt;

import java.util.Date;

public record Jwt (
    String id, String name, String email, Date expiration
){
}
