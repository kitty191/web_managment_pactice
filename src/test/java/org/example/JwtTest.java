package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {


    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("name", "admin");

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "emhlbmd3ZWlzb25n")
                .addClaims(dataMap)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiYWRtaW4iLCJpZCI6MSwiZXhwIjoxNzg5MDk5MTE3fQ.pTZfEirfN6G-FtWiWf4GHYBvvlQBncJFpiGDQYVfTuE";
        Claims claims = Jwts.parser().setSigningKey("emhlbmd3ZWlzb25n")
                .parseClaimsJws(token)
                .getBody();

        System.out.println(claims);
    }
}
