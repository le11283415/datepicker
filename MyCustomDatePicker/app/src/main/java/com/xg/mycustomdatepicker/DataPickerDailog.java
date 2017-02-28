package com.xg.mycustomdatepicker;

import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by yefeng on 2017/2/23.
 * Modified by xxx
 */

public class DataPickerDailog  {


    private Context mContext;
    private Dialogcallback mCallBack;
    private Dialog mDialog;
    private DateSelectorWheelView mDSWView;

    public DataPickerDailog(Context context ,Dialogcallback callback) {
    this.mContext = context;
    this.mCallBack = callback;
    initDialog();
    }

    private void initDialog() {

    mDialog = new Dialog(mContext, R.style.DialogCreatePostStyle);
    mDialog.setCancelable(true);
    mDialog.setOnKeyListener(mOnKeyListener);

    View view = LayoutInflater.from(mContext).inflate(R.layout.view_dialog_pickdate, null);

    initViews(view);
    mDialog.setContentView(view);

    Window window = mDialog.getWindow();
    WindowManager.LayoutParams lp = window.getAttributes();
    lp.width = ScreenUtil.getScreenWidth(mContext);
    lp.height = ScreenUtil.dip2px(mContext,280);
    window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
    window.setGravity(Gravity.BOTTOM);
    window.setAttributes(lp);

    }

    private void initViews(View view) {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        mDSWView = (DateSelectorWheelView) view.findViewById(R.id.dsw_view);
        TextView tvCancle= (TextView) view.findViewById(R.id.tv_cancle);
        TextView tvSure = (TextView) view.findViewById(R.id.tv_sure);

        Calendar c = Calendar.getInstance();
        mDSWView.setCurrentYear(c.get(Calendar.YEAR) + "");
        mDSWView.setCurrentMonth(c.get(Calendar.MONTH) + "");
        mDSWView.setCurrentDay(c.get(Calendar.DAY_OF_MONTH) + "");
        tvTitle.setText("选择日期");
        tvCancle.setOnClickListener(mOnClickListener);
        tvSure.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_cancle:
                    mDialog.dismiss();
                    break;
                case R.id.tv_sure:
                    mCallBack.pickWeightResult(mDSWView.getSelectedDate());
                    mDialog.dismiss();
                    break;
            }

        }
    };
    private DialogInterface.OnKeyListener mOnKeyListener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                mDialog.dismiss();
            }
            return false;
        }
    };


    public interface Dialogcallback {
        void pickWeightResult(String sex);
    }

    public void show() {
        mDialog.show();
    }

    public void hide() {
        mDialog.hide();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

}
