package vn.quankundeptrai.banana.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import vn.quankundeptrai.banana.enums.Level;

/**
 * Created by TQN on 2/15/18.
 */

public class UserResponse {
    @SerializedName("nickname")
    @Expose
    private String name;

    @SerializedName("point_sum")
    @Expose
    private int point;

    @SerializedName("level")
    @Expose
    private Level level;

    @SerializedName("avatar")
    @Expose
    private String avatarSrc;

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public Level getLevel() {
        return level;
    }

    public String getAvatarSrc() {
        return avatarSrc;
    }
}
