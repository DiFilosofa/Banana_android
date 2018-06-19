package vn.quankundeptrai.banana.data.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TQN on 1/30/18.
 */

public class LogInRequest {
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    public LogInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
