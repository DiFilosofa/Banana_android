package vn.quankundeptrai.banana.data.network;

/**
 * Created by TQN on 1/19/2018.
 */

public class ApiConstants {
    public static final String BASE_URL = "https://bananaserver.herokuapp.com/";

    public static final String LOGIN = BASE_URL + "user/login";

    public static final String SIGN_UP = BASE_URL + "user";

    public static final String FEEDBACK = BASE_URL + "feedback";

    public static final String GET_EVENTS = BASE_URL + "eventsAll/{id}";
    public static final String GET_EVENT_DETAIL = BASE_URL + "events/{eventId}";
}
