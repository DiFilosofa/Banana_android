package vn.quankundeptrai.banana.interfaces;

/**
 * Created by TQN on 2/9/18.
 */

public interface IJobListener<T> {
    void onComplete(T result);
}
