package com.duyangs.zbaselib.util;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.NotificationCompat;

/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselib.util</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/05/04 0004
 */
public class NotificationUtil {

        protected static int notifyID = 0525; // start notification id
        protected static int foregroundNotifyID = 0555;    //当前app是不是在用户可是界面

        public static void send(String string, Context context, Class activity) {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            Intent intent = new Intent(context, activity);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    notifyID,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pendingIntent);// 设置通知栏点击意图
            mBuilder.setContentTitle("这是标题");// 设置通知栏标题
            mBuilder.setContentText(string);
//            mBuilder.setSmallIcon(R.m.ic_launcher);// 设置通知小ICON（5.0必须采用白色透明图片）
            mBuilder.setTicker(string + "有警报！"); // 通知首次出现在通知栏，带上升动画效果的
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//悬挂式Notification，5.0后显示
                mBuilder.setFullScreenIntent(pendingIntent, true);
                mBuilder.setCategory(NotificationCompat.CATEGORY_MESSAGE);
                mBuilder.setVisibility(Notification.VISIBILITY_PUBLIC);
            }
//            mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));// 设置通知大ICON
            mBuilder.setWhen(System.currentTimeMillis());// 通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
            mBuilder.setPriority(NotificationCompat.PRIORITY_MAX); // 设置该通知优先级
            mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);//在任何情况下都显示，不受锁屏影响。
            mBuilder.setAutoCancel(true);// 设置这个标志当用户单击面板就可以让通知将自动取消
            mBuilder.setOngoing(false);// ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
            // 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用NotificationCompat.DEFAULT_ALL属性，可以组合
            mBuilder.setVibrate(new long[]{0, 100, 500, 100});//振动效果需要振动权限
            Uri defaultSoundUrlUri = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); //声音
            mBuilder.setSound(defaultSoundUrlUri);
            mBuilder.setDefaults(NotificationCompat.DEFAULT_LIGHTS);//闪灯
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
            //Notification notification = mBuilder.getNotification();//API 11
            Notification notification = mBuilder.build();//API 16
            mNotificationManager.notify(1, notification);
        }

        public static void sendNotification(Context context, String message, boolean isForeground) {
            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            try {
                String notifyText = message;
                PackageManager packageManager = context.getPackageManager();
                String appName = (String) packageManager
                        .getApplicationLabel(context.getApplicationInfo());
                // notification title
                String contentTitle = appName;
                String packageName = context.getApplicationInfo().packageName;
                Uri defaultSoundUrlUri = RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                // create and send notification
                android.support.v4.app.NotificationCompat.Builder mBuilder = new android.support.v4.app.NotificationCompat.Builder(context)
                        .setSmallIcon(context.getApplicationInfo().icon)
                        .setSound(defaultSoundUrlUri)
                        .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true);
                Intent msgIntent = context.getPackageManager()
                        .getLaunchIntentForPackage(packageName);
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        context,
                        notifyID,
                        msgIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setPriority(NotificationCompat.PRIORITY_MAX); // 设置该通知优先级
                mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);//在任何情况下都显示，不受锁屏影响。
                mBuilder.setContentTitle(contentTitle);
                mBuilder.setTicker(notifyText);
                mBuilder.setContentText(notifyText);
                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setFullScreenIntent(pendingIntent, true);
                mBuilder.setOnlyAlertOnce(false);
                mBuilder.setDefaults(NotificationCompat.DEFAULT_LIGHTS);//闪灯
                Notification notification = mBuilder.build();
                if (isForeground) {
                    notificationManager.notify(foregroundNotifyID, notification);
                    notificationManager.cancel(foregroundNotifyID);
                } else {
                    notificationManager.notify(notifyID, notification);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
