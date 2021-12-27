package me.djamelkorei.projecttaskmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import me.djamelkorei.projecttaskmanagement.domain.enumeration.Priority;
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
  @JsonIgnore
  private Status status;

  @Enumerated(EnumType.STRING)
  @Column(name = "priority")
  @JsonIgnore
  private Priority priority;

  @JsonIgnore
  private Instant createdAt;
  @JsonIgnore
  private Instant completedAt;
  @JsonIgnore
  private Instant approvedAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;

}
