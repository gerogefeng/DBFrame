package cn.devifish.dbframe.base;

import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;

public abstract class ContextView extends BaseView {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @Override
    protected void init() {
        initVar(context);
        super.init();
    }

    protected abstract void initVar(ViewFlowContext context);

    protected ViewFlowContext getContext() {
        return context;
    }
}
