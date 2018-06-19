package vn.quankundeptrai.banana.data.models.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TQN on 2/12/18.
 */

public class FeedbackRequest {
    @SerializedName("feedback")
    @Expose
    private String feedback;

    public FeedbackRequest(String feedback) {
        this.feedback = feedback;
    }
}
