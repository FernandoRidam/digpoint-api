package br.com.api.digpoint.models;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.io.Serializable;

import java.util.UUID;
import java.util.Date;

@Entity
@Table(name = "point")
public class Point implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "date", nullable = false, updatable = false)
  @CreationTimestamp
  private Date date;

  @ManyToOne
  @JoinColumn(name = "employee_id", nullable = false)
  @JsonIgnore
  private Emplyee employee;

  public UUID getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public Emplyee getEmployee() {
    return employee;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setEmployee(Emplyee employee) {
    this.employee = employee;
  }
}
