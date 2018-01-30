package vn.quankundeptrai.banana.data;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import vn.quankundeptrai.banana.data.exceptions.ServerResponseThrowable;
import vn.quankundeptrai.banana.data.models.other.User;
import vn.quankundeptrai.banana.data.models.requests.LogInRequest;
import vn.quankundeptrai.banana.data.models.requests.SignupRequest;
import vn.quankundeptrai.banana.data.models.responses.BaseResponse;
import vn.quankundeptrai.banana.data.models.responses.LoginResponse;
import vn.quankundeptrai.banana.data.network.ApiInterfaces;
import vn.quankundeptrai.banana.data.network.RetrofitManager;
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
                CoreManager.getInstance().setUser(new User(data.getToken(), data.getNickname(), email, password, data.getPhone(), data.getAddress()));

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
}
