package me.djamelkorei.projecttaskmanagement.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * User entity.
 *
 * @author Djamel Eddine Korei
 */
@Entity(name = "user")
@Getter
@Setter
public class User implements UserDetails, Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  private String firstName;

  private String lastName;

  @Column(unique = true)
  private String username;

  @Column(unique = true)
  private String email;

  private String password;

  @Column(length = 20)
  private String resetKey;

  private Instant resetDate;

  private String photoName;

  @Column(columnDefinition = "boolean default false", nullable = false)
  private boolean active;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  @OneToMany(mappedBy = "user")
  private Set<Task> tasks;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Stream.of(role.getRoleName())
      .map(SimpleGrantedAuthority::new)
      .collect(Collectors.toList());
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
    return active;
  }

  public boolean hasRole(String roleName) {
    return this.role.getRoleName().equalsIgnoreCase(roleName);
  }
}
