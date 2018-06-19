package vn.quankundeptrai.banana.data.models.responses.googledirections;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.PolyUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TQN on 3/4/18.
 */

public class GoogleDirectionOverviewPolylines implements Serializable {
    @SerializedName("points")
    @Expose
    private String polylineEncode;

    public List<LatLng> getPolylineList() {
        return PolyUtil.decode(polylineEncode);
    }
}
