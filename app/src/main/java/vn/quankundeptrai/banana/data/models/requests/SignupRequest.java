package vn.quankundeptrai.banana.data.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TQN on 1/30/18.
 */

public class SignupRequest {
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("confirmPassword")
    @Expose
    private String confirmPassword;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("address")
    @Expose
    private String address;

    public SignupRequest(String email, String password, String confirmPassword, String nickname, String phone, String address) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.nickname = nickname;
        this.phone = phone;
        this.address = address;
    }
}
