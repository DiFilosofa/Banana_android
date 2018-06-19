package vn.quankundeptrai.banana.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TQN on 2/13/18.
 */

public enum VehicleSpeed {
    @SerializedName("0")STABLE,
    @SerializedName("1")SLOW,
    @SerializedName("2")VERY_SLOW,
    @SerializedName("3")JAMMED
}
