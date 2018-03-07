package vn.quankundeptrai.banana.data.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleLatLng;

/**
 * Created by TQN on 3/7/18.
 */

public class PostEventRequest {
    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("name")
    @Expose
    private String eventName;

    @SerializedName("eventType")
    @Expose
    private int type = 1;

    @SerializedName("latitude")
    @Expose
    private Double lat;

    @SerializedName("longitude")
    @Expose
    private Double lng;

    @SerializedName("end_latitude")
    @Expose
    private Double endLat;

    @SerializedName("end_longitude")
    @Expose
    private Double endLng;

    @SerializedName("estimateLength")
    @Expose
    private float estimateLength;

    @SerializedName("density")
    @Expose
    private int density;

    @SerializedName("car_speed")
    @Expose
    private int carSpeed;

    @SerializedName("motorbike_speed")
    @Expose
    private int motorSpeed;

    @SerializedName("has_rain")
    @Expose
    private boolean hasRain;

    @SerializedName("has_flood")
    @Expose
    private boolean hasFlood;

    @SerializedName("has_accident")
    @Expose
    private boolean hasAccident;

    @SerializedName("has_police")
    @Expose
    private boolean hasPolice;

    @SerializedName("should_travel")
    @Expose
    private boolean shouldTravel;

    public PostEventRequest(String userId, String eventName, GoogleLatLng startLatLng, GoogleLatLng endLatLng, float estimateLength, int density, int carSpeed, int motorSpeed, boolean hasRain, boolean hasFlood, boolean hasAccident, boolean hasPolice, boolean shouldTravel) {
        this.userId = userId;
        this.eventName = eventName;
        this.lat = startLatLng.getLat();
        this.lng = startLatLng.getLng();
        this.endLat = endLatLng.getLat();
        this.endLng = endLatLng.getLng();
        this.estimateLength = estimateLength;
        this.density = density;
        this.carSpeed = carSpeed;
        this.motorSpeed = motorSpeed;
        this.hasRain = hasRain;
        this.hasFlood = hasFlood;
        this.hasAccident = hasAccident;
        this.hasPolice = hasPolice;
        this.shouldTravel = shouldTravel;
    }
}
