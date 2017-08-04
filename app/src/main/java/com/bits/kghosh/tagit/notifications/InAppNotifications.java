package com.bits.kghosh.tagit.notifications;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.bits.kghosh.tagit.R;

/**
 * Created by kghosh on 04-Aug-2017.
 */

public class InAppNotifications {

    public static void showInfo(Context ctx, View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(ctx.getResources().getColor(R.color.notification_info));
        snackbar.show();
    }

    public static void showSuccess(Context ctx, View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(ctx.getResources().getColor(R.color.notification_success));
        snackbar.show();
    }

    public static void showWarning(Context ctx, View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(ctx.getResources().getColor(R.color.notification_warning));
        snackbar.show();
    }

    public static void showError(Context ctx, View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(ctx.getResources().getColor(R.color.notification_error));
        snackbar.show();
    }
}
