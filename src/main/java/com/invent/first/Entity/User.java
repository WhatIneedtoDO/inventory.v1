package com.invent.first.Entity;

import com.invent.first.Entity.Enum.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String firstname;
  private String lastname;
  private String username;
  @JsonIgnoreProperties
  private String password;

  @Enumerated(EnumType.STRING)
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Role role;

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  private List<Token> tokens;
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  private List<Computer> computers;
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  private List<Monitor> monitors;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Printers> printers;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Telephones> telephones;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<ServerEqs> serverEqs;
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  private List<HistoryOfChanges> history;
  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  private List<PowerSystem> powerSystem;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
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
