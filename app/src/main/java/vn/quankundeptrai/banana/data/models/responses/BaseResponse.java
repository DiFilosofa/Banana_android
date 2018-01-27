package vn.quankundeptrai.banana.data.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TQN on 1/19/2018.
 */

public class BaseResponse<T> {
    @SerializedName("success")
    @Expose
    private boolean isSuccess;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private T data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public T getData() {
        return data;
    }

    public String getMessage(){
        return message;
    }

    public int getCode(){
        return code;
    }
}
