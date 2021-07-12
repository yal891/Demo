package com.sample.demo.Controller;


import com.sample.demo.Entity.*;
import com.sample.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ResourceDetailService resourceDetailsService;
    @Autowired
    private ProjectColumnService columnsService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectResourceService projectToResourceService;

    private User getCurrentUser(Principal principal) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        return userService.findByUsername(authenticationToken.getName());
    }

    @PostMapping("/addProject")
    public ResponseEntity<?> addProject(Principal principal,
                                        @RequestParam(name="projectName") String projectName){
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(authenticationToken.getName());
        Project projectToAdd = new Project();
        projectToAdd.setProjectName(projectName);
        boolean isSuccessful = projectService.create(projectToAdd,user);
        if(!isSuccessful){
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when creating new project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectToAdd,HttpStatus.OK);
    }

    @PostMapping("/deleteProject")
    public ResponseEntity<?> deleteProject(Principal principal,
                                           @RequestParam(name="projectId") Integer projectId){
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(authenticationToken.getName());
        Project projectToDelete=projectService.get(projectId);
        boolean isSuccessful=projectService.delete(projectToDelete);
        if(!isSuccessful){
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when deleting project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectToDelete,HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<?> read(Principal principal,
                                  @RequestParam(name = "projectId")Integer projectId){
        //--------------user validation
        User user = getCurrentUser(principal);


        Project project = projectService.get(projectId);
        if(project == null){
            return new ResponseEntity<>("{\"error\":\"project not found!\"}", HttpStatus.BAD_REQUEST);
        }
        if(!user.getUserName().equals(project.getOwner().getUserName())){
            return new ResponseEntity<>("{\"error\":\"project does not belong to the user\"}",HttpStatus.BAD_REQUEST);
        }
        //--------------end of validation

        String body = projectService.toJson(projectId);
        String columnDetails = columnsService.getColumnsJson(project);
        return new ResponseEntity<>(String.format("{\"columnInfo\":%s,\"tableDetail\":%s}", columnDetails,body), HttpStatus.OK);
    }

    @PostMapping("/addResource")
    public ResponseEntity<?> addResource(Principal principal,
                                         @RequestParam(name = "projectId")Integer projectId,
                                         @RequestParam(name = "resourceId")Integer resourceId){
        //--------------user validation
        User user = getCurrentUser(principal);

        Project project = projectService.get(projectId);
        if(project == null){
            return new ResponseEntity<>("{\"error\":\"project not found!\"}",HttpStatus.BAD_REQUEST);
        }
        if(!user.getUserName().equals(project.getOwner().getUserName())){
            return new ResponseEntity<>("{\"error\":\"project does not belong to the user\"}",HttpStatus.BAD_REQUEST);
        }
        //--------------end of validation

        Resource resource = resourceService.get(resourceId);
        if(resource==null){
            return new ResponseEntity<>("{\"error\":\"resource does not exist!\"}",HttpStatus.BAD_REQUEST);
        }

        ProjectResources ptr = new ProjectResources();
        boolean isSuccessful = projectToResourceService.create(ptr,project,resource);
        if(!isSuccessful){
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when linking resource to current project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ptr,HttpStatus.OK);
    }

    @PostMapping("/deleteResource")
    public ResponseEntity<?> deleteResource(Principal principal,
                                            @RequestParam(name = "projectId")Integer projectId,
                                            @RequestParam(name = "resourceId")Integer resourceId){
        //--------------user validation
        User user = getCurrentUser(principal);

        Project project = projectService.get(projectId);
        if(project == null){
            return new ResponseEntity<>("{\"error\":\"project not found!\"}",HttpStatus.BAD_REQUEST);
        }
        if(!user.getUserName().equals(project.getOwner().getUserName())){
            return new ResponseEntity<>("{\"error\":\"project does not belong to the user\"}",HttpStatus.BAD_REQUEST);
        }
        //--------------end of validation

        Resource resource = resourceService.get(resourceId);
        if(resource==null){
            return new ResponseEntity<>("{\"error\":\"resource does not exist!\"}",HttpStatus.BAD_REQUEST);
        }

        ProjectResources ptr = projectToResourceService.get(project,resource);
        boolean isSuccessful = projectToResourceService.delete(ptr);
        if(!isSuccessful){
            return new ResponseEntity<>("{\"error\":\"sth wrong happens when linking resource to current project!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ptr,HttpStatus.OK);
    }

    @PostMapping("/addTemplate")
    public ResponseEntity<?> addTemplate(Principal principal,
                                         @RequestParam(name = "projectId")Integer projectId,
                                         @RequestParam(name="columnName") String columnName,
                                         @RequestParam(name="columnType") String columnType,
                                         @RequestParam(name="formula") String formula) {
        //--------------user validation
        User user = getCurrentUser(principal);

        Project project = projectService.get(projectId);
        if(project == null){
            return new ResponseEntity<>("{\"error\":\"project not found!\"}",HttpStatus.BAD_REQUEST);
        }
        if(!user.getUserName().equals(project.getOwner().getUserName())){
            return new ResponseEntity<>("{\"error\":\"project does not belong to the user\"}",HttpStatus.BAD_REQUEST);
        }
        //--------------end of validation


        //set type by columnType param
        Type type = columnType.equals("Number") ? Type.Number : (columnType.equals("Text") ? Type.Text : (columnType.equals("Formula") ? Type.Formula : null));
        if (type == null) {
            return new ResponseEntity<>("{\"error\":\"Type not recognized\"}", HttpStatus.BAD_REQUEST);
        }
        ProjectColumns columnToAdd = new ProjectColumns(columnName, type, formula);
        boolean isSuccessful = columnsService.create(columnToAdd, project);
        if (!isSuccessful) {
            return new ResponseEntity<>("{\"error\":\"Sth wrong happens when creating new column for current project! Check if column name is taken\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(columnToAdd, HttpStatus.OK);
    }

    @PostMapping("/deleteTemplate")
    public ResponseEntity<?> deleteTemplate(Principal principal,
                                            @RequestParam(name = "projectId")Integer projectId,
                                            @RequestParam(name="columnName") String columnName){
        //--------------user validation
        User user = getCurrentUser(principal);

        Project project = projectService.get(projectId);
        if(project == null){
            return new ResponseEntity<>("{\"error\":\"project not found!\"}",HttpStatus.BAD_REQUEST);
        }
        if(!user.getUserName().equals(project.getOwner().getUserName())){
            return new ResponseEntity<>("{\"error\":\"project does not belong to the user\"}",HttpStatus.BAD_REQUEST);
        }
        //--------------end of validation


        ProjectColumns columnToDelete = columnsService.get(project,columnName);
        if(columnToDelete==null){
            return new ResponseEntity<>("{\"error\":\"column not found!\"}", HttpStatus.BAD_REQUEST);
        }
        boolean isSuccessful = columnsService.delete(columnToDelete);
        if (!isSuccessful) {
            return new ResponseEntity<>("{\"error\":\"Sth wrong happens when deleting the column\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(columnToDelete, HttpStatus.OK);
    }

    @PostMapping("/updateTemplate")
    public ResponseEntity<?> updateTemplate(Principal principal,
                                            @RequestParam(name = "projectId")Integer projectId,
                                            @RequestParam(name="columnName") String columnName,
                                            @RequestParam(name="columnType") String columnType,
                                            @RequestParam(name="formula") String formula) {
        //--------------user validation
        User user = getCurrentUser(principal);

        Project project = projectService.get(projectId);
        if(project == null){
            return new ResponseEntity<>("{\"error\":\"project not found!\"}",HttpStatus.BAD_REQUEST);
        }
        if(!user.getUserName().equals(project.getOwner().getUserName())){
            return new ResponseEntity<>("{\"error\":\"project does not belong to the user\"}",HttpStatus.BAD_REQUEST);
        }
        //--------------end of validation


        //set type by columnType param
        Type type = columnType.equals("Number") ? Type.Number : (columnType.equals("Text") ? Type.Text : (columnType.equals("Formula") ? Type.Formula : null));
        if (type == null) {
            return new ResponseEntity<>("{\"error\":\"Type not recognized\"}", HttpStatus.BAD_REQUEST);
        }
        //get the column
        ProjectColumns columnToUpdate = columnsService.get(project,columnName);
        if(columnToUpdate==null){
            return new ResponseEntity<>("{\"error\":\"column not found!\"}", HttpStatus.BAD_REQUEST);
        }
        //update info
        columnToUpdate.setType(type);
        columnToUpdate.setFormula_text(formula);
        //do update
        boolean isSuccessful = columnsService.update(columnToUpdate,columnName);
        if (!isSuccessful) {
            return new ResponseEntity<>("{\"error\":\"Sth wrong happens when creating new column for current project! Check if column name is taken\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(columnToUpdate, HttpStatus.OK);
    }

    @PostMapping("/setEntry")
    public ResponseEntity<?> setEntry(Principal principal,
                                      @RequestParam(name = "resourceId")Integer resourceId,
                                      @RequestParam(name = "columnId")Integer columnId,
                                      @RequestParam(name = "value") String value){
        ProjectColumns column = columnsService.get(columnId);
        if(column == null){
            return new ResponseEntity<>("{\"error\":\"column does not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        //user validation


        User user = getCurrentUser(principal);

        Project project = column.getProject();
        if (project!=null && !user.getUserName().equals(project.getOwner().getUserName())) {
            return new ResponseEntity<>("{\"error\":\"project does not belong to the user\"}", HttpStatus.BAD_REQUEST);
        }

        //

        Resource resource = resourceService.get(resourceId);
        if(resource==null){
            return new ResponseEntity<>("{\"error\":\"resource does not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        ResourcesDetails rd = resourceDetailsService.get(resource,column);
        if(rd==null){
            rd = new ResourcesDetails();
            rd.setColumn_value(value);
            resourceDetailsService.create(rd,resource,column);
            return new ResponseEntity<>(rd, HttpStatus.OK);
        }
        rd.setColumn_value(value);
        boolean isSuccessful = resourceDetailsService.update(rd);
        if(isSuccessful){
            return new ResponseEntity<>(rd,HttpStatus.OK);
        }
        return new ResponseEntity<>("{\"error\":\"sth wrong happens:(\"}",HttpStatus.BAD_REQUEST);
    }

}
