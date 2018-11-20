/**
 *
 */
package com.crossover.techtrial.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.RideRepository;
import java.util.List;

/**
 * @author crossover
 *
 */
@Service
public class RideServiceImpl implements RideService {

    @Autowired
    RideRepository rideRepository;

    /**
     * 
     * @param ride
     * @return 
     */
    @Override
    public Ride save(Ride ride) {
        return rideRepository.save(ride);
    }

    /**
     * 
     * @param rideId
     * @return 
     */
    @Override
    public Ride findById(Long rideId) {
        Optional<Ride> optionalRide = rideRepository.findById(rideId);
        if (optionalRide.isPresent()) {
            return optionalRide.get();
        } else {
            return null;
        }
    }

    /**
     * 
     * @return 
     */
    @Override
    public List<Ride> findAll() {
        return (List<Ride>) rideRepository.findAll();
    }

}
