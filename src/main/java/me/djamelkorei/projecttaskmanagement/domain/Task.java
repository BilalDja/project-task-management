package me.djamelkorei.projecttaskmanagement.domain;

import lombok.Getter;
import lombok.Setter;
import me.djamelkorei.projecttaskmanagement.domain.enumeration.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * Task entity.
 *
 * @author Djamel Eddine Korei
 */
@Entity(name = "task")
@Getter
@Setter
public class Task implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "task_id")
  private Long taskId;

  private String title;

  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private Status status;

  private Instant createdAt;

  private Instant completedAt;

  private Instant approvedAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

}
