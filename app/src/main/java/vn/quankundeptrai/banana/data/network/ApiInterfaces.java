package vn.quankundeptrai.banana.data.network;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import vn.quankundeptrai.banana.data.models.requests.LogInRequest;
import vn.quankundeptrai.banana.data.models.responses.BaseResponse;
import vn.quankundeptrai.banana.data.models.responses.LoginResponse;

/**
 * Created by TQN on 1/19/2018.
 */

public interface ApiInterfaces {
    @POST(ApiConstants.LOGIN)
    Observable<BaseResponse<LoginResponse>> login(@Body LogInRequest logInRequest);
}
