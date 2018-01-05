package cn.devifish.dbframe.base;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public abstract class HoldView<T> extends BaseView {

    protected abstract void initData(T t);

}
