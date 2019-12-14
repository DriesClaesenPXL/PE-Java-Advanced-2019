package city;

import common.DistanceFunction;

public class City implements DistanceFunction {
	private String name;
	private double latitude;
	private double longitude;

	public City(String name, double latitude, double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return name;
	}

    @Override
    public double distance(Object other) {
        double radTheta = Math.toRadians(this.longitude - other.longitude);
        double radLatitude = Math.toRadians(this.latitude);
        double radOtherLatitude = Math.toRadians(other.latitude);
        double dist = Math.sin(radLatitude) * Math.sin(radOtherLatitude) + Math.cos(radLatitude) * Math.cos(radOtherLatitude) * Math.cos(radTheta);
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515 * 1.609344;
    }


	/*
		Source: https://www.geodatasource.com/developers/java
	    Calculates the distance between 2 points when given latitude and longitude in decimal degrees

		double radTheta = Math.toRadians(longitude - other.longitude);
		double radLatitude = Math.toRadians(latitude);
		double radOtherLatitude = Math.toRadians(other.latitude);
		double dist = Math.sin(radLatitude) * Math.sin(radOtherLatitude) + Math.cos(radLatitude) * Math.cos(radOtherLatitude) * Math.cos(radTheta);
		dist = Math.acos(dist);
		dist = Math.toDegrees(dist);
		dist = dist * 60 * 1.1515 * 1.609344;
	 */
}
