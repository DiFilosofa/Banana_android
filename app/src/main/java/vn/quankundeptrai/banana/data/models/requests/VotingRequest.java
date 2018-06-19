package vn.quankundeptrai.banana.data.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TQN on 2/25/18.
 */

public class VotingRequest {
    @SerializedName("userId")
    @Expose
    private String userId;

    public VotingRequest(String userId) {
        this.userId = userId;
    }
}
