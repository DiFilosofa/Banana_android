package vn.quankundeptrai.banana.interfaces;

/**
 * Created by TQN on 1/19/2018.
 */

public interface ITask<T> {
    void onPreTask();

    void onDone(T result);

    void onPostTask();
}
