package com.lipari.app.users.dto;

import com.lipari.app.users.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * The DTO for Register dto.
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
