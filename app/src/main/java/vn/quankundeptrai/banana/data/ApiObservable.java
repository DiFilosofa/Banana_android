package vn.quankundeptrai.banana.data;

import android.content.Context;
import android.util.Pair;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.exceptions.ServerResponseThrowable;
import vn.quankundeptrai.banana.data.models.other.Event;
import vn.quankundeptrai.banana.data.models.other.User;
import vn.quankundeptrai.banana.data.models.requests.FeedbackRequest;
import vn.quankundeptrai.banana.data.models.requests.LogInRequest;
import vn.quankundeptrai.banana.data.models.requests.PostEventRequest;
import vn.quankundeptrai.banana.data.models.requests.SignupRequest;
import vn.quankundeptrai.banana.data.models.requests.VotingRequest;
import vn.quankundeptrai.banana.data.models.responses.BaseResponse;
import vn.quankundeptrai.banana.data.models.responses.LoginResponse;
import vn.quankundeptrai.banana.data.models.responses.UserResponse;
import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleDirectionResponse;
import vn.quankundeptrai.banana.data.models.responses.googledirections.GoogleLatLng;
import vn.quankundeptrai.banana.data.network.ApiInterfaces;
import vn.quankundeptrai.banana.data.network.RetrofitManager;
import vn.quankundeptrai.banana.enums.LeaderboardType;
import vn.quankundeptrai.banana.enums.RxStatus;

/**
 * Created by TQN on 1/30/18.
 */

public class ApiObservable {
    public static ApiInterfaces getInterface() {
        return RetrofitManager.getInstance().getRetrofit();
    }

    static <T> Observable<T> error(final Throwable throwable) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                e.onError(throwable);
            }
        });
    }

    private static Observable<RxStatus> saveUserToLocal(final LoginResponse data, final String email, final String password) {
        return Observable.fromCallable(new Callable<RxStatus>() {
            @Override
            public RxStatus call() throws Exception {
                CoreManager.getInstance().setUser(new User(data.getUserId(), data.getToken(), data.getNickname(), email, password, data.getPhone(), data.getAddress()));

                return RxStatus.Success;
            }
        });
    }

    public static Observable<RxStatus> login(final String email, final String password) {
        return getInterface().login(new LogInRequest(email, password))
                .flatMap(new Function<BaseResponse<LoginResponse>, Observable<RxStatus>>() {
                    @Override
                    public Observable<RxStatus> apply(BaseResponse<LoginResponse> response) throws Exception {
                        if (response.isSuccess()) {
                            return saveUserToLocal(response.getData(), email, password);
                        }
                        return error(new ServerResponseThrowable(response));
                    }
                });
    }

    public static Observable<RxStatus> signUpAndLogin(final String email, final String password, final String confirmPassword, final String nickname, final String address, final String phone) {
        return getInterface().register(new SignupRequest(email, password, confirmPassword, nickname, phone, address))
                .flatMap(new Function<BaseResponse<Object>, Observable<RxStatus>>() {
                    @Override
                    public Observable<RxStatus> apply(BaseResponse<Object> response) throws Exception {
                        if (response.isSuccess()) {
                            return login(email, password);
                        }
                        return error(new ServerResponseThrowable(response));
                    }
                });
    }

    public static Observable<BaseResponse<Object>> feedback(String feedback) {
        return getInterface().feedback(CoreManager.getInstance().getToken(), new FeedbackRequest(feedback));
    }

    public static Observable<BaseResponse<ArrayList<Event>>> getAllEvents() {
        User user = CoreManager.getInstance().getUser();
        return getInterface().getAllEvents(user == null ? "-1" : user.getId());
    }

    public static Observable<BaseResponse<Event>> getAnEvent(String eventId) {
        return getInterface().getAnEvent(eventId);
    }

    public static Observable<BaseResponse<ArrayList<UserResponse>>> getLeaderboard(long millis, LeaderboardType type) {
        switch (type) {
            case YEAR:
                return getInterface().getThisYearLeaderboard(CoreManager.getInstance().getToken(), millis);
            case MONTH:
                return getInterface().getThisMonthLeaderboard(CoreManager.getInstance().getToken(), millis);
            case ALL_TIME:
                return getInterface().getAllTimeLeaderboard(CoreManager.getInstance().getToken());
        }
        return null;
    }

    public static Observable<BaseResponse<Object>> upvote(String eventId) {
        CoreManager coreManager = CoreManager.getInstance();
        return getInterface().upvote(coreManager.getToken(), eventId, new VotingRequest(coreManager.getUser().getId()));
    }

    public static Observable<BaseResponse<Object>> downvote(String eventId) {
        CoreManager coreManager = CoreManager.getInstance();
        return getInterface().downvote(coreManager.getToken(), eventId, new VotingRequest(coreManager.getUser().getId()));
    }

    public static Observable<GoogleDirectionResponse> getEventPolyline(Context context, Pair<LatLng, LatLng> eventLocations) {
        return getInterface().getEventPolyline(
                String.format("%f,%f", eventLocations.first.latitude, eventLocations.first.longitude),
                String.format("%f,%f", eventLocations.second.latitude, eventLocations.second.longitude),
                false, context.getResources().getString(R.string.google_map_key));
    }

    public static Observable<RxStatus> postEvent(String eventName, GoogleLatLng startLatLng, GoogleLatLng endLatLng, Float distance, int density, int carSpeed, int motorSpeed, boolean hasRain, boolean hasFlood, boolean hasAccident, boolean hasPolice, boolean shouldTravel) {
        CoreManager coreManager = CoreManager.getInstance();
        return getInterface().postEvent(
                coreManager.getToken(),
                new PostEventRequest(
                        coreManager.getUser().getId(),
                        eventName, startLatLng, endLatLng, distance,
                        density, carSpeed, motorSpeed,
                        hasRain, hasFlood, hasAccident, hasPolice, shouldTravel
                )).flatMap(new Function<BaseResponse<Object>, ObservableSource<RxStatus>>() {
            @Override
            public ObservableSource<RxStatus> apply(BaseResponse<Object> response) throws Exception {
                if (response.isSuccess()) {
                    return Observable.fromCallable(new Callable<RxStatus>() {
                        @Override
                        public RxStatus call() throws Exception {
                            return RxStatus.Success;
                        }
                    });
                } else {
                    return Observable.fromCallable(new Callable<RxStatus>() {
                        @Override
                        public RxStatus call() throws Exception {
                            return RxStatus.Fail;
                        }
                    });
                }
            }
        });
    }
}
