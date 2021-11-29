package com.crud.demo;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    
    @Autowired
    private UsersService service;

    @GetMapping("/Users")
    public List<Users> list(){
        return service.listAll();
    }

    @GetMapping("/Users/{id}")
    public ResponseEntity<Users> get(@PathVariable Integer id){
        try{
            Users user = service.get(id);
            return new ResponseEntity<Users>(user, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Users")
    public void add(@RequestBody Users user){
        service.save(user);
    }

    @PutMapping("/Users/{id}")
    public ResponseEntity<?> update(@RequestBody Users user,@PathVariable Integer id){
        try{
            Users oldUser = service.get(id);
            oldUser.setName(user.getName());
            oldUser.setNumber(user.getNumber());
            service.save(oldUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Users/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
