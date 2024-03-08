package com.mitocode.security;

import java.util.Date;

//Clase S3
public record AuthResponse (String token, Date expiration) {
    //usamos Date y no LocalDate xq la libreria de seguridad es compatible con Java 6 en adelante
}
