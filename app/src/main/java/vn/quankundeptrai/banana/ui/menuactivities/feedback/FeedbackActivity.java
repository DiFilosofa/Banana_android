package vn.quankundeptrai.banana.ui.menuactivities.feedback;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import vn.quankundeptrai.banana.R;
import vn.quankundeptrai.banana.ui.base.BaseActivity;
import vn.quankundeptrai.banana.ui.menuactivities.help.HelpPresenter;

/**
 * Created by TQN on 1/31/18.
 */

public class FeedbackActivity extends BaseActivity<FeedbackPresenter> implements FeedbackMvpView, View.OnClickListener {
    private EditText feedback;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_feedback;
    }

    @Override
    protected String getScreenTitle() {
        return getString(R.string.title_feedback);
    }

    @Override
    protected FeedbackPresenter onCreatePresenter() {
        return new FeedbackPresenter();
    }

    @Override
    protected void initialView() {
        feedback = mainView.findViewById(R.id.feedbackInput);
        mainView.findViewById(R.id.feedbackSend).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.feedbackSend:
                String feedbackText = feedback.getText().toString().trim();
                if(feedbackText.isEmpty()){
                    Toast.makeText(this, getString(R.string.empty_feedback), Toast.LENGTH_SHORT).show();
                    break;
                }
                getPresenter().feedback(feedbackText);
                break;
        }
    }

    @Override
    public void onFeedbackSuccess() {
        Toast.makeText(this, getString(R.string.feedback_thanks), Toast.LENGTH_SHORT).show();
    }
}
