/**
 *
 */
package com.crossover.techtrial.service;

import java.util.List;
import com.crossover.techtrial.model.Person;

/**
 * PersonService interface for Persons.
 *
 * @author cossover
 *
 */
public interface PersonService {
    /**
     * 
     * @return 
     */
    public List<Person> getAll();

    /**
     * 
     * @param p
     * @return 
     */
    public Person save(Person p);

    /**
     * 
     * @param personId
     * @return 
     */
    public Person findById(Long personId);

}
