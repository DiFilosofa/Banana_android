package vn.quankundeptrai.banana.data.network;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.constants.AppConstants;
import vn.quankundeptrai.banana.data.constants.FormatConstants;
import vn.quankundeptrai.banana.data.exceptions.ServerResponseThrowable;
import vn.quankundeptrai.banana.data.models.responses.BaseResponse;
import vn.quankundeptrai.banana.interfaces.IErrorTask;
import vn.quankundeptrai.banana.interfaces.ITask;
import vn.quankundeptrai.banana.ui.base.BaseActivity;

/**
 * Created by TQN on 1/19/2018.
 */

public class RetrofitManager {
    private static RetrofitManager retrofitManager;
    private ApiInterfaces retrofit;

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager();
        }

        return retrofitManager;
    }

    public ApiInterfaces getRetrofit() {
        if (retrofit == null) {
            retrofit = initRetrofit(ApiConstants.BASE_URL);
        }

        return retrofit;
    }

    public ApiInterfaces getRetrofit(String customRootUrl) {
        return initRetrofit(customRootUrl);
    }

    private ApiInterfaces initRetrofit(String customRootUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(AppConstants.API_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(AppConstants.API_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(AppConstants.API_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(customRootUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setDateFormat(FormatConstants.SERVER_FORMAT)
                        .setLenient()
                        .excludeFieldsWithoutExposeAnnotation().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(ApiInterfaces.class);
    }

    public <T> Disposable callApi(final BaseActivity activity, Observable<T> observable, final ITask<T> task) {
        if (task != null) {
            task.onPreTask();
        }
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (task != null) {
                            task.onPostTask();
                        }
                    }
                })
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(@NonNull T result) throws Exception {
                        if (task != null) {
                            task.onDone(result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (task instanceof IErrorTask && ((IErrorTask) task).onError()) {
                            return;
                        }

                        error(activity, throwable);
                    }
                });
    }

    public <T> Disposable callBaseApi(final BaseActivity activity, Observable<BaseResponse<T>> observable, final ITask<T> task) {
        if (task != null) {
            task.onPreTask();
        }
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (task != null) {
                            task.onPostTask();
                        }
                    }
                })
                .subscribe(new Consumer<BaseResponse<T>>() {
                    @Override
                    public void accept(@NonNull BaseResponse<T> tBaseResponse) throws Exception {
                        if (tBaseResponse.isSuccess()) {
                            if (task != null) {
                                task.onDone(tBaseResponse.getData());
                            }
                        } else {
                            error(activity, new ServerResponseThrowable(tBaseResponse));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (task instanceof IErrorTask && ((IErrorTask) task).onError()) {
                            return;
                        }

                        error(activity, throwable);
                    }
                });
    }

    public void error(BaseActivity activity, Throwable throwable) {
        if (throwable != null) {
            if (throwable instanceof ServerResponseThrowable) {
                ServerResponseThrowable serverResponseThrowable = (ServerResponseThrowable) throwable;
                if (serverResponseThrowable.isUnauthorized()) {
//                    CoreManager.getInstance().logOut();
//                    Intent intent = new Intent(activity, SplashActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    activity.startActivity(intent);
//                    activity.finish();
                    ///Go back to start screen
                } else {
                    activity.hideLoading();
                    Toast.makeText(activity, serverResponseThrowable.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.e("Error", Log.getStackTraceString(throwable));
                activity.hideLoading();
                Toast.makeText(activity, activity.getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
        } else {
            activity.hideLoading();
            Toast.makeText(activity, activity.getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }

}
