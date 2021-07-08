package com.website.content.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "resource_detail")
public class ResourceDetail<itar> {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private int columnId;

  @Column(name = "resource_detail_value")
  private String columnValue;

  @CreatedDate
  @Column (name = "time_created",updatable = false, nullable = false)
  private Date time_created;

  @LastModifiedDate
  @Column (name = "time_updated",nullable = false)
  private Date time_updated;

  @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
          CascadeType.DETACH, CascadeType.REFRESH})
  @JoinColumn(name="resource_id")
  private Resource resource;


  @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
          CascadeType.DETACH, CascadeType.REFRESH})
  @JoinColumn(name="project_id")
  private Project project;

  public ResourceDetail() {
  }

  public ResourceDetail(String columnValue, Date time_created, Date time_updated,
                        Resource resource, Project project) {

    this.columnValue = columnValue;
    this.time_created = time_created;
    this.time_updated = time_updated;
    this.resource = resource;
    this.project = project;
  }

  public int getColumnId() {
    return columnId;
  }

  public void setColumnId(int columnId) {
    this.columnId = columnId;
  }


  public String getColumnValue() {
    return columnValue;
  }

  public void setColumnValue(String columnValue) {
    this.columnValue = columnValue;
  }

  public Date getTime_created() {
    return time_created;
  }

  public void setTime_created(Date time_created) {
    this.time_created = time_created;
  }

  public Date getTime_updated() {
    return time_updated;
  }

  public void setTime_updated(Date time_updated) {
    this.time_updated = time_updated;
  }
  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

}
