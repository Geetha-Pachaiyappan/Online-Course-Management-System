package com.example.Online.Course.Management.System.jwtToken;

import com.example.Online.Course.Management.System.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Parser;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private String secret = "MyJWTTokenForOnlineCourseProject";
  //  private final String encodedSecret = Base64.getEncoder().encodeToString(secret.getBytes());
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(User user){
     //   long expirationMs = 20 * 60 * 1000;
        Date expire = new Date(System.currentTimeMillis() + 1000 * 60 * 60);  // 1 hour

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("role",user.getRole());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claims(claims)
                .issuedAt(new Date())
                .expiration(expire)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Integer extractUserId(String token){
        return extractAllClaims(token).get("userId",Integer.class);
    }

    // Validate Token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        Date expiration = claims.getExpiration();
        return expiration != null && expiration.before(new Date());
    }


}
