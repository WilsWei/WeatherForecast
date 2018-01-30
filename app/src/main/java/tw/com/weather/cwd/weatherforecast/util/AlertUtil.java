package tw.com.weather.cwd.weatherforecast.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import tw.com.weather.cwd.weatherforecast.R;

/**
 * Created by siang on 2018/1/29.
 */

public class AlertUtil {

    /**
     * 顯示只有一個按鈕的提示對話框
     * @param message 要顯示的訊息
     * @param btnResId 按鈕文字資源id
     * @param btnClickListener 按鈕點擊事件listener
     * @param cancelable 點擊背景是否可取消
     */
    public static AlertDialog showAlertDialog(Context context, String message, int btnResId, DialogInterface.OnClickListener btnClickListener, boolean cancelable){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(message)
                .setTitle(R.string.dialog_title)
                .setCancelable(cancelable);

        builder.setPositiveButton(btnResId, btnClickListener);

        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    /**
     * 顯示提示對話框
     * @param message 傳回來的訊息
     */
    public static AlertDialog showAlertDialog(Context context, String message){
       return showAlertDialog(context, message,
                android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }, false);
    }

}
