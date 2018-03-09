package vn.quankundeptrai.banana.ui.menu.editprofile;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.data.models.other.User;
import vn.quankundeptrai.banana.ui.base.BaseActivity;

/**
 * Created by TQN on 3/9/18.
 */

public class EditProfileActivity extends BaseActivity<EditProfilePresenter> implements EditProfileMvpView, View.OnClickListener, IPickResult {
    private EditText name, phone, address;
    private TextView email, level;
    private CircleImageView avatar;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected String getScreenTitle() {
        return getString(R.string.your_account);
    }

    @Override
    protected EditProfilePresenter onCreatePresenter() {
        return new EditProfilePresenter();
    }

    @Override
    protected void initialView() {
        showLoading();
        email = mainView.findViewById(R.id.email);
        name = mainView.findViewById(R.id.nameInput);
        phone = mainView.findViewById(R.id.phoneInput);
        address = mainView.findViewById(R.id.addressInput);
        level = mainView.findViewById(R.id.userLevel);
        (avatar = mainView.findViewById(R.id.userImage)).setOnClickListener(this);
        mainView.findViewById(R.id.editProfileBtn).setOnClickListener(this);
        loadProfile();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editProfileBtn:
                if (allValidated()) {
                    showLoading();
                    getPresenter().updateProfile(name.getText().toString().trim(), phone.getText().toString().trim(), address.getText().toString().trim());
                }
                break;
            case R.id.userImage:
                PickImageDialog.build(new PickSetup().setWidth(300).setHeight(400)).show(this);
                break;
        }
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            showLoading();
            getPresenter().uploadAvatar(new File(r.getPath()));

        } else {
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean allValidated() {

        return true;
    }

    private void loadProfile() {
        User user = CoreManager.getInstance().getUser();
        email.setText(user.getEmail());
        name.setText(user.getNickname());
        phone.setText(user.getPhone());
        address.setText(user.getAddress());
        level.setText(String.format(getString(R.string.rankingDetail), user.getPointSum(), user.getLevel().toString()));
        if (user.getAvatar() != null && !user.getAvatar().isEmpty()) {
            int size = (int) getResources().getDimension(R.dimen.avatar_size);
            Picasso.with(this).load(user.getAvatar()).resize(size, size).centerCrop().into(avatar);
        }
        hideLoading();
    }

    @Override
    public void onUpdateProfileSuccess() {
        hideLoading();
        Toast.makeText(this, getString(R.string.profile_update_success), Toast.LENGTH_SHORT).show();
        finish();
    }
}
