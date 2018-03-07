package vn.quankundeptrai.banana.data.models.responses.googledirections;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TQN on 3/4/18.
 */

public class GoogleDirectionLegs implements Serializable {
    @SerializedName("distance")
    @Expose
    private GoogleDirectionDistance distance;

    @SerializedName("steps")
    @Expose
    private List<Object> steps;

    @SerializedName("start_address")
    @Expose
    private String startAddress;

    @SerializedName("start_location")
    @Expose
    private GoogleLatLng startLocation;

    @SerializedName("end_location")
    @Expose
    private GoogleLatLng endLocation;

    @SerializedName("end_address")
    @Expose
    private String endAddress;

    public GoogleDirectionDistance getDistance() {
        return distance;
    }

    public List<Object> getSteps() {
        return steps;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public GoogleLatLng getStartLocation() {
        return startLocation;
    }

    public GoogleLatLng getEndLocation() {
        return endLocation;
    }
}
