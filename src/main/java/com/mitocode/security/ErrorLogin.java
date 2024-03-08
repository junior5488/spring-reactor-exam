package com.mitocode.security;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorLogin {
    private String message;
    private Date timestamp;
}
