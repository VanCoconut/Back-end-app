package com.lipari.app.users.dto;

import com.lipari.app.users.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDto {

	private String firstname,lastname,username,password,role;




}
