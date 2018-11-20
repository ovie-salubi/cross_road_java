/**
 *
 */
package com.crossover.techtrial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.service.PersonService;

/**
 *
 * @author crossover
 */
@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * 
     * @param p
     * @return 
     */
    @PostMapping(path = "/api/person")
    public ResponseEntity<Person> register(@RequestBody Person p) {
        return ResponseEntity.ok(personService.save(p));
    }

    /**
     * 
     * @return 
     */
    @GetMapping(path = "/api/person")
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.getAll());
    }

    /**
     * 
     * @param personId
     * @return 
     */
    @GetMapping(path = "/api/person/{perso-id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(name = "person-id", required = true) Long personId) {
        Person person = personService.findById(personId);
        if (person != null) {
            return ResponseEntity.ok(person);
        }
        return ResponseEntity.notFound().build();
    }

}
