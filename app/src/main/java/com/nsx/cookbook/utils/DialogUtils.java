package com.nsx.cookbook.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.nsx.cookbook.widget.DesignProgressDialog;


/**
 * Created by Administrator on 2017/5/8.
 */

public class DialogUtils {
    //所有的对话框管理
    private static DesignProgressDialog dialog;

    //进度条对话框
    public static Dialog showProgressDialog(Context context) {
        dialog = new DesignProgressDialog(context);
        dialog.show();
        return dialog;
    }

    //放弃对话框
    public static Dialog abandonDialog(Context context, DialogInterface.OnClickListener finishListener) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage("辛苦编辑的内容要没有啦\n确定放弃吗")
                .setNegativeButton("放弃", finishListener)
                .setPositiveButton("继续编辑", null)
                .create();
        dialog.show();
        return dialog;
    }

    public static void dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }
}
