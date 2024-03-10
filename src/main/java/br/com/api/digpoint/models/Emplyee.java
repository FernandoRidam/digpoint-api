package br.com.api.digpoint.models;

import jakarta.persistence.*;

import java.io.Serializable;

import java.util.UUID;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Emplyee implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String name;
  private String code;

  @ManyToOne
  @JoinColumn(name = "company_id", nullable = false)
  @JsonIgnore
  private Company company;

  @OneToMany(mappedBy = "employee")
  private List<Point> points;

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public Company getCompany() {
    return company;
  }

  public List<Point> getPoints() {
    return points;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public void setPoints(List<Point> points) {
    this.points = points;
  }
}
