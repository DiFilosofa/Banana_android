package vn.quankundeptrai.banana.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TQN on 1/30/18.
 */

public class LoginResponse{
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("_id")
    @Expose
    private String userId;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("address")
    @Expose
    private String address;

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getToken() {
        return token;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUserId() {
        return userId;
    }
}
