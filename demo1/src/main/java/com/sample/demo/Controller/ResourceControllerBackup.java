//
//
//package com.sample.demo.Controller;
//
//import com.sample.demo.Entity.Resource;
//import com.sample.demo.Entity.ResourcesDetails;
//import com.sample.demo.Service.*;
//
//import com.sample.demo.repository.ResourcesDetailRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//import javax.transaction.Transactional;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/resource")
//public class ResourceControllerBackup {
//
//    @Autowired
//    ResourceService resourceService;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    ProjectService projectService;
//
//    @Autowired
//    ProjectResourceService projectResourceService;
//
//    @Autowired
//    ResourceDetailService resourceDetailService;
////
////    @Autowired
////    ResourceDetailService detailService;
//
//    @Autowired
//    ResourcesDetailRepository repository;
//
//    //mapping for resource
//
//    @PostMapping("/addResource")
//    public Resource addResource(){//@RequestBody Resource resource){
//        Resource resource = new Resource();
//        resource.setLast_updated(new Timestamp(System.currentTimeMillis()));
//        resource.setTime_created(new Timestamp(System.currentTimeMillis()));
//        return resourceService.createResource(resource);
//    }
//
//    @GetMapping("/read")
//    public ResponseEntity<List<Object>> getAllResource(){
//        List<Resource> list1 = resourceService.getAllResource();
//        List<ResourcesDetails> list2 = resourceDetailService.getAllDetails();
//        List<Object> listEverything = new ArrayList<>();
//        listEverything.add(list1);
//        listEverything.add(list2);
//        return ResponseEntity.ok(listEverything);
//    }
//
//    @GetMapping("/getById/{id}")
//    public Resource getResourcesById(@PathVariable Integer id){
//        System.out.println(resourceService.getResourceById(id));
//        return resourceService.getResourceById(id);
//    }
//
//    @DeleteMapping("/deleteById/{id}")
//    public ResponseEntity<?> deleteResource(@PathVariable(value = "id") int id){
//
//        Resource resourceToDelete = resourceService.getResourceById(id);
//        if(resourceToDelete==null){
//            return new ResponseEntity<>("{\"error\":\"resource does not exist!\"}",HttpStatus.BAD_REQUEST);
//        }
//        resourceService.deleteResourceById(id);
//        return new ResponseEntity<>(resourceToDelete, HttpStatus.OK);
//    }
//
//
//}
