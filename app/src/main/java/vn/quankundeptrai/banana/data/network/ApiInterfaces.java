package vn.quankundeptrai.banana.data.network;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.data.models.requests.FeedbackRequest;
import vn.quankundeptrai.banana.data.models.requests.LogInRequest;
import vn.quankundeptrai.banana.data.models.requests.SignupRequest;
import vn.quankundeptrai.banana.data.models.requests.VotingRequest;
import vn.quankundeptrai.banana.data.models.responses.BaseResponse;
import vn.quankundeptrai.banana.data.models.responses.LoginResponse;
import vn.quankundeptrai.banana.data.models.responses.UserResponse;

/**
 * Created by TQN on 1/19/2018.
 */

public interface ApiInterfaces {
    @POST(ApiConstants.LOGIN)
    Observable<BaseResponse<LoginResponse>> login(
            @Body LogInRequest logInRequest);

    @POST(ApiConstants.SIGN_UP)
    Observable<BaseResponse<Object>> register(
            @Body SignupRequest signupRequest);

    @POST(ApiConstants.FEEDBACK)
    Observable<BaseResponse<Object>> feedback(
            @Header("Authorization") String token,
            @Body FeedbackRequest feedbackRequest);

    @GET(ApiConstants.GET_EVENTS)
    Observable<BaseResponse<ArrayList<Event>>> getAllEvents(
            @Path("id") String userId);

    @GET(ApiConstants.GET_EVENT_DETAIL)
    Observable<BaseResponse<Event>> getAnEvent(
            @Path("eventId") String eventId);

    @GET(ApiConstants.GET_LEADERBOARD_ALL)
    Observable<BaseResponse<ArrayList<UserResponse>>> getAllTimeLeaderboard(
            @Header("Authorization") String token);

    @GET(ApiConstants.GET_LEADERBOARD_YEAR)
    Observable<BaseResponse<ArrayList<UserResponse>>> getThisYearLeaderboard(
            @Header("Authorization") String token,
            @Path("yearInMillis") long year);

    @GET(ApiConstants.GET_LEADERBOARD_MONTH)
    Observable<BaseResponse<ArrayList<UserResponse>>> getThisMonthLeaderboard(
            @Header("Authorization") String token,
            @Path("monthInMillis") long month);

    @POST(ApiConstants.UPVOTE)
    Observable<BaseResponse<Object>> upvote(
            @Header("Authorization") String token,
            @Path("eventId") String eventId,
            @Body VotingRequest getVotingRequest);

    @POST(ApiConstants.DOWNVOTE)
    Observable<BaseResponse<Object>> downvote(
            @Header("Authorization") String token,
            @Path("eventId") String eventId,
            @Body VotingRequest votingRequest);
}
