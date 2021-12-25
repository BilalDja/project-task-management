package me.djamelkorei.projecttaskmanagement.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Role entity.
 *
 * @author Djamel Eddine Korei
 */
@Entity(name = "role")
@Getter
@Setter
public class Role implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long roleId;

  @Column(unique = true)
  private String roleName;

}
