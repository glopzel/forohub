package com.forohub.demo.domain.usuario;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    private String pass;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getUsername(){ return user; }

    public String getPassword() { return pass; }

    public boolean isAccountNonExpired() { return UserDetails.super.isAccountNonExpired(); }

    public boolean isAccountNonLocked() { return UserDetails.super.isAccountNonLocked(); }

    public boolean isCredentialsNonExpired() { return UserDetails.super.isCredentialsNonExpired(); }

    public boolean isEnabled() { return UserDetails.super.isEnabled(); }

}
