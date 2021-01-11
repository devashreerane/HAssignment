package com.assignment.utils;

import android.util.Log;
import android.util.SparseArray;

import java.util.Vector;

/**
 * EventInjectManager class.
 *
 */

public class EventInjectManager {

    public static final int EVENT_TYPE_COUNTRY_SELECTED = -100;
    public static final int EVENT_TYPE_OTP_NUMBER_RECIEVED = -101;
    public static final int EVENT_TYPE_GET_OTP_NUMBER_RECIEVED = -102;
    public static final int EVENT_TYPE_YES_OR_NO_SELECTED = -103;
    public static final int EVENT_TYPE_DATE_SET = -104;
    public static final int EVENT_TYPE_PROMO_CODE_SELECTED = -105;
    public static final int EVENT_TYPE_SHOW_EPG_GRID = -106;
    public static final int EVENT_TYPE_CONTACT_US_SUBMIT_DISABLE = -107;
    public static final int EVENT_TYPE_SPOTLIGHT_TOUR = -108;
    public static final int EVENT_TYPE_SUBSCRIPTION_RESPONSE = -109;
    public static final int EVENT_TYPE_SUBTITLE_INDEX_SELECTED = -111;
    public static final int EVENT_TYPE_AUDIO_INDEX_SELECTED = -112;
    public static final int EVENT_TYPE_DISPLAY_LANGUAGE_SELECTED = -113;
    public static final int EVENT_TYPE_DONE = -114;
    public static final int EVENT_TYPE_QUALITY_INDEX_SELECTED = -115;
    public static final int EVENT_TYPE_SEARCH = -116;
    public static final int EVENT_RELOAD_MENU = -117;
    public static final int PLAYBACK_STOPPED = -118;
    public static final int EVENT_PAYMENT_METHOD = -119;
    public static final int REMOVE_CONTINUE_WATCH_DATA = -120;
    public static final int UPDATE_CONTINUE_WATCH_DATA = -121;
    public static final int EVENT_TYPE_GENRE_RECEIVED = -122;
    public static final int EVENT_IS_AUTOPLAY = -123;
    public static final int REMOVE_CONTINUE_WATCH_CAROUSEL = -124;
    public static final int WATCH_HISTORY_UPDATED = -125;
    public static final int EVENT_SETTINGS_LOADED = -126;
    public static final int EVENT_SUBSCRIPTION_SELECTED = -127;
    public static final int EVENT_SHOW_VIDEO_DETAIL = -128;
    public static final int CAST_CONNECTED_STATUS = -129;
    public static final int EVENT_LANGUAGE_CHANGED = -130;
    public static final int UNSUBSCRIBE_PLAN = -131;
    public static final int CORE_DATA_LOADED = -132;
    public static final int IN_APP_PURCHASE_FINISHED = -133;
    public static final int IN_APP_UNSUBSCRIBE_FINISHED = -134;
    public static final int DOWNLOAD_FINISHED = -135;
    public static final int DOWNLOAD_STARTED = -136;
    public static final int EVENT_TYPE_PLAYER_STATE_CHANGED = -137;
    public static final int HEADPHONES_CONNECTED = -138;
    public static final int SETTINGS_DOWNLOAD_ON_WIFI = -139;
    public static final int PHONE_CALL_STARTED = -140;
    public static final int PHONE_CALL_ENDED = -141;
    public static final int SHOW_NULL_HOME_SCREEN = -142;
    public static final int CONTENT_LANGUAGE_CHANGED = -143;
    public static final int DOWNLOAD_REMOVED = -144;
    public static final int THROW_PLAYBACK_ERROR = -145;
    public static final int EVENT_TYPE_CONTENT_LANGUAGE_CHANGED = -146;
    public static final int EVENT_TYPE_TV_GUIDE_FILTER_CHANGED = -147;
    public static final int USER_PROFILE_LOADED = -148;
    public static final int DISPLAY_LANGUAGE_CHANGED_LOAD_DETAILS = -149;
    public static final int SEASON_TAB_INDEX = -150;

    public static final int PAYMENT_HISTORY_INDEX = -151;

    public static final int SHOW_RESET_POP_UP_IN_PLAYER = -155;
    public static final int SHOW_DELETE_ONCE_POP_UP_IN_PLAYER = -152;
    public static final int SHOW_DELETE_ONCE_POP_UP_FOR_MAX_DEVICE = -153;
    public static final int SHOW_DOWNLOAD_DEVICE_ERROR_POP_UP = -154;
    public static final int FACEBOOK_PRE_AD_LOAD = -156;

    public static final int EVENT_SETTINGS_LOADED_OLD_USER = -157;

    public static final int EVENT_SETTINGS_LOADED_SOCIAL_ACCOUNT = -158;
    public static final int EVENT_LOAD_FROM_SOCIAL_GDPR = -159;
    public static final int SEARCH_RESULT_COUNT = -160;
    public static final int SEARCH_RESULT_TITLE_COUNT = -161;
    public static final int SEARCH_RESULT_KEYPAD_HIDDEN = -162;
    public static final int PAGE_REFRESH_DOWNLOAD = -163;
    public static final int EVENT_TYPE_SETTINGS_SCREEN = -164;
    public static final int EVENT_TYPE_THROUGH_LOGIN_POPUP = -165;
    public static final int ADS_SUNSCRIPTION = -166;
    public static final int RENEW_CALLBACK = -167;
    public static final int WATCH_LIST_DELETE = -168;
    public static final int WATCH_LIST_ADD = -169;
    public static final int DEVICE_LANGUAGE_ERROR = -170;
    public static final int WATCH_FREE_EPISODES_BEFORE_TV = -171;
    public static final int EVENT_SETTINGS_LOADED_TV_OLD_USER = -172;
    public static final int PLAYBACK_PLAY = -173;
    public static final int HIDE_SUBSCRIPTION_LAYOUT = -174;
    public static final int NAVIGATION_DRAWER = -175;
    public static final int DOWNLOAD_NETWORK_OFFLINE = -176;
    public static final int KILL_PLAYER = -177;
    public static final int SUBSCRIBE_POPUP_AFTER_TRAILER= -178;
    public static final int CAT1_CONTENT_LANGUAGE= -179;

    //SugarBox

    public static final int SUGAR_BOX_DISCONNECTED = -179;
    public static final int SUGAR_BOX_WIFI_ZONE_AVAILABE = -180;
    public static final int SUGAR_BOX_WIFI_ZONE_WEAK = -181;
    public static final int SUGAR_BOX_WIFI_ZONE_LOST = -182;
    public static final int SUGAR_BOX_CONNECTION_ERROR = -183;
    public static final int SUGAR_BOX_CONNECTED = -184;
    public static final int SUGAR_BOX_AUTHENTICATION_REQUIRED = -185;
    public static final int HOME_PAGE_REFRESH = -186;
    public static final int SUGAR_BOX_AUTHENTICATED = -187;
    public static final int SB_TUTORIAL_AUTHENTICATION = -188;
    public static final int SB_LOCATION_ACCESS = -189;
    public static final int SUGAR_BOX_CELLULAR_DATA_AVAILABLE = -190;
    public static final int SUGAR_BOX_CELLULAR_DATA_UNAVAILABLE = -191;
    public static final int SUGAR_BOX_DETAILS_PLAYER_PAGE_EXIT = -192;
    public static final int SUGARBOX_DISCONNECT_REMOVE_ICON = -193;
    public static final int SUGAR_BOX_LOCATIONACCESS_SETTINGS= -194;
    public static final int SUGAR_BOX_WIFI_ZONE_UNAVAILABE= -195;
    public static final int SUGAR_BOX_AUTHENTICATION_ERROR = -196;
    public static final int SUGAR_BOX_ON = -197;

    public static final int MASTHEAD_AD_PLAYBACK = -198;
    public static final int PLAYBACK_PLAY_NEXT_CONTENT = -199;
    public static final int BANNER_PLAYER_VISIBILITY = -200;
    public static final int BANNER_PLAYER_MUTE_UNMUTE = -201;

    private static final String TAG = "EventInjectManager";

    private static EventInjectManager eventInjectManager;

    private SparseArray<Vector<EventInjectListener>> eventsArray = new SparseArray<>();

    private EventInjectManager() {
    }

    public static EventInjectManager getInstance() {
        synchronized (EventInjectManager.class) {
            if (eventInjectManager == null) {
                eventInjectManager = new EventInjectManager();
            }
            return eventInjectManager;
        }
    }

    public void registerForEvent(int eventType, EventInjectListener eventInjectListener) {
        Log.d(TAG, "registerForEvent: 123456" + eventType);
        if (eventInjectListener == null) {
            return;
        }
        Vector<EventInjectListener> eventInjectListeners = eventsArray.get(eventType);
        if (eventInjectListeners == null) {
            eventInjectListeners = new Vector<>();
            eventInjectListeners.add(eventInjectListener);
            eventsArray.put(eventType, eventInjectListeners);
        } else {
            if (!eventInjectListeners.contains(eventInjectListener)) {
                eventInjectListeners.add(eventInjectListener);
            }
        }
    }

    public void unRegisterForEvent(int eventType, EventInjectListener eventInjectListener) {
        if (eventInjectListener == null) {
            return;
        }
        Vector<EventInjectListener> eventInjectListeners = eventsArray.get(eventType);
        if (eventInjectListeners != null) {
            eventInjectListeners.remove(eventInjectListener);
        }
    }

    public void injectEvent(int eventType, Object data) {
        Vector<EventInjectListener> eventInjectListeners = eventsArray.get(eventType);
        EventInjectListener eventInjectListener;
        if (eventInjectListeners != null) {
            int size = eventInjectListeners.size();
            Log.d(TAG, "Size of listeners array : " + size);
            for (int i = 0; i < size; i++) {
                if (i < eventInjectListeners.size()) {
                    eventInjectListener = eventInjectListeners.get(i);
                    eventInjectListener.eventReceived(eventType, data);
                }
            }
        } else {
            Log.d(TAG, "Sorry no one is listening");
        }
    }

    /**
     * <b>Please call this only in onDestroy of {@link }
     * . Nowhere else</b>
     */
    public void clearAllRegistrations() {
        eventsArray.clear();
    }

    public interface EventInjectListener {
        void eventReceived(int eventType, Object data);
    }
}
