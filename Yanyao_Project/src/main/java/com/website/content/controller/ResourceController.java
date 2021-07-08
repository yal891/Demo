package com.website.content.controller;



import com.website.content.Service.ResourceService;
import com.website.content.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ResourceController {

    @Qualifier ("resourceServiceImpl")
    @Autowired
    private ResourceService service;


    @GetMapping("/resources")
    public List<Resource> list() {
        return service.findAll();
    }

    @PostMapping("/resources")
    public Resource create(@RequestBody Resource resource) {
        return service.save(resource);
    }

    @GetMapping("/resources/{id}")
    public Resource findById( @PathVariable("id") Integer id) {
        return service.findOneById(id);
    }


    @PutMapping("/updateResources")
    public Resource update( @RequestBody Resource resource) {
        return service.save(resource);
    }


    @DeleteMapping("/resources/{id}")
    public void deleteResource(@PathVariable("id") Integer id){
        Resource resource = service.findOneById(id);
        service.deleteById(id);
    }
}
