package vn.quankundeptrai.banana.data.models.responses.googledirections;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by TQN on 3/4/18.
 */

public class GoogleDirectionDistance implements Serializable {
    @SerializedName("text")
    @Expose
    private String distance;

    public String getDistance() {
        return distance;
    }
}
