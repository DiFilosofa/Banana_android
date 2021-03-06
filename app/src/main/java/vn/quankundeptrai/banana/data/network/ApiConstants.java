package vn.quankundeptrai.banana.data.network;

/**
 * Created by TQN on 1/19/2018.
 */

public class ApiConstants {
    public static final String BASE_URL = "https://bananaserver.herokuapp.com/";

    public static final String LOGIN = BASE_URL + "user/login";

    public static final String SIGN_UP = BASE_URL + "user";

    public static final String AVATAR = BASE_URL + "user/avatar/{userId}";

    public static final String PROFILE = BASE_URL + "user/{userId}";

    public static final String FEEDBACK = BASE_URL + "feedback";

    public static final String POST_EVENT = BASE_URL + "events";
    public static final String EVENTS_IMG = POST_EVENT + "/media/{eventId}";

    public static final String GET_EVENTS = BASE_URL + "eventsAll/{id}";
    public static final String GET_EVENT_DETAIL = BASE_URL + "events/{eventId}";
    public static final String GET_LEADERBOARD_ALL = BASE_URL + "leaderboard";
    public static final String GET_LEADERBOARD_YEAR = BASE_URL + "leaderboard/year/{yearInMillis}";
    public static final String GET_LEADERBOARD_MONTH = BASE_URL + "leaderboard/month/{monthInMillis}";
    public static final String UPVOTE = BASE_URL+"events/upvote/{eventId}";
    public static final String DOWNVOTE = BASE_URL+"events/downvote/{eventId}";

    public static final String GGMAPS_DIRECTION = "https://maps.googleapis.com/maps/api/directions/json";
}
