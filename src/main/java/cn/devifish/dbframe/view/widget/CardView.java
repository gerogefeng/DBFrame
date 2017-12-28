package cn.devifish.dbframe.view.widget;

import cn.devifish.dbframe.base.BaseView;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;

@ViewController(value = "/layout/db_list_item.fxml")
public class CardView extends BaseView {

    @Override
    protected void initView() throws Exception {
        System.out.println(getContext());
    }

    @Override
    protected void initEvent() throws Exception {

    }
}
