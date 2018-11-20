/**
 *
 */
package com.crossover.techtrial.dto;

/**
 * @author crossover
 *
 */
public class TopDriverDTO {

    private String name;

    private String email;

    private Long totalRideDurationInSeconds;

    private Long maxRideDurationInSecods;

    private Double averageDistance;

    /**
     * Constructor for TopDriverDTO
     *
     * @param name
     * @param email
     * @param totalRideDurationInSeconds
     * @param maxRideDurationInSecods
     * @param averageDistance
     */
    public TopDriverDTO(String name, String email, Long totalRideDurationInSeconds, Long maxRideDurationInSecods, Double averageDistance) {
        this.name = name;
        this.email = email;
        this.averageDistance = averageDistance;
        this.maxRideDurationInSecods = maxRideDurationInSecods;
        this.totalRideDurationInSeconds = totalRideDurationInSeconds;

    }

    public TopDriverDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTotalRideDurationInSeconds() {
        return totalRideDurationInSeconds;
    }

    public void setTotalRideDurationInSeconds(Long totalRideDurationInSeconds) {
        this.totalRideDurationInSeconds = totalRideDurationInSeconds;
    }

    public Long getMaxRideDurationInSecods() {
        return maxRideDurationInSecods;
    }

    public void setMaxRideDurationInSecods(Long maxRideDurationInSecods) {
        this.maxRideDurationInSecods = maxRideDurationInSecods;
    }

    public Double getAverageDistance() {
        return averageDistance;
    }

    public void setAverageDistance(Double averageDistance) {
        this.averageDistance = averageDistance;
    }

}
