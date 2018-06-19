package vn.quankundeptrai.banana.data.models.other;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TQN on 2/13/18.
 */

public class EventPoint {
    @SerializedName("upvotes")
    @Expose
    private int upvoteCount;

    @SerializedName("downvotes")
    @Expose
    private int downvoteCount;

    @SerializedName("DownvoteUsers")
    @Expose
    private List<String> downvoteList;

    @SerializedName("UpvoteUsers")
    @Expose
    private List<String> upvoteList;

    public int getUpvoteCount() {
        return upvoteCount;
    }

    public int getDownvoteCount() {
        return downvoteCount;
    }

    public int getPointSum() {
        return upvoteCount - downvoteCount;
    }

    public List<String> getDownvoteList() {
        return downvoteList;
    }

    public List<String> getUpvoteList() {
        return upvoteList;
    }
}