package com.opsDashboard.security;

import com.opsDashboard.user.dto.UserDTO;
import com.opsDashboard.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService
{
    private final String secret;
    private final int exp;

    private final UserService userService;

    public JwtService(final UserService userService, @Value("${jwt.secret}") final String secret, @Value("${jwt.exp}") final int exp)
    {
        this.userService = userService;
        this.secret = secret;
        this.exp = exp;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final var claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userName)
    {
        var claims = new HashMap<String, Object>();

        return createToken(claims, userName);
//        return new JwtResponse(createToken(claims, userName));
    }

    private String createToken(Map<String, Object> claims, String subject)
    {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.exp))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validateToken(String token, UserDTO userDetails)
    {
        final var username = extractUsername(token);

        return (username.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }

    public String getToken(HttpServletRequest request)
    {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new IllegalArgumentException("Invalid token!");

        return authHeader.substring(7);
    }

    public String getUserEmail(HttpServletRequest request)
    {
        return extractUsername(getToken(request));
    }

    public int getUserIdFromToken(HttpServletRequest request)
    {
        return this.userService.getUserIdByEmail(extractUsername(getToken(request)));
    }
}
