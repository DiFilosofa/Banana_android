package vn.quankundeptrai.banana.interfaces;

/**
 * Created by TQN on 2/25/18.
 */

public interface IEventDataCallback {
    void onUpvoteClick(String eventId);
    void onDownvoteClick(String eventId);
    void onEventClick(String eventId);
}
