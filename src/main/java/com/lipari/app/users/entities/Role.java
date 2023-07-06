package com.lipari.app.users.entities;

import java.util.List;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_role")
public class Role {

	@Id
	@Column(name = "role_id")
	private Long id;
	private String name;

}
