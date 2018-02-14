package vn.quankundeptrai.banana.data.models.other;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import vn.quankundeptrai.banana.enums.Density;
import vn.quankundeptrai.banana.enums.VehicleSpeed;

/**
 * Created by TQN on 2/13/18.
 */

public class Event {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("density")
    @Expose
    private Density density;

    @SerializedName("latitude")
    @Expose
    private float lat;

    @SerializedName("longitude")
    @Expose
    private float lng;

    @SerializedName("end_latitude")
    @Expose
    private float endLat;

    @SerializedName("end_longitude")
    @Expose
    private float endLng;

    @SerializedName("district")
    @Expose
    private int district;

    @SerializedName("has_flood")
    @Expose
    private boolean hasFlood;

    @SerializedName("has_rain")
    @Expose
    private boolean hasRain;

    @SerializedName("has_accident")
    @Expose
    private boolean hasAccident;

    @SerializedName("car_speed")
    @Expose
    private VehicleSpeed carSpeed;

    @SerializedName("motorbike_speed")
    @Expose
    private VehicleSpeed motorbikeSpeed;

    @SerializedName("Point")
    @Expose
    private EventPoint point;

//    @SerializedName("eventType")
//    @Expose
//    private int eventType;

    @SerializedName("created_at")
    @Expose
    private Date createdAt;

    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

    @SerializedName("should_travel")
    @Expose
    private boolean shouldTravel;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Density getDensity() {
        return density;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public float getEndLat() {
        return endLat;
    }

    public float getEndLng() {
        return endLng;
    }

    public int getDistrict() {
        return district;
    }

    public boolean hasFlood() {
        return hasFlood;
    }

    public boolean hasRain() {
        return hasRain;
    }

    public boolean hasAccident() {
        return hasAccident;
    }

    public EventPoint getPoint() {
        return point;
    }

    public VehicleSpeed getCarSpeed() {
        return carSpeed;
    }

    public VehicleSpeed getMotorbikeSpeed() {
        return motorbikeSpeed;
    }

//    public int getEventType() {
//        return eventType;
//    }

    public boolean shouldTravel() {
        return shouldTravel;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
