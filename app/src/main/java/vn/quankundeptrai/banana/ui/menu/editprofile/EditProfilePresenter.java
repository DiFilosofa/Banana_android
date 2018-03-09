package vn.quankundeptrai.banana.ui.menu.editprofile;

import android.graphics.Bitmap;

import java.io.File;

import vn.quankundeptrai.banana.data.ApiObservable;
import vn.quankundeptrai.banana.data.models.responses.UserResponse;
import vn.quankundeptrai.banana.enums.RxStatus;
import vn.quankundeptrai.banana.interfaces.ITask;
import vn.quankundeptrai.banana.ui.base.BasePresenter;

/**
 * Created by TQN on 3/9/18.
 */

public class EditProfilePresenter extends BasePresenter<EditProfileMvpView> {
    void updateProfile(String name, String phone, String address) {
        callApi(ApiObservable.updateProfile(name, phone, address), new ITask<RxStatus>() {
            @Override
            public void onPreTask() {

            }

            @Override
            public void onDone(RxStatus result) {
                getMvpView().onUpdateProfileSuccess();
            }

            @Override
            public void onPostTask() {

            }
        });
    }

    void uploadAvatar(File avatar, final String path) {
        callApi(ApiObservable.uploadAvatar(avatar), new ITask<RxStatus>() {
            @Override
            public void onPreTask() {

            }

            @Override
            public void onDone(RxStatus result) {
                getMvpView().onUpdateProfileSuccess();
            }

            @Override
            public void onPostTask() {

            }
        });
    }
}
