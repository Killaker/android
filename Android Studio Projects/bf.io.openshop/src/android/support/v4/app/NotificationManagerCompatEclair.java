package android.support.v4.app;

import android.app.*;

class NotificationManagerCompatEclair
{
    static void cancelNotification(final NotificationManager notificationManager, final String s, final int n) {
        notificationManager.cancel(s, n);
    }
    
    public static void postNotification(final NotificationManager notificationManager, final String s, final int n, final Notification notification) {
        notificationManager.notify(s, n, notification);
    }
}
