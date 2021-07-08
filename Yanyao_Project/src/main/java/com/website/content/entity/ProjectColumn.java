package com.website.content.entity;

import javax.persistence.*;

@Entity
@Table(name = "project_column")
public class ProjectColumn {
    public enum  ColumnType {
        number, text, formula;
    }
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int columnId;

    @Column(name = "project_column_name")
    private String columnName;

    @Column(name = "project_column_type")
    private ColumnType columnType;

    @Column(name = "project_formula_text")
    private String formulaText;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="resource_id")
    private Resource resource;


    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="project_id")
    private Project project;

    public ProjectColumn() {
    }

    public ProjectColumn(String columnName,
                          ColumnType columnType, String formulaText, Resource resource, Project project) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.formulaText = formulaText;
        this.resource = resource;
        this.project = project;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFormulaText() {
        return formulaText;
    }

    public void setFormulaText(String formulaText) {
        this.formulaText = formulaText;
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

