package com.mitocode.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Clase S4
@Component
public class JwtUtil implements Serializable {
    @Value("${jjwt.secret}") //"${...}" EL Expression Language
    private String secret;
    @Value("${jjwt.expiration}")
    private String expirationTime;

    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>(); //PayLoad
        claims.put("roles", user.getRoles());
        claims.put("username", user.getUsername());
        claims.put("password-demo", "sample");

        return doGenerateToken(claims, user.getUsername());
    }

    private String doGenerateToken (Map<String, Object> claims, String username){
        Long expirationTimeLong  = Long.parseLong(expirationTime);

        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000); //45 min

        //generar una semilla(llave) para compilar lo de abajo y generar el token JWT
        //sugar syntax: sintaxis del framework
        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());

        //Jwts me ofrece una serie de metodos para construir el token JWT
        return Jwts.builder()
                .setClaims(claims) //PayLoad que acabo de construir
                .setSubject(username) //Quien ha generado el token
                .setIssuedAt(createdDate) //Pide un valor Date, fecha de creacion
                .setExpiration(expirationDate) //Pide un valor Date, fecha de expiracion
                .signWith(key) //Llave
                .compact(); //Compactar en una cadena de texto, y esta cadena es el token JWT
    }

    /////////////////////////////////////////////////////////////
    //Metodo utilitarios de comprobacion

    public Claims getAllClaimsFromToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String getUsernameFromToken(String token){
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token){
        return getAllClaimsFromToken(token).getExpiration();
    }

    private boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token){
        return !isTokenExpired(token);
    }

}
