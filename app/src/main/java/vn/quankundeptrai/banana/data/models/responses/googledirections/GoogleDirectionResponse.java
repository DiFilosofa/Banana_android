package vn.quankundeptrai.banana.data.models.responses.googledirections;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleDirectionRoutes;

/**
 * Created by TQN on 3/4/18.
 */

public class GoogleDirectionResponse {
    @SerializedName("routes")
    @Expose
    private List<GoogleDirectionRoutes> routes;

    public List<GoogleDirectionRoutes> getRoutes() {
        return routes;
    }
}
