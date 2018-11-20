/**
 *
 */
package com.crossover.techtrial.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.service.RideService;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * RideController for Ride related APIs.
 *
 * @author crossover
 *
 */
@RestController
public class RideController {

    @Autowired
    RideService rideService;

    /**
     *
     * @param ride
     * @return
     */
    @PostMapping(path = "/api/ride")
    public ResponseEntity<Ride> createNewRide(@RequestBody Ride ride) {
        return ResponseEntity.ok(rideService.save(ride));
    }

    /**
     *
     * @param rideId
     * @return
     */
    @GetMapping(path = "/api/ride/{ride-id}")
    public ResponseEntity<Ride> getRideById(@PathVariable(name = "ride-id", required = true) Long rideId) {
        Ride ride = rideService.findById(rideId);
        if (ride != null) {
            return ResponseEntity.ok(ride);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * This API returns the top 5 drivers with their email,name, total minutes,
     * maximum ride duration in minutes. Only rides that starts and ends within
     * the mentioned durations should be counted. Any rides where either start
     * or endtime is outside the search, should not be considered.
     *
     * DONT CHANGE METHOD SIGNATURE AND RETURN TYPES
     *
     * @param count
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping(path = "/api/top-rides")
    public ResponseEntity<List<TopDriverDTO>> getTopDriver(
            @RequestParam(value = "max", defaultValue = "5") Long count,
            @RequestParam(value = "startTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startTime,
            @RequestParam(value = "endTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endTime) {
        List<TopDriverDTO> topDrivers = new ArrayList<>();
        /**
         * Your Implementation Here. And Fill up topDrivers Arraylist with Top
         *
         */
        Long countParam = 5L;
        if (count == null || !Objects.equals(count, countParam)) {
            count = countParam;
        }

        List<Ride> allRides = rideService.findAll();
        Map<String, TopDriverDTO> driverSet = new HashMap();
        HashMap<String, Integer> driverRideCount = new HashMap();
        HashMap<String, Long> driverRideDistance = new HashMap();
        HashMap<String, Long> driverMaxRideDuration = new HashMap();
        TreeMap<Double, TopDriverDTO> sortedMap = new TreeMap<Double, TopDriverDTO>();
        //get drivers of selected rides
        TopDriverDTO topDriverDTO = null;
        //filter rides that satisfy are bound within the..
        //..startTime and endTime parameters  
        if (allRides != null && !allRides.isEmpty()) {
            int rideCount;
            long rideDistance;
            long rideDuration;
            for (Ride ride : allRides) {
                if (ride.getStartTime().isAfter(startTime)
                        && ride.getEndTime().isBefore(endTime)) {
                    long rideTimeInseconds = ride.getStartTime().until(ride.getEndTime(), ChronoUnit.SECONDS);
                    Person driver = ride.getDriver();
                    if (driverSet.get(driver.getEmail()) == null) {
                        topDriverDTO = new TopDriverDTO();
                        topDriverDTO.setEmail(driver.getEmail());
                        topDriverDTO.setName(driver.getName());
                        topDriverDTO.setTotalRideDurationInSeconds(topDriverDTO.getTotalRideDurationInSeconds() + rideTimeInseconds);
                        topDriverDTO.setEmail(driver.getEmail());
                        driverRideCount.put(driver.getEmail(), 1);
                        driverRideDistance.put(driver.getEmail(), ride.getDistance());
                        driverSet.put(driver.getEmail(), topDriverDTO);
                        driverMaxRideDuration.put(driver.getEmail(), rideTimeInseconds);
                    } else {
                        topDriverDTO = driverSet.get(driver.getEmail());
                        topDriverDTO.setTotalRideDurationInSeconds(topDriverDTO.getTotalRideDurationInSeconds() + rideTimeInseconds);
                        rideCount = driverRideCount.get(driver.getEmail());
                        driverRideCount.put(driver.getEmail(), ++rideCount);
                        rideDistance = driverRideDistance.get(driver.getEmail());
                        driverRideDistance.put(driver.getEmail(), ++rideDistance);
                        rideDuration = driverMaxRideDuration.get(driver.getEmail());
                        if (rideTimeInseconds > rideDuration) {
                            driverMaxRideDuration.put(driver.getEmail(), rideTimeInseconds);
                        }

                    }
                }
            }

            Collection<TopDriverDTO> driverCollection = driverSet.values();
            Iterator<TopDriverDTO> iter = driverCollection.iterator();

            while (iter.hasNext()) {
                topDriverDTO = iter.next();
                rideDistance = driverRideDistance.get(topDriverDTO.getEmail());
                rideCount = driverRideCount.get(topDriverDTO.getEmail());
                topDriverDTO.setAverageDistance(new Double(rideDistance / rideCount));
                topDriverDTO.setMaxRideDurationInSecods(driverMaxRideDuration.get(topDriverDTO.getEmail()));
                sortedMap.put(topDriverDTO.getAverageDistance(), topDriverDTO);
            }
            
            //get top 5...
            Iterator<Double> iterator = sortedMap.descendingKeySet().iterator();
            for(int i = 0; i < count; i++){
                topDrivers.add(sortedMap.get(iterator.next()));
            }

        }

        return ResponseEntity.ok(topDrivers);
    }
}
