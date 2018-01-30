package vn.quankundeptrai.banana.ui.account.signup;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.customviews.general.LoadingView;
import vn.quankundeptrai.banana.enums.RxStatus;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.main.MainActivity;
import vn.quankundeptrai.banana.utils.KeyboardUtils;
import vn.quankundeptrai.banana.utils.ValidationUtils;

/**
 * Created by TQN on 1/27/18.
 */

public class SignupActivity extends BaseActivity<SignupPresenter> implements SignupMvpView, View.OnClickListener {
    private EditText emailInput, passwordInput, confirmPasswordInput, nicknameInput, addressInput, phoneInput;
    private LoadingView loader;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected SignupPresenter onCreatePresenter() {
        return new SignupPresenter();
    }

    @Override
    protected void initialView() {
        loader = mainView.findViewById(R.id.loader);
        emailInput = mainView.findViewById(R.id.emailInput);
        passwordInput = mainView.findViewById(R.id.passwordInput);
        confirmPasswordInput = mainView.findViewById(R.id.passwordConfirmInput);
        nicknameInput = mainView.findViewById(R.id.nicknameInput);
        addressInput = mainView.findViewById(R.id.addressInput);
        phoneInput = mainView.findViewById(R.id.phoneInput);
        mainView.findViewById(R.id.exitBtn).setOnClickListener(this);
        mainView.findViewById(R.id.registerBtn).setOnClickListener(this);
        loader.loadComplete();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exitBtn:
                finish();
                break;
            case R.id.registerBtn:
                if (validate()) {
                    KeyboardUtils.hideKeyboard(this);
                    loader.reset();
                    getPresenter().signup(
                            emailInput.getText().toString().trim(),
                            passwordInput.getText().toString().trim(),
                            confirmPasswordInput.getText().toString().trim(),
                            nicknameInput.getText().toString().trim(),
                            addressInput.getText().toString().trim(),
                            phoneInput.getText().toString().trim()
                    );
                }
                break;
        }
    }

    private boolean validate() {
        if (!ValidationUtils.isValidEmail(emailInput.getText().toString().trim())) {
            Toast.makeText(this, getBaseContext().getString(R.string.invalidEmail), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!ValidationUtils.isMatch(passwordInput.getText().toString().trim(), confirmPasswordInput.getText().toString().trim())) {
            Toast.makeText(this, getBaseContext().getString(R.string.unmatchPasswords), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!ValidationUtils.isPasswordValid(passwordInput.getText().toString().trim())) {
            Toast.makeText(this, getBaseContext().getString(R.string.password), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onRegisterAttempt(RxStatus result) {
        switch (result) {
            case Success:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                ActivityCompat.finishAffinity(this);
                startActivity(intent);
                break;

            case Fail:
                loader.loadComplete();
                Toast.makeText(this, getBaseContext().getString(R.string.error), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
