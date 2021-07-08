package com.website.content.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "resource", uniqueConstraints = {@UniqueConstraint (columnNames = "resource_name")})
@EntityListeners(AuditingEntityListener.class)
public class Resource {

    @Id
    //@GeneratedValue (strategy = GenerationType.IDENTITY)
    private int resourceCode;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "editable")
    private boolean editable = false;


    @CreatedDate
    @Column (name = "time_created",updatable = false, nullable = false)
    private Date createTime;

    @LastModifiedDate
    @Column (name = "time_updated",nullable = false)
    private Date updateTime;


    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="resource",
            cascade = CascadeType.ALL)
    private List<ResourceDetail> ResourceDetail;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="resource",
            cascade = CascadeType.ALL)
    private List<ProjectToResource> ProjectToResource;

    public Resource() {
    }

    public Resource( int resourceCode, String resourceName, boolean editable,
                     Date createTime, Date updateTime) {
        this.resourceCode = resourceCode;
        this.resourceName = resourceName;
        this.editable = editable;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    public int getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(int resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<ResourceDetail> getResourceColumn() {
        return ResourceDetail;
    }

    public void setResourceColumn(List<ResourceDetail> resourceDetail) {
        this.ResourceDetail = resourceDetail;
    }

    public List<ProjectToResource> getProjectToResource() {
        return ProjectToResource;
    }

    public void setProjectToResource(List<ProjectToResource> projectToResource) {
        ProjectToResource = projectToResource;
    }
}
