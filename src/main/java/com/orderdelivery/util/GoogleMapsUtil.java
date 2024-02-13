package com.orderdelivery.util;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.Distance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsUtil {
	@Value("${google.maps.api.key}")
    private String apiKey;

    public Distance getDistance(String origin, String destination) throws Exception {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        DirectionsResult directionsResult = DirectionsApi.getDirections(context, origin, destination).await();

        if (directionsResult.routes != null && directionsResult.routes.length > 0 &&
                directionsResult.routes[0].legs != null && directionsResult.routes[0].legs.length > 0) {
            return directionsResult.routes[0].legs[0].distance;
        } else {
            throw new RuntimeException("Unable to retrieve distance information from the API response");
        }
    }
    
    public static long getDistanceMock(String origin, String destination) throws Exception {
    	return 10;
    }
}
