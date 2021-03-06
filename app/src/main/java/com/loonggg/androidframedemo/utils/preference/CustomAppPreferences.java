package com.loonggg.androidframedemo.utils.preference;

import android.content.Context;
import javax.inject.Inject;
import net.grandcentrix.tray.AppPreferences;

/**
 * 全局Preference<BR>
 *
 */
public class CustomAppPreferences extends AppPreferences {

  public static final String KEY_USER_ID = "uid";
  public static final String KEY_USER_TYPE = "user_type";
  public static final String KEY_HAS_VIEW_GUIDE = "has_view_guide";
  public static final String KEY_COOKIE_SESSION_ID = "cookie_session_id";
  public static final String KEY_USER_INFO = "key_user_info";
  //开启横屏
  public static final String KEY_SCREEN = "key_screen";
  //系统消息通知
  public static final String KEY_SYSTEM_MESSAGE = "key_system_message";
  //流量提醒
  public static final String KEY_TRAFFIC_ALERT = "key_traffic_alert";
  //推送广告提醒
  public static final String KEY_MOVEMENT = "key_movement";

  @Inject public CustomAppPreferences(Context context) {
    super(context);
  }

  public void saveUserId(String userId) {
    put(KEY_USER_ID, userId);
  }

  public String getUserId() {
    return getString(KEY_USER_ID, "");
  }

  public boolean showGuideView() {
    return getBoolean(KEY_HAS_VIEW_GUIDE, true);
  }

  public void setNoGuideView() {
    put(KEY_HAS_VIEW_GUIDE, false);
  }
}
