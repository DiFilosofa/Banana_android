package vn.quankundeptrai.banana.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TQN on 2/13/18.
 */

public enum Density {
    @SerializedName("0")FREE,
    @SerializedName("1")NORMAL,
    @SerializedName("2")QUITE_CROWDED,
    @SerializedName("3")CROWDED,
    @SerializedName("4")VERY_CROWDED
}
