package android.support.v4.app;

import android.os.*;
import android.content.*;
import android.widget.*;
import android.app.*;
import android.graphics.*;
import java.util.*;

public static class Builder implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions
{
    private Notification$Builder b;
    private Bundle mExtras;
    
    public Builder(final Context context, final Notification notification, final CharSequence contentTitle, final CharSequence contentText, final CharSequence contentInfo, final RemoteViews remoteViews, final int number, final PendingIntent contentIntent, final PendingIntent pendingIntent, final Bitmap largeIcon, final int n, final int n2, final boolean b, final boolean showWhen, final boolean usesChronometer, final int priority, final CharSequence subText, final boolean localOnly, final ArrayList<String> list, final Bundle bundle, final String group, final boolean groupSummary, final String sortKey) {
        this.b = new Notification$Builder(context).setWhen(notification.when).setShowWhen(showWhen).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((0x2 & notification.flags) != 0x0).setOnlyAlertOnce((0x8 & notification.flags) != 0x0).setAutoCancel((0x10 & notification.flags) != 0x0).setDefaults(notification.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(pendingIntent, (0x80 & notification.flags) != 0x0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(usesChronometer).setPriority(priority).setProgress(n, n2, b).setLocalOnly(localOnly).setGroup(group).setGroupSummary(groupSummary).setSortKey(sortKey);
        this.mExtras = new Bundle();
        if (bundle != null) {
            this.mExtras.putAll(bundle);
        }
        if (list != null && !list.isEmpty()) {
            this.mExtras.putStringArray("android.people", (String[])list.toArray(new String[list.size()]));
        }
    }
    
    @Override
    public void addAction(final NotificationCompatBase.Action action) {
        NotificationCompatApi20.addAction(this.b, action);
    }
    
    @Override
    public Notification build() {
        this.b.setExtras(this.mExtras);
        return this.b.build();
    }
    
    @Override
    public Notification$Builder getBuilder() {
        return this.b;
    }
}
