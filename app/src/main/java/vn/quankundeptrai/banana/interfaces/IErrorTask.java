package vn.quankundeptrai.banana.interfaces;

/**
 * Created by TQN on 1/19/2018.
 */

public interface IErrorTask<T> extends ITask<T>{
    boolean onError();
}
