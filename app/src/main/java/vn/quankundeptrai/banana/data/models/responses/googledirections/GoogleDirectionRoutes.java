package vn.quankundeptrai.banana.data.models.responses.googledirections;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TQN on 3/4/18.
 */

public class GoogleDirectionRoutes implements Serializable {
    @SerializedName("legs")
    @Expose
    private List<GoogleDirectionLegs> legs;


    @SerializedName("overview_polyline")
    @Expose
    private GoogleDirectionOverviewPolylines routes;

    public List<GoogleDirectionLegs> getLegs() {
        return legs;
    }

    public GoogleDirectionOverviewPolylines getRoutes() {
        return routes;
    }
}
