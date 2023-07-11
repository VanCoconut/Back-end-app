package com.lipari.app.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The  Auth request is the DTO used for log in.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    /**
     * The Username.
     */
    private String username;

    /**
     * The Password.
     */
    private String password;
}
