package com.sample.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.demo.Entity.ProjectColumns;
import com.sample.demo.Entity.Resource;
import com.sample.demo.Entity.ResourcesDetails;
import com.sample.demo.Service.ProjectColumnService;
import com.sample.demo.Service.ResourceDetailService;
import com.sample.demo.Service.ResourceService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ProjectColumnService columnsService;
    @Autowired
    private ResourceDetailService resourceDetailsService;

    @GetMapping("/read")
    public ResponseEntity<?> read(){
        String body = resourceService.getAllJson();
        String columnDetails = columnsService.getColumnsJson(null);
        return new ResponseEntity<>(String.format("{\"columnInfo\":%s,\"tableDetail\":%s}", columnDetails,body), HttpStatus.OK);
    }

    @PostMapping("/addColumn")
    public ResponseEntity<?> addColumn(@RequestParam(name="columnName") String columnName){
        ProjectColumns columnToAdd = new ProjectColumns(columnName);
        boolean isSuccessful = columnsService.create(columnToAdd);
        if(isSuccessful){
            return new ResponseEntity<>(columnToAdd,HttpStatus.OK);
        }
        return new ResponseEntity<>("{\"error\":\"column name taken!\"}",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/deleteColumn")
    public ResponseEntity<?> deleteColumn(@RequestParam(name="columnName") String columnName){
        ProjectColumns columnToDelete = columnsService.get(null,columnName);
        boolean isSuccessful = columnsService.delete(columnToDelete);
        if(isSuccessful){
            return new ResponseEntity<>(columnToDelete,HttpStatus.OK);
        }
        return new ResponseEntity<>("{\"error\":\"column does not exist!\"}",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/updateColumn")
    public ResponseEntity<?> deleteColumn(@RequestParam(name="oldColumnName") String oldColumnName,@RequestParam(name = "newColumnName")String newColumnName){
        ProjectColumns columnToUpdate = columnsService.get(null,oldColumnName);
        boolean isSuccessful = columnsService.update(columnToUpdate,newColumnName);
        if(isSuccessful){
            return new ResponseEntity<>(columnToUpdate,HttpStatus.OK);
        }
        return new ResponseEntity<>("{\"error\":\"column does not exist!\"}",HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/addResource")
    public ResponseEntity<?> addResource(){
        Resource resourceToAdd = new Resource();
        resourceService.create(resourceToAdd);
        return new ResponseEntity<>(resourceToAdd, HttpStatus.OK);
    }

    @PostMapping("/deleteResource")
    public ResponseEntity<?> deleteResource(@RequestParam(name = "resourceId")Integer resourceId){
        Resource resourceToDelete = resourceService.get(resourceId);
        if(resourceToDelete==null){
            return new ResponseEntity<>("{\"error\":\"resource does not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        boolean isSuccessful = resourceService.delete(resourceToDelete);
        return new ResponseEntity<>(resourceToDelete, HttpStatus.OK);
    }

    @PostMapping("/setEntry")
    public ResponseEntity<?> setEntry(@RequestParam(name = "resourceId")Integer resourceId,
                                      @RequestParam(name = "columnId")Integer columnId,
                                      @RequestParam(name = "value") String value){
        ProjectColumns column = columnsService.get(columnId);
        if(column == null){
            return new ResponseEntity<>("{\"error\":\"column does not exist!\"}",HttpStatus.BAD_REQUEST);
        }
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
