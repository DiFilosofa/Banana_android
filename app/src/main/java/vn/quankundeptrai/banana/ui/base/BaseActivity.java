package vn.quankundeptrai.banana.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.data.CoreManager;
import vn.quankundeptrai.banana.data.constants.ExtraKeys;
import vn.quankundeptrai.banana.interfaces.IPermissionCallback;
import vn.quankundeptrai.banana.utils.KeyboardUtils;

/**
 * Created by TQN on 1/19/2018.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{
    protected View mainView;
    private RelativeLayout mainContent;
    private T presenter;
    private IPermissionCallback permissionCallback;
    private ProgressDialog ringProgressDialog;


    protected abstract int getLayoutResource();

    protected abstract String getScreenTitle();

    protected abstract T onCreatePresenter();

    protected abstract void initialView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CoreManager.getInstance().setCurrentActivity(this);
        setContentView(R.layout.activity_base);

        String title = getScreenTitle();
        if (title != null) {
            setTitle(getScreenTitle());
            findViewById(R.id.headerLeftBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onToolbarLeftButtonClick();
                }
            });
        } else {
            destroyHeader();
        }

        mainView = getLayoutInflater().inflate(getLayoutResource(), null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mainView.setLayoutParams(layoutParams);

        mainContent = findViewById(R.id.mainContent);
        mainContent.addView(mainView);

        if (getPresenter() != null) {
            getPresenter().attachView((BaseMvpView) this);
        }
        initialView();
    }

    protected void setTitle(String title) {
        ((TextView) findViewById(R.id.headerTitle)).setText(title);
    }

    protected void onToolbarLeftButtonClick() {
        setResult(RESULT_CANCELED);
        finish();
    }

    protected void setLeftHeaderIcon(int resourceId){
        ((ImageView)mainView.findViewById(R.id.headerLeftBtn)).setImageResource(resourceId);
    }

    public void destroyHeader(){
        ((ViewGroup) findViewById(R.id.root)).removeView(findViewById(R.id.header));
    }

    public void hideHeader() {
        findViewById(R.id.header).setVisibility(View.GONE);
        findViewById(R.id.headerShadow).setVisibility(View.GONE);
    }

    public void showHeader(){
        findViewById(R.id.header).setVisibility(View.VISIBLE);
        findViewById(R.id.headerShadow).setVisibility(View.VISIBLE);
    }

    public T getPresenter() {
        if (presenter == null) {
            presenter = onCreatePresenter();
        }

        return presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyboardUtils.hideKeyboard(this);
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }

    protected void result() {
        setResult(RESULT_OK);
        finish();
    }

    protected <T> void result(T data) {
        Bundle bundle = new Bundle();
        if (data instanceof Boolean) {
            bundle.putBoolean(ExtraKeys.DATA, (Boolean) data);
        } else if (data instanceof String) {
            bundle.putString(ExtraKeys.DATA, (String) data);
        } else if (data instanceof Long) {
            bundle.putLong(ExtraKeys.DATA, (Long) data);
        } else if (data instanceof Integer) {
            bundle.putInt(ExtraKeys.DATA, (Integer) data);
        }

        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CoreManager.getInstance().setCurrentActivity(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (permissionCallback != null) {
            permissionCallback.onRequest(requestCode, grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED);
        }

        permissionCallback = null;
    }

    public void setPermissionCallback(IPermissionCallback permissionCallback) {
        this.permissionCallback = permissionCallback;
    }

    public void showLoading() {
        showLoading(this.getString(R.string.loading));
    }

    public void showLoading(String msg) {
        if (ringProgressDialog == null) {
            ringProgressDialog = ProgressDialog.show(this, "", msg, true);
            ringProgressDialog.setCancelable(false);
            ringProgressDialog.setCanceledOnTouchOutside(false);
        }
    }

    public void hideLoading() {
        if (ringProgressDialog != null && ringProgressDialog.isShowing()) {
            ringProgressDialog.dismiss();
            ringProgressDialog = null;
        }
    }

    public void startBaseActivityWithExtra(Context context, Class<?> baseActivity, String extraName, Object extra){
        if (extra instanceof Integer){
            startActivity((new Intent(context, baseActivity)).putExtra(extraName, (int)extra));
            return;
        }

        if (extra instanceof String){
            startActivity((new Intent(context, baseActivity)).putExtra(extraName, (String)extra));
            return;
        }

        if (extra instanceof Float){
            startActivity((new Intent(context, baseActivity)).putExtra(extraName, (float) extra));
            return;
        }

        if (extra instanceof Boolean){
            startActivity((new Intent(context, baseActivity)).putExtra(extraName, (boolean) extra));
            return;
        }

        if (extra instanceof Serializable){
            startActivity((new Intent(context, baseActivity)).putExtra(extraName, (Serializable) extra));
            return;
        }
    }
}
