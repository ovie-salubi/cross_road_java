/**
 *
 */
package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Ride;
import java.util.List;

/**
 * RideService for rides.
 *
 * @author crossover
 *
 */
public interface RideService {

    /**
     * 
     * @param ride
     * @return 
     */
    public Ride save(Ride ride);

    /**
     * 
     * @param rideId
     * @return 
     */
    public Ride findById(Long rideId);
    
    /**
     * 
     * @return 
     */
    public List<Ride> findAll();

}
