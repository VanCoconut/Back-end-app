package com.lipari.app.users.entities;

import com.lipari.app.basket.entities.Basket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The type App user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
public class AppUser implements UserDetails {

    /**
     * The Id.
     */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

    /**
     * The Nome.
     */
    @Column(name = "name")
	private String nome;

    /**
     * The Cognome.
     */
    @Column(name = "surname")
	private String cognome;

    /**
     * The Username.
     */
    @Column(name = "username")
	private String username;

    /**
     * The Password.
     */
    @Column(name = "password")
	private String password;

    /**
     * The Email.
     */
    @Column(name = "email")
	private String email;

    /**
     * The Basket.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "basket_id")
	private Basket basket;

    /**
     * The Address list.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private Collection<Address> addressList = new ArrayList<>();

    /**
     * The Roles.
     */
    @ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(r->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
