package com.lipari.app.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Auth request.
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
