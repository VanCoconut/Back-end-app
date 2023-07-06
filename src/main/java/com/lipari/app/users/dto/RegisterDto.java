package com.lipari.app.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The type Register dto.
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDto {

    /**
     * The Firstname.
     */
    private String firstname, /**
     * The Lastname.
     */
    lastname, /**
     * The Username.
     */
    username, /**
     * The Password.
     */
    password, /**
     * The Role.
     */
    role;




}
