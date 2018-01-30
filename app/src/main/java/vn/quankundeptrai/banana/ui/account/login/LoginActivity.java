package vn.quankundeptrai.banana.ui.account.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.customviews.general.LoadingView;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.data.models.other.User;
import vn.quankundeptrai.banana.enums.RxStatus;
import vn.quankundeptrai.banana.ui.account.signup.SignupActivity;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.main.MainActivity;
import vn.quankundeptrai.banana.utils.KeyboardUtils;
import vn.quankundeptrai.banana.utils.ValidationUtils;

/**
 * Created by TQN on 1/27/18.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginMvpView, View.OnClickListener {
    private EditText emailInput, passwordInput;
    private LoadingView loader;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initialView() {
        loader = mainView.findViewById(R.id.loginLoader);
        loader.reset();
        if (CoreManager.getInstance().isLogined(this)) {
            User user = CoreManager.getInstance().getUser();
            getPresenter().login(user.getEmail(), user.getPassword());
        } else {
            emailInput = mainView.findViewById(R.id.emailInput);
            passwordInput = mainView.findViewById(R.id.passwordInput);
            mainView.findViewById(R.id.loginBtn).setOnClickListener(this);
            mainView.findViewById(R.id.registerBtn).setOnClickListener(this);
            mainView.findViewById(R.id.forgotPasswordBtn).setOnClickListener(this);
            loader.loadComplete();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                KeyboardUtils.hideKeyboard(this);
                loader.reset();
                if (validate()) {
                    getPresenter().login(emailInput.getText().toString().trim(), passwordInput.getText().toString().trim());
                }
                break;
            case R.id.registerBtn:
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                break;
            case R.id.forgotPasswordBtn:

                break;
        }
    }

    private boolean validate() {
        if (!ValidationUtils.isValidEmail(emailInput.getText().toString())) {
            Toast.makeText(this, getBaseContext().getString(R.string.invalidEmail), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!ValidationUtils.isPasswordValid(passwordInput.getText().toString())) {
            Toast.makeText(this, getBaseContext().getString(R.string.invalidPassword), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onLoginAttempt(RxStatus status) {
        switch (status) {
            case Success:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case Fail:
                loader.loadComplete();
                Toast.makeText(this, getBaseContext().getString(R.string.error), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
