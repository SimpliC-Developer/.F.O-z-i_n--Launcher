package simplified.creative.codes.nest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.*;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.*;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import simplified.creative.codes.nest.Tools.Analog;
import simplified.creative.codes.nest.Tools.ContentsA;
import simplified.creative.codes.nest.Tools.ContentsB;
import simplified.creative.codes.nest.Tools.Methods;

import java.io.*;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static android.view.View.OnTouchListener;
import static simplified.creative.codes.nest.Tools.Methods.*;

public class TheNest extends Activity implements ContentsA.AdapterCallback, ContentsB.AdapterCallback {

    /*
       ----------------------------
       [                          ]
       [     FOZIN - LAUNCHER     ]
       [                          ]
       ----------------------------
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createCycle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startCycle();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopCycle();
    }

    @Override
    public void onPause() {
        super.onPause();
        pauseCycle();
    }

    @Override
    public void onResume() {
        super.onResume();
        resumeCycle();
    }

    @Override
    public void onBackPressed() {
        backClick();
    }

    /*
       ------------------------
       [                      ]
       [     LIFE - CYCLE     ]
       [                      ]
       ------------------------
    */

    LayoutInflater inflater;
    View theNestAdditionalView, theNestHomeView, theNestDrawerView,  theNestSettingsView;
    RelativeLayout theNestRootLayout, theNestAdditionalLayout, theNestHomeLayout, theNestDrawerLayout,
            theNestSettingsLayout;
    private void createCycle(){
        setContentView(R.layout.the_nest_root_layout);
        inflater = LayoutInflater.from(TheNest.this);
        theNestRootLayout = findViewById(R.id.the_nest_root_layout);
        initialize();
    }

    private boolean configurationsMatched;
    private boolean instanceCreated;
    private void startCycle(){
        if(instanceCreated && configurationsMatched){
            homeScreenStateA();
        }
    }

    private void stopCycle(){
        if(instanceCreated && configurationsMatched){
            if(theNestRootLayout.findViewById(R.id.the_nest_home_layout) != null)
                homeScreenStateB();

            if(theNestRootLayout.findViewById(R.id.the_nest_drawer_layout) != null)
                drawerTooltipReset();

            if(theNestRootLayout.findViewById(R.id.the_nest_settings_layout) != null)
                settingsTooltipReset();

            if(theNestRootLayout.findViewById(R.id.the_nest_home_layout) == null) {
                theNestRootLayout.removeView(theNestSettingsView);
                theNestRootLayout.removeView(theNestDrawerView);
                theNestRootLayout.addView(theNestHomeView);
            }
        }
    }

    private void pauseCycle(){
        if(instanceCreated && configurationsMatched){
            if(theNestRootLayout.findViewById(R.id.the_nest_home_layout) != null)
                homeScreenStateB();
        }
    }

    private void resumeCycle(){
        if(instanceCreated && configurationsMatched){
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
            if(theNestRootLayout.findViewById(R.id.the_nest_home_layout) != null)
                homeScreenStateA();

            if(theNestRootLayout.findViewById(R.id.the_nest_drawer_layout) != null)
                drawerScreenStateA(0);
        }
    }

    private void backClick(){
        if(instanceCreated && configurationsMatched){
            if(theNestRootLayout.findViewById(R.id.the_nest_home_layout) != null) {
                if(homeAppletLayout.findViewById(R.id.home_applet_a) == null){
                    homeApplet(1);
                }
            } else {
                theNestRootLayout.addView(theNestHomeView);
                homeScreenStateA();
                theNestRootLayout.removeView(theNestDrawerView);
                theNestRootLayout.removeView(theNestSettingsView);
            }
        }
    }

    /*
       --------------------------
       [                        ]
       [     CONFIGURATIONS     ]
       [                        ]
       --------------------------
    */

    private void initializeConfigurations(){
        if(!fileExist(this, "Configurations - 01")){
            create(this, "Configurations - 01", "# CONFIGURATIONS...");
            create(this, "Configurations - 01", "-------------------");
            create(this, "Configurations - 01", " ");
            create(this, "Configurations - 01", "Status Bar - Enabled");
            create(this, "Configurations - 01", "Navigation Bar - Enabled");
            create(this, "Configurations - 01", "Background - " + R.color.origin);
            create(this, "Configurations - 01", "UI - " + R.color.orange_3);
            create(this, "Configurations - 01", "Tint A - " + R.color.white);
            create(this, "Configurations - 01", "Tint B - " + R.color.black);
            create(this, "Configurations - 01", "Font A Mode - SimpliC");
            create(this, "Configurations - 01", "Font A Style - " + Typeface.BOLD);
            create(this, "Configurations - 01", "Font B Style - " + Typeface.NORMAL);
            create(this, "Configurations - 01", " ");
            create(this, "Configurations - 01", "---------- > ---------- > ---------- >");
        }
        if(!fileExist(this, "Configurations - 02")){
            create(this, "Configurations - 02", "# CONFIGURATIONS...");
            create(this, "Configurations - 02", "-------------------");
            create(this, "Configurations - 02", " ");
            create(this, "Configurations - 02", "Widget State - Enabled");
            create(this, "Configurations - 02", "Widget Mode - [ NONE ]");
            create(this, "Configurations - 02", "Widget Component - [ NONE ]");
            create(this, "Configurations - 02", "Widget ID - 0");
            create(this, "Configurations - 02", "Widget Style - A");
            create(this, "Configurations - 02", "Applet Text - ∆×••⟨[ ]⟩");
            create(this, "Configurations - 02", "Wallpaper State - Enabled");
            create(this, "Configurations - 02", "Wallpaper Mode - [ NONE ]");
            create(this, "Configurations - 02", "Wallpaper Component - [ NONE ]");
            create(this, "Configurations - 02", "Folder State - Enabled");
            create(this, "Configurations - 02", "Folder Name - Folder");
            create(this, "Configurations - 02", "Folder Sorting Order - Alphabetically");
            create(this, "Configurations - 02", "Status State - Enabled");
            create(this, "Configurations - 02", "Status Mode - [ NONE ]");
            create(this, "Configurations - 02", "Drawer Mode - Icon");
            create(this, "Configurations - 02", "Drawer Icon - BeeHive");
            create(this, "Configurations - 02", "Drawer Size - C");
            create(this, "Configurations - 02", "Drawer Alpha - 1.0f");
            create(this, "Configurations - 02", "Shortcut State - Enabled");
            create(this, "Configurations - 02", "Widget Creation - Denied");
            create(this, "Configurations - 02", " ");
            create(this, "Configurations - 02", "---------- > ---------- > ---------- >");
        }
        if(!fileExist(this, "Configurations - 03")) {
            create(this, "Configurations - 03", "# CONFIGURATIONS...");
            create(this, "Configurations - 03", "-------------------");
            create(this, "Configurations - 03", " ");
            create(this, "Configurations - 03", "Drawer Grid Style - Tiles");
            create(this, "Configurations - 03", "Drawer Sorting Order - Alphabetically");
            create(this, "Configurations - 03", "Drawer Browse Mode - Index");
            create(this, "Configurations - 03", " ");
            create(this, "Configurations - 03", "---------- > ---------- > ---------- >");
        }
        if(!fileExist(this, "Configurations - 04")){
            create(this, "Configurations - 04", "# CONFIGURATIONS...");
            create(this, "Configurations - 04", "-------------------");
            create(this, "Configurations - 04", " ");
            create(this, "Configurations - 04", "Settings Tab - Auto");
            create(this, "Configurations - 04", "Misc List Style - Square");
            create(this, "Configurations - 04", "Home List Style - Hexagon");
            create(this, "Configurations - 04", " ");
            create(this, "Configurations - 04", "---------- > ---------- > ---------- >");
        }
    }

    private static String statusBar;
    private static String navigationBar;
    public static int background;
    public static int tintA;
    public static int tintB;
    public static int ui;
    public static String fontAMode;
    public static int fontAStyle;
    public static int fontBStyle;
    private void configurationsA(){
        List<String> readValues = read(this, "Configurations - 01");
        statusBar = readValues.get(3).substring(13).trim();
        navigationBar = readValues.get(4).substring(17).trim();
        background = Integer.parseInt(readValues.get(5).substring(12).trim());
        ui = Integer.parseInt(readValues.get(6).substring(4).trim());
        tintA = Integer.parseInt(readValues.get(7).substring(8).trim());
        tintB = Integer.parseInt(readValues.get(8).substring(8).trim());
        fontAMode = readValues.get(9).substring(13).trim();
        fontAStyle = Integer.parseInt(readValues.get(10).substring(14).trim());
        fontBStyle = Integer.parseInt(readValues.get(11).substring(14).trim());

        userInterfaceB();
        userInterfaceA();
    }

    private String widgetState;
    private String widgetMode;
    private String widgetComponent;
    private int widgetID;
    private String widgetStyle;
    private String appletText;
    private String wallpaperState;
    private String wallpaperMode;
    private String wallpaperComponent;
    private String folderState;
    private String folderName;
    private String folderSortingOrder;
    private String statusState;
    private String statusMode;
    private String drawerMode;
    private String drawerIcon;
    private float drawerAlpha;
    private String drawerSize;
    private String shortcutState;
    private String widgetCreation;
    private void configurationsB(){
        List<String> readValues = read(this, "Configurations - 02");
        widgetState = readValues.get(3).substring(15).trim();
        widgetMode = readValues.get(4).substring(14).trim();
        widgetComponent = readValues.get(5).substring(19).trim();
        widgetID = Integer.parseInt(readValues.get(6).substring(12).trim());
        widgetStyle = readValues.get(7).substring(15).trim();
        appletText = readValues.get(8).substring(14).trim();
        wallpaperState = readValues.get(9).substring(17).trim();
        wallpaperMode = readValues.get(10).substring(17).trim();
        wallpaperComponent = readValues.get(11).substring(22).trim();
        folderState = readValues.get(12).substring(15).trim();
        folderName = readValues.get(13).substring(14).trim();
        folderSortingOrder = readValues.get(14).substring(23).trim();
        statusState = readValues.get(15).substring(15).trim();
        statusMode = readValues.get(16).substring(14).trim();
        drawerMode = readValues.get(17).substring(14).trim();
        drawerIcon = readValues.get(18).substring(14).trim();
        drawerSize = readValues.get(19).substring(14).trim();
        drawerAlpha = Float.parseFloat(readValues.get(20).substring(15).trim());
        shortcutState = readValues.get(21).substring(17).trim();
        widgetCreation = readValues.get(22).substring(18).trim();
    }

    public static String drawerGridStyle;
    private String drawerSortingOrder;
    private String drawerBrowseMode;
    private void configurationsC(){
        List<String> readValues = read(TheNest.this, "Configurations - 03");
        drawerGridStyle = readValues.get(3).substring(20).trim();
        drawerSortingOrder = readValues.get(4).substring(23).trim();
        drawerBrowseMode = readValues.get(5).substring(21).trim();
    }

    private String settingsTab;
    private String miscListStyle;
    private String homeListStyle;
    private void configurationsD(){
        List<String> readValues = read(this, "Configurations - 04");
        settingsTab = readValues.get(3).substring(15).trim();
        miscListStyle = readValues.get(4).substring(18).trim();
        homeListStyle = readValues.get(5).substring(18).trim();
    }

    /*
       ------------------------------
       [                            ]
       [     THE NEST - SCREENS     ]
       [                            ]
       ------------------------------
    */

    private void additionalScreen(){
        if(theNestAdditionalView == null){
            theNestAdditionalView = inflater.inflate(R.layout.the_nest_additional_layout, null);
            theNestAdditionalLayout = theNestAdditionalView.findViewById(R.id.the_nest_additional_layout);
        }
        if(theNestRootLayout.findViewById(R.id.the_nest_additional_layout) == null){
            theNestRootLayout.addView(theNestAdditionalView);
            setSize(theNestAdditionalLayout, size(2), size(2));
        }
        additionalWelcome();
    }

    private void homeScreen(){
        if(theNestHomeView == null){
            theNestHomeView = inflater.inflate(R.layout.the_nest_home_layout, null);
            theNestHomeLayout = theNestHomeView.findViewById(R.id.the_nest_home_layout);
        }
        if(theNestRootLayout.findViewById(R.id.the_nest_home_layout) == null){
            theNestRootLayout.addView(theNestHomeView);
            setSize(theNestHomeLayout, size(2), size(2));
        }
        homeScreenState = 1;
        configurationsB();

        homeEdge();
        homeWidget();
        homeApplet(1);
        homeTooltip();
        homeWallpaper();
        homeFolder();
        homeStatus();
        homeDrawer();
        homeShortcut();

        centerPositions();
    }

    private void drawerScreen(){
        if(theNestDrawerView == null){
            theNestDrawerView = inflater.inflate(R.layout.the_nest_drawer_layout, null);
            theNestDrawerLayout = theNestDrawerView.findViewById(R.id.the_nest_drawer_layout);
        }
        if(theNestRootLayout.findViewById(R.id.the_nest_drawer_layout) == null){
            theNestRootLayout.addView(theNestDrawerView);
            setSize(theNestDrawerLayout, size(2), size(2));
        }
        configurationsC();

        drawerEdge();
        drawerTooltip();
        drawerApps();
        drawerTiles();

        drawerEdgePositions();
        drawerScreenStateA(1);
    }

    private void settingsScreen(){
        if(theNestSettingsView == null){
            theNestSettingsView = inflater.inflate(R.layout.the_nest_settings_layout, null);
            theNestSettingsLayout = theNestSettingsView.findViewById(R.id.the_nest_settings_layout);
        }
        if(theNestRootLayout.findViewById(R.id.the_nest_settings_layout) == null){
            theNestRootLayout.addView(theNestSettingsView);
            setSize(theNestSettingsLayout, size(2), size(2));
        }
        configurationsD();

        settingsButtons();
        if(settingsTab.equals("Auto")){
            if(settingsEdgeALayout == null)
                settingsPanel();
        } else {
            settingsPanel();
        }
        settingsTooltip();

        settingsScreenStateA();
    }

    /*
       ------------------------------
       [                            ]
       [     THE NEST - METHODS     ]
       [                            ]
       ------------------------------
    */

    @SuppressLint("ClickableViewAccessibility")
    private void userInterfaceA(){
        getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(this, background));
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.transparent));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.transparent));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            if(!statusBar.equals("Enabled") && !navigationBar.equals("Enabled")){
                theNestRootLayout.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        userInterfaceB();
                        return true;
                    }
                });
            } else {
                theNestRootLayout.setOnTouchListener(null);
            }
        }
    }
    private void userInterfaceB(){
        if(statusBar.equals("Enabled") && navigationBar.equals("Enabled")){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                getWindow().setDecorFitsSystemWindows(true);
                getWindow().getDecorView().getWindowInsetsController().setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_TOUCH);
                getWindow().getDecorView().getWindowInsetsController().show(WindowInsets.Type.systemBars());
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
        if(statusBar.equals("Enabled") && !navigationBar.equals("Enabled")){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                getWindow().setDecorFitsSystemWindows(true);
                getWindow().getDecorView().getWindowInsetsController().setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
                getWindow().getDecorView().getWindowInsetsController().show(WindowInsets.Type.statusBars());
                getWindow().getDecorView().getWindowInsetsController().hide(WindowInsets.Type.navigationBars());
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
        if(!statusBar.equals("Enabled") && navigationBar.equals("Enabled")){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                getWindow().setDecorFitsSystemWindows(true);
                getWindow().getDecorView().getWindowInsetsController().setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
                getWindow().getDecorView().getWindowInsetsController().hide(WindowInsets.Type.statusBars());
                getWindow().getDecorView().getWindowInsetsController().show(WindowInsets.Type.navigationBars());
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
        if(!statusBar.equals("Enabled") && !navigationBar.equals("Enabled")){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                getWindow().setDecorFitsSystemWindows(false);
                getWindow().getDecorView().getWindowInsetsController().setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
                getWindow().getDecorView().getWindowInsetsController().hide(WindowInsets.Type.systemBars());
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }

    private void customTouchModeA(View view, String string, int hold, int press){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    touchStart(view);
                    onTouchRunnable = new Runnable() {
                        @Override
                        public void run() {
                            if(hold == 0){
                                homeAppletBString = string;
                                homeFolderHold_1();
                            }
                            if(hold == 1){
                                homeAppletBString = string;
                                homeShortcutHold_1();
                            }
                            if(hold == 2)
                                drawerTilesHold_0();
                            if(hold == 3){
                                homeAppletBString = string;
                                drawerAppsHold_0();
                            }
                            if(hold == 4)
                                homeWidgetHold();
                            if(hold == 5)
                                homeWallpaperHold();
                            if(hold == 6)
                                homeFolderHold();
                            if(hold == 7)
                                homeStatusHold();
                            if(hold == 8)
                                homeShortcutHold();
                            touchVibrate(TheNest.this, view);
                        }
                    };
                    onTouchHandler.postDelayed(onTouchRunnable, 300);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    touchStop(view);
                    if((System.currentTimeMillis() - time) < 200){
                        if(press == 0){
                            homeAppletBString = string;
                            homeFolderPress_4();
                        }
                        if(press == 1){
                            homeAppletBString = string;
                            homeShortcutPress_0();
                        }
                        if(press == 2){
                            if(!drawerAlphabetsString.equals(string)){
                                drawerAlphabetsString = string;
                                drawerTilesPress_1();
                            }
                        }
                        if(press == 3){
                            homeAppletBString = string;
                            drawerAppsPress_0();
                        }
                        if(press == 4)
                            settingsMiscPress_14(Integer.parseInt(string));
                        if(press == 5){
                            settingsDrawerBString = string;
                            settingsDrawerPress_8();
                        }
                        if(press == 6){
                            settingsHomeBString = string;
                            settingsHomePress_37();
                        }
                        if(press == 7)
                            settingsHomePress_39(string);
                        if(press == 8)
                            settingsHomePress_40(string);
                        if(press == 9)
                            settingsHomePress_41(string);
                    }
                }
                touchEnd(event, view);
                return true;
            }
        });
    }

    private void customTouchModeB(View view, String text, int mode, int type, int count){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    touchStart(view);
                    onTouchRunnable = new Runnable() {
                        @Override
                        public void run() {
                            if(!text.isEmpty()) {
                                touchTip(TheNest.this, text, mode);
                            }
                            touchVibrate(TheNest.this, view);
                        }
                    };
                    onTouchHandler.postDelayed(onTouchRunnable, 300);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    touchStop(view);
                    if((System.currentTimeMillis() - time) < 200){
                        if(mode == -1){
                            if(type == -1){
                                if(count == 0)
                                    additionalWelcomePress_0();
                                if(count == 1)
                                    additionalNotesPress_0();
                                if(count == 2)
                                    additionalInfoPress_0();
                                if(count == 3)
                                    additionalInfoPress_1();
                                if(count == 4)
                                    additionalErrorPress_0();
                            }
                        }
                        if(mode == 0){
                            if(type == 0){
                                if(count == 0)
                                    homeWidgetPress_0();
                                if(count == 1)
                                    homeWidgetPress_1();
                                if(count == 2)
                                    homeWidgetPress_2();
                                if(count == 3)
                                    homeWidgetPress_3();
                            }
                            if(type == 1){
                                if(count == 0)
                                    homeAppletPress_0();
                                if(count == 1)
                                    homeAppletPress_1();
                                if(count == 2)
                                    homeAppletPress_2();
                                if(count == 3)
                                    homeAppletPress_3();
                                if(count == 4)
                                    homeAppletPress_4();
                                if(count == 5)
                                    homeAppletPress_5();
                                if(count == 6)
                                    homeAppletPress_6();
                                if(count == 7)
                                    homeAppletPress_7();
                            }
                            if(type == 2){
                                if(count == 0)
                                    homeWallpaperPress_0();
                                if(count == 1)
                                    homeWallpaperPress_1();
                            }
                            if(type == 3){
                                if(count == 0)
                                    homeFolderPress_0();
                                if(count == 1)
                                    homeFolderPress_1();
                                if(count == 2)
                                    homeFolderPress_2();
                                if(count == 3)
                                    homeFolderPress_3();
                            }
                            if(type == 4){
                                if(count == 0)
                                    homeStatusPress_0();
                            }
                            if(type == 5){
                                if(count == 0)
                                    homeDrawerPress_0();
                            }
                            if(type == 6){

                            }
                        }
                        if(mode == 1){
                            if(type == 0){
                                if(count == 0)
                                    drawerTilesPress_0();
                            }
                            if(type == 1){
                                if(count == 0)
                                    drawerAppletPress_0();
                                if(count == 1)
                                    drawerAppletPress_1();
                                if(count == 2)
                                    drawerAppletPress_2();
                                if(count == 3)
                                    drawerAppletPress_3();
                                if(count == 4)
                                    drawerAppletPress_4();
                                if(count == 5)
                                    drawerAppletPress_5();
                                if(count == 6)
                                    drawerAppletPress_6();
                                if(count == 7)
                                    drawerAppletPress_7();
                                if(count == 8)
                                    drawerAppletPress_8();
                                if(count == 9)
                                    drawerAppletPress_9();
                            }
                        }
                        if(mode == 2){
                            if(type == 0){
                                if(count == 0)
                                    settingsButtonsPress_0();
                                if(count == 1)
                                    settingsButtonsPress_1();
                                if(count == 2)
                                    settingsButtonsPress_2();
                                if(count == 3)
                                    settingsButtonsPress_3();
                            }
                            if(type == 1){
                                if(count == 0)
                                    settingsAboutPress_0();
                                if(count == 1)
                                    settingsAboutPress_1();
                                if(count == 2)
                                    settingsAboutPress_2();
                                if(count == 3)
                                    settingsAboutPress_3();
                                if(count == 4)
                                    settingsAboutPress_4();
                            }
                            if(type == 2){
                                if(count == 0)
                                    settingsMiscPress_0();
                                if(count == 1)
                                    settingsMiscPress_1();
                                if(count == 2)
                                    settingsMiscPress_2();
                                if(count == 3)
                                    settingsMiscPress_3();
                                if(count == 4)
                                    settingsMiscPress_4();
                                if(count == 5)
                                    settingsMiscPress_5();
                                if(count == 6)
                                    settingsMiscPress_6();
                                if(count == 7)
                                    settingsMiscPress_7();
                                if(count == 8)
                                    settingsMiscPress_8();
                                if(count == 9)
                                    settingsMiscPress_9();
                                if(count == 10)
                                    settingsMiscPress_10();
                                if(count == 11)
                                    settingsMiscPress_11();
                                if(count == 12)
                                    settingsMiscPress_12();
                                if(count == 13)
                                    settingsMiscPress_13();
                                //------------------------
                                if(count == 15)
                                    settingsMiscPress_15();
                                if(count == 16)
                                    settingsMiscPress_16();
                                if(count == 17)
                                    settingsMiscPress_17();
                                if(count == 18)
                                    settingsMiscPress_18();
                                if(count == 19)
                                    settingsMiscPress_19();
                                if(count == 20)
                                    settingsMiscPress_20();
                                if(count == 21)
                                    settingsMiscPress_21();
                                if(count == 22)
                                    settingsMiscPress_22();
                            }
                            if(type == 3) {
                                if (count == 0)
                                    settingsDrawerPress_0();
                                if (count == 1)
                                    settingsDrawerPress_1();
                                if (count == 2)
                                    settingsDrawerPress_2();
                                if (count == 3)
                                    settingsDrawerPress_3();
                                if (count == 4)
                                    settingsDrawerPress_4();
                                if (count == 5)
                                    settingsDrawerPress_5();
                                if (count == 6)
                                    settingsDrawerPress_6();
                                if (count == 7)
                                    settingsDrawerPress_7();
                            }
                            if(type == 4) {
                                if (count == 0)
                                    settingsHomePress_0();
                                if (count == 1)
                                    settingsHomePress_1();
                                if (count == 2)
                                    settingsHomePress_2();
                                if (count == 3)
                                    settingsHomePress_3();
                                if (count == 4)
                                    settingsHomePress_4();
                                if (count == 5)
                                    settingsHomePress_5();
                                if (count == 6)
                                    settingsHomePress_6();
                                if (count == 7)
                                    settingsHomePress_7();
                                if (count == 8)
                                    settingsHomePress_8();
                                if (count == 9)
                                    settingsHomePress_9();
                                if (count == 10)
                                    settingsHomePress_10();
                                if (count == 11)
                                    settingsHomePress_11();
                                if (count == 12)
                                    settingsHomePress_12();
                                if (count == 13)
                                    settingsHomePress_13();
                                if (count == 14)
                                    settingsHomePress_14();
                                if (count == 15)
                                    settingsHomePress_15();
                                if (count == 16)
                                    settingsHomePress_16();
                                if (count == 17)
                                    settingsHomePress_17();
                                if (count == 18)
                                    settingsHomePress_18();
                                if (count == 19)
                                    settingsHomePress_19();
                                if (count == 20)
                                    settingsHomePress_20();
                                if (count == 21)
                                    settingsHomePress_21();
                                if (count == 22)
                                    settingsHomePress_22();
                                if (count == 23)
                                    settingsHomePress_23();
                                if (count == 24)
                                    settingsHomePress_24();
                                if (count == 25)
                                    settingsHomePress_25();
                                if (count == 26)
                                    settingsHomePress_26();
                                if (count == 27)
                                    settingsHomePress_27();
                                if (count == 28)
                                    settingsHomePress_28();
                                if (count == 29)
                                    settingsHomePress_29();
                                if (count == 30)
                                    settingsHomePress_30();
                                if (count == 31)
                                    settingsHomePress_31();
                                if (count == 32)
                                    settingsHomePress_32();
                                if (count == 33)
                                    settingsHomePress_33();
                                if (count == 34)
                                    settingsHomePress_34();
                                if (count == 35)
                                    settingsHomePress_35();
                                if (count == 36)
                                    settingsHomePress_36();
                                //-----
                                if (count == 38)
                                    settingsHomePress_38();
                                if (count == 42)
                                    settingsHomePress_42();
                                if (count == 43)
                                    settingsHomePress_43();
                            }
                        }
                    }
                }
                touchEnd(event, view);
                return true;
            }
        });
    }

    private void centerPositions(){
        if(wallpaperState.equals("Enabled")){
            if(statusState.equals("Enabled")){
                layoutParamsTypeC(homeWallpaperLayout, new int[]{direction(5)}, direction(8), R.id.home_tooltip, direction(9), R.id.home_status);
            } else {
                layoutParamsTypeB(homeWallpaperLayout, new int[]{direction(5)}, direction(8), R.id.home_tooltip);
            }
        }
        if(statusState.equals("Enabled")){
            if(wallpaperState.equals("Enabled")){
                layoutParamsTypeB(homeStatusLayout, new int[]{direction(6)}, direction(8), R.id.home_tooltip);
            } else {
                layoutParamsTypeB(homeStatusLayout, new int[]{direction(1)}, direction(8), R.id.home_tooltip);
            }
        }
        if(folderState.equals("Enabled")){
            if(wallpaperState.equals("Enabled")){
                layoutParamsTypeB(homeFolderALayout, new int[]{direction(5)}, direction(8), R.id.home_wallpaper);
                homeFolderALayout.setBackground(null);
                setMargins(this, homeFolderAIcon, 0, 0, 0, 0);
            } else {
                if(statusState.equals("Enabled")){
                    layoutParamsTypeB(homeFolderALayout, new int[]{direction(1)}, direction(8), R.id.home_status);
                    backgroundTypeA(this, homeFolderALayout,background(8), tintA, 3);
                    setMargins(this, homeFolderAIcon, 20, 20, 20, 20);
                } else {
                    layoutParamsTypeB(homeFolderALayout, new int[]{direction(1)}, direction(8), R.id.home_tooltip);
                    backgroundTypeA(this, homeFolderALayout, background(5), tintA, 3);
                    setMargins(this, homeFolderAIcon, 30, 30, 30, 30);
                }
            }
        }
    }

    private void emptyTouch(View view){
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    /*
       --------------------------
       [                        ]
       [     INITIALIZATION     ]
       [                        ]
       --------------------------
    */

    private void initialize(){
        safetyCheck();
        if(configurationsMatched){
            if(!fileExist(this, "CREATED")){
                initializeConfigurations();
                configurationsA();
                additionalScreen();
            } else {
                instanceCreated = true;
                configurationsA();
                homeScreen();
            }
        } else {
            initializeConfigurations();
            configurationsA();
            additionalError();
        }
    }

    private void safetyCheck(){
        if(getPackageName().equals(textB(99))){

            if(android.os.Build.VERSION.SDK_INT >= 21 && android.os.Build.VERSION.SDK_INT <= 33){

                if(BuildConfig.VERSION_CODE == 2 && BuildConfig.VERSION_NAME.equals(textB(101))){
                    try {
                        if(getPackageManager().getApplicationInfo(textB(99), 0).loadLabel(getPackageManager()).equals("Fozin")){

                            Bitmap icon = ((BitmapDrawable) getPackageManager().getApplicationIcon(textB(99))).getBitmap();
                            if(icon.sameAs(((BitmapDrawable) getDrawable(R.drawable.icon_19)).getBitmap())){

                                if(getPackageManager().getPackageInfo(textB(99), 0).installLocation == -1){
                                    configurationsMatched = true;
                                    /*for (Signature signature : getPackageManager().getPackageInfo(textB(99),
                                            PackageManager.GET_SIGNATURES).signatures) {
                                        MessageDigest digest = MessageDigest.getInstance("SHA1");
                                        digest.update(signature.toByteArray());
                                        byte[] array = digest.digest();

                                        final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                                                '9', 'A', 'B', 'C', 'D', 'E', 'F' };
                                        char[] hexChars = new char[array.length * 2];
                                        for (int i = 0; i < array.length; i++) {
                                            int j = array[i] & 0xFF;
                                            hexChars[i * 2] = hexArray[j >>> 4];
                                            hexChars[i * 2 + 1] = hexArray[j & 0x0F];
                                        }
                                        if(String.valueOf(hexChars).equalsIgnoreCase(textB(100)))
                                        //--- Missing Signature Check
                                    }*/
                                }
                            }
                        }
                    } catch (Exception e){}
                }
            }
        }
    }

    /*
       -------------------------------
       [                             ]
       [     ADDITIONAL - SCREEN     ]
       [                             ]
       -------------------------------
    */

    View additionalWelcomeView;
    RelativeLayout additionalWelcomeLayout, additionalWelcomeFrame1, additionalWelcomeFrame2, additionalWelcomeFrame3,
            additionalWelcomeFrame4;
    ImageView additionalWelcomeIcon1, additionalWelcomeIcon2, additionalWelcomeIcon3;
    TextView additionalWelcomeText1, additionalWelcomeText2, additionalWelcomeText3, additionalWelcomeText4,
            additionalWelcomeText5, additionalWelcomeText6, additionalWelcomeText7, additionalWelcomeText8;
    private void additionalWelcome(){
        if(additionalWelcomeView == null){
            additionalWelcomeView = inflater.inflate(R.layout.additional_welcome, null);
            additionalWelcomeLayout = additionalWelcomeView.findViewById(R.id.additional_welcome);
            additionalWelcomeFrame1 = additionalWelcomeView.findViewById(R.id.additional_welcome_frame_1);
            additionalWelcomeFrame2 = additionalWelcomeView.findViewById(R.id.additional_welcome_frame_2);
            additionalWelcomeFrame3 = additionalWelcomeView.findViewById(R.id.additional_welcome_frame_3);
            additionalWelcomeFrame4 = additionalWelcomeView.findViewById(R.id.additional_welcome_frame_4);
            additionalWelcomeIcon1 = additionalWelcomeView.findViewById(R.id.additional_welcome_icon_1);
            additionalWelcomeIcon2 = additionalWelcomeView.findViewById(R.id.additional_welcome_icon_2);
            additionalWelcomeIcon3 = additionalWelcomeView.findViewById(R.id.additional_welcome_icon_3);
            additionalWelcomeText1 = additionalWelcomeView.findViewById(R.id.additional_welcome_text_1);
            additionalWelcomeText2 = additionalWelcomeView.findViewById(R.id.additional_welcome_text_2);
            additionalWelcomeText3 = additionalWelcomeView.findViewById(R.id.additional_welcome_text_3);
            additionalWelcomeText4 = additionalWelcomeView.findViewById(R.id.additional_welcome_text_4);
            additionalWelcomeText5 = additionalWelcomeView.findViewById(R.id.additional_welcome_text_5);
            additionalWelcomeText6 = additionalWelcomeView.findViewById(R.id.additional_welcome_text_6);
            additionalWelcomeText7 = additionalWelcomeView.findViewById(R.id.additional_welcome_text_7);
            additionalWelcomeText8 = additionalWelcomeView.findViewById(R.id.additional_welcome_text_8);

            backgroundTypeC(this, additionalWelcomeFrame1, background(4), tintA);
            backgroundTypeA(this, additionalWelcomeFrame2, background(5), tintA, 3);
            backgroundTypeA(this, additionalWelcomeFrame3, background(8), tintA, 3);
            backgroundTypeA(this, additionalWelcomeFrame4, background(8), tintA, 3);

            imageTypeA(this, additionalWelcomeIcon1, icon(17), R.color.transparent, 160);
            imageTypeA(this, additionalWelcomeIcon2, icon(18), R.color.transparent, 85);
            imageTypeA(this, additionalWelcomeIcon3, drawable(2), tintA, 50);

            textType(this, additionalWelcomeText1, textB(88), tintA, fontAStyle);
            textType(this, additionalWelcomeText2, textB(89), tintA, fontAStyle);
            textType(this, additionalWelcomeText3, textC(26), tintA, fontAStyle);
            textType(this, additionalWelcomeText4, textB(90), tintA, fontAStyle);
            textType(this, additionalWelcomeText5, textB(91), tintA, fontBStyle);
            textType(this, additionalWelcomeText6, textC(24), tintA, fontAStyle);
            textType(this, additionalWelcomeText7, textB(92), tintA, fontBStyle);
            textType(this, additionalWelcomeText8, textB(93), tintA, fontBStyle);

            customTouchModeB(additionalWelcomeFrame3, "", -1, -1, 0);
        }
        if(theNestAdditionalLayout.findViewById(R.id.additional_welcome) == null){
            theNestAdditionalLayout.addView(additionalWelcomeView);

            layoutParamsTypeA(additionalWelcomeLayout, new int[]{direction(2)});
            setSize(additionalWelcomeLayout, size(2), size(1));
            setMargins(this, additionalWelcomeLayout, 20, 20, 20, 20);
        }
    }

    private void additionalWelcomePress_0(){
        theNestAdditionalLayout.removeView(additionalWelcomeView);
        additionalNotes();
    }

    //..........
    //--------------- ADDITIONAL --------------- []
    //------------------ NOTES ----------------- []
    //``````````

    View additionalNotesView;
    RelativeLayout additionalNotesLayout, additionalNotesFrame1, additionalNotesFrame2;
    ImageView additionalNotesSquare1, additionalNotesSquare2, additionalNotesLine1, additionalNotesLine2,
            additionalNotesIcon1;
    TextView additionalNotesText1, additionalNotesText2, additionalNotesText3;
    ScrollView additionalNotesScrollView;
    private void additionalNotes(){
        if(additionalNotesView == null){
            additionalNotesView = inflater.inflate(R.layout.additional_notes, null);
            additionalNotesLayout = additionalNotesView.findViewById(R.id.additional_notes);
            additionalNotesScrollView = additionalNotesView.findViewById(R.id.additional_notes_scroll_view);
            additionalNotesFrame1 = additionalNotesView.findViewById(R.id.additional_notes_frame_1);
            additionalNotesFrame2 = additionalNotesView.findViewById(R.id.additional_notes_frame_2);
            additionalNotesSquare1 = additionalNotesView.findViewById(R.id.additional_notes_square_1);
            additionalNotesSquare2 = additionalNotesView.findViewById(R.id.additional_notes_square_2);
            additionalNotesIcon1 = additionalNotesView.findViewById(R.id.additional_notes_icon_1);
            additionalNotesLine1 = additionalNotesView.findViewById(R.id.additional_notes_line_1);
            additionalNotesLine2 = additionalNotesView.findViewById(R.id.additional_notes_line_2);
            additionalNotesText1 = additionalNotesView.findViewById(R.id.additional_notes_text_1);
            additionalNotesText2 = additionalNotesView.findViewById(R.id.additional_notes_text_2);
            additionalNotesText3 = additionalNotesView.findViewById(R.id.additional_notes_text_3);

            backgroundTypeA(this, additionalNotesFrame1, background(10), tintA, 3);
            backgroundTypeA(this, additionalNotesFrame2, background(8), tintA, 3);
            backgroundTypeA(this, additionalNotesSquare1, background(8), tintA, 3);
            backgroundTypeA(this, additionalNotesSquare2, background(8), tintA, 3);

            imageTypeB(this, additionalNotesLine1, background(6), tintA);
            imageTypeB(this, additionalNotesLine2, background(6), tintA);
            imageTypeA(this, additionalNotesIcon1, drawable(2), tintA, 50);

            textType(this, additionalNotesText1, textC(76), tintA, fontAStyle);
            textType(this, additionalNotesText2, textB(91), tintA, fontBStyle);
            textType(this, additionalNotesText3, textB(94), tintA, fontBStyle);
            customTouchModeB(additionalNotesFrame2, "", -1, -1, 1);
        }
        if(theNestAdditionalLayout.findViewById(R.id.additional_notes) == null){
            theNestAdditionalLayout.addView(additionalNotesView);

            layoutParamsTypeA(additionalNotesLayout, new int[]{direction(2)});
            setSize(additionalNotesLayout, size(2), size(2));
            setMargins(this, additionalNotesLayout, 20, 20, 20, 20);
        }
    }

    private void additionalNotesPress_0(){
        theNestAdditionalLayout.removeView(additionalNotesView);
        additionalInfo();
    }

    //..........
    //--------------- ADDITIONAL --------------- []
    //------------------ info ------------------ []
    //``````````

    View additionalInfoView;
    RelativeLayout additionalInfoLayout, additionalInfoFrame1, additionalInfoFrame2;
    ImageView additionalInfoIcon1, additionalInfoIcon2, additionalInfoLine, additionalInfoAngle;
    TextView additionalInfoText1, additionalInfoText2, additionalInfoText3, additionalInfoText4;
    private void additionalInfo(){
        if(additionalInfoView == null){
            additionalInfoView = inflater.inflate(R.layout.additional_info, null);
            additionalInfoLayout = additionalInfoView.findViewById(R.id.additional_info);
            additionalInfoFrame1 = additionalInfoView.findViewById(R.id.additional_info_frame_1);
            additionalInfoFrame2 = additionalInfoView.findViewById(R.id.additional_info_frame_2);
            additionalInfoAngle = additionalInfoView.findViewById(R.id.additional_info_angle);
            additionalInfoLine = additionalInfoView.findViewById(R.id.additional_info_line);
            additionalInfoIcon1 = additionalInfoView.findViewById(R.id.additional_info_icon_1);
            additionalInfoIcon2 = additionalInfoView.findViewById(R.id.additional_info_icon_2);
            additionalInfoText1 = additionalInfoView.findViewById(R.id.additional_info_text_1);
            additionalInfoText2 = additionalInfoView.findViewById(R.id.additional_info_text_2);
            additionalInfoText3 = additionalInfoView.findViewById(R.id.additional_info_text_3);
            additionalInfoText4 = additionalInfoView.findViewById(R.id.additional_info_text_4);

            backgroundTypeA(this, additionalInfoFrame1, background(8), tintA, 3);
            backgroundTypeC(this, additionalInfoFrame2, background(4), tintA);

            imageTypeA(this, additionalInfoIcon1, icon(39), tintA, 120);
            imageTypeB(this, additionalInfoLine, background(6), tintA);
            imageTypeA(this, additionalInfoAngle, drawable(2), tintA, 40);

            textType(this, additionalInfoText1, textC(77), tintA, fontAStyle);
            textType(this, additionalInfoText2, textB(95), tintA, fontBStyle);
            textType(this, additionalInfoText3, textC(78), tintA, fontAStyle);
            textType(this, additionalInfoText4, "", tintA, fontBStyle);

            customTouchModeB(additionalInfoFrame1, "", -1, -1, 2);
            customTouchModeB(additionalInfoFrame2, "", -1, -1, 3);
        }
        if(theNestAdditionalLayout.findViewById(R.id.additional_info) == null){
            theNestAdditionalLayout.addView(additionalInfoView);

            layoutParamsTypeA(additionalInfoLayout, new int[]{direction(2)});
            setSize(additionalInfoLayout, size(1), size(1));
            setMargins(this, additionalInfoLayout, 20, 20, 20, 20);
            additionalInfoTips();
        }
    }

    private void additionalInfoPress_0(){
        create(TheNest.this, "CREATED", "# Setup Completed...");
        restart(this);
    }

    private void additionalInfoPress_1(){
        if(additionalInfoInt == 0) {
            additionalInfoInt = 1;
        } else {
            additionalInfoInt = 0;
        }
        additionalInfoTips();
    }

    private int additionalInfoInt = 0;
    private void additionalInfoTips(){
        if(additionalInfoInt == 0){
            imageTypeA(this, additionalInfoIcon2, icon(35), tintB, 50);
            additionalInfoText4.setText(textB(96));
        } else {
            imageTypeA(this, additionalInfoIcon2, icon(43), tintB, 50);
            additionalInfoText4.setText(textB(97));
        }
    }

    //..........
    //--------------- ADDITIONAL --------------- []
    //----------------- ERROR ------------------ []
    //``````````

    View additionalErrorView;
    RelativeLayout additionalErrorLayout, additionalErrorFrame1, additionalErrorFrame2, additionalErrorFrame3;
    ImageView additionalErrorIcon1, additionalErrorAngle;
    TextView additionalErrorLine1, additionalErrorLine2, additionalErrorText1, additionalErrorText2, additionalErrorText3;
    private void additionalError(){
        if(additionalErrorView == null){
            additionalErrorView = inflater.inflate(R.layout.additional_error, null);
            additionalErrorLayout = additionalErrorView.findViewById(R.id.additional_error);
            additionalErrorFrame1 = additionalErrorView.findViewById(R.id.additional_error_frame_1);
            additionalErrorFrame2 = additionalErrorView.findViewById(R.id.additional_error_frame_2);
            additionalErrorFrame3 = additionalErrorView.findViewById(R.id.additional_error_frame_3);
            additionalErrorAngle = additionalErrorView.findViewById(R.id.additional_error_angle);
            additionalErrorIcon1 = additionalErrorView.findViewById(R.id.additional_error_icon_1);
            additionalErrorText1 = additionalErrorView.findViewById(R.id.additional_error_text_1);
            additionalErrorText2 = additionalErrorView.findViewById(R.id.additional_error_text_2);
            additionalErrorText3 = additionalErrorView.findViewById(R.id.additional_error_text_3);
            additionalErrorLine1 = additionalErrorView.findViewById(R.id.additional_error_line_1);
            additionalErrorLine2 = additionalErrorView.findViewById(R.id.additional_error_line_2);

            backgroundTypeA(this, additionalErrorFrame1, background(12), tintA, 3);
            backgroundTypeA(this, additionalErrorFrame2, background(5), tintA, 3);
            backgroundTypeC(this, additionalErrorFrame3, background(3), tintA);

            imageTypeA(this, additionalErrorAngle, drawable(2), tintA, 40);
            imageTypeA(this, additionalErrorIcon1, icon(32), tintA, 50);

            textType(this, additionalErrorText1, textC(67), tintA, fontAStyle);
            textType(this, additionalErrorText2, textC(12), tintA, fontBStyle);
            textType(this, additionalErrorText3, textB(98), tintB, fontBStyle);
            textType(this, additionalErrorLine1, textA(9), tintA, Typeface.BOLD);
            textType(this, additionalErrorLine2, textA(9), tintA, Typeface.BOLD);

            customTouchModeB(additionalErrorFrame2, "", -1, -1, 4);
        }
        if(theNestRootLayout.findViewById(R.id.additional_error) == null){
            theNestRootLayout.addView(additionalErrorView);

            layoutParamsTypeA(additionalErrorLayout, new int[]{direction(2)});
            setSize(additionalErrorLayout, size(2), size(2));
            setMargins(this, additionalErrorLayout, 20, 20, 20, 20);
        }
    }

    private void additionalErrorPress_0(){
        Methods.startActivity(this, textB(99), Intent.ACTION_DELETE);
    }

    /*
       -------------------------
       [                       ]
       [     HOME - SCREEN     ]
       [                       ]
       -------------------------
    */

    private int homeScreenState;
    private void homeScreenStateA(){
        if(homeScreenState == 0){
            homeScreenState = 1;
            if(widgetState.equals("Enabled") && widgetComponent.equals("Clock 2"))
                homeWidgetClockB();

            if(widgetState.equals("Enabled") && widgetMode.equals("Device")){
                if(settingsHomeBBoolean) {
                    homeWidgetEnable();
                    settingsHomeBBoolean = false;
                } else {
                    if(homeWidgetLayout.findViewById(R.id.home_widget_e) == null){
                        if(homeWidgetHost != null)
                            homeWidgetHost.startListening();
                    }
                }
            }
            if(homeAppletView.findViewById(R.id.home_applet_b) != null){
                if(!isAppInstalled(this, homeAppletBString))
                    homeApplet(1);
            }
            if(homeAppletView.findViewById(R.id.home_applet_d) != null){
                if(homeAppletDInt == 1){
                    if(widgetState.equals("Disabled") || widgetMode.equals("[ NONE ]"))
                        homeApplet(1);
                }
                if(homeAppletDInt == 2){
                    if(wallpaperState.equals("Disabled") || widgetMode.equals("[ NONE ]"))
                        homeApplet(1);
                }
            }
            if(wallpaperState.equals("Enabled") && wallpaperMode.equals("Device"))
                homeWallpaperDevice();

            if(folderState.equals("Enabled") && theNestHomeLayout.findViewById(R.id.home_folder_b) != null)
                homeFolderInitialize(75);

            if(statusState.equals("Enabled") && statusMode.equals("Battery")){
                if(homeStatusReceiver_1 != null && homeStatusIntent_1 != null){
                    unregisterReceiver(homeStatusReceiver_1);
                    unregisterReceiver(homeStatusReceiver_2);
                    registerReceiver(homeStatusReceiver_1, homeStatusIntent_1);
                    registerReceiver(homeStatusReceiver_2, homeStatusIntent_2);
                }
            }
            if(shortcutState.equals("Enabled") && theNestHomeLayout.findViewById(R.id.home_shortcut_b) != null)
                homeShortcutInitialize(75);
        }
    }

    private void homeScreenStateB(){
        homeScreenState = 0;
        if(widgetState.equals("Enabled") && widgetMode.equals("Device")){
            if(homeWidgetHost != null)
                homeWidgetHost.stopListening();
        }
        try {
            homeTooltipStateA(homeTooltipLayout.getContext());
            homeTooltipHandler.removeCallbacks(homeTooltipRunnable);
        } catch (Exception e){}
        if(statusState.equals("Enabled") && statusMode.equals("Battery")){
            if(homeStatusReceiver_1 != null){
                unregisterReceiver(homeStatusReceiver_1);
                unregisterReceiver(homeStatusReceiver_2);
            }
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //-------------------- EDGE -------------------- []
    //``````````

    private void homeEdge(){
        homeEdgeA();
        homeEdgeB();
        homeEdgeC();
    }

    View homeEdgeAView;
    RelativeLayout homeEdgeALayout;
    TextView homeEdgeAText1, homeEdgeAText2;
    private void homeEdgeA(){
        if(homeEdgeAView == null){
            homeEdgeAView = inflater.inflate(R.layout.home_edge_a, null);
            homeEdgeALayout = homeEdgeAView.findViewById(R.id.home_edge_a);
            homeEdgeAText1 = homeEdgeAView.findViewById(R.id.home_edge_a_text_1);
            homeEdgeAText2 = homeEdgeAView.findViewById(R.id.home_edge_a_text_2);

            textType(this, homeEdgeAText1, textA(13), tintA, Typeface.BOLD);
            textType(this, homeEdgeAText2, textA(13), tintA, Typeface.BOLD);
        }
        homeEdgeCommon(R.id.home_edge_a, homeEdgeAView, homeEdgeALayout, 3, 5, 0);
    }

    View homeEdgeBView;
    RelativeLayout homeEdgeBLayout;
    TextView homeEdgeBText1, homeEdgeBText2;
    private void homeEdgeB(){
        if(homeEdgeBView == null){
            homeEdgeBView = inflater.inflate(R.layout.home_edge_b, null);
            homeEdgeBLayout = homeEdgeBView.findViewById(R.id.home_edge_b);
            homeEdgeBText1 = homeEdgeBView.findViewById(R.id.home_edge_b_text_1);
            homeEdgeBText2 = homeEdgeBView.findViewById(R.id.home_edge_b_text_2);

            textType(this, homeEdgeBText1, textA(13), tintA, Typeface.BOLD);
            textType(this, homeEdgeBText2, textA(13), tintA, Typeface.BOLD);
        }
        homeEdgeCommon(R.id.home_edge_b, homeEdgeBView, homeEdgeBLayout, 3, 6, 10);
    }

    View homeEdgeCView;
    RelativeLayout homeEdgeCLayout;
    TextView homeEdgeCText1, homeEdgeCText2, homeEdgeCText3;
    private void homeEdgeC(){
        if(homeEdgeCView == null){
            homeEdgeCView = inflater.inflate(R.layout.home_edge_c, null);
            homeEdgeCLayout = homeEdgeCView.findViewById(R.id.home_edge_c);
            homeEdgeCText1 = homeEdgeCView.findViewById(R.id.home_edge_c_text_1);
            homeEdgeCText2 = homeEdgeCView.findViewById(R.id.home_edge_c_text_2);
            homeEdgeCText3 = homeEdgeCView.findViewById(R.id.home_edge_c_text_3);

            textType(this, homeEdgeCText1, textA(13), tintA, Typeface.BOLD);
            textType(this, homeEdgeCText2, textA(13), tintA, Typeface.BOLD);
            textType(this, homeEdgeCText3, textA(13), tintA, Typeface.BOLD);
        }
        homeEdgeCommon(R.id.home_edge_c, homeEdgeCView, homeEdgeCLayout, 4, 6, 10);
    }

    private void homeEdgeCommon(int id, View view, RelativeLayout layout, int a, int b, int end){
        if(theNestHomeLayout.findViewById(id) == null){
            theNestHomeLayout.addView(view);
            layoutParamsTypeA(layout, new int[]{direction(a), direction(b)});
            setSize(layout, size(1), size(1));
            setMargins(this, layout, 0, 0, 0, end);
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------- WIDGET ------------------- []
    //``````````

    private void homeWidget(){
        if(widgetState.equals("Disabled")){
            theNestHomeLayout.removeView(homeWidgetView);
        } else {
            homeWidget0();
            homeWidgetLayout.removeAllViews();

            if(widgetMode.equals("[ NONE ]"))
                homeWidgetA();
            if(widgetMode.equals("Device"))
                homeWidgetB();

            if(widgetMode.equals("SimpliC")){
                if(widgetComponent.equals("Clock 1"))
                    homeWidgetC();
                if(widgetComponent.equals("Clock 2"))
                    homeWidgetD();
            }
        }
    }

    // -----[ HOME WIDGET VIEWS ]----- //

    View homeWidgetView;
    RelativeLayout homeWidgetLayout;
    private void homeWidget0(){
        if(homeWidgetView == null){
            homeWidgetView = inflater.inflate(R.layout.home_widget, null);
            homeWidgetLayout = homeWidgetView.findViewById(R.id.home_widget);
        }
        if(theNestHomeLayout.findViewById(R.id.home_widget) == null){
            theNestHomeLayout.addView(homeWidgetView);

            layoutParamsTypeA(homeWidgetLayout, new int[]{direction(3), direction(6)});
            setSize(homeWidgetLayout, size(1), size(1));
            setMargins(this, homeWidgetLayout, 45, 0, 40, 50);
        }
    }

    View homeWidgetAView;
    RelativeLayout homeWidgetALayout;
    TextView homeWidgetAText1, homeWidgetAText2;
    private void homeWidgetA(){
        if(homeWidgetAView == null){
            homeWidgetAView = inflater.inflate(R.layout.home_widget_a, null);
            homeWidgetALayout = homeWidgetAView.findViewById(R.id.home_widget_a);
            homeWidgetAText1 = homeWidgetAView.findViewById(R.id.home_widget_a_text_1);
            homeWidgetAText2 = homeWidgetAView.findViewById(R.id.home_widget_a_text_2);

            backgroundTypeA(this, homeWidgetAView, background(8), tintA, 3);
            textType(this, homeWidgetAText1, textA(0), tintA, Typeface.BOLD);
            textType(this, homeWidgetAText2, textC(0), tintA, fontAStyle);
            customTouchModeA(homeWidgetALayout, "", 4, -1);
        }
        if(homeWidgetLayout.findViewById(R.id.home_widget_a) == null)
            homeWidgetLayout.addView(homeWidgetAView);
    }

    View homeWidgetBView;
    RelativeLayout homeWidgetBLayout, homeWidgetResource, homeWidgetBFrame;
    ImageView homeWidgetBIcon;
    private void homeWidgetB(){
        if(homeWidgetBView == null){
            homeWidgetBView = inflater.inflate(R.layout.home_widget_b, null);
            homeWidgetBLayout = homeWidgetBView.findViewById(R.id.home_widget_b);
            homeWidgetResource = homeWidgetBView.findViewById(R.id.home_widget_b_resource);
            homeWidgetBFrame = homeWidgetBView.findViewById(R.id.home_widget_b_frame);
            homeWidgetBIcon = homeWidgetBView.findViewById(R.id.home_widget_b_icon);

            backgroundTypeA(this, homeWidgetBLayout, background(8), tintA, 3);
            backgroundTypeB(this, homeWidgetBFrame, drawable(2), background, 30);
            imageTypeA(this, homeWidgetBIcon, drawable(2), tintA, 30);
            customTouchModeB(homeWidgetBIcon, textC(9), 0, 0, 0);
        }
        if(homeWidgetLayout.findViewById(R.id.home_widget_b) == null){
            homeWidgetLayout.addView(homeWidgetBView);
            homeWidgetEnable();
        }
    }

    View homeWidgetCView;
    RelativeLayout homeWidgetCLayout;
    Analog homeWidgetClock;
    TextClock homeWidgetCText1, homeWidgetCText2;
    private void homeWidgetC(){
        if(homeWidgetCView == null){
            homeWidgetCView = inflater.inflate(R.layout.home_widget_c, null);
            homeWidgetCLayout = homeWidgetCView.findViewById(R.id.home_widget_c);
            homeWidgetClock = homeWidgetCView.findViewById(R.id.home_widget_clock);
            homeWidgetCText1 = homeWidgetCView.findViewById(R.id.home_widget_c_text_1);
            homeWidgetCText2 = homeWidgetCView.findViewById(R.id.home_widget_c_text_2);

            backgroundTypeA(this, homeWidgetCLayout, background(8), tintA, 3);
            textType(this, homeWidgetCText1, (String) homeWidgetCText1.getText(), tintA, fontBStyle);
            textType(this, homeWidgetCText2, (String) homeWidgetCText1.getText(), tintA, fontBStyle);
            customTouchModeB(homeWidgetCLayout, "", 0, 0, 3);
        }
        if(homeWidgetLayout.findViewById(R.id.home_widget_c) == null){
            homeWidgetLayout.addView(homeWidgetCView);
            setSize(homeWidgetCLayout, density(this, 185), density(this, 130));
        }
    }

    View homeWidgetDView;
    RelativeLayout homeWidgetDLayout;
    TextView homeWidgetDText4;
    TextClock homeWidgetDText1, homeWidgetDText2, homeWidgetDText3;
    private void homeWidgetD(){
        if(homeWidgetDView == null){
            homeWidgetDView = inflater.inflate(R.layout.home_widget_d, null);
            homeWidgetDLayout = homeWidgetDView.findViewById(R.id.home_widget_d);
            homeWidgetDText1 = homeWidgetDView.findViewById(R.id.home_widget_d_text_1);
            homeWidgetDText2 = homeWidgetDView.findViewById(R.id.home_widget_d_text_2);
            homeWidgetDText3 = homeWidgetDView.findViewById(R.id.home_widget_d_text_3);
            homeWidgetDText4 = homeWidgetDView.findViewById(R.id.home_widget_d_text_4);

            backgroundTypeA(this, homeWidgetDLayout, background(8), tintA, 3);

            textType(this, homeWidgetDText1, (String) homeWidgetDText1.getText(), tintA, fontAStyle);
            textType(this, homeWidgetDText2, (String) homeWidgetDText2.getText(), tintA, fontBStyle);
            textType(this, homeWidgetDText3, (String) homeWidgetDText3.getText(), tintA, fontBStyle);
            textType(this, homeWidgetDText4, "", tintA, fontBStyle);

            customTouchModeB(homeWidgetDLayout, "", 0, 0, 3);
        }
        if(homeWidgetLayout.findViewById(R.id.home_widget_d) == null){
            homeWidgetLayout.addView(homeWidgetDView);
            setSize(homeWidgetDLayout, density(this, 170), density(this, 130));
            homeWidgetClockB();
        }
    }

    View homeWidgetEView;
    RelativeLayout homeWidgetELayout, homeWidgetEFrame;
    TextView homeWidgetEText1, homeWidgetEText2, homeWidgetEText3;
    ImageView homeWidgetEIcon;
    private void homeWidgetE(){
        if(homeWidgetEView == null){
            homeWidgetEView = inflater.inflate(R.layout.home_widget_e, null);
            homeWidgetELayout = homeWidgetEView.findViewById(R.id.home_widget_e);
            homeWidgetEFrame = homeWidgetEView.findViewById(R.id.home_widget_e_frame);
            homeWidgetEIcon = homeWidgetEView.findViewById(R.id.home_widget_e_icon);
            homeWidgetEText1 = homeWidgetEView.findViewById(R.id.home_widget_e_text_1);
            homeWidgetEText2 = homeWidgetEView.findViewById(R.id.home_widget_e_text_2);
            homeWidgetEText3 = homeWidgetEView.findViewById(R.id.home_widget_e_text_3);

            backgroundTypeA(this, homeWidgetELayout, background(8), tintA, 3);

            textType(this, homeWidgetEText1, textC(67), tintA, fontAStyle);
            textType(this, homeWidgetEText2, textB(61), tintA, fontBStyle);
            textType(this, homeWidgetEText3, "", tintA, fontBStyle);

            customTouchModeB(homeWidgetEFrame, "", 0, 0, 1);
            customTouchModeB(homeWidgetEText2, "", 0, 0, 2);

            homeWidgetEText2.setVisibility(View.GONE);
        }
        if(homeWidgetLayout.findViewById(R.id.home_widget_e) == null)
            homeWidgetLayout.addView(homeWidgetEView);
    }

    // -----[ HOME WIDGET BUTTONS ]----- //

    public void homeWidgetHold(){
        if(theNestHomeLayout.findViewById(R.id.home_applet_c) == null)
            homeApplet(3);
        homeAppletToggle(0);
    }

    private void homeWidgetPress_0(){
        if(homeAppletLayout.findViewById(R.id.home_applet_d) == null)
            homeApplet(4);
        homeAppletOptions(1);
    }

    private void homeWidgetPress_1(){
        homeWidgetCommonA(homeWidgetEText2, homeWidgetEFrame);
    }

    private void homeWidgetPress_2(){
        homeWidgetCommonA(homeWidgetEFrame, homeWidgetEText2);
    }

    private void homeWidgetPress_3(){
        Intent i = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        startActivity(i);
    }

    // -----[ HOME WIDGET COMMON METHODS ]----- //

    private void homeWidgetCommonA(View view_1, View view_2){
        view_1.setVisibility(View.VISIBLE);
        view_2.setVisibility(View.GONE);
    }

    // -----[ HOME WIDGET RARE METHODS ]----- //

    private void homeWidgetEnable(){
        if(isAppInstalled(this, widgetComponent)){
            if(homeWidgetManager == null)
                homeWidgetManager = AppWidgetManager.getInstance(getApplicationContext());

            if(homeWidgetHost == null)
                homeWidgetHost = new AppWidgetHost(getApplicationContext(), 2062);

            homeWidgetHost.startListening();
            homeWidgetInfo = homeWidgetManager.getAppWidgetInfo(widgetID);
            if(homeWidgetInfo != null)
                homeWidgetInitiate(homeWidgetInfo);
        } else {
            homeWidgetRemove();
        }
    }

    private void homeWidgetInitiate(AppWidgetProviderInfo info){
        if(hostView != null && hostView.getChildCount() > 0)
            hostView.removeAllViews();

        if(hostView == null) {
            hostView = homeWidgetHost.createView(getApplicationContext(), widgetID, info);
            hostView.setAppWidget(widgetID, info);
        }
        widgetSize();
        homeWidgetManager.bindAppWidgetIdIfAllowed(widgetID, info.provider);
    }

    private void widgetSize(){
        int widgetWidth = 0;
        int widgetHeight = 0;
        if(widgetStyle.equals("A")){
            widgetWidth = 180;
            widgetHeight = 100;
        }
        if(widgetStyle.equals("B")){
            widgetWidth = 400;
            widgetHeight = 100;
        }
        if(widgetStyle.equals("C")){
            widgetWidth = 150;
            widgetHeight = 150;
        }
        if(widgetStyle.equals("D")){
            widgetWidth = 400;
            widgetHeight = 150;
        }
        setSize(homeWidgetBView, density(this, widgetWidth), density(this, widgetHeight));

        if(hostView != null && homeWidgetResource != null){
            int widgetW = 0;
            int widgetH = 0;
            if(homeWidgetInfo != null){
                widgetW = homeWidgetInfo.minWidth;
                widgetH = homeWidgetInfo.minHeight;
            }
            int dimen_a = 0;
            int dimen_b = 0;
            if(widgetStyle.equals("A")){
                dimen_a = widgetW - 100;
                dimen_b = widgetH - 100;
            }
            if(widgetStyle.equals("B")){
                dimen_a = widgetW + 100;
                dimen_b = widgetH + 50;
            }
            if(widgetStyle.equals("C") || widgetStyle.equals("D")){
                dimen_a = widgetW + 100;
                dimen_b = widgetH + 100;
            }

            if((widgetH) > 500){
                homeWidgetLayout.removeView(homeWidgetBView);
                homeWidgetE();
                homeWidgetEIcon.setImageBitmap(roundedBitmap(drawableIcon(this, widgetComponent, 50)));
                homeWidgetEText3.setText(homeWidgetInfo.loadLabel(getPackageManager()));
            } else {
                if(homeWidgetLayout.findViewById(R.id.home_widget_e) != null){
                    homeWidgetLayout.removeView(homeWidgetEView);
                    homeWidgetLayout.addView(homeWidgetBView);
                }
                if(homeWidgetInfo != null && homeWidgetInfo.resizeMode != AppWidgetProviderInfo.RESIZE_NONE)
                    try {
                        hostView.updateAppWidgetSize(null, widgetW, widgetH, dimen_a, dimen_b);
                    } catch (Exception e){}
                homeWidgetResource.removeAllViews();
                homeWidgetResource.addView(hostView);
            }
        }
    }

    AppWidgetManager homeWidgetManager;
    AppWidgetHost homeWidgetHost;
    AppWidgetHostView hostView;
    AppWidgetProviderInfo homeWidgetInfo;
    private void homeWidgetRemove(){
        edit(this, "Configurations - 02", "Widget Mode - " + widgetMode, "Widget Mode - [ NONE ]");
        edit(this, "Configurations - 02", "Widget Component - " + widgetComponent, "Widget Component - [ NONE ]");
        edit(this, "Configurations - 02", "Widget ID - " + widgetID, "Widget ID - 0");
        if(homeWidgetHost != null){
            homeWidgetHost.stopListening();
            homeWidgetHost.deleteAppWidgetId(widgetID);
            homeWidgetHost = null;
        }
        if(hostView != null){
            hostView.removeAllViews();
            hostView = null;
        }
        if(homeWidgetResource != null)
            homeWidgetResource.removeAllViews();
        homeWidgetManager = null;
        configurationsB();
        homeWidget();
    }

    private void homeWidgetClockB(){
        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.AM_PM) == Calendar.AM){
            if(calendar.get(Calendar.HOUR) <= 6){
                homeWidgetDText4.setText(textB(0));
            } else {
                homeWidgetDText4.setText(textB(1));
            }
        } else {
            if(calendar.get(Calendar.HOUR) < 6){
                homeWidgetDText4.setText(textB(2));
            } else {
                homeWidgetDText4.setText(textB(3));
            }
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------- APPLET ------------------- []
    //``````````

    private void homeApplet(int mode){
        homeApplet0();
        homeAppletLayout.removeAllViews();

        if(mode == 1)
            homeAppletA();

        if(mode == 2)
            homeAppletB();

        if(mode == 3)
            homeAppletC();

        if(mode == 4)
            homeAppletD();
    }

    // -----[ HOME APPLET VIEWS ]----- //

    View homeAppletView;
    RelativeLayout homeAppletLayout;
    private void homeApplet0(){
        if(homeAppletView == null){
            homeAppletView = inflater.inflate(R.layout.home_applet, null);
            homeAppletLayout = homeAppletView.findViewById(R.id.home_applet);
        }
        if(theNestHomeLayout.findViewById(R.id.home_applet) == null){
            theNestHomeLayout.addView(homeAppletView);
            setSize(homeAppletLayout, size(1), size(1));
            homeAppletPositions();
        }
    }

    View homeAppletAView;
    RelativeLayout homeAppletALayout;
    ImageView homeAppletAIcon;
    TextView homeAppletAText;
    private void homeAppletA(){
        if(homeAppletAView == null){
            homeAppletAView = inflater.inflate(R.layout.home_applet_a, null);
            homeAppletALayout = homeAppletAView.findViewById(R.id.home_applet_a);
            homeAppletAIcon = homeAppletAView.findViewById(R.id.home_applet_a_icon);
            homeAppletAText = homeAppletAView.findViewById(R.id.home_applet_a_text);

            backgroundTypeC(this, homeAppletALayout, background(3), tintA);
            imageTypeA(this, homeAppletAIcon, icon(43), tintB, 75);
            textType(this, homeAppletAText, appletText, tintB, fontAStyle);

            customTouchModeB(homeAppletAIcon, textC(6), 0, 1, 0);
            homeAppletText();
        }
        if(homeAppletLayout.findViewById(R.id.home_applet_a) == null)
            homeAppletLayout.addView(homeAppletAView);
    }

    View homeAppletBView;
    RelativeLayout homeAppletBLayout, homeAppletBFrame1, homeAppletBFrame2, homeAppletBFrame3, homeAppletBFrame4;
    ImageView homeAppletBIcon1, homeAppletBIcon2, homeAppletBIcon3, homeAppletBIcon4;
    TextView homeAppletBText;
    private void homeAppletB(){
        if(homeAppletBView == null){
            homeAppletBView = inflater.inflate(R.layout.home_applet_b, null);
            homeAppletBLayout = homeAppletBView.findViewById(R.id.home_applet_b);
            homeAppletBIcon1 = homeAppletBView.findViewById(R.id.home_applet_b_icon_1);
            homeAppletBIcon2 = homeAppletBView.findViewById(R.id.home_applet_b_icon_2);
            homeAppletBIcon3 = homeAppletBView.findViewById(R.id.home_applet_b_icon_3);
            homeAppletBIcon4 = homeAppletBView.findViewById(R.id.home_applet_b_icon_4);
            homeAppletBFrame1 = homeAppletBView.findViewById(R.id.home_applet_b_frame_1);
            homeAppletBFrame2 = homeAppletBView.findViewById(R.id.home_applet_b_frame_2);
            homeAppletBFrame3 = homeAppletBView.findViewById(R.id.home_applet_b_frame_3);
            homeAppletBFrame4 = homeAppletBView.findViewById(R.id.home_applet_b_frame_4);
            homeAppletBText = homeAppletBView.findViewById(R.id.home_applet_b_text);

            backgroundTypeC(this, homeAppletBFrame4, background(3), tintA);
            backgroundTypeA(this, homeAppletBFrame1, background(5), tintB, 3);
            backgroundTypeA(this, homeAppletBFrame2, background(5), tintB, 3);
            backgroundTypeA(this, homeAppletBFrame3, background(5), tintB, 3);

            imageTypeA(this, homeAppletBIcon2, icon(38), tintB, 30);
            imageTypeA(this, homeAppletBIcon3, icon(40), tintB, 30);
            imageTypeA(this, homeAppletBIcon4, icon(32), tintB, 30);

            textType(this, homeAppletBText, "", tintA, fontAStyle);

            customTouchModeB(homeAppletBFrame1, textC(10), 0, 1,2);
            customTouchModeB(homeAppletBFrame2, textC(11), 0, 1,1);
            customTouchModeB(homeAppletBFrame3, textC(12), 0, 1,6);
        }
        if(homeAppletLayout.findViewById(R.id.home_applet_b) == null){
            homeAppletLayout.addView(homeAppletBView);
            layoutParamsTypeA(homeAppletBLayout, new int[]{direction(2)});
        }
    }

    View homeAppletCView;
    RelativeLayout homeAppletCLayout, homeAppletCFrame;
    TextView homeAppletCText;
    ImageView homeAppletCCircle;
    private void homeAppletC(){
        if(homeAppletCView == null){
            homeAppletCView = inflater.inflate(R.layout.home_applet_c, null);
            homeAppletCLayout = homeAppletCView.findViewById(R.id.home_applet_c);
            homeAppletCText = homeAppletCView.findViewById(R.id.home_applet_c_text);
            homeAppletCFrame = homeAppletCView.findViewById(R.id.home_applet_c_frame);
            homeAppletCCircle = homeAppletCView.findViewById(R.id.home_applet_c_circle);

            backgroundTypeC(this, homeAppletCLayout, background(3), tintA);
            backgroundTypeA(this, homeAppletCFrame, background(7), ui, 3);
            textType(this, homeAppletCText, "", tintB, fontBStyle);
        }
        if(homeAppletLayout.findViewById(R.id.home_applet_c) == null){
            homeAppletLayout.addView(homeAppletCView);
        }
    }

    View homeAppletDView;
    RelativeLayout homeAppletDLayout, homeAppletDFrame1, homeAppletDFrame2, homeAppletDFrame3, homeAppletDFrame4,
            homeAppletDFrame5, homeAppletDFrame6;
    ImageView homeAppletDIcon1, homeAppletDIcon2, homeAppletDIcon3, homeAppletDIcon4;
    TextView homeAppletDText;
    private void homeAppletD(){
        if(homeAppletDView == null){
            homeAppletDView = inflater.inflate(R.layout.home_applet_d, null);
            homeAppletDLayout = homeAppletDView.findViewById(R.id.home_applet_d);
            homeAppletDFrame1 = homeAppletDView.findViewById(R.id.home_applet_d_frame_1);
            homeAppletDFrame2 = homeAppletDView.findViewById(R.id.home_applet_d_frame_2);
            homeAppletDFrame3 = homeAppletDView.findViewById(R.id.home_applet_d_frame_3);
            homeAppletDFrame4 = homeAppletDView.findViewById(R.id.home_applet_d_frame_4);
            homeAppletDFrame5 = homeAppletDView.findViewById(R.id.home_applet_d_frame_5);
            homeAppletDFrame6 = homeAppletDView.findViewById(R.id.home_applet_d_frame_6);
            homeAppletDIcon1 = homeAppletDView.findViewById(R.id.home_applet_d_icon_1);
            homeAppletDIcon2 = homeAppletDView.findViewById(R.id.home_applet_d_icon_2);
            homeAppletDIcon3 = homeAppletDView.findViewById(R.id.home_applet_d_icon_3);
            homeAppletDIcon4 = homeAppletDView.findViewById(R.id.home_applet_d_icon_4);
            homeAppletDText = homeAppletDView.findViewById(R.id.home_applet_d_text);

            backgroundTypeA(this, homeAppletDFrame1, background(12), tintA, 3);
            backgroundTypeC(this, homeAppletDFrame3, background(4), tintA);
            backgroundTypeA(this, homeAppletDFrame4, background(5), tintA, 3);
            backgroundTypeA(this, homeAppletDFrame5, background(5), tintA, 3);
            backgroundTypeA(this, homeAppletDFrame6, background(5), tintA, 3);

            imageTypeA(this, homeAppletDIcon2, icon(48), tintA, 40);
            imageTypeA(this, homeAppletDIcon3, icon(49), tintA, 40);
            imageTypeA(this, homeAppletDIcon4, icon(41), tintA, 40);

            textType(this, homeAppletDText, "", tintA, fontAStyle);

            customTouchModeB(homeAppletDFrame4, textC(10), 0, 1, 4);
            customTouchModeB(homeAppletDFrame5, textC(16), 0, 1, 5);
            customTouchModeB(homeAppletDFrame6, textC(19), 0, 1, 7);
        }
        if(homeAppletLayout.findViewById(R.id.home_applet_d) == null)
            homeAppletLayout.addView(homeAppletDView);
    }

    // -----[ HOME APPLET BUTTONS ]----- //

    private void homeAppletPress_0(){
        homeScreenStateB();
        theNestRootLayout.removeView(theNestHomeView);
        settingsScreen();
    }

    private void homeAppletPress_1(){
        if(isAppInstalled(this, homeAppletBString)){
            Methods.startActivity(this, homeAppletBString, Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        } else {
            homeApplet(1);
        }
    }

    private void homeAppletPress_2(){
        if(homeAppletBInt == 1)
            homeFolderRemove(homeAppletBString);

        if(homeAppletBInt == 2)
            homeShortcutRemove(homeAppletBString);

        homeApplet(1);
    }

    private void homeAppletPress_3(){
        if(homeAppletCString2.equals("Enabled")) {
            edit(this,  "Configurations - 02", homeAppletCString1 + " State - Enabled", homeAppletCString1 + " State - Disabled");
        } else {
            edit(this, "Configurations - 02", homeAppletCString1 + " State - Disabled", homeAppletCString1 + " State - Enabled");
        }
        configurationsB();
        if(homeAppletCInt == 0){
            homeWidget();
            homeAppletPositions();
        }
        if(homeAppletCInt == 1){
            homeWallpaper();
            centerPositions();
        }
        if(homeAppletCInt == 2){
            homeFolder();
            homeWallpaper();
            homeStatus();
            centerPositions();
            InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
        if(homeAppletCInt == 3){
            homeStatus();
            centerPositions();
        }
        if(homeAppletCInt == 4)
            homeShortcut();

        homeAppletToggle(homeAppletCInt);
    }

    private void homeAppletPress_4(){
        if(homeAppletDInt == 1)
            homeWidgetRemove();

        if(homeAppletDInt == 2)
            homeWallpaperRemove();

        homeApplet(1);
    }

    private void homeAppletPress_5(){
        if(homeAppletDInt == 1){
            String style = "";
            if(widgetStyle.equals("A"))
                style = "B";
            if(widgetStyle.equals("B"))
                style = "C";
            if(widgetStyle.equals("C"))
                style = "D";
            if(widgetStyle.equals("D"))
                style = "A";

            edit(this, "Configurations - 02",   "Widget Style - " + widgetStyle,  "Widget Style - " +  style);
            configurationsB();
            widgetSize();
        }
    }

    private void homeAppletPress_6(){
        if(isAppInstalled(this, homeAppletBString)){
            Methods.startActivity(this, homeAppletBString, Intent.ACTION_DELETE);
        } else {
            homeApplet(1);
        }
    }

    private void homeAppletPress_7(){
        if(homeAppletDInt == 2){
            try {
                homeWallpaperManager.forgetLoadedWallpaper();
            } catch (Exception e){}
            imageTypeD(homeWallpaperCImage, homeWallpaperManager.getDrawable(), 200);
        }
    }

    // -----[ HOME APPLET METHODS ]----- //

    private void homeAppletPositions(){
        if(theNestHomeLayout.findViewById(R.id.home_widget) == null) {
            layoutParamsTypeA(homeAppletLayout, new int[]{direction(1), direction(3)});
            setMargins(this, homeAppletLayout, 45, 0, 40, 60);
        } else {
            layoutParamsTypeB(homeAppletLayout, new int[]{direction(1)}, direction(8), R.id.home_widget);
            setMargins(this, homeAppletLayout, 20, 0, 20, 20);
        }
    }

    private void homeAppletText(){
        if(appletText.isEmpty()){
            homeAppletAText.setVisibility(View.GONE);
        } else {
            homeAppletAText.setVisibility(View.VISIBLE);
        }
    }

    private int homeAppletBInt;
    private String homeAppletBString;
    private void homeAppletApp(String packageName, int focus){
        homeAppletBString = packageName;
        homeAppletBInt = focus;
        if(!systemApp(this, packageName)){
            homeAppletBFrame3.setVisibility(View.VISIBLE);
        } else {
            homeAppletBFrame3.setVisibility(View.GONE);
        }
        homeAppletBText.setText(appLabel(this, homeAppletBString));
        homeAppletBIcon1.setImageBitmap(appIcon(this, homeAppletBString, 50));
    }

    private int homeAppletCInt;
    private String homeAppletCString1;
    private String homeAppletCString2;
    private void homeAppletToggle(int mode){
        homeAppletCInt = mode;
        if(homeAppletCInt == 0){
            homeAppletCString1 = "Widget";
            homeAppletCString2 = widgetState;
            textType(this, homeAppletCText, textB(107), tintB, fontBStyle);
            homeAppletCText.setText(textB(4));
        }
        if(homeAppletCInt == 1){
            homeAppletCString1 = "Wallpaper";
            homeAppletCString2 = wallpaperState;
            homeAppletCText.setText(textB(5));
        }
        if(homeAppletCInt == 2){
            homeAppletCString1 = "Folder";
            homeAppletCString2 = folderState;
            homeAppletCText.setText(textB(6));
        }
        if(homeAppletCInt == 3){
            homeAppletCString1 = "Status";
            homeAppletCString2 = statusState;
            homeAppletCText.setText(textB(7));
        }
        if(homeAppletCInt == 4){
            homeAppletCString1 = "Shortcut";
            homeAppletCString2 = shortcutState;
            homeAppletCText.setText(textB(8));
        }
        if(homeAppletCString2.equals("Enabled")) {
            toggleMode(this, homeAppletCCircle, true);
        } else {
            toggleMode(this, homeAppletCCircle, false);
        }
        customTouchModeB(homeAppletCFrame, "", 0, 1, 3);
    }

    private int homeAppletDInt;
    private void homeAppletOptions(int mode){
        homeAppletDInt = mode;
        if(homeAppletDInt == 1){
            imageTypeA(this, homeAppletDIcon1, icon(46), tintB, 75);
            homeAppletDText.setText(textB(9));
            homeAppletDFrame5.setVisibility(View.VISIBLE);
            homeAppletDFrame6.setVisibility(View.GONE);
        }
        if(homeAppletDInt == 2){
            imageTypeA(this, homeAppletDIcon1, icon(47), tintB, 75);
            homeAppletDText.setText(textB(10));
            homeAppletDFrame5.setVisibility(View.GONE);
            homeAppletDFrame6.setVisibility(View.VISIBLE);
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------- TOOLTIP ------------------ []
    //``````````

    View homeTooltipView;
    static RelativeLayout homeTooltipLayout;
    static TextView homeTooltipText;
    private void homeTooltip(){
        if(homeTooltipView == null){
            homeTooltipView = inflater.inflate(R.layout.home_tooltip, null);
            homeTooltipLayout = homeTooltipView.findViewById(R.id.home_tooltip);
            homeTooltipText = homeTooltipView.findViewById(R.id.home_tooltip_text);

            backgroundTypeC(this, homeTooltipLayout, background(3), tintA);
        }
        if(theNestHomeLayout.findViewById(R.id.home_tooltip) == null){
            theNestHomeLayout.addView(homeTooltipView);
            layoutParamsTypeB(homeTooltipLayout, new int[]{direction(1)}, direction(8), R.id.home_applet);
            homeTooltipStateA(homeTooltipLayout.getContext());
        }
    }

    public static void homeTooltipStateA(Context context){
        homeTooltipText.setText("");
        setMargins(context, homeTooltipLayout, 0, 0, 0, 0);
        setSize(homeTooltipLayout, 0, 0);
    }

    public static void homeTooltipStateB(Context context, String tooltip){
        textType(context, homeTooltipText, tooltip, tintB, fontAStyle);
        setMargins(context, homeTooltipLayout, 20, 0, 20, 20);
        setSize(homeTooltipLayout, size(1), density(context, 35));
    }

    public static Handler homeTooltipHandler = new Handler();
    public static Runnable homeTooltipRunnable = new Runnable() {
        @Override
        public void run() {
            homeTooltipStateA(homeTooltipLayout.getContext());
        }
    };

    //..........
    //-------------------- HOME -------------------- []
    //------------------ WALLPAPER ----------------- []
    //``````````

    private void homeWallpaper(){
        if(wallpaperState.equals("Disabled")){
            theNestHomeLayout.removeView(homeWallpaperView);
        } else {
            homeWallpaper0();
            homeWallpaperLayout.removeAllViews();
            if(wallpaperMode.equals("[ NONE ]"))
                homeWallpaperA();

            if(wallpaperMode.equals("Device"))
                homeWallpaperDevice();

            if(wallpaperMode.equals("SimpliC")){
                if(wallpaperComponent.equals("Blocks"))
                    homeWallpaperD();
            }
            if(theNestHomeLayout.findViewById(R.id.home_folder_b) != null)
                theNestHomeLayout.removeView(homeWallpaperView);
        }
    }

    // -----[ HOME WALLPAPER VIEWS ]----- //

    View homeWallpaperView;
    RelativeLayout homeWallpaperLayout;
    private void homeWallpaper0(){
        if(homeWallpaperView == null){
            homeWallpaperView = inflater.inflate(R.layout.home_wallpaper, null);
            homeWallpaperLayout = homeWallpaperView.findViewById(R.id.home_wallpaper);
        }
        if(theNestHomeLayout.findViewById(R.id.home_wallpaper) == null){
            theNestHomeLayout.addView(homeWallpaperView);
            setMargins(this, homeWallpaperLayout,20,0,20, 20);
            setSize(homeWallpaperLayout, size(1), size(1));
        }
    }

    View homeWallpaperAView;
    RelativeLayout homeWallpaperALayout;
    TextView homeWallpaperAText1, homeWallpaperAText2;
    private void homeWallpaperA(){
        if(homeWallpaperAView == null){
            homeWallpaperAView = inflater.inflate(R.layout.home_wallpaper_a, null);
            homeWallpaperALayout = homeWallpaperAView.findViewById(R.id.home_wallpaper_a);
            homeWallpaperAText1 = homeWallpaperAView.findViewById(R.id.home_wallpaper_a_text_1);
            homeWallpaperAText2 = homeWallpaperAView.findViewById(R.id.home_wallpaper_a_text_2);

            backgroundTypeA(this, homeWallpaperALayout, background(8), tintA, 3);
            textType(this, homeWallpaperAText1, textA(11), tintA, Typeface.BOLD);
            textType(this, homeWallpaperAText2, textC(1), tintA, fontAStyle);
            customTouchModeA(homeWallpaperALayout, "", 5, -1);
        }
        if(homeWallpaperLayout.findViewById(R.id.home_wallpaper_a) == null){
            homeWallpaperLayout.addView(homeWallpaperAView);
        }
    }

    View homeWallpaperBView;
    RelativeLayout homeWallpaperBLayout;
    ImageView homeWallpaperBIcon;
    TextView homeWallpaperBText1, homeWallpaperBText2;
    private void homeWallpaperB(){
        if(homeWallpaperBView == null){
            homeWallpaperBView = inflater.inflate(R.layout.home_wallpaper_b, null);
            homeWallpaperBLayout = homeWallpaperBView.findViewById(R.id.home_wallpaper_b);
            homeWallpaperBIcon = homeWallpaperBView.findViewById(R.id.home_wallpaper_b_icon);
            homeWallpaperBText1 = homeWallpaperBView.findViewById(R.id.home_wallpaper_b_text_1);
            homeWallpaperBText2 = homeWallpaperBView.findViewById(R.id.home_wallpaper_b_text_2);

            backgroundTypeA(this, homeWallpaperBLayout, background(8), tintA, 3);
            textType(this, homeWallpaperBText1, "", tintA, fontAStyle);
            textType(this, homeWallpaperBText2, textB(11), tintA, fontBStyle);
        }
        if(homeWallpaperLayout.findViewById(R.id.home_wallpaper_b) == null){
            homeWallpaperLayout.addView(homeWallpaperBView);
            setSize(homeWallpaperBLayout, density(this, 200), size(1));
        }
        homeWallpaperBIcon.setImageBitmap(appIcon(this, homeWallpaperInfo.getPackageName(), 50));
        homeWallpaperBText1.setText(homeWallpaperInfo.loadLabel(getPackageManager()));
    }

    View homeWallpaperCView;
    RelativeLayout homeWallpaperCLayout, homeWallpaperCFrame;
    ImageView homeWallpaperCIcon, homeWallpaperCImage;
    private void homeWallpaperC(){
        if(homeWallpaperCView == null){
            homeWallpaperCView = inflater.inflate(R.layout.home_wallpaper_c, null);
            homeWallpaperCLayout = homeWallpaperCView.findViewById(R.id.home_wallpaper_c);
            homeWallpaperCImage = homeWallpaperCView.findViewById(R.id.home_wallpaper_c_image);
            homeWallpaperCIcon = homeWallpaperCView.findViewById(R.id.home_wallpaper_c_icon);
            homeWallpaperCFrame = homeWallpaperCView.findViewById(R.id.home_wallpaper_c_frame);

            backgroundTypeA(this, homeWallpaperCLayout, background(8), tintA, 3);

            backgroundTypeB(this, homeWallpaperCFrame, drawable(2), background, 30);
            imageTypeA(this, homeWallpaperCIcon, drawable(2), tintA, 30);
            imageTypeD(homeWallpaperCImage, homeWallpaperManager.getDrawable(),200);

            customTouchModeB(homeWallpaperCIcon, textC(9), 0, 2, 0);
        }
        if(homeWallpaperLayout.findViewById(R.id.home_wallpaper_c) == null){
            homeWallpaperLayout.addView(homeWallpaperCView);
            setSize(homeWallpaperCImage, density(this, 200), density(this, 200));
        }
    }

    View homeWallpaperDView;
    RelativeLayout homeWallpaperDLayout;
    ImageView homeWallpaperDImage;
    private void homeWallpaperD(){
        if(homeWallpaperDView == null){
            homeWallpaperDView = inflater.inflate(R.layout.home_wallpaper_d, null);
            homeWallpaperDLayout = homeWallpaperDView.findViewById(R.id.home_wallpaper_d);
            homeWallpaperDImage = homeWallpaperDView.findViewById(R.id.home_wallpaper_d_image);

            backgroundTypeA(this, homeWallpaperDLayout, background(8), tintA, 3);
            imageTypeA(this, homeWallpaperDImage, drawable(1), R.color.transparent, 200);
        }
        if(homeWallpaperLayout.findViewById(R.id.home_wallpaper_d) == null){
            homeWallpaperLayout.addView(homeWallpaperDView);
            setSize(homeWallpaperDImage, density(this, 200), density(this, 200));
        }
    }

    View homeWallpaperEView;
    RelativeLayout homeWallpaperELayout, homeWallpaperEFrame;
    ImageView homeWallpaperEIcon;
    ScrollView homeWallpaperEScrollView;
    TextView homeWallpaperEText1, homeWallpaperEText2;
    private void homeWallpaperE(){
        if(homeWallpaperEView == null){
            homeWallpaperEView = inflater.inflate(R.layout.home_wallpaper_e, null);
            homeWallpaperELayout = homeWallpaperEView.findViewById(R.id.home_wallpaper_e);
            homeWallpaperEIcon = homeWallpaperEView.findViewById(R.id.home_wallpaper_e_icon);
            homeWallpaperEText1 = homeWallpaperEView.findViewById(R.id.home_wallpaper_e_text_1);
            homeWallpaperEText2 = homeWallpaperEView.findViewById(R.id.home_wallpaper_e_text_2);
            homeWallpaperEFrame = homeWallpaperEView.findViewById(R.id.home_wallpaper_e_frame);
            homeWallpaperEScrollView = homeWallpaperEView.findViewById(R.id.home_wallpaper_e_scroll_view);

            backgroundTypeA(this, homeWallpaperELayout, background(8), tintA, 3);
            backgroundTypeA(this, homeWallpaperEFrame, background(9), tintA, 3);

            imageTypeA(this, homeWallpaperEIcon, icon(20), tintA, 40);

            textType(this, homeWallpaperEText1, textC(67), tintA, fontAStyle);
            textType(this, homeWallpaperEText2, textB(82), tintA, fontBStyle);

            customTouchModeB(homeWallpaperEFrame, textC(75), 0, 2, 1);
        }
        if(homeWallpaperLayout.findViewById(R.id.home_wallpaper_e) == null){
            homeWallpaperLayout.addView(homeWallpaperEView);
            setSize(homeWallpaperELayout, density(this, 200), density(this, 200));
        }
    }

    // -----[ HOME WALLPAPER BUTTONS ]----- //

    private void homeWallpaperHold(){
        if(theNestHomeLayout.findViewById(R.id.home_applet_c) == null)
            homeApplet(3);
        homeAppletToggle(1);
    }

    private void homeWallpaperPress_0(){
        if(homeAppletLayout.findViewById(R.id.home_applet_d) == null)
            homeApplet(4);
        homeAppletOptions(2);
    }

    private void homeWallpaperPress_1(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // -----[ HOME WALLPAPER METHODS ]----- //

    WallpaperManager homeWallpaperManager;
    WallpaperInfo homeWallpaperInfo;
    private void homeWallpaperDevice(){
        if(homeWallpaperManager == null)
            homeWallpaperManager = WallpaperManager.getInstance(this);
        homeWallpaperInfo = homeWallpaperManager.getWallpaperInfo();
        if (homeWallpaperInfo == null) {
            if(permission(this, "android.permission.READ_EXTERNAL_STORAGE")){
                if (homeWallpaperManager.getDrawable() != null) {
                    homeWallpaperC();
                } else {
                    homeWallpaperRemove();
                }
            } else {
                homeWallpaperE();
            }
        } else {
            homeWallpaperB();
        }
    }

    private void homeWallpaperRemove(){
        edit(this, "Configurations - 02", "Wallpaper Mode - " + wallpaperMode, "Wallpaper Mode - [ NONE ]");
        edit(this, "Configurations - 02", "Wallpaper Component - " + wallpaperComponent, "Wallpaper Component - [ NONE ]");
        configurationsB();
        homeWallpaper();
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------- FOLDER ------------------- []
    //``````````

    private void homeFolder(){
        if(folderState.equals("Disabled")){
            theNestHomeLayout.removeView(homeFolderAView);
            theNestHomeLayout.removeView(homeFolderBView);
        } else {
            homeFolderA();
        }
    }

    // -----[ HOME FOLDER VIEWS ]----- //

    View homeFolderAView;
    RelativeLayout homeFolderALayout;
    ImageView homeFolderAIcon;
    private void homeFolderA(){
        if(homeFolderAView == null){
            homeFolderAView = inflater.inflate(R.layout.home_folder_a, null);
            homeFolderALayout = homeFolderAView.findViewById(R.id.home_folder_a);
            homeFolderAIcon = homeFolderAView.findViewById(R.id.home_folder_a_icon);

            imageTypeA(this, homeFolderAIcon, icon(37), tintA, 65);
            customTouchModeB(homeFolderALayout, textC(2), 0, 3, 0);
        }
        if(theNestHomeLayout.findViewById(R.id.home_folder_a) == null){
            theNestHomeLayout.addView(homeFolderAView);
            setMargins(this, homeFolderALayout, 20, 0, 20, 0);
        }
    }

    View homeFolderBView;
    RelativeLayout homeFolderBLayout, homeFolderBFrame1, homeFolderBFrame2;
    TextView homeFolderBText1, homeFolderBText2;
    EditText homeFolderBEditText;
    ImageView homeFolderBIcon1, homeFolderBIcon2;
    private void homeFolderB(){
        if(homeFolderBView == null){
            homeFolderBView = inflater.inflate(R.layout.home_folder_b, null);
            homeFolderBLayout = homeFolderBView.findViewById(R.id.home_folder_b);
            homeFolderBFrame1 = homeFolderBView.findViewById(R.id.home_folder_b_frame_1);
            homeFolderBFrame2 = homeFolderBView.findViewById(R.id.home_folder_b_frame_2);
            homeFolderBText1 = homeFolderBView.findViewById(R.id.home_folder_b_text_1);
            homeFolderBText2 = homeFolderBView.findViewById(R.id.home_folder_b_text_2);
            homeFolderBEditText = homeFolderBView.findViewById(R.id.home_folder_b_edittext);
            homeFolderBIcon1 = homeFolderBView.findViewById(R.id.home_folder_b_icon_1);
            homeFolderBIcon2 = homeFolderBView.findViewById(R.id.home_folder_b_icon_2);

            backgroundTypeC(this, homeFolderBFrame1, background(6), tintA);
            backgroundTypeC(this, homeFolderBFrame2, background(4), tintA);

            imageTypeA(this, homeFolderBIcon1, icon(45), tintB, 50);
            imageTypeA(this, homeFolderBIcon2, icon(33), tintA, 70);

            textType(this, homeFolderBText1, "", tintB, fontBStyle);
            textType(this, homeFolderBText2, "", tintA, fontBStyle);

            customTypeB(this, homeFolderBEditText, textA(6), tintB, fontBStyle);

            customTouchModeB(homeFolderBIcon2, textC(15), 0, 3,1);
            customTouchModeB(homeFolderBFrame2, textC(14), 0, 3,2);

            homeFolderBEditText.setVisibility(View.GONE);
            homeFolderCommonA();
        }
        if(theNestHomeLayout.findViewById(R.id.home_folder_b) == null){
            theNestHomeLayout.addView(homeFolderBView);
            layoutParamsTypeB(homeFolderBLayout, new int[]{direction(1)}, direction(8), R.id.home_tooltip);
            setMargins(this, homeFolderBLayout, 20, 0, 40, 40);
            setSize(homeFolderBLayout, size(1), size(1));
        }
    }

    View homeFolderCView;
    RelativeLayout homeFolderCLayout, homeFolderCFrame;
    TextView homeFolderCText1, homeFolderCText2;
    private void homeFolderC(){
        if(homeFolderCView == null){
            homeFolderCView = inflater.inflate(R.layout.home_folder_c, null);
            homeFolderCLayout = homeFolderCView.findViewById(R.id.home_folder_c);
            homeFolderCFrame = homeFolderCView.findViewById(R.id.home_folder_c_frame);
            homeFolderCText1 = homeFolderCView.findViewById(R.id.home_folder_c_text_1);
            homeFolderCText2 = homeFolderCView.findViewById(R.id.home_folder_c_text_2);

            backgroundTypeC(this, homeFolderCLayout, background(13), tintA);
            textType(this, homeFolderCText1, textA(5), tintB, Typeface.BOLD);
            textType(this, homeFolderCText2, textC(8), tintB, fontAStyle);
            customTouchModeA(homeFolderCFrame, "", 6, -1);
        }
        if(homeFolderBLayout.findViewById(R.id.home_folder_c) == null) {
            homeFolderBLayout.addView(homeFolderCView);
            homeFolderBLayout.removeView(homeFolderDView);
            layoutParamsTypeB(homeFolderCLayout, new int[]{direction(5)}, direction(8), R.id.home_folder_b_icon_2);
            setMargins(this, homeFolderCLayout, 10, 0, 0, 0);
            setSize(homeFolderCLayout, size(1), size(1));
        }
    }

    View homeFolderDView;
    RelativeLayout homeFolderDLayout;
    LinearLayout homeFolderDListView;
    ScrollView homeFolderDScrollView;
    private void homeFolderD(){
        if(homeFolderDView == null){
            homeFolderDView = inflater.inflate(R.layout.home_folder_d, null);
            homeFolderDLayout = homeFolderDView.findViewById(R.id.home_folder_d);
            homeFolderDScrollView = homeFolderDView.findViewById(R.id.home_folder_d_scroll_view);
            homeFolderDListView = homeFolderDView.findViewById(R.id.home_folder_d_list_view);

            backgroundTypeC(this, homeFolderDLayout, background(13), tintA);
        }
        if(homeFolderBLayout.findViewById(R.id.home_folder_d) == null) {
            homeFolderBLayout.removeView(homeFolderCView);
            homeFolderBLayout.addView(homeFolderDView);
            layoutParamsTypeB(homeFolderDLayout, new int[]{direction(5)}, direction(8), R.id.home_folder_b_icon_2);
            setMargins(this, homeFolderDLayout, 10, 0, 0, 0);
            setSize(homeFolderDLayout, size(1), size(1));
        }
    }

    // -----[ HOME FOLDER BUTTONS ]----- //

    private void homeFolderHold(){
        if(theNestHomeLayout.findViewById(R.id.home_applet_c) == null)
            homeApplet(3);
        homeAppletToggle(2);
    }

    private void homeFolderHold_1(){
        if(isAppInstalled(TheNest.this, homeAppletBString)){
            if(homeAppletLayout.findViewById(R.id.home_applet_b) == null)
                homeApplet(2);
            homeAppletApp(homeAppletBString, 1);
        } else {
            homeShortcutRemove(homeAppletBString);
        }
    }

    private void homeFolderPress_0(){
        theNestHomeLayout.removeView(homeWallpaperView);
        theNestHomeLayout.removeView(homeStatusView);
        theNestHomeLayout.removeView(homeFolderAView);
        homeFolderB();
        homeFolderInitialize(75);
    }

    private void homeFolderPress_1(){
        theNestHomeLayout.removeView(homeFolderBView);
        homeWallpaper();
        homeFolder();
        homeStatus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    private void homeFolderPress_2(){
        backgroundTypeC(this, homeFolderBFrame2, background(4), ui);
        imageTypeA(this, homeFolderBIcon1, icon(31), tintB, 50);
        customTouchModeB(homeFolderBFrame2, textC(13), 0, 3, 3);

        homeFolderBEditText.setVisibility(View.VISIBLE);
        homeFolderBText1.setVisibility(View.GONE);
        homeFolderCommonA();
    }

    private void homeFolderPress_3(){
        if(!homeFolderBEditText.getText().toString().isEmpty()){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

            edit(this, "Configurations - 02", "Folder Name - " + folderName, "Folder Name - " + homeFolderBEditText.getText().toString().trim());
            folderName = homeFolderBEditText.getText().toString().trim();

            backgroundTypeC(this, homeFolderBFrame2, background(4), tintA);
            imageTypeA(this, homeFolderBIcon1, icon(45), tintB, 50);
            customTouchModeB(homeFolderBFrame2, textC(14), 0,3,2);

            homeFolderBEditText.setVisibility(View.GONE);
            homeFolderBText1.setVisibility(View.VISIBLE);
            homeFolderCommonA();
        }
    }

    private void homeFolderPress_4(){
        if(isAppInstalled(TheNest.this, homeAppletBString)){
            try {
                Intent intent = getPackageManager().getLaunchIntentForPackage(homeAppletBString);
                startActivity(intent);
            } catch (Exception e){}
        } else {
            homeShortcutRemove(homeAppletBString);
        }
    }

    // -----[ HOME FOLDER COMMON METHODS ]----- //

    private void homeFolderCommonA(){
        homeFolderBText1.setText(folderName);
        homeFolderBEditText.setText(folderName);
    }

    private void homeFolderCommonB(){
        homeFolderC();
        homeFolderBText2.setText("");
    }

    private void homeFolderCommonD(){
        if(folderListArray.size() >= 10){
            homeFolderBText2.setText(textA(8) + " " + folderListArray.size());
        } else {
            homeFolderBText2.setText(textA(15) + " " + folderListArray.size());
        }
    }

    // -----[ HOME FOLDER RARE METHODS ]----- //

    List<String> folderListArray;
    private void homeFolderInitialize(int delay){
        if(fileExist(this, "Array - 01")){
            Handler homeFolderHandler = new Handler();
            Runnable homeFolderRunnable = new Runnable() {
                @Override
                public void run() {
                    folderListArray = (ArrayList<String>) read(TheNest.this, "Array - 01");
                    for (Iterator<String> iterator = folderListArray.iterator(); iterator.hasNext();) {
                        String value = iterator.next();
                        if (value.equals("REMOVED") || !isAppInstalled(TheNest.this, value))
                            iterator.remove();
                    }
                    if(folderListArray.size() == 0){
                        homeFolderCommonB();
                    } else {
                        homeFolderD();
                        homeFolderCommonD();
                        if(homeFolderDListView.getChildCount() != folderListArray.size()){
                            if(folderSortingOrder.equals("Alphabetically")){
                                Collections.sort(folderListArray, new Comparator<String>() {
                                    @Override
                                    public int compare(String a, String b) {
                                        return appLabel(TheNest.this, a).compareTo(appLabel(TheNest.this, b));
                                    }
                                });
                            } else {
                                Collections.sort(folderListArray);
                            }
                            homeFolderDListView.removeAllViews();
                            homeFolderInitialize(folderListArray, homeFolderDListView);
                        }
                    }
                }
            };
            homeFolderHandler.postDelayed(homeFolderRunnable, delay);
        } else {
            homeFolderCommonB();
        }
    }

    private void homeFolderRemove(String appletAppPackage){
        edit(this, "Array - 01",  appletAppPackage,"REMOVED");
        folderListArray.remove(appletAppPackage);
        for(int i = 0; i < homeFolderDListView.getChildCount(); ++i){
            TextView textView = homeFolderDListView.getChildAt(i).findViewById(R.id.linear_type_g_text);
            if(textView.getText().equals(appLabel(this, appletAppPackage)))
                homeFolderDListView.removeView(homeFolderDListView.getChildAt(i));
        }
        homeFolderCommonD();
        if(homeFolderDListView.getChildCount() == 0)
            homeFolderInitialize(0);
        settingsHomeEInt = 0;
    }

    View folderListView;
    RelativeLayout folderListFrame;
    ImageView folderListIcon;
    TextView folderListText;
    @SuppressLint("ClickableViewAccessibility")
    private void homeFolderInitialize(List<String> list, LinearLayout layout){
        for (int i = 0; i < list.size(); i++) {
            folderListView = inflater.inflate(R.layout.linear_type_g, layout, false);
            folderListFrame = folderListView.findViewById(R.id.linear_type_g_frame);
            folderListIcon = folderListView.findViewById(R.id.linear_type_g_icon);
            folderListText = folderListView.findViewById(R.id.linear_type_g_text);

            folderListIcon.setImageBitmap(appIcon(this, list.get(i), 40));
            textType(this, folderListText, appLabel(this, list.get(i)), tintB, fontBStyle);

            customTouchModeA(folderListView, list.get(i), 0, 0);
            layout.addView(folderListView);
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------- STATUS ------------------- []
    //``````````

    private void homeStatus(){
        if(statusState.equals("Disabled")){
            theNestHomeLayout.removeView(homeStatusView);
        } else {
            homeStatus0();
            homeStatusLayout.removeAllViews();

            if(statusMode.equals("[ NONE ]"))
                homeStatusA();

            if(statusMode.equals("Battery Status"))
                homeStatusB();
        }
    }

    // -----[ HOME STATUS VIEWS ]----- //

    View homeStatusView;
    RelativeLayout homeStatusLayout;
    private void homeStatus0(){
        if(homeStatusView == null){
            homeStatusView = inflater.inflate(R.layout.home_status,null);
            homeStatusLayout = homeStatusView.findViewById(R.id.home_status);
        }
        if(theNestHomeLayout.findViewById(R.id.home_status) == null){
            theNestHomeLayout.addView(homeStatusView);
            setSize(homeStatusLayout, size(1), size(1));
            setMargins(this, homeStatusLayout, 20, 0, 0, 20);
        }
    }

    View homeStatusAView;
    RelativeLayout homeStatusALayout;
    TextView homeStatusAText1, homeStatusAText2;
    private void homeStatusA(){
        if(homeStatusAView == null){
            homeStatusAView = inflater.inflate(R.layout.home_status_a, null);
            homeStatusALayout = homeStatusAView.findViewById(R.id.home_status_a);
            homeStatusAText1 = homeStatusAView.findViewById(R.id.home_status_a_text_1);
            homeStatusAText2 = homeStatusAView.findViewById(R.id.home_status_a_text_2);

            textType(this, homeStatusAText1, textA(12), tintA, fontAStyle);
            textType(this, homeStatusAText2, textC(3), tintA, fontAStyle);
            customTouchModeA(homeStatusALayout, "", 7, -1);
        }
        if(homeStatusLayout.findViewById(R.id.home_status_a) == null){
            homeStatusLayout.addView(homeStatusAView);
            setSize(homeStatusALayout, density(this, 100), size(1));
        }
    }

    View homeStatusBView;
    RelativeLayout homeStatusBLayout;
    ImageView homeStatusBIcon;
    TextView homeStatusBText1, homeStatusBText2;
    private void homeStatusB(){
        if(homeStatusBView == null){
            homeStatusBView = inflater.inflate(R.layout.home_status_b, null);
            homeStatusBLayout = homeStatusBView.findViewById(R.id.home_status_b);
            homeStatusBIcon = homeStatusBView.findViewById(R.id.home_status_b_icon);
            homeStatusBText1 = homeStatusBView.findViewById(R.id.home_status_b_text_1);
            homeStatusBText2 = homeStatusBView.findViewById(R.id.home_status_b_text_2);

            textType(this, homeStatusBText1, "", tintA, Typeface.BOLD);
            textType(this, homeStatusBText2, textB(12), tintA, fontBStyle);
            customTouchModeB(homeStatusLayout, textC(18), 0, 4, 0);
            homeStatusBText2.setVisibility(View.GONE);

            homeStatusIntent_1 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            registerReceiver(homeStatusReceiver_1, homeStatusIntent_1);

            homeStatusIntent_2 = new IntentFilter();
            homeStatusIntent_2.addAction(Intent.ACTION_POWER_CONNECTED);
            homeStatusIntent_2.addAction(Intent.ACTION_POWER_DISCONNECTED);
            registerReceiver(homeStatusReceiver_2, homeStatusIntent_2);
        }
        if(homeStatusLayout.findViewById(R.id.home_status_b) == null){
            homeStatusLayout.addView(homeStatusBView);
            homeStatusBatteryState();
        }
    }

    // -----[ HOME STATUS BUTTONS ]----- //

    private void homeStatusHold(){
        if(theNestHomeLayout.findViewById(R.id.home_applet_c) == null)
            homeApplet(3);
        homeAppletToggle(3);
    }

    private void homeStatusPress_0(){
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        if(powerManager.isPowerSaveMode()){
            Intent intent = new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS);
            startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_POWER_USAGE_SUMMARY);
            startActivity(intent);
        }
    }

    // -----[ HOME STATUS METHODS ]----- //

    IntentFilter homeStatusIntent_1;
    BroadcastReceiver homeStatusReceiver_1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED))
                homeStatusBatteryState();
        }
    };

    IntentFilter homeStatusIntent_2;
    BroadcastReceiver homeStatusReceiver_2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)
                    || intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED))
                homeStatusBatteryState();
        }
    };

    private void homeStatusBatteryState(){
        Intent intent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        int percent = (level * 100) / scale;
        int icon = 0;
        if(percent >= 0 && percent <= 5)
            icon = icon(21);

        if(percent >= 5 && percent <= 20)
            icon = icon(26);

        if(percent >= 21 && percent <= 40)
            icon = icon(25);

        if(percent >= 41 && percent <= 60)
            icon = icon(24);

        if(percent >= 61 && percent <= 80)
            icon = icon(23);

        if(percent >= 81 && percent <= 100)
            icon = icon(22);

        imageTypeC(this, homeStatusBIcon, icon, R.color.transparent, 150, 100);
        homeStatusBText1.setText(percent + "%");
        int homeStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        if(homeStatus == BatteryManager.BATTERY_STATUS_CHARGING || homeStatus == BatteryManager.BATTERY_STATUS_FULL){
            homeStatusBText2.setVisibility(View.VISIBLE);
        } else {
            homeStatusBText2.setVisibility(View.GONE);
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------- DRAWER ------------------- []
    //``````````

    private void homeDrawer(){
        homeDrawer0();
        homeDrawerLayout.removeAllViews();

        if(drawerMode.equals("Icon"))
            homeDrawerA();
    }

    // -----[ HOME DRAWER VIEWS ]----- //

    View homeDrawerView;
    RelativeLayout homeDrawerLayout;
    private void homeDrawer0(){
        if(homeDrawerView == null){
            homeDrawerView = inflater.inflate(R.layout.home_drawer, null);
            homeDrawerLayout = homeDrawerView.findViewById(R.id.home_drawer);
        }
        if(theNestHomeLayout.findViewById(R.id.home_drawer) == null){
            theNestHomeLayout.addView(homeDrawerView);
            layoutParamsTypeB(homeDrawerLayout, new int[]{direction(6)}, direction(7),  R.id.home_edge_c);
            setSize(homeDrawerLayout, size(1), size(1));
        }
    }

    View homeDrawerAView;
    RelativeLayout homeDrawerALayout;
    ImageView homeDrawerAIcon;
    private void homeDrawerA(){
        if(homeDrawerAView == null){
            homeDrawerAView = inflater.inflate(R.layout.home_drawer_a, null);
            homeDrawerALayout = homeDrawerAView.findViewById(R.id.home_drawer_a);
            homeDrawerAIcon = homeDrawerAView.findViewById(R.id.home_drawer_a_icon);
            customTouchModeB(homeDrawerALayout, textC(5), 0, 5, 0);
            homeDrawerPresets(homeDrawerAIcon);
        }
        if(homeDrawerLayout.findViewById(R.id.home_drawer_a) == null){
            homeDrawerLayout.addView(homeDrawerAView);
            layoutParamsTypeA(homeDrawerALayout, new int[]{direction(3)});
        }
    }

    // -----[ HOME DRAWER BUTTONS ]----- //

    private void homeDrawerPress_0(){
        homeScreenStateB();
        theNestRootLayout.removeView(theNestHomeView);
        drawerScreen();
    }

    // -----[ HOME DRAWER METHODS ]----- //

    private void homeDrawerPresets(ImageView imageView){
        int dimen = 0;
        if(drawerSize.equals("A"))
            dimen = 40;
        if(drawerSize.equals("B"))
            dimen = 60;
        if(drawerSize.equals("C"))
            dimen = 80;
        if(drawerSize.equals("D"))
            dimen = 100;
        if(drawerSize.equals("E"))
            dimen = 120;

        int icon = 0;
        if(drawerIcon.equals("BeeHive"))
            icon = icon(35);
        if(drawerIcon.equals("SimpliC"))
            icon = icon(56);

        imageTypeA(this, imageView, icon, tintA, dimen + 20);
        setSize(imageView, density(this, dimen), density(this, dimen));
        imageView.setAlpha(drawerAlpha);

        if(imageView == homeDrawerAIcon){
            int bottom = 0;
            int end = 0;
            if(drawerSize.equals("A")){
                bottom = 10;
                end = 72;
            }
            if(drawerSize.equals("B")){
                bottom = 10;
                end = 62;
            }
            if(drawerSize.equals("C")){
                bottom = 10;
                end = 52;
            }
            if(drawerSize.equals("D")){
                bottom = 5;
                end = 42;
            }
            if(drawerSize.equals("E")){
                bottom = 5;
                end = 32;
            }
            setMargins(this, homeDrawerLayout, 0, bottom, 0,  end);
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------ SHORTCUT ------------------ []
    //``````````

    private void homeShortcut() {
        if (shortcutState.equals("Disabled")) {
            theNestHomeLayout.removeView(homeShortcutView);
        } else {
            homeShortcut0();
            homeShortcutLayout.removeAllViews();
            homeShortcutInitialize(100);
        }
    }

    // -----[ HOME SHORTCUT VIEWS ]----- //

    View homeShortcutView;
    RelativeLayout homeShortcutLayout;
    private void homeShortcut0(){
        if(homeShortcutView == null){
            homeShortcutView = inflater.inflate(R.layout.home_shortcut, null);
            homeShortcutLayout = homeShortcutView.findViewById(R.id.home_shortcut);
        }
        if(theNestHomeLayout.findViewById(R.id.home_shortcut) == null){
            theNestHomeLayout.addView(homeShortcutView);
            layoutParamsTypeC(homeShortcutLayout, new int[]{direction(5)}, direction(9), R.id.home_edge_c, direction(7), R.id.home_edge_c);
            setSize(homeShortcutLayout, size(1), size(1));
            setMargins(this, homeShortcutLayout, 0, -57, 20, -100);
        }
    }

    View homeShortcutAView;
    RelativeLayout homeShortcutALayout, homeShortcutAFrame;
    TextView homeShortcutAText1, homeShortcutAText2;
    private void homeShortcutA(){
        if(homeShortcutAView == null){
            homeShortcutAView = inflater.inflate(R.layout.home_shortcut_a, null);
            homeShortcutALayout = homeShortcutAView.findViewById(R.id.home_shortcut_a);
            homeShortcutAFrame = homeShortcutAView.findViewById(R.id.home_shortcut_a_frame);
            homeShortcutAText1 = homeShortcutAView.findViewById(R.id.home_shortcut_a_text_1);
            homeShortcutAText2 = homeShortcutAView.findViewById(R.id.home_shortcut_a_text_2);

            backgroundTypeA(this, homeShortcutAFrame, background(7), tintA, 3);
            textType(this, homeShortcutAText1, textA(4), tintA, fontAStyle);
            textType(this, homeShortcutAText2, textC(4), tintA, fontAStyle);
            customTouchModeA(homeShortcutAFrame, "", 8, -1);
        }
        if(homeShortcutLayout.findViewById(R.id.home_shortcut_a) == null)
            homeShortcutLayout.addView(homeShortcutAView);
    }

    View homeShortcutBView;
    RelativeLayout homeShortcutBLayout, homeShortcutBFrame1, homeShortcutBFrame2;
    HorizontalScrollView homeShortcutBAppView;
    LinearLayout homeShortcutBAppLayout;
    TextView homeShortcutBText;
    private void homeShortcutB(){
        if(homeShortcutBView == null){
            homeShortcutBView = inflater.inflate(R.layout.home_shortcut_b, null);
            homeShortcutBLayout = homeShortcutBView.findViewById(R.id.home_shortcut_b);
            homeShortcutBFrame1 = homeShortcutBView.findViewById(R.id.home_shortcut_b_frame_1);
            homeShortcutBFrame2 = homeShortcutBView.findViewById(R.id.home_shortcut_b_frame_2);
            homeShortcutBAppView = homeShortcutBView.findViewById(R.id.home_shortcut_b_app_view);
            homeShortcutBAppLayout = homeShortcutBView.findViewById(R.id.home_shortcut_b_app_layout);
            homeShortcutBText = homeShortcutBView.findViewById(R.id.home_shortcut_b_text);

            backgroundTypeC(this, homeShortcutBFrame1, background(4), tintA);
            backgroundTypeA(this, homeShortcutBFrame2, background(7), tintA, 3);
            textType(this, homeShortcutBText, "", tintB, Typeface.BOLD);
        }
        if(homeShortcutLayout.findViewById(R.id.home_shortcut_b) == null)
            homeShortcutLayout.addView(homeShortcutBView);
    }

    // -----[ HOME SHORTCUT BUTTONS ]----- //

    private void homeShortcutHold(){
        if(theNestHomeLayout.findViewById(R.id.home_applet_c) == null)
            homeApplet(3);
        homeAppletToggle(4);
    }

    private void homeShortcutHold_1(){
        if(isAppInstalled(TheNest.this, homeAppletBString)){
            if(homeAppletLayout.findViewById(R.id.home_applet_b) == null){
                homeApplet(2);
            }
            homeAppletApp(homeAppletBString, 2);
        } else {
            homeShortcutRemove(homeAppletBString);
        }
    }

    private void homeShortcutPress_0(){
        if(isAppInstalled(TheNest.this, homeAppletBString)){
            try {
                Intent intent = getPackageManager().getLaunchIntentForPackage(homeAppletBString);
                startActivity(intent);
            } catch (Exception e){}
        } else {
            homeShortcutRemove(homeAppletBString);
        }
    }

    // -----[ HOME SHORTCUT METHODS ]----- //

    List<String> shortcutListArray;
    private void homeShortcutInitialize(int delay){
        if(fileExist(this, "Array - 02")){
            Handler homeFolderHandler = new Handler();
            Runnable homeFolderRunnable = new Runnable() {
                @Override
                public void run() {
                    shortcutListArray = (ArrayList<String>) read(TheNest.this, "Array - 02");
                    for (Iterator<String> iterator = shortcutListArray.iterator(); iterator.hasNext();) {
                        String value = iterator.next();
                        if (value.equals("REMOVED") || !isAppInstalled(TheNest.this, value))
                            iterator.remove();
                    }
                    if(shortcutListArray.size() == 0){
                        homeShortcutLayout.removeAllViews();
                        homeShortcutA();
                    } else {
                        homeShortcutB();
                        homeShortcutBText.setText(String.valueOf(shortcutListArray.size()));

                        if(homeShortcutBAppLayout.getChildCount() != shortcutListArray.size()){
                            homeShortcutBAppLayout.removeAllViews();
                            homeShortcutInitialize(shortcutListArray, homeShortcutBAppLayout);
                        }
                    }
                }
            };
            homeFolderHandler.postDelayed(homeFolderRunnable, delay);
        } else {
            homeShortcutA();
        }
    }

    private void homeShortcutRemove(String appletAppPackage){
        edit(this, "Array - 02",  appletAppPackage,"REMOVED");
        shortcutListArray.remove(appletAppPackage);
        if(homeShortcutBAppLayout.getChildCount() <= 4) {
            homeShortcutInitialize(0);
        } else {
            homeShortcutBText.setText(String.valueOf(shortcutListArray.size()));
            for(int i = 0; i < homeShortcutBAppLayout.getChildCount(); ++i){
                TextView textView = homeShortcutBAppLayout.getChildAt(i).findViewById(R.id.linear_type_a_text);
                if(textView.getText().toString().trim().equals(appLabel(this, appletAppPackage)))
                    homeShortcutBAppLayout.removeView(homeShortcutBAppLayout.getChildAt(i));
            }
        }
        settingsHomeHInt = 0;
    }

    View shortcutListView;
    RelativeLayout shortcutListFrame;
    ImageView shortcutListIcon;
    TextView shortcutListText;
    @SuppressLint("ClickableViewAccessibility")
    private void homeShortcutInitialize(List<String> list, LinearLayout layout){
        for (int i = 0; i < list.size(); i++) {
            shortcutListView = inflater.inflate(R.layout.linear_type_a, layout, false);
            shortcutListFrame = shortcutListView.findViewById(R.id.linear_type_a_frame);
            shortcutListIcon = shortcutListView.findViewById(R.id.linear_type_a_icon);
            shortcutListText = shortcutListView.findViewById(R.id.linear_type_a_text);

            shortcutListIcon.setImageBitmap(appIcon(this, list.get(i), 40));
            if(list.size() >= 5){
                setSize(homeShortcutBFrame2, density(this, 270), size(1));
            } else {
                setSize(homeShortcutBFrame2, size(1), size(1));
            }
            if(list.size() >= 3){
                homeShortcutBFrame1.setVisibility(View.VISIBLE);
            } else {
                homeShortcutBFrame1.setVisibility(View.GONE);
            }
            textType(this, shortcutListText, " " + appLabel(this, list.get(i)), tintA, fontBStyle);
            if(list.size() == 1){
                //shortcutListText.setVisibility(View.VISIBLE);
            } else {
                shortcutListText.setVisibility(View.GONE);
            }

            customTouchModeA(shortcutListFrame, list.get(i), 1, 1);
            layout.addView(shortcutListView);
        }
    }

    /*
       ---------------------------
       [                         ]
       [     DRAWER - SCREEN     ]
       [                         ]
       ---------------------------
    */

    private void drawerScreenStateA(int mode){
        if (theNestDrawerLayout.findViewById(R.id.drawer_applet) != null && !isAppInstalled(this, drawerAppletString)) {
            if(mode == 0)
                drawerRefresh();
            drawerAppletClose();
        }
        if (theNestDrawerLayout.findViewById(R.id.drawer_applet) != null && (drawerAppletCFrame6 != null
                && drawerAppletCFrame6.getVisibility() == View.VISIBLE)) {
            if(drawerAppletPermission())
                drawerAppOptions();
        }
        drawerExtra();
    }

    //..........
    //-------------------- DRAWER -------------------- []
    //--------------------- EDGE --------------------- []
    //``````````

    private void drawerEdge(){
        drawerEdgeA();
        drawerEdgeB();
    }

    View drawerEdgeAView;
    RelativeLayout drawerEdgeALayout;
    ImageView drawerEdgeASquare, drawerEdgeACircle1, drawerEdgeACircle2, drawerEdgeALine;
    TextView drawerEdgeALines;
    private void drawerEdgeA(){
        if(drawerEdgeAView == null){
            drawerEdgeAView = inflater.inflate(R.layout.drawer_edge_a, null);
            drawerEdgeALayout = drawerEdgeAView.findViewById(R.id.drawer_edge_a);
            drawerEdgeASquare = drawerEdgeAView.findViewById(R.id.drawer_edge_a_square);
            drawerEdgeACircle1 = drawerEdgeAView.findViewById(R.id.drawer_edge_a_circle_1);
            drawerEdgeACircle2 = drawerEdgeAView.findViewById(R.id.drawer_edge_a_circle_2);
            drawerEdgeALine = drawerEdgeAView.findViewById(R.id.drawer_edge_a_line);
            drawerEdgeALines = drawerEdgeAView.findViewById(R.id.drawer_edge_a_lines);

            imageTypeB(this, drawerEdgeACircle1, background(4), tintA);
            imageTypeB(this, drawerEdgeACircle2, background(4), tintA);
            textType(this, drawerEdgeALines, textA(13), tintA, Typeface.BOLD);
            backgroundTypeA(this, drawerEdgeASquare, background(8), tintA, 3);
            imageTypeB(this, drawerEdgeALine, background(6), tintA);
        }
        if(theNestDrawerLayout.findViewById(R.id.drawer_edge_a) == null){
            theNestDrawerLayout.addView(drawerEdgeAView);
            setMargins(this, drawerEdgeALayout, 10, 0, 20, 0);
        }
    }

    View drawerEdgeBView;
    RelativeLayout drawerEdgeBLayout;
    ImageView drawerEdgeBSquare, drawerEdgeBCircle1, drawerEdgeBCircle2, drawerEdgeBLine;
    TextView drawerEdgeBLines;
    private void drawerEdgeB(){
        if(drawerEdgeBView == null){
            drawerEdgeBView = inflater.inflate(R.layout.drawer_edge_b, null);
            drawerEdgeBLayout = drawerEdgeBView.findViewById(R.id.drawer_edge_b);
            drawerEdgeBSquare = drawerEdgeBView.findViewById(R.id.drawer_edge_b_square);
            drawerEdgeBCircle1 = drawerEdgeBView.findViewById(R.id.drawer_edge_b_circle_1);
            drawerEdgeBCircle2 = drawerEdgeBView.findViewById(R.id.drawer_edge_b_circle_2);
            drawerEdgeBLine = drawerEdgeBView.findViewById(R.id.drawer_edge_b_line);
            drawerEdgeBLines = drawerEdgeBView.findViewById(R.id.drawer_edge_b_lines);

            imageTypeB(this, drawerEdgeBCircle1, background(4), tintA);
            imageTypeB(this, drawerEdgeBCircle2, background(4), tintA);
            textType(this, drawerEdgeBLines, textA(13), tintA, Typeface.BOLD);
            backgroundTypeA(this, drawerEdgeBSquare, background(8), tintA, 3);
            imageTypeB(this, drawerEdgeBLine, background(6), tintA);
        }
        if(theNestDrawerLayout.findViewById(R.id.drawer_edge_b) == null){
            theNestDrawerLayout.addView(drawerEdgeBView);
            setMargins(this, drawerEdgeBLayout, 0, 10, 20, 0);
        }
    }

    private void drawerEdgePositions(){
        if (theNestDrawerLayout.findViewById(R.id.drawer_tiles) != null) {
            layoutParamsTypeB(drawerEdgeALayout, new int[]{direction(3), direction(5)}, direction(7), R.id.drawer_tiles);
            layoutParamsTypeB(drawerEdgeBLayout, new int[]{direction(4), direction(5)}, direction(8), R.id.drawer_tiles);
        } else {
            layoutParamsTypeB(drawerEdgeALayout, new int[]{direction(3), direction(5)}, direction(7), R.id.drawer_applet);
            layoutParamsTypeB(drawerEdgeBLayout, new int[]{direction(4), direction(5)}, direction(8), R.id.drawer_applet);
        }
    }

    //..........
    //-------------------- DRAWER -------------------- []
    //--------------------- TOOLTIP ------------------ []
    //``````````

    View drawerTooltipView;
    public static RelativeLayout drawerTooltipLayout, drawerTooltipFrame;
    public static TextView drawerTooltipText;
    private void drawerTooltip(){
        if(drawerTooltipView == null){
            drawerTooltipView = inflater.inflate(R.layout.drawer_tooltip, null);
            drawerTooltipLayout = drawerTooltipView.findViewById(R.id.drawer_tooltip);
            drawerTooltipFrame = drawerTooltipView.findViewById(R.id.drawer_tooltip_frame);
            drawerTooltipText = drawerTooltipView.findViewById(R.id.drawer_tooltip_text);

            backgroundTypeC(this, drawerTooltipFrame, background(3), tintA);
        }
        if(theNestDrawerLayout.findViewById(R.id.drawer_tooltip) == null){
            theNestDrawerLayout.addView(drawerTooltipView);
            layoutParamsTypeB(drawerTooltipLayout, new int[]{direction(3), direction(6)}, direction(10), R.id.drawer_edge_a);
            drawerTooltipStateA(this);
        }
    }

    private void drawerTooltipReset(){
        try {
            drawerTooltipStateA(homeTooltipLayout.getContext());
            drawerTooltipHandler.removeCallbacks(drawerTooltipRunnable);
        } catch (Exception e){}
    }

    public static void drawerTooltipStateA(Context context){
        drawerTooltipText.setText("");
        setMargins(context, drawerTooltipLayout, 35, 0, 0, 0);
        setSize(drawerTooltipLayout, 0, 0);
    }

    public static void drawerTooltipStateB(Context context, String tooltip){
        textType(context, drawerTooltipText, tooltip, tintB, fontAStyle);
        setMargins(context, drawerTooltipLayout, 20, 0, 20, 20);
        setSize(drawerTooltipLayout, size(1), size(1));
    }

    public static Handler drawerTooltipHandler= new Handler();
    public static Runnable drawerTooltipRunnable = new Runnable() {
        @Override
        public void run() {
            drawerTooltipStateA(drawerTooltipLayout.getContext());
        }
    };

    //..........
    //-------------------- DRAWER -------------------- []
    //---------------------- TILES ------------------- []
    //``````````

    private View drawerTilesView;
    private RelativeLayout drawerTilesLayout, drawerTilesFrame1, drawerTilesFrame2, drawerTilesFrame3, drawerTilesFrame4;
    private ImageView drawerTilesIcon;
    private TextView drawerTilesText;
    private ScrollView drawerTilesScrollView;
    private LinearLayout drawerTilesAlphabets;
    private void drawerTiles() {
        if (drawerTilesView == null) {
            drawerTilesView = inflater.inflate(R.layout.drawer_tiles, null);
            drawerTilesLayout = drawerTilesView.findViewById(R.id.drawer_tiles);
            drawerTilesFrame1 = drawerTilesView.findViewById(R.id.drawer_tiles_frame_1);
            drawerTilesFrame2 = drawerTilesView.findViewById(R.id.drawer_tiles_frame_2);
            drawerTilesFrame3 = drawerTilesView.findViewById(R.id.drawer_tiles_frame_3);
            drawerTilesFrame4 = drawerTilesView.findViewById(R.id.drawer_tiles_frame_4);
            drawerTilesIcon = drawerTilesView.findViewById(R.id.drawer_tiles_icon);
            drawerTilesText = drawerTilesView.findViewById(R.id.drawer_tiles_text);
            drawerTilesScrollView = drawerTilesView.findViewById(R.id.drawer_tiles_scroll_view);
            drawerTilesAlphabets = drawerTilesView.findViewById(R.id.drawer_tiles_alphabets);

            backgroundTypeB(this, drawerTilesFrame1, background(2), tintA, 90);
            backgroundTypeB(this, drawerTilesFrame2, background(2), tintA, 90);
            backgroundTypeA(this, drawerTilesFrame3, background(7), tintA, 3);
            imageTypeA(this, drawerTilesIcon, icon(41), tintA, 50);
            textType(this, drawerTilesText, "", tintA, Typeface.BOLD);

            customTouchModeB(drawerTilesFrame1, textC(19), 1, 0, 0);
        }
        if (theNestDrawerLayout.findViewById(R.id.drawer_tiles) == null) {
            theNestDrawerLayout.addView(drawerTilesView);
            layoutParamsTypeA(drawerTilesLayout, new int[]{direction(5), direction(2)});
            setMargins(this, drawerTilesLayout, 10, 5, 20, 0);
            if (drawerAppletLayout != null && theNestDrawerLayout.findViewById(R.id.drawer_applet) != null)
                theNestDrawerLayout.removeView(drawerTilesView);
        }
    }

    // -----[ DRAWER TILES BUTTONS ]----- //

    private void drawerTilesHold_0(){
        if(!drawerAlphabetsString.isEmpty()){
            drawerRefresh();
        }
        drawerAlphabetsString = "";
    }

    private void drawerTilesPress_0(){
        drawerRefresh();
    }

    private void drawerTilesPress_1(){
        List<String> temporary = new ArrayList<>();
        for(String string : drawerAppsArray){
            String label = appLabel(TheNest.this, string);
            if(label.startsWith(drawerAlphabetsString.toLowerCase())
                    || label.startsWith(drawerAlphabetsString.toUpperCase()))
                temporary.add(string);
        }
        drawerAppsCommonC(temporary);
    }

    // -----[ DRAWER TILES METHODS ]----- //

    private boolean drawerRefresh;
    private void drawerRefresh(){
        if(!drawerRefresh){
            drawerAppsFrame1.setVisibility(View.VISIBLE);
            //theNestDrawerLayout.addView(drawerExtraView);
            drawerRefresh = true;
            drawerAppsCount = 0;
            drawerAppsCommonA();
            drawerAppsList();
        }
    }

    List<String> drawerAlphabets;
    private void drawerTilesIndex(){
        if(drawerBrowseMode.equals("Index")){
            drawerAlphabets = new ArrayList<>();

            drawerAlphabetsString = "";
            for(String string : drawerAppsArray){
                String text = appLabel(TheNest.this, string);
                if(!drawerAlphabets.contains(text.substring(0, 1)))
                    drawerAlphabets.add(text.substring(0, 1));
            }
            drawerTilesAlphabets.removeAllViews();
            drawerTilesInitialize(drawerAlphabets, drawerTilesAlphabets);
            drawerTilesFrame3.setVisibility(View.VISIBLE);
        } else {
            drawerTilesFrame3.setVisibility(View.GONE);
        }
        drawerTilesText.setText(String.valueOf(drawerAppsArray.size()));
    }

    View linearCView;
    RelativeLayout linearCFrame;
    TextView linearCText;
    public static String drawerAlphabetsString = "";
    @SuppressLint("ClickableViewAccessibility")
    private void drawerTilesInitialize(List<String> list, LinearLayout layout){
        for (int i = 0; i < list.size(); i++) {
            linearCView = inflater.inflate(R.layout.linear_type_c, layout, false);
            linearCFrame = linearCView.findViewById(R.id.linear_type_c_frame);
            linearCText = linearCView.findViewById(R.id.linear_type_c_text);

            textType(this, linearCText, list.get(i), tintA, fontBStyle);
            customTouchModeA(linearCFrame, list.get(i), 2, 2);
            layout.addView(linearCView);
        }
    }

    //..........
    //-------------------- DRAWER -------------------- []
    //--------------------- APPS --------------------- []
    //``````````

    private View drawerAppsView;
    private RelativeLayout drawerAppsLayout, drawerAppsFrame1, drawerAppsFrame2;
    ScrollView drawerAppsScrollView1, drawerAppsScrollView2, drawerAppsScrollView3;
    ImageView drawerAppsIcon;
    TextView drawerAppsText;
    GridLayout drawerAppsGridLayout, drawerAppsShapeLayout;
    LinearLayout drawerAppsListLayout;
    private void drawerApps() {
        if (drawerAppsView == null) {
            drawerAppsView = inflater.inflate(R.layout.drawer_apps, null);
            drawerAppsLayout = drawerAppsView.findViewById(R.id.drawer_apps);
            drawerAppsFrame1 = drawerAppsView.findViewById(R.id.drawer_apps_frame_1);
            drawerAppsFrame2 = drawerAppsView.findViewById(R.id.drawer_apps_frame_2);
            drawerAppsIcon = drawerAppsView.findViewById(R.id.drawer_apps_icon);
            drawerAppsText = drawerAppsView.findViewById(R.id.drawer_apps_text);
            drawerAppsScrollView1 = drawerAppsView.findViewById(R.id.drawer_apps_scroll_view_1);
            drawerAppsScrollView2 = drawerAppsView.findViewById(R.id.drawer_apps_scroll_view_2);
            drawerAppsScrollView3 = drawerAppsView.findViewById(R.id.drawer_apps_scroll_view_3);
            drawerAppsGridLayout = drawerAppsView.findViewById(R.id.drawer_apps_grid_layout);
            drawerAppsListLayout = drawerAppsView.findViewById(R.id.drawer_apps_list_layout);
            drawerAppsShapeLayout = drawerAppsView.findViewById(R.id.drawer_apps_shape_layout);

            backgroundTypeA(this, drawerAppsFrame2, background(8), tintA, 3);
            imageTypeA(this, drawerAppsIcon, icon(61), tintA, 120);
            textType(this, drawerAppsText, textC(79), tintA, fontAStyle);
        }
        if (theNestDrawerLayout.findViewById(R.id.drawer_apps) == null) {
            theNestDrawerLayout.addView(drawerAppsView);
            layoutParamsTypeB(drawerAppsLayout, new int[]{direction(6), direction(4)}, direction(8), R.id.drawer_tooltip);
            setMargins(this, drawerAppsLayout, 20, 50, 0, 0);
        }
        drawerAppsList();
    }

    // -----[ DRAWER APPS BUTTONS ]----- //

    private void drawerAppsHold_0(){
        if(isAppInstalled(TheNest.this, homeAppletBString)){
            theNestDrawerLayout.removeView(drawerTilesView);
            drawerApplet0();
            drawerAppletCurrent(homeAppletBString);
        }
    }

    private void drawerAppsPress_0(){
        if(isAppInstalled(TheNest.this, homeAppletBString)){
            try {
                Intent intent = getPackageManager().getLaunchIntentForPackage(homeAppletBString);
                startActivity(intent);
            } catch (Exception e){}
        }
    }

    // -----[ DRAWER APPS COMMON ]----- //

    private void drawerAppsCommonA(){
        drawerAppsGridLayout.removeAllViews();
        drawerAppsListLayout.removeAllViews();
        drawerAppsShapeLayout.removeAllViews();
    }

    private void drawerAppsCommonB(int id_a, View view, int id_b, int id_c, int id_d, int mode){
        drawerListView = inflater.inflate(id_a, (ViewGroup) view, false);
        drawerListFrame = drawerListView.findViewById(id_b);
        drawerListIcon = drawerListView.findViewById(id_c);
        drawerListText = drawerListView.findViewById(id_d);

        if(mode == 0)
            backgroundTypeA(this, drawerListFrame, background(8), tintA, 3);

        if(mode == 1)
            backgroundTypeC(this, drawerListFrame, background(6), tintA);
    }

    private void drawerAppsCommonC(List<String> list){
        drawerAppsGridLayout.removeAllViews();
        drawerAppsListLayout.removeAllViews();
        drawerAppsShapeLayout.removeAllViews();
        drawerAppsInitialize(list);
    }

    // -----[ DRAWER APPS METHODS ]----- //

    private List<String> drawerAppsArray;
    private int drawerAppsCount = 0;
    private void drawerAppsList(){
        Handler drawerAppsHandler = new Handler();
        Runnable drawerAppsRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);

                List<ResolveInfo> drawerPackages = getPackageManager().queryIntentActivities(intent, PackageManager.GET_META_DATA);
                if(drawerAppsCount != drawerPackages.size()){
                    drawerAppsCount = drawerPackages.size();

                    if(drawerSortingOrder.equals("Alphabetically")){
                        Collections.sort(drawerPackages, new Comparator<ResolveInfo>() {
                            @Override
                            public int compare(ResolveInfo info_a, ResolveInfo info_b) {
                                return info_a.loadLabel(getPackageManager()).toString().compareTo(info_b.loadLabel(getPackageManager()).toString());
                            }
                        });
                    }

                    drawerAppsArray = new ArrayList<>();
                    for (ResolveInfo resolveInfo : drawerPackages){
                        if(!drawerAppsArray.contains(resolveInfo.activityInfo.packageName))
                            drawerAppsArray.add(resolveInfo.activityInfo.packageName);
                    }
                    if(fileExist(TheNest.this, "Array - 03")) {
                        List<String> readValues = read(TheNest.this, "Array - 03");
                        for (Iterator<String> iterator = drawerAppsArray.iterator(); iterator.hasNext();) {
                            String value = iterator.next();
                            if (readValues.contains(value))
                                iterator.remove();
                        }
                    }
                    drawerAppsCommonC(drawerAppsArray);
                    drawerTilesIndex();
                    drawerRefresh = false;
                    theNestDrawerLayout.removeView(drawerExtraView);
                    drawerAppsFrame1.setVisibility(View.GONE);
                }
            }
        };
        drawerAppsHandler.postDelayed(drawerAppsRunnable, 100);
    }

    View drawerListView;
    RelativeLayout drawerListFrame;
    ImageView drawerListIcon;
    TextView drawerListText;
    List<String> appNames;
    @SuppressLint("ClickableViewAccessibility")
    private void drawerAppsInitialize(List<String> list){
        for (int i = 0; i < list.size(); ++i) {
            if(drawerAppsViewType() == drawerAppsGridLayout)
                drawerAppsCommonB(R.layout.linear_type_d, drawerAppsGridLayout, R.id.linear_type_d_frame,
                        R.id.linear_type_d_icon, R.id.linear_type_d_text, 0);

            if(drawerAppsViewType() == drawerAppsListLayout)
                drawerAppsCommonB(R.layout.linear_type_e, drawerAppsListLayout, R.id.linear_type_e_frame,
                        R.id.linear_type_e_icon, R.id.linear_type_e_text, 1);

            if(drawerAppsViewType() == drawerAppsShapeLayout)
                drawerAppsCommonB(R.layout.linear_type_f, drawerAppsShapeLayout, R.id.linear_type_f_frame,
                        R.id.linear_type_f_icon, R.id.linear_type_f_text, -1);

            drawerListView.setTag(list.get(i));
            drawerListIcon.setImageBitmap(appIcon(this, list.get(i), 40));

            int tint;
            if(drawerAppsViewType() == drawerAppsListLayout) {
                tint = tintB;
            } else {
                tint = tintA;
            }
            textType(this, drawerListText, "", tint, fontBStyle);

            String app = appLabel(this, list.get(i));
            if(fileExist(this, "Array - 04")){
                if(appNames == null || appNames.size() == 0)
                    appNames = read(this, "Array - 04");
                for(String label : appNames){
                    if(label.startsWith(list.get(i)))
                        app = label.substring(list.get(i).length() + 3).trim();
                }
            }

            if(drawerAppsViewType() == drawerAppsListLayout){
                if(app.length() >= 12){
                    drawerListText.setText(app.substring(0, 12) + textA(16));
                } else {
                    drawerListText.setText(app);
                }
            } else {
                drawerListText.setText(app);
            }

            customTouchModeA(drawerListFrame, list.get(i), 3, 3);
            drawerAppsViewType().addView(drawerListView);
        }
    }

    private ViewGroup drawerAppsViewType(){
        ViewGroup view = null;
        if(drawerGridStyle.equals("Tiles"))
            view = drawerAppsGridLayout;

        if(drawerGridStyle.equals("List"))
            view = drawerAppsListLayout;

        if(drawerGridStyle.equals("Bubbles"))
            view = drawerAppsShapeLayout;
        return view;
    }

    //..........
    //-------------------- DRAWER -------------------- []
    //-------------------- EXTRA --------------------- []
    //``````````

    private View drawerExtraView;
    private RelativeLayout drawerExtraLayout, drawerExtraFrame;
    ImageView drawerExtraIcon;
    TextView drawerExtraText;
    private void drawerExtra() {
        if (drawerExtraView == null) {
            drawerExtraView = inflater.inflate(R.layout.drawer_extra, null);
            drawerExtraLayout = drawerExtraView.findViewById(R.id.drawer_extra);
            drawerExtraFrame = drawerExtraView.findViewById(R.id.drawer_extra_frame);
            drawerExtraIcon = drawerExtraView.findViewById(R.id.drawer_extra_icon);
            drawerExtraText = drawerExtraView.findViewById(R.id.drawer_extra_text);

            drawerExtraLayout.setBackgroundColor(ContextCompat.getColor(this, background));
            backgroundTypeA(this, drawerExtraFrame, background(8), tintA, 3);
            imageTypeA(this, drawerExtraIcon, icon(61), tintA, 120);
            textType(this, drawerExtraText, textC(79), tintA, fontAStyle);
        }
        if(drawerAppsArray == null || drawerAppsArray.size() == 0){
            if (theNestDrawerLayout.findViewById(R.id.drawer_extra) == null) {
                theNestDrawerLayout.addView(drawerExtraView);
                setSize(drawerExtraLayout, size(2), size(2));
                setMargins(this, drawerExtraLayout, 0, 0, 0, 0);
            }
        }
    }

    //..........
    //-------------------- DRAWER -------------------- []
    //-------------------- APPLET -------------------- []
    //``````````

    private View drawerAppletView;
    private RelativeLayout drawerAppletLayout;
    private void drawerApplet0() {
        if (drawerAppletView == null) {
            drawerAppletView = inflater.inflate(R.layout.drawer_applet, null);
            drawerAppletLayout = drawerAppletView.findViewById(R.id.drawer_applet);
        }
        if (theNestDrawerLayout.findViewById(R.id.drawer_applet) == null) {
            theNestDrawerLayout.addView(drawerAppletView);
            layoutParamsTypeA(drawerAppletLayout, new int[]{direction(5), direction(2)});
            setSize(drawerAppletLayout, size(1), size(1));
            setMargins(this, drawerAppletLayout, 20, 20, 20, 20);
            drawerAppletA();
        }
    }

    private View drawerAppletAView;
    private RelativeLayout drawerAppletALayout, drawerAppletAFrame1, drawerAppletAFrame2, drawerAppletAFrame3;
    private ImageView drawerAppletAIcon1, drawerAppletAIcon2, drawerAppletAIcon3, drawerAppletAIcon4;
    private TextView drawerAppletAText;
    private void drawerAppletA() {
        if (drawerAppletAView == null) {
            drawerAppletAView = inflater.inflate(R.layout.drawer_applet_a, null);
            drawerAppletALayout = drawerAppletAView.findViewById(R.id.drawer_applet_a);
            drawerAppletAFrame1 = drawerAppletAView.findViewById(R.id.drawer_applet_a_frame_1);
            drawerAppletAFrame2 = drawerAppletAView.findViewById(R.id.drawer_applet_a_frame_2);
            drawerAppletAFrame3 = drawerAppletAView.findViewById(R.id.drawer_applet_a_frame_3);
            drawerAppletAText = drawerAppletAView.findViewById(R.id.drawer_applet_a_text);
            drawerAppletAIcon1 = drawerAppletAView.findViewById(R.id.drawer_applet_a_icon_1);
            drawerAppletAIcon2 = drawerAppletAView.findViewById(R.id.drawer_applet_a_icon_2);
            drawerAppletAIcon3 = drawerAppletAView.findViewById(R.id.drawer_applet_a_icon_3);
            drawerAppletAIcon4 = drawerAppletAView.findViewById(R.id.drawer_applet_a_icon_4);

            imageTypeA(this, drawerAppletAIcon2, icon(45), tintA, 50);
            imageTypeA(this, drawerAppletAIcon3, icon(30), tintA, 50);
            imageTypeA(this, drawerAppletAIcon4, icon(33), tintA, 50);

            backgroundTypeA(this, drawerAppletAFrame1, background(9), tintA, 3);
            backgroundTypeA(this, drawerAppletAFrame2, background(5), tintA, 3);
            backgroundTypeA(this, drawerAppletAFrame3, background(5), tintA, 3);

            textType(this, drawerAppletAText, "", tintA, fontAStyle);

            customTouchModeB(drawerAppletAFrame2, textC(14), 1, 1,0);
            customTouchModeB(drawerAppletAFrame3, textC(9), 1, 1,8);
            customTouchModeB(drawerAppletAIcon4, textC(21), 1, 1,1);
        }
        if (drawerAppletLayout.findViewById(R.id.drawer_applet_a) == null) {
            drawerAppletLayout.addView(drawerAppletAView);
            layoutParamsTypeA(drawerAppletALayout, new int[]{direction(2)});
        }
    }

    private View drawerAppletBView;
    private RelativeLayout drawerAppletBLayout, drawerAppletBFrame1, drawerAppletBFrame2, drawerAppletBFrame3, drawerAppletBFrame4;
    private EditText drawerAppletBEditText;
    private ImageView drawerAppletBIcon1, drawerAppletBIcon2, drawerAppletBAngle;
    private void drawerAppletB() {
        if (drawerAppletBView == null) {
            drawerAppletBView = inflater.inflate(R.layout.drawer_applet_b, null);
            drawerAppletBLayout = drawerAppletBView.findViewById(R.id.drawer_applet_b);
            drawerAppletBFrame1 = drawerAppletBView.findViewById(R.id.drawer_applet_b_frame_1);
            drawerAppletBFrame2 = drawerAppletBView.findViewById(R.id.drawer_applet_b_frame_2);
            drawerAppletBFrame3 = drawerAppletBView.findViewById(R.id.drawer_applet_b_frame_3);
            drawerAppletBFrame4 = drawerAppletBView.findViewById(R.id.drawer_applet_b_frame_4);
            drawerAppletBEditText = drawerAppletBView.findViewById(R.id.drawer_applet_b_edit_text);
            drawerAppletBAngle = drawerAppletBView.findViewById(R.id.drawer_applet_b_angle);
            drawerAppletBIcon1 = drawerAppletBView.findViewById(R.id.drawer_applet_b_icon_1);
            drawerAppletBIcon2 = drawerAppletBView.findViewById(R.id.drawer_applet_b_icon_2);

            backgroundTypeC(this, drawerAppletBFrame1, background(3), tintA);
            backgroundTypeA(this, drawerAppletBFrame2, background(5), tintB, 3);
            backgroundTypeA(this, drawerAppletBFrame3, background(5), tintB, 3);
            backgroundTypeA(this, drawerAppletBFrame4, background(7), tintB, 3);

            imageTypeA(this, drawerAppletBAngle, drawable(2), tintA, 30);
            imageTypeA(this, drawerAppletBIcon1, icon(31), tintB, 50);
            imageTypeA(this, drawerAppletBIcon2, icon(42), tintB, 50);

            customTypeB(this, drawerAppletBEditText, textA(7), tintB, fontBStyle);

            emptyTouch(drawerAppletBFrame1);
            customTouchModeB(drawerAppletBFrame2, textC(13),1,1,2);
            customTouchModeB(drawerAppletBFrame3, textC(22),1,1,3);
        }
        if (drawerAppletALayout.findViewById(R.id.drawer_applet_b) == null) {
            drawerAppletALayout.addView(drawerAppletBView);
            layoutParamsTypeC(drawerAppletBLayout, new int[]{direction(1)},
                    direction(10), R.id.drawer_applet_a_frame_2,
                    direction(12), R.id.drawer_applet_a_frame_2);
            setSize(drawerAppletBLayout, size(1), size(1));
            setMargins(this, drawerAppletBLayout, 0, -10, 10, 0);
            drawerAppletBEditText.setText(drawerAppLabel);
        }
    }

    private View drawerAppletCView;
    private RelativeLayout drawerAppletCLayout, drawerAppletCFrame1, drawerAppletCFrame2, drawerAppletCFrame3,
            drawerAppletCFrame4, drawerAppletCFrame5, drawerAppletCFrame6, drawerAppletCFrame7;
    private ImageView drawerAppletCAngle, drawerAppletCIcon1, drawerAppletCIcon2, drawerAppletCIcon3, drawerAppletCIcon4,
            drawerAppletCIcon5;
    private TextView drawerAppletCText1, drawerAppletCText2;
    private ScrollView drawerAppletCScrollView;
    private void drawerAppletC() {
        if (drawerAppletCView == null) {
            drawerAppletCView = inflater.inflate(R.layout.drawer_applet_c, null);
            drawerAppletCLayout = drawerAppletCView.findViewById(R.id.drawer_applet_c);
            drawerAppletCScrollView = drawerAppletCView.findViewById(R.id.drawer_applet_c_scroll_view);
            drawerAppletCFrame1 = drawerAppletCView.findViewById(R.id.drawer_applet_c_frame_1);
            drawerAppletCFrame2 = drawerAppletCView.findViewById(R.id.drawer_applet_c_frame_2);
            drawerAppletCFrame3 = drawerAppletCView.findViewById(R.id.drawer_applet_c_frame_3);
            drawerAppletCFrame4 = drawerAppletCView.findViewById(R.id.drawer_applet_c_frame_4);
            drawerAppletCFrame5 = drawerAppletCView.findViewById(R.id.drawer_applet_c_frame_5);
            drawerAppletCFrame6 = drawerAppletCView.findViewById(R.id.drawer_applet_c_frame_6);
            drawerAppletCFrame7 = drawerAppletCView.findViewById(R.id.drawer_applet_c_frame_7);
            drawerAppletCAngle = drawerAppletCView.findViewById(R.id.drawer_applet_c_angle);
            drawerAppletCIcon1 = drawerAppletCView.findViewById(R.id.drawer_applet_c_icon_1);
            drawerAppletCIcon2 = drawerAppletCView.findViewById(R.id.drawer_applet_c_icon_2);
            drawerAppletCIcon3 = drawerAppletCView.findViewById(R.id.drawer_applet_c_icon_3);
            drawerAppletCIcon4 = drawerAppletCView.findViewById(R.id.drawer_applet_c_icon_4);
            drawerAppletCIcon5 = drawerAppletCView.findViewById(R.id.drawer_applet_c_icon_5);
            drawerAppletCText1 = drawerAppletCView.findViewById(R.id.drawer_applet_c_text_1);
            drawerAppletCText2 = drawerAppletCView.findViewById(R.id.drawer_applet_c_text_2);

            backgroundTypeC(this, drawerAppletCFrame1, background(3), tintA);
            backgroundTypeA(this, drawerAppletCFrame2, background(5), tintB, 3);
            backgroundTypeA(this, drawerAppletCFrame3, background(5), tintB, 3);
            backgroundTypeA(this, drawerAppletCFrame4, background(5), tintB, 3);
            backgroundTypeA(this, drawerAppletCFrame5, background(5), tintB, 3);
            backgroundTypeC(this, drawerAppletCFrame6, background(3), tintA);
            backgroundTypeA(this, drawerAppletCFrame7, background(9), tintB, 3);

            imageTypeA(this, drawerAppletCAngle, drawable(2), tintA, 30);
            imageTypeA(this, drawerAppletCIcon1, icon(40), tintB, 50);
            imageTypeA(this, drawerAppletCIcon2, icon(32), tintB, 50);
            imageTypeA(this, drawerAppletCIcon3, icon(38), tintB, 50);
            imageTypeA(this, drawerAppletCIcon4, icon(34), tintB, 50);
            imageTypeA(this, drawerAppletCIcon5, icon(20), tintB, 50);

            textType(this, drawerAppletCText1, textC(67), tintB, fontAStyle);
            textType(this, drawerAppletCText2, textB(83), tintB, fontBStyle);

            emptyTouch(drawerAppletCFrame1);
            customTouchModeB(drawerAppletCFrame2, textC(11), 1, 1,4);
            customTouchModeB(drawerAppletCFrame3, textC(12), 1, 1,5);
            customTouchModeB(drawerAppletCFrame4, textC(17), 1, 1,6);
            customTouchModeB(drawerAppletCFrame5, textC(23), 1, 1,7);
            customTouchModeB(drawerAppletCFrame7, textC(75), 1, 1,9);
        }
        if (drawerAppletALayout.findViewById(R.id.drawer_applet_c) == null) {
            drawerAppletALayout.addView(drawerAppletCView);
            layoutParamsTypeC(drawerAppletCLayout, new int[]{direction(1)},
                    direction(10), R.id.drawer_applet_a_frame_3,
                    direction(12), R.id.drawer_applet_a_frame_3);
            setSize(drawerAppletCLayout, size(1), size(1));
            setMargins(this, drawerAppletCLayout, 0, -45, 10, 0);
            drawerAppOptions();
        }
    }

    // -----[ DRAWER APPLET BUTTONS ]----- //

    private void drawerAppletPress_0(){
        drawerAppletCommonE(1);
    }

    private void drawerAppletPress_1(){
        drawerAppletClose();
        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    private void drawerAppletPress_2(){
        if(!drawerAppletBEditText.getText().toString().isEmpty()){
            if(!drawerAppLabel.equals(drawerAppletBEditText.getText().toString())){
                String label = drawerAppletBEditText.getText().toString().trim();
                if(!fileExist(this, "Array - 04")){
                    create(this, "Array - 04", drawerAppletString + " - " + label);
                } else {
                    try {
                        if (new Scanner(new File(getFilesDir(), "Array - 04")).useDelimiter("\\Z").next().contains(drawerAppletString)) {
                            edit(this, "Array - 04", drawerAppletString + " - " + drawerAppLabel, drawerAppletString + " - " + label);
                        } else {
                            create(this, "Array - 04", drawerAppletString + " - " + label);
                        }
                    } catch (Exception e) {}
                }
                drawerAppletCommonA(label);
            }
        }
    }

    private void drawerAppletPress_3(){
        String label = appLabel(this, drawerAppletString);
        if(!drawerAppLabel.equals(label)){
            edit(this, "Array - 04",
                    drawerAppletString + " - " + drawerAppLabel,
                    drawerAppletString + " - " + label);
            drawerAppletCommonA(label);
        }
    }

    private void drawerAppletPress_4(){
        drawerAppletCommonB(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    }

    private void drawerAppletPress_5(){
        drawerAppletCommonB(Intent.ACTION_DELETE);
    }

    private void drawerAppletPress_6(){
        if(isAppInstalled(this, drawerAppletString)){
            create(this, "Array - 03", drawerAppletString);
            drawerAppsArray.remove(drawerAppletString);
            for(int i = 0; i < drawerAppsViewType().getChildCount(); ++i){
                if(drawerAppsViewType().getChildAt(i).getTag().toString().equals(drawerAppletString)) {
                    drawerAppsViewType().removeView(drawerAppsViewType().getChildAt(i));
                }
            }
            drawerTilesIndex();
            if(drawerAppsViewType().getChildCount() == 0)
                drawerRefresh();
        }
        drawerAppletClose();
    }

    private void drawerAppletPress_7(){
        if(drawerAppletPermission()){
            if(isAppInstalled(this, drawerAppletString)){
                try {
                    ApplicationInfo ai = getPackageManager().getApplicationInfo(drawerAppletString, 0);
                    String app = ai.loadLabel(getPackageManager()).toString();

                    File f1 = new File(ai.publicSourceDir);
                    File f2;

                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                        f2 = new File(getExternalFilesDir(null).getAbsolutePath() + "/SimplifiCc/Apps");
                    } else{
                        f2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/SimplifiCc/Apps");
                    }

                    if(!f2.exists())
                        f2.mkdirs();

                    File f3 = new File(f2.getPath() + "/" + app + ".apk");

                    if(!drawerThreadState){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                drawerThreadState = true;
                                try {
                                    f3.createNewFile();
                                    InputStream in = new FileInputStream(f1);
                                    OutputStream out = new FileOutputStream(f3);
                                    byte[] bytes = new byte[1024];
                                    int i;
                                    while ((i = in.read(bytes)) > 0){
                                        out.write(bytes, 0, i);
                                    }
                                    in.close();
                                    out.close();
                                    //runOnUiThread(new Runnable() { public void run() {}});
                                } catch (Exception e){}
                                drawerThreadState = false;
                            }
                        }).start();
                    }
                    touchTip(this, textC(20), 1);
                } catch (Exception e) {}
            } else {
                drawerAppletClose();
            }
        } else {
            drawerAppletCFrame1.setVisibility(View.GONE);
            drawerAppletCFrame6.setVisibility(View.VISIBLE);
            drawerAppletCAngle.setVisibility(View.INVISIBLE);
        }
    }

    private void drawerAppletPress_8(){
        drawerAppletCommonE(2);
    }

    private void drawerAppletPress_9(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // -----[ DRAWER APPLET COMMON ]----- //

    private void drawerAppletCommonA(String label){
        ViewGroup view = null;
        int id = 0;
        if(drawerGridStyle.equals("Tiles")) {
            view = drawerAppsGridLayout;
            id = R.id.linear_type_d_text;
        }
        if(drawerGridStyle.equals("List")){
            view = drawerAppsListLayout;
            id = R.id.linear_type_e_text;
        }
        if(drawerGridStyle.equals("Bubbles")){
            view = drawerAppsShapeLayout;
            id = R.id.linear_type_f_text;
        }
        for(int i = 0; i < view.getChildCount(); ++i){
            TextView textView = view.getChildAt(i).findViewById(id);
            if(textView.getText().equals(drawerAppLabel))
                textView.setText(label);
        }
        appNames = read(this, "Array - 04");
        drawerAppletCurrent(drawerAppletString);
    }

    private void drawerAppletCommonB(String intent){
        if(isAppInstalled(this, drawerAppletString)){
            Methods.startActivity(this, drawerAppletString, intent);
        } else {
            drawerAppletClose();
        }
    }

    private void drawerAppletCommonC(int id, View view, RelativeLayout frame, ImageView icon){
        if (drawerAppletALayout.findViewById(id) != null) {
            drawerAppletALayout.removeView(view);
            buttonModeA(this, frame, icon, background(5), false);
        }
    }

    private void drawerAppletCommonD(int id, View view, RelativeLayout frame, ImageView icon){
        if (drawerAppletALayout.findViewById(id) != null) {
            drawerAppletALayout.removeView(view);
            buttonModeA(this, frame, icon, background(5), false);
        } else {
            if(drawerAppletInt == 1)
                drawerAppletB();
            if(drawerAppletInt == 2)
                drawerAppletC();
            buttonModeA(this, frame, icon, background(5), true);
        }
    }

    private void drawerAppletCommonE(int mode){
        if(isAppInstalled(this, drawerAppletString)){
            drawerAppletInt = mode;
            drawerAppletSwitch();
        } else {
            drawerAppletClose();
        }
    }

    // -----[ DRAWER APPLET METHODS ]----- //

    private int drawerAppletInt = 0;
    private void drawerAppletSwitch(){
        if(drawerAppletInt == 1){
            drawerAppletCommonD(R.id.drawer_applet_b, drawerAppletBView, drawerAppletAFrame2, drawerAppletAIcon2);
            drawerAppletCommonC(R.id.drawer_applet_c, drawerAppletCView, drawerAppletAFrame3, drawerAppletAIcon3);
        }
        if(drawerAppletInt == 2){
            drawerAppletCommonC(R.id.drawer_applet_b, drawerAppletBView, drawerAppletAFrame2, drawerAppletAIcon2);
            drawerAppletCommonD(R.id.drawer_applet_c, drawerAppletCView, drawerAppletAFrame3, drawerAppletAIcon3);
        }
        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    private boolean drawerAppletPermission(){
        boolean result = false;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            if(permission(this, "android.permission.WRITE_EXTERNAL_STORAGE") /*&&
                    permission(this, "android.permission.MANAGE_EXTERNAL_STORAGE")*/){
                result = true;
            }
        } else{
            if(permission(this, "android.permission.WRITE_EXTERNAL_STORAGE")){
                result = true;
            }
        }
        return result;
    }

    private String drawerAppletString = "";
    private String drawerAppLabel;
    private boolean drawerThreadState;
    private void drawerAppletCurrent(String appPackage){
        drawerEdgePositions();
        drawerAppletString = appPackage;
        drawerAppLabel = appLabel(this, drawerAppletString);
        if(fileExist(this, "Array - 04")){
            List<String> readValues = read(this, "Array - 04");
            for(String value : readValues){
                if(value.startsWith(appPackage)){
                    drawerAppLabel = value.substring(appPackage.length() + 3).trim();
                }
            }
        }
        drawerAppletAText.setText(drawerAppLabel);
        drawerAppletAIcon1.setImageBitmap(appIcon(this, appPackage, 50));

        if (drawerAppletLayout.findViewById(R.id.drawer_applet_b) != null)
            drawerAppletBEditText.setText(drawerAppLabel);

        if (drawerAppletLayout.findViewById(R.id.drawer_applet_c) != null)
            drawerAppOptions();
    }

    private void drawerAppOptions(){
        drawerAppletCFrame1.setVisibility(View.VISIBLE);
        drawerAppletCFrame6.setVisibility(View.GONE);
        drawerAppletCAngle.setVisibility(View.VISIBLE);
        drawerAppletCFrame3.setVisibility(View.VISIBLE);
        if(systemApp(this, drawerAppletString))
            drawerAppletCFrame3.setVisibility(View.GONE);
    }

    private void drawerAppletClose(){
        drawerAppletCommonC(R.id.drawer_applet_b, drawerAppletBView, drawerAppletAFrame2, drawerAppletAIcon2);
        drawerAppletCommonC(R.id.drawer_applet_c, drawerAppletCView, drawerAppletAFrame3, drawerAppletAIcon3);
        theNestDrawerLayout.removeView(drawerAppletView);
        theNestDrawerLayout.addView(drawerTilesView);
        drawerEdgePositions();
    }

    /*
       -----------------------------
       [                           ]
       [     SETTINGS - SCREEN     ]
       [                           ]
       -----------------------------
    */

    private void settingsScreenStateA(){
        if(settingsDrawerLayout != null && settingsDrawerLayout.findViewById(R.id.settings_drawer_b) != null)
            hiddenAppsInitialize();

        if(settingsHomeLayout != null && settingsHomeLayout.findViewById(R.id.settings_home_b) != null)
            homeWidgetState();

        if(settingsHomeLayout != null && settingsHomeLayout.findViewById(R.id.settings_home_d) != null)
            homeWallpaperState();

        if(settingsHomeLayout != null && settingsHomeLayout.findViewById(R.id.settings_home_e) != null)
            homeFolderState();

        if(settingsHomeLayout != null && settingsHomeLayout.findViewById(R.id.settings_home_f) != null)
            homeStatusState();

        if(settingsHomeLayout != null && settingsHomeLayout.findViewById(R.id.settings_home_h) != null)
            homeShortcutState();
    }

    //..........
    //-------------------- SETTINGS -------------------- []
    //---------------------- BUTTONS ------------------- []
    //``````````

    View settingsButtonsView;
    RelativeLayout settingsButtonsLayout, settingsButtonsFrame1, settingsButtonsFrame2, settingsButtonsFrame3,
            settingsButtonsFrame4;
    ImageView settingsButtonsIcon1, settingsButtonsIcon2, settingsButtonsIcon3;
    TextView settingsButtonsText1, settingsButtonsText2;
    private void settingsButtons(){
        if(settingsButtonsView == null){
            settingsButtonsView = inflater.inflate(R.layout.settings_buttons, null);
            settingsButtonsLayout = settingsButtonsView.findViewById(R.id.settings_buttons);
            settingsButtonsFrame1 = settingsButtonsView.findViewById(R.id.settings_buttons_frame_1);
            settingsButtonsFrame2 = settingsButtonsView.findViewById(R.id.settings_buttons_frame_2);
            settingsButtonsFrame3 = settingsButtonsView.findViewById(R.id.settings_buttons_frame_3);
            settingsButtonsFrame4 = settingsButtonsView.findViewById(R.id.settings_buttons_frame_4);
            settingsButtonsIcon1 = settingsButtonsView.findViewById(R.id.settings_buttons_icon_1);
            settingsButtonsIcon2 = settingsButtonsView.findViewById(R.id.settings_buttons_icon_2);
            settingsButtonsIcon3 = settingsButtonsView.findViewById(R.id.settings_buttons_icon_3);
            settingsButtonsText1 = settingsButtonsView.findViewById(R.id.settings_buttons_text_1);
            settingsButtonsText2 = settingsButtonsView.findViewById(R.id.settings_buttons_text_2);

            backgroundTypeA(this, settingsButtonsFrame1, background(5), tintA, 3);
            backgroundTypeA(this, settingsButtonsFrame2, background(5), tintA, 3);
            backgroundTypeA(this, settingsButtonsFrame3, background(5), tintA, 3);
            backgroundTypeA(this, settingsButtonsFrame4, background(5), tintA, 3);

            textType(this, settingsButtonsText1, textA(6), tintA, Typeface.BOLD);
            textType(this, settingsButtonsText2, textA(9) + textA(9), tintA, Typeface.BOLD);

            imageTypeA(this, settingsButtonsIcon1, icon(29), tintA, 65);
            imageTypeA(this, settingsButtonsIcon2, icon(36), tintA, 50);
            imageTypeA(this, settingsButtonsIcon3, icon(39), tintA, 50);

            customTouchModeB(settingsButtonsFrame1, "", 2, 0,0);
            customTouchModeB(settingsButtonsFrame2, "", 2, 0,1);
            customTouchModeB(settingsButtonsFrame3, "", 2, 0,2);
            customTouchModeB(settingsButtonsFrame4, "", 2, 0,3);
        }
        if(theNestSettingsLayout.findViewById(R.id.settings_buttons) == null){
            theNestSettingsLayout.addView(settingsButtonsView);
            layoutParamsTypeA(settingsButtonsLayout, new int[]{direction(3), direction(5)});
            setSize(settingsButtonsLayout, size(1), size(1));
            setMargins(this, settingsButtonsLayout, 20, 0, 20, 20);
        }
    }

    // -----[ SETTINGS BUTTONS ]----- //

    private void settingsButtonsPress_0(){
        if(settingsButtonsInt != 1){
            settingsButtonsInt = 1;
            settingsButtonsMode();
        }
    }

    private void settingsButtonsPress_1(){
        if(settingsButtonsInt != 2){
            settingsButtonsInt = 2;
            settingsButtonsMode();
        }
    }

    private void settingsButtonsPress_2(){
        if(settingsButtonsInt != 3){
            settingsButtonsInt = 3;
            settingsButtonsMode();
        }
    }

    private void settingsButtonsPress_3(){
        if(settingsButtonsInt != 4){
            settingsButtonsInt = 4;
            settingsButtonsMode();
        }
    }

    // -----[ SETTINGS BUTTONS METHODS ]----- //

    private int settingsButtonsInt;
    private void settingsButtonsMode(){
        theNestSettingsLayout.removeView(settingsAboutView);
        theNestSettingsLayout.removeView(settingsMiscView);
        theNestSettingsLayout.removeView(settingsDrawerView);
        theNestSettingsLayout.removeView(settingsHomeView);
        buttonModeB(this, settingsButtonsFrame1, settingsButtonsText1, background(5), false);
        buttonModeA(this, settingsButtonsFrame2, settingsButtonsIcon1, background(5), false);
        buttonModeA(this, settingsButtonsFrame3, settingsButtonsIcon2, background(5), false);
        buttonModeA(this, settingsButtonsFrame4, settingsButtonsIcon3, background(5), false);

        if(settingsButtonsInt == 1){
            settingsAbout();
            buttonModeB(TheNest.this, settingsButtonsFrame1, settingsButtonsText1, background(5), true);
        }
        if(settingsButtonsInt == 2){
            settingsMisc();
            buttonModeA(this, settingsButtonsFrame2, settingsButtonsIcon1, background(5), true);
        }
        if(settingsButtonsInt == 3){
            settingsDrawer();
            buttonModeA(this, settingsButtonsFrame3, settingsButtonsIcon2, background(5), true);
        }
        if(settingsButtonsInt == 4){
            settingsHome();
            buttonModeA(this, settingsButtonsFrame4, settingsButtonsIcon3, background(5), true);
        }
    }

    //..........
    //-------------------- SETTINGS -------------------- []
    //---------------------- TOOLTIP ------------------- []
    //``````````

    View settingsTooltipView;
    public static RelativeLayout settingsTooltipLayout, settingsTooltipFrame;
    public static TextView settingsTooltipText;
    private void settingsTooltip(){
        if(settingsTooltipView == null){
            settingsTooltipView = inflater.inflate(R.layout.settings_tooltip, null);
            settingsTooltipLayout = settingsTooltipView.findViewById(R.id.settings_tooltip);
            settingsTooltipFrame = settingsTooltipView.findViewById(R.id.settings_tooltip_frame);
            settingsTooltipText = settingsTooltipView.findViewById(R.id.settings_tooltip_text);

            backgroundTypeC(this, settingsTooltipFrame, background(3), tintA);
        }
        if(theNestSettingsLayout.findViewById(R.id.settings_tooltip) == null){
            theNestSettingsLayout.addView(settingsTooltipView);
            layoutParamsTypeB(settingsTooltipLayout, new int[]{direction(4), direction(5)}, direction(9), R.id.settings_edge_b);
            settingsTooltipStateA(this);
        }
    }

    private void settingsTooltipReset(){
        try {
            settingsTooltipStateA(homeTooltipLayout.getContext());
            settingsTooltipHandler.removeCallbacks(settingsTooltipRunnable);
        } catch (Exception e){}
    }

    public static void settingsTooltipStateA(Context context){
        settingsTooltipText.setText("");
        setMargins(context, settingsTooltipLayout, 0, 40, 0, 0);
        setSize(settingsTooltipLayout, 0, 0);
    }

    public static void settingsTooltipStateB(Context context, String tooltip){
        textType(context, settingsTooltipText, tooltip, tintB, fontAStyle);
        setMargins(context, settingsTooltipLayout, 0, 20, 20, 20);
        setSize(settingsTooltipLayout, size(1), size(1));
    }

    public static Handler settingsTooltipHandler= new Handler();
    public static Runnable settingsTooltipRunnable = new Runnable() {
        @Override
        public void run() {
            settingsTooltipStateA(settingsTooltipLayout.getContext());
        }
    };

    //..........
    //-------------------- SETTINGS -------------------- []
    //---------------------- PANEL --------------------- []
    //``````````

    private void settingsPanel(){
        settingsEdgeA();
        settingsEdgeB();
        if(settingsTab.equals("About") || settingsTab.equals("Auto"))
            settingsButtonsPress_0();

        if(settingsTab.equals("Misc"))
            settingsButtonsPress_1();

        if(settingsTab.equals("Drawer"))
            settingsButtonsPress_2();

        if(settingsTab.equals("Home"))
            settingsButtonsPress_3();
    }

    //..........
    //-------------------- SETTINGS -------------------- []
    //---------------------- EDGE --------------------- []
    //``````````

    View settingsEdgeAView;
    RelativeLayout settingsEdgeALayout, settingsEdgeAFrame;
    ImageView settingsEdgeALine1, settingsEdgeALine2;
    TextView settingsEdgeAText;
    private void settingsEdgeA(){
        if(settingsEdgeAView == null){
            settingsEdgeAView = inflater.inflate(R.layout.settings_edge_a, null);
            settingsEdgeALayout = settingsEdgeAView.findViewById(R.id.settings_edge_a);
            settingsEdgeAFrame = settingsEdgeAView.findViewById(R.id.settings_edge_a_frame);
            settingsEdgeALine1 = settingsEdgeAView.findViewById(R.id.settings_edge_a_line_1);
            settingsEdgeALine2 = settingsEdgeAView.findViewById(R.id.settings_edge_a_line_2);
            settingsEdgeAText = settingsEdgeAView.findViewById(R.id.settings_edge_a_text);

            backgroundTypeA(this, settingsEdgeAFrame, background(8), tintA, 3);
            imageTypeB(this, settingsEdgeALine1, background(6), tintA);
            imageTypeB(this, settingsEdgeALine2, background(6), tintA);
            textType(this, settingsEdgeAText, "", tintA, fontAStyle);
        }
        if(theNestSettingsLayout.findViewById(R.id.settings_edge_a) == null){
            theNestSettingsLayout.addView(settingsEdgeAView);
            layoutParamsTypeB(settingsEdgeALayout, new int[]{direction(6)}, direction(8), R.id.settings_buttons);
            setMargins(this, settingsEdgeALayout, 20, 0, 0, 20);
        }
    }

    View settingsEdgeBView;
    RelativeLayout settingsEdgeBLayout;
    ImageView settingsEdgeBLine1, settingsEdgeBLine2, settingsEdgeBDot1, settingsEdgeBDot2,
            settingsEdgeBSquare, settingsEdgeBCylinder1, settingsEdgeBCylinder2;
    private void settingsEdgeB(){
        if(settingsEdgeBView == null){
            settingsEdgeBView = inflater.inflate(R.layout.settings_edge_b, null);
            settingsEdgeBLayout = settingsEdgeBView.findViewById(R.id.settings_edge_b);
            settingsEdgeBLine1 = settingsEdgeBView.findViewById(R.id.settings_edge_b_line_1);
            settingsEdgeBLine2 = settingsEdgeBView.findViewById(R.id.settings_edge_b_line_2);
            settingsEdgeBDot1 = settingsEdgeBView.findViewById(R.id.settings_edge_b_dot_1);
            settingsEdgeBDot2 = settingsEdgeBView.findViewById(R.id.settings_edge_b_dot_2);
            settingsEdgeBCylinder1 = settingsEdgeBView.findViewById(R.id.settings_edge_b_cylinder_1);
            settingsEdgeBCylinder2 = settingsEdgeBView.findViewById(R.id.settings_edge_b_cylinder_2);
            settingsEdgeBSquare = settingsEdgeBView.findViewById(R.id.settings_edge_b_square);

            imageTypeB(this, settingsEdgeBLine1, background(6), tintA);
            imageTypeB(this, settingsEdgeBLine2, background(6), tintA);
            imageTypeB(this, settingsEdgeBDot1, background(4), tintA);
            imageTypeB(this, settingsEdgeBDot2, background(4), tintA);
            imageTypeB(this, settingsEdgeBCylinder1, background(6), tintA);
            imageTypeB(this, settingsEdgeBCylinder2, background(6), tintA);
            backgroundTypeA(this, settingsEdgeBSquare, background(8), tintA, 3);
        }
        if(theNestSettingsLayout.findViewById(R.id.settings_edge_b) == null){
            theNestSettingsLayout.addView(settingsEdgeBView);
            layoutParamsTypeA(settingsEdgeBLayout, new int[]{direction(4), direction(6)});
            setMargins(this, settingsEdgeBLayout, 0, 20, 0, 20);
        }
    }

    //..........
    //-------------------- SETTINGS -------------------- []
    //---------------------- ABOUT --------------------- []
    //``````````

    View settingsAboutView;
    RelativeLayout settingsAboutLayout, settingsAboutFrame1, settingsAboutFrame2, settingsAboutFrame3, settingsAboutFrame4;
    ScrollView settingsAboutScrollView;
    TextView settingsAboutText1,settingsAboutText2, settingsAboutText3, settingsAboutText4,
            settingsAboutText5, settingsAboutText6, settingsAboutText7, settingsAboutText8,
            settingsAboutText9, settingsAboutText10, settingsAboutText11, settingsAboutText12,
            settingsAboutText13, settingsAboutText14, settingsAboutText15, settingsAboutText16,
            settingsAboutText17, settingsAboutText18, settingsAboutText19, settingsAboutText20,
            settingsAboutText21, settingsAboutText22, settingsAboutText23;
    ImageView settingsAboutIcon1, settingsAboutIcon2, settingsAboutIcon3;
    private void settingsAbout(){
        if(settingsAboutView == null){
            settingsAboutView = inflater.inflate(R.layout.settings_about, null);
            settingsAboutLayout = settingsAboutView.findViewById(R.id.settings_about);
            settingsAboutScrollView = settingsAboutView.findViewById(R.id.settings_about_scroll_view);
            settingsAboutIcon1 = settingsAboutView.findViewById(R.id.settings_about_icon_1);
            settingsAboutIcon2 = settingsAboutView.findViewById(R.id.settings_about_icon_2);
            settingsAboutIcon3 = settingsAboutView.findViewById(R.id.settings_about_icon_3);
            settingsAboutFrame1 = settingsAboutView.findViewById(R.id.settings_about_frame_1);
            settingsAboutFrame2 = settingsAboutView.findViewById(R.id.settings_about_frame_2);
            settingsAboutFrame3 = settingsAboutView.findViewById(R.id.settings_about_frame_3);
            settingsAboutFrame4 = settingsAboutView.findViewById(R.id.settings_about_frame_4);
            settingsAboutText1 = settingsAboutView.findViewById(R.id.settings_about_text_1);
            settingsAboutText2 = settingsAboutView.findViewById(R.id.settings_about_text_2);
            settingsAboutText3 = settingsAboutView.findViewById(R.id.settings_about_text_3);
            settingsAboutText4 = settingsAboutView.findViewById(R.id.settings_about_text_4);
            settingsAboutText5 = settingsAboutView.findViewById(R.id.settings_about_text_5);
            settingsAboutText6 = settingsAboutView.findViewById(R.id.settings_about_text_6);
            settingsAboutText7 = settingsAboutView.findViewById(R.id.settings_about_text_7);
            settingsAboutText8 = settingsAboutView.findViewById(R.id.settings_about_text_8);
            settingsAboutText9 = settingsAboutView.findViewById(R.id.settings_about_text_9);
            settingsAboutText10 = settingsAboutView.findViewById(R.id.settings_about_text_10);
            settingsAboutText11 = settingsAboutView.findViewById(R.id.settings_about_text_11);
            settingsAboutText12 = settingsAboutView.findViewById(R.id.settings_about_text_12);
            settingsAboutText13 = settingsAboutView.findViewById(R.id.settings_about_text_13);
            settingsAboutText14 = settingsAboutView.findViewById(R.id.settings_about_text_14);
            settingsAboutText15 = settingsAboutView.findViewById(R.id.settings_about_text_15);
            settingsAboutText16 = settingsAboutView.findViewById(R.id.settings_about_text_16);
            settingsAboutText17 = settingsAboutView.findViewById(R.id.settings_about_text_17);
            settingsAboutText18 = settingsAboutView.findViewById(R.id.settings_about_text_18);
            settingsAboutText19 = settingsAboutView.findViewById(R.id.settings_about_text_19);
            settingsAboutText20 = settingsAboutView.findViewById(R.id.settings_about_text_20);
            settingsAboutText21 = settingsAboutView.findViewById(R.id.settings_about_text_21);
            settingsAboutText22 = settingsAboutView.findViewById(R.id.settings_about_text_22);
            settingsAboutText23 = settingsAboutView.findViewById(R.id.settings_about_text_23);

            backgroundTypeC(this, settingsAboutLayout, background(3), tintA);

            Handler settingsAboutHandler = new Handler();
            Runnable settingsAboutRunnable = new Runnable() {
                @Override
                public void run() {
                    backgroundTypeA(TheNest.this, settingsAboutFrame2, background(8), tintB, 3);
                    backgroundTypeA(TheNest.this, settingsAboutFrame3, background(8), tintB, 3);
                    backgroundTypeA(TheNest.this, settingsAboutFrame4, background(9), tintB, 3);

                    imageTypeA(TheNest.this, settingsAboutIcon1, icon(17), R.color.transparent, 120);
                    imageTypeA(TheNest.this, settingsAboutIcon2, icon(20), tintB, 65);
                    imageTypeA(TheNest.this, settingsAboutIcon3, icon(18), R.color.transparent, 90);

                    textType(TheNest.this, settingsAboutText1, textC(24), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText2, textC(25), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText3, textB(13), tintB, fontBStyle);
                    textType(TheNest.this, settingsAboutText4, textC(30), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText5, textC(31), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText6, textC(32), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText7, textC(33), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText8, textB(14), tintB, fontBStyle);
                    textType(TheNest.this, settingsAboutText9, textB(15), tintB, fontBStyle);
                    textType(TheNest.this, settingsAboutText10, textB(16), tintB, fontBStyle);
                    textType(TheNest.this, settingsAboutText11, textB(17), tintB, fontBStyle);
                    textType(TheNest.this, settingsAboutText12, textB(18), tintB, fontBStyle);
                    textType(TheNest.this, settingsAboutText13, textB(19), tintB, fontBStyle);
                    textType(TheNest.this, settingsAboutText14, textB(20), tintB, fontBStyle);
                    textType(TheNest.this, settingsAboutText15, textC(34), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText16, textB(22), tintB, fontBStyle);
                    textType(TheNest.this, settingsAboutText17, textC(35), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText18, textC(36), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText19, textB(23), tintB, fontBStyle);
                    textType(TheNest.this, settingsAboutText20, textC(37), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText21, textA(1), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText22, textA(10), tintB, fontAStyle);
                    textType(TheNest.this, settingsAboutText23, textB(21), tintB, fontBStyle);
                }
            };
            settingsAboutHandler.postDelayed(settingsAboutRunnable, 100);

            customTouchModeB(settingsAboutIcon1,"",2, 1,0);
            customTouchModeB(settingsAboutIcon3, "",2, 1,1);
            customTouchModeB(settingsAboutFrame2, "", 2, 1,2);
            customTouchModeB(settingsAboutFrame3, "", 2, 1,3);
            customTouchModeB(settingsAboutFrame4, "", 2, 1,4);
        }
        if(theNestSettingsLayout.findViewById(R.id.settings_about) == null){
            theNestSettingsLayout.addView(settingsAboutView);
            layoutParamsTypeC(settingsAboutLayout, new int[]{direction(5)}, direction(8), R.id.settings_edge_a, direction(7), R.id.settings_tooltip);
            setSize(settingsAboutLayout, size(2), size(2));
            setMargins(this, settingsAboutLayout, -40, 20, 20, 60);
            settingsEdgeAText.setText(textC(38));
        }
    }

    // -----[ SETTINGS ABOUT BUTTONS ]----- //

    private void settingsAboutPress_0(){
        settingsAboutCommonA(24, 25);
    }

    private void settingsAboutPress_1(){
        settingsAboutCommonA(26, 27);
    }

    private void settingsAboutPress_2(){
        if(isAppInstalled(this, "com.google.android.gm")){
            Intent intent = new Intent(Intent.ACTION_SEND);
            String email = textB(84);
            intent.putExtra(Intent.EXTRA_EMAIL, email);
            intent.putExtra(Intent.EXTRA_SUBJECT,textB(85));
            intent.putExtra(Intent.EXTRA_TEXT,textB(86));
            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent, "Send mail"));
        } else {
            settingsAboutCommonB("Email Copied", textB(84));
            touchTip(this, textC(28), 2);
        }
    }

    private void settingsAboutPress_3(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(textB(87)));
        try {
            startActivity(intent);
        } catch (Exception e){
            settingsAboutCommonB("Link Copied", textB(87));
            touchTip(this, textC(29), 2);
        }
    }

    private void settingsAboutPress_4(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // -----[ SETTINGS ABOUT COMMON ]----- //

    private void settingsAboutCommonA(int a, int b){
        settingsAboutText1.setText(textC(a));
        settingsAboutText2.setText(textC(b));
    }

    private void settingsAboutCommonB(String text_1, String text_2){
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText(text_1, text_2);
        clipboard.setPrimaryClip(clip);
    }

    //..........
    //-------------------- SETTINGS -------------------- []
    //---------------------- MISC --------------------- []
    //``````````

    View settingsMiscView;
    RelativeLayout settingsMiscLayout;
    private void settingsMisc(){
        if(settingsMiscView == null){
            settingsMiscView = inflater.inflate(R.layout.settings_misc, null);
            settingsMiscLayout = settingsMiscView.findViewById(R.id.settings_misc);
            settingsMiscA();
        }
        if(theNestSettingsLayout.findViewById(R.id.settings_misc) == null){
            theNestSettingsLayout.addView(settingsMiscView);
            layoutParamsTypeC(settingsMiscLayout, new int[]{direction(5)}, direction(8), R.id.settings_edge_a, direction(7), R.id.settings_tooltip);
            setSize(settingsMiscLayout, size(2), size(2));
            setMargins(this, settingsMiscLayout, -40, 20, 20, 60);
            settingsEdgeAText.setText(textC(39));
        }
    }

    View settingsMiscAView;
    RelativeLayout settingsMiscALayout;
    ListView settingsMiscAListView;
    ContentsA miscContents;
    private void settingsMiscA(){
        if(settingsMiscAView == null){
            settingsMiscAView = inflater.inflate(R.layout.settings_misc_a, null);
            settingsMiscALayout = settingsMiscAView.findViewById(R.id.settings_misc_a);
            settingsMiscAListView = settingsMiscAView.findViewById(R.id.settings_misc_a_list_view);

            backgroundTypeC(this, settingsMiscALayout, background(3), tintA);
            settingsMiscList();
        }
        if(settingsMiscLayout.findViewById(R.id.settings_misc_a) == null){
            settingsMiscLayout.addView(settingsMiscAView);
            setSize(settingsMiscALayout, size(2), size(2));
        }
    }

    // -----[ SETTINGS MISC COMMON ]----- //

    private void settingsMiscCommonA(View view){
        settingsMiscLayout.removeView(view);
        settingsMiscAView.setVisibility(View.VISIBLE);
    }

    // -----[ SETTINGS MISC METHODS ]----- //

    private void settingsMiscList(){
        List<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(roundedBitmap(drawableIcon(this, defaultApp(this), 60)));
        bitmaps.add(reduceBitmap(this, icon(13), 60));
        bitmaps.add(reduceBitmap(this, icon(42), 60));
        bitmaps.add(reduceBitmap(this, icon(15), 60));
        bitmaps.add(reduceBitmap(this, icon(1), 60));
        bitmaps.add(reduceBitmap(this, icon(16), 60));
        bitmaps.add(reduceBitmap(this, icon(14), 60));

        List<String> strings_a = new ArrayList<>();
        strings_a.add(textC(42));
        strings_a.add(textC(43));
        strings_a.add(textC(22));
        strings_a.add(textC(44));
        strings_a.add(textC(45));
        strings_a.add(textC(46));
        strings_a.add(textC(47));

        List<String> strings_b = new ArrayList<>();
        strings_b.add(textB(24));
        strings_b.add(textB(25));
        strings_b.add(textB(26));
        strings_b.add(textB(27));
        strings_b.add(textB(28));
        strings_b.add(textB(29));
        strings_b.add(textB(30));

        Handler settingsMiscAHandler = new Handler();
        Runnable settingsMiscARunnable = new Runnable() {
            @Override
            public void run() {
                int layout = 0;
                if(miscListStyle.equals("Square"))
                    layout = R.layout.list_style_a;

                if(miscListStyle.equals("Cylinder"))
                    layout = R.layout.list_style_b;

                if(miscListStyle.equals("Hexagon"))
                    layout = R.layout.list_style_c;

                miscContents = new ContentsA(TheNest.this, layout, bitmaps, strings_a, strings_b, TheNest.this);
                settingsMiscAListView.setAdapter(miscContents);
            }
        };
        settingsMiscAHandler.postDelayed(settingsMiscARunnable, 100);
    }

    @Override
    public void settingsMiscList(int mode) {
        settingsMiscAView.setVisibility(View.GONE);
        if(mode == 0)
            settingsMiscB();

        if(mode == 1)
            settingsMiscC();

        if(mode == 2)
            settingsMiscD();

        if(mode == 3)
            settingsMiscE();

        if(mode == 4)
            settingsMiscF();
    }

    //..........
    //-------------------- MISC -------------------- []
    //--------------------- RESET ------------------ []
    //``````````

    View settingsMiscBView;
    RelativeLayout settingsMiscBLayout, settingsMiscBFrame1, settingsMiscBFrame2, settingsMiscBFrame3, settingsMiscBFrame4;
    ImageView settingsMiscBIcon1, settingsMiscBIcon2, settingsMiscBIcon3, settingsMiscBIcon4, settingsMiscBAngle;
    TextView settingsMiscBText1, settingsMiscBText2;
    @SuppressLint({"WrongConstant", "ClickableViewAccessibility"})
    private void settingsMiscB(){
        if(settingsMiscBView == null){
            settingsMiscBView = inflater.inflate(R.layout.settings_misc_b, null);
            settingsMiscBLayout = settingsMiscBView.findViewById(R.id.settings_misc_b);
            settingsMiscBFrame1 = settingsMiscBView.findViewById(R.id.settings_misc_b_frame_1);
            settingsMiscBFrame2 = settingsMiscBView.findViewById(R.id.settings_misc_b_frame_2);
            settingsMiscBFrame3 = settingsMiscBView.findViewById(R.id.settings_misc_b_frame_3);
            settingsMiscBFrame4 = settingsMiscBView.findViewById(R.id.settings_misc_b_frame_4);
            settingsMiscBIcon1 = settingsMiscBView.findViewById(R.id.settings_misc_b_icon_1);
            settingsMiscBIcon2 = settingsMiscBView.findViewById(R.id.settings_misc_b_icon_2);
            settingsMiscBIcon3 = settingsMiscBView.findViewById(R.id.settings_misc_b_icon_3);
            settingsMiscBIcon4 = settingsMiscBView.findViewById(R.id.settings_misc_b_icon_4);
            settingsMiscBAngle = settingsMiscBView.findViewById(R.id.settings_misc_b_angle);
            settingsMiscBText1 = settingsMiscBView.findViewById(R.id.settings_misc_b_text_1);
            settingsMiscBText2 = settingsMiscBView.findViewById(R.id.settings_misc_b_text_2);

            backgroundTypeC(this, settingsMiscBFrame3, background(3), tintA);
            backgroundTypeC(this, settingsMiscBFrame4, background(4), tintA);

            imageTypeA(this, settingsMiscBAngle, drawable(2), tintA, 30);
            imageTypeA(this, settingsMiscBIcon1, icon(11), tintA, 50);
            imageTypeA(this, settingsMiscBIcon2, icon(12), tintA, 50);
            imageTypeA(this, settingsMiscBIcon3, icon(31), tintB, 50);
            imageTypeA(this, settingsMiscBIcon4, icon(33), tintA, 75);

            textType(this, settingsMiscBText1, "", tintB, fontAStyle);
            textType(this, settingsMiscBText2, "", tintB, fontBStyle);

            customTouchModeB(settingsMiscBIcon4, textC(48), 2, 2,0);
            customTouchModeB(settingsMiscBFrame1, "", 2, 2,2);
            customTouchModeB(settingsMiscBFrame2, "", 2, 2,3);
            customTouchModeB(settingsMiscBFrame4, textC(49), 2, 2, 1);
        }
        if(settingsMiscLayout.findViewById(R.id.settings_misc_b) == null){
            settingsMiscLayout.addView(settingsMiscBView);
            layoutParamsTypeA(settingsMiscBLayout, new int[]{direction(2), direction(3)});
            setSize(settingsMiscBLayout, size(2), size(1));
            setMargins(this, settingsMiscBLayout, 20, 20, 20, 20);
            setMargins(this, settingsMiscBAngle, 20, 0, 15, 0);
            miscResetMode();
        }
    }

    // -----[ MISC RESET COMMON ]----- //

    private void miscResetCommon(int int_a, int int_b, boolean boolean_a, boolean boolean_b, int id){
        settingsMiscBText1.setText(textC(int_a));
        settingsMiscBText2.setText(textB(int_b));
        buttonModeC(this, settingsMiscBFrame1, settingsMiscBIcon1, boolean_a);
        buttonModeC(this, settingsMiscBFrame2, settingsMiscBIcon2, boolean_b);
        layoutParamsTypeC(settingsMiscBAngle, new int[]{0}, direction(8), id, direction(13), id);
    }

    // -----[ MISC RESET METHODS ]----- //

    int settingsMiscBInt = 0;
    private void miscResetMode(){
        if(settingsMiscBInt == 0)
            miscResetCommon(50, 31, true, false, R.id.settings_misc_b_frame_1);

        if(settingsMiscBInt == 1)
            miscResetCommon(51, 32, false, true, R.id.settings_misc_b_frame_2);
    }

    // -----[ MISC RESET BUTTONS ]----- //

    private void settingsMiscPress_0(){
        settingsMiscCommonA(settingsMiscBView);
    }

    private void settingsMiscPress_1(){
        new File(getFilesDir(), "Configurations - 01").delete();
        new File(getFilesDir(), "Configurations - 02").delete();
        new File(getFilesDir(), "Configurations - 03").delete();
        new File(getFilesDir(), "Configurations - 04").delete();

        if(settingsMiscBInt == 1){
            //((ActivityManager) getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData();
            getCacheDir().delete();
            getFilesDir().delete();
            new File(getFilesDir(), "CREATED").delete();
            new File(getFilesDir(), "Array - 01").delete();
            new File(getFilesDir(), "Array - 02").delete();
            new File(getFilesDir(), "Array - 03").delete();
            new File(getFilesDir(), "Array - 04").delete();
        }
        initializeConfigurations();
        restart(TheNest.this);
    }

    private void settingsMiscPress_2(){
        if(settingsMiscBInt != 0){
            settingsMiscBInt = 0;
            miscResetMode();
        }
    }

    private void settingsMiscPress_3(){
        if(settingsMiscBInt != 1){
            settingsMiscBInt = 1;
            miscResetMode();
        }
    }

    //..........
    //-------------------- MISC -------------------- []
    //--------------------- BARS ------------------- []
    //``````````

    View settingsMiscCView;
    RelativeLayout settingsMiscCLayout, settingsMiscCFrame1, settingsMiscCFrame2, settingsMiscCFrame3, settingsMiscCFrame4;
    ImageView settingsMiscCAngle, settingsMiscCCircle, settingsMiscCIcon1, settingsMiscCIcon2, settingsMiscCIcon3;
    TextView settingsMiscCText1, settingsMiscCText2;
    private void settingsMiscC(){
        if(settingsMiscCView == null){
            settingsMiscCView = inflater.inflate(R.layout.settings_misc_c, null);
            settingsMiscCLayout = settingsMiscCView.findViewById(R.id.settings_misc_c);
            settingsMiscCFrame1 = settingsMiscCView.findViewById(R.id.settings_misc_c_frame_1);
            settingsMiscCFrame2 = settingsMiscCView.findViewById(R.id.settings_misc_c_frame_2);
            settingsMiscCFrame3 = settingsMiscCView.findViewById(R.id.settings_misc_c_frame_3);
            settingsMiscCFrame4 = settingsMiscCView.findViewById(R.id.settings_misc_c_frame_4);
            settingsMiscCCircle = settingsMiscCView.findViewById(R.id.settings_misc_c_circle);
            settingsMiscCAngle = settingsMiscCView.findViewById(R.id.settings_misc_c_angle);
            settingsMiscCIcon1 = settingsMiscCView.findViewById(R.id.settings_misc_c_icon_1);
            settingsMiscCIcon2 = settingsMiscCView.findViewById(R.id.settings_misc_c_icon_2);
            settingsMiscCIcon3 = settingsMiscCView.findViewById(R.id.settings_misc_c_icon_3);
            settingsMiscCText1 = settingsMiscCView.findViewById(R.id.settings_misc_c_text_1);
            settingsMiscCText2 = settingsMiscCView.findViewById(R.id.settings_misc_c_text_2);

            backgroundTypeC(this, settingsMiscCFrame3, background(3), tintA);
            backgroundTypeA(this, settingsMiscCFrame4, background(7), ui, 3);

            imageTypeA(this, settingsMiscCAngle, drawable(2), tintA, 30);
            imageTypeA(this, settingsMiscCIcon1, icon(27), tintA, 50);
            imageTypeA(this, settingsMiscCIcon2, icon(28), tintA, 50);
            imageTypeA(this, settingsMiscCIcon3, icon(33), tintA, 75);

            textType(this, settingsMiscCText1, "", tintB, fontAStyle);
            textType(this, settingsMiscCText2, "", tintB, fontBStyle);

            customTouchModeB(settingsMiscCIcon3, textC(48), 2, 2, 4);
            customTouchModeB(settingsMiscCFrame1, "", 2, 2,6);
            customTouchModeB(settingsMiscCFrame2, "", 2, 2,7);
            customTouchModeB(settingsMiscCFrame4, "", 2, 2,5);
        }
        if(settingsMiscLayout.findViewById(R.id.settings_misc_c) == null){
            settingsMiscLayout.addView(settingsMiscCView);
            layoutParamsTypeA(settingsMiscCLayout, new int[]{direction(2), direction(3)});
            setSize(settingsMiscCLayout, size(2), size(1));
            setMargins(this, settingsMiscCLayout, 20, 20, 20, 20);
            setMargins(this, settingsMiscCAngle, 20, 0, 15, 0);
            miscBarsMode();
        }
    }

    // -----[ MISC BARS BUTTONS ]----- //

    private void settingsMiscPress_4(){
        settingsMiscCommonA(settingsMiscCView);
    }

    private void settingsMiscPress_5(){
        if(settingsMiscCInt == 0)
            miscBarsCommonB(statusBar, "Status");

        if(settingsMiscCInt == 1)
            miscBarsCommonB(navigationBar, "Navigation");

        configurationsA();
        miscBarsMode();
    }

    private void settingsMiscPress_6(){
        if(settingsMiscCInt != 0){
            settingsMiscCInt = 0;
            miscBarsMode();
        }
    }

    private void settingsMiscPress_7(){
        if(settingsMiscCInt != 1){
            settingsMiscCInt = 1;
            miscBarsMode();
        }
    }

    // -----[ MISC BARS COMMON ]----- //

    private void miscBarsCommonA(int int_a, int int_b, String state, boolean boolean_a, boolean boolean_b, int id){
        settingsMiscCText1.setText(textC(int_a));
        settingsMiscCText2.setText(textB(int_b));
        if(state.equals("Enabled")) {
            toggleMode(this, settingsMiscCCircle, true);
        } else {
            toggleMode(this, settingsMiscCCircle, false);
        }
        buttonModeC(this, settingsMiscCFrame1, settingsMiscCIcon1, boolean_a);
        buttonModeC(this, settingsMiscCFrame2, settingsMiscCIcon2, boolean_b);
        layoutParamsTypeC(settingsMiscCAngle, new int[]{0}, direction(8), id, direction(13), id);
    }

    private void miscBarsCommonB(String state, String type){
        if(state.equals("Enabled")) {
            edit(TheNest.this,  "Configurations - 01",type + " Bar - Enabled",type + " Bar - Disabled");
        } else {
            edit(TheNest.this, "Configurations - 01", type + " Bar - Disabled", type + " Bar - Enabled");
        }
    }

    // -----[ MISC BARS METHODS ]----- //

    int settingsMiscCInt = 0;
    private void miscBarsMode(){
        if(settingsMiscCInt == 0)
            miscBarsCommonA(52, 33, statusBar, true, false,  R.id.settings_misc_c_frame_1);

        if(settingsMiscCInt == 1)
            miscBarsCommonA(53, 34, navigationBar, false, true,  R.id.settings_misc_c_frame_2);
    }

    //..........
    //-------------------- MISC -------------------- []
    //--------------------- TABS ------------------- []
    //``````````

    View settingsMiscDView;
    RelativeLayout settingsMiscDLayout, settingsMiscDFrame1, settingsMiscDFrame2, settingsMiscDFrame3, settingsMiscDFrame4,
            settingsMiscDFrame5;
    ImageView settingsMiscDAngle, settingsMiscDIcon1, settingsMiscDIcon2, settingsMiscDIcon3,
            settingsMiscDIcon4, settingsMiscDIcon5;
    TextView settingsMiscDText1, settingsMiscDText2, settingsMiscDText3, settingsMiscDText4;
    private void settingsMiscD(){
        if(settingsMiscDView == null){
            settingsMiscDView = inflater.inflate(R.layout.settings_misc_d, null);
            settingsMiscDLayout = settingsMiscDView.findViewById(R.id.settings_misc_d);
            settingsMiscDFrame1 = settingsMiscDView.findViewById(R.id.settings_misc_d_frame_1);
            settingsMiscDFrame2 = settingsMiscDView.findViewById(R.id.settings_misc_d_frame_2);
            settingsMiscDFrame3 = settingsMiscDView.findViewById(R.id.settings_misc_d_frame_3);
            settingsMiscDFrame4 = settingsMiscDView.findViewById(R.id.settings_misc_d_frame_4);
            settingsMiscDFrame5 = settingsMiscDView.findViewById(R.id.settings_misc_d_frame_5);
            settingsMiscDAngle = settingsMiscDView.findViewById(R.id.settings_misc_d_angle);
            settingsMiscDIcon1 = settingsMiscDView.findViewById(R.id.settings_misc_d_icon_1);
            settingsMiscDIcon2 = settingsMiscDView.findViewById(R.id.settings_misc_d_icon_2);
            settingsMiscDIcon3 = settingsMiscDView.findViewById(R.id.settings_misc_d_icon_3);
            settingsMiscDIcon4 = settingsMiscDView.findViewById(R.id.settings_misc_d_icon_4);
            settingsMiscDIcon5 = settingsMiscDView.findViewById(R.id.settings_misc_d_icon_5);
            settingsMiscDText1 = settingsMiscDView.findViewById(R.id.settings_misc_d_text_1);
            settingsMiscDText2 = settingsMiscDView.findViewById(R.id.settings_misc_d_text_2);
            settingsMiscDText3 = settingsMiscDView.findViewById(R.id.settings_misc_d_text_3);
            settingsMiscDText4 = settingsMiscDView.findViewById(R.id.settings_misc_d_text_4);

            backgroundTypeC(this, settingsMiscDFrame3, background(3), tintA);
            backgroundTypeA(this, settingsMiscDFrame4, background(7), tintA, 3);

            imageTypeA(this, settingsMiscDAngle, drawable(2), tintA, 30);
            imageTypeA(this, settingsMiscDIcon1, icon(3), tintA, 50);
            imageTypeA(this, settingsMiscDIcon2, icon(2), tintA, 50);
            imageTypeA(this, settingsMiscDIcon3, icon(33), tintA, 75);

            textType(this, settingsMiscDText1, "", tintB, fontAStyle);
            textType(this, settingsMiscDText2, "", tintB, fontBStyle);
            textType(this, settingsMiscDText3, "", tintA, fontBStyle);
            textType(this, settingsMiscDText4, "", tintA, fontBStyle);

            customTouchModeB(settingsMiscDIcon3, textC(48), 2, 2, 8);
            customTouchModeB(settingsMiscDFrame1, "", 2, 2, 10);
            customTouchModeB(settingsMiscDFrame2, "", 2, 2, 11);
            customTouchModeB(settingsMiscDFrame4, "", 2,2,9);
            customTouchModeB(settingsMiscDIcon4, "", 2, 2, 12);
            customTouchModeB(settingsMiscDIcon5, "", 2, 2, 13);
        }
        if(settingsMiscLayout.findViewById(R.id.settings_misc_d) == null){
            settingsMiscLayout.addView(settingsMiscDView);
            layoutParamsTypeA(settingsMiscDLayout, new int[]{direction(2), direction(3)});
            setSize(settingsMiscDLayout, size(2), size(1));
            setMargins(this, settingsMiscDLayout, 20, 20, 20, 20);
            setMargins(this, settingsMiscDAngle, 20, 0, 15, 0);
            miscTabsMode();
        }
    }

    // -----[ MISC TABS BUTTONS ]----- //

    private void settingsMiscPress_8(){
        settingsMiscCommonA(settingsMiscDView);
    }

    private void settingsMiscPress_9(){
        if(settingsMiscDInt_1 == 0){
            String tab = "";
            if(settingsTab.equals("About"))
                tab = "Misc";

            if(settingsTab.equals("Misc"))
                tab = "Drawer";

            if(settingsTab.equals("Drawer"))
                tab = "Home";

            if(settingsTab.equals("Home"))
                tab = "Auto";

            if(settingsTab.equals("Auto"))
                tab = "About";

            edit(this, "Configurations - 04", "Settings Tab - " + settingsTab, "Settings Tab - " + tab);
            configurationsD();
            settingsMiscDText3.setText(settingsTab);
        }
        if(settingsMiscDInt_1 == 1){
            if(settingsMiscDInt_2 == 0)
                miscTabsCommonB("Misc", miscListStyle, settingsMiscAListView, 0);

            if(settingsMiscDInt_2 == 1)
                miscTabsCommonB("Home", homeListStyle, settingsHomeAListView, 1);
        }
    }

    private void settingsMiscPress_10(){
        if(settingsMiscDInt_1 != 0){
            settingsMiscDInt_1 = 0;
            miscTabsMode();
        }
    }

    private void settingsMiscPress_11(){
        if(settingsMiscDInt_1 != 1){
            settingsMiscDInt_1 = 1;
            miscTabsMode();
        }
    }

    private void settingsMiscPress_12(){
        if(settingsMiscDInt_2 != 0){
            settingsMiscDInt_2 = 0;
            miscTabsOptions();
        }
    }

    private void settingsMiscPress_13(){
        if(settingsMiscDInt_2 != 1){
            settingsMiscDInt_2 = 1;
            miscTabsOptions();
        }
    }

    // -----[ MISC TABS COMMON ]----- //

    private String miscTabsCommonA(String listStyle){
        String style = "";
        if(listStyle.equals("Square"))
            style = "Cylinder";

        if(listStyle.equals("Cylinder"))
            style = "Hexagon";

        if(listStyle.equals("Hexagon"))
            style = "Square";
        return style;
    }

    private void miscTabsCommonB(String type, String listStyle, View view, int mode){
        edit(this,  "Configurations - 04", type + " List Style - " + listStyle,
                type + " List Style - " + miscTabsCommonA(listStyle));
        configurationsD();
        if(mode == 0)
            settingsMiscDText3.setText(miscListStyle);
        if(mode == 1)
            settingsMiscDText3.setText(homeListStyle);
        if(view != null){
            if(mode == 0){
                miscContents.clear();
                settingsMiscList();
            }
            if(mode == 1){
                settingsContents.clear();
                settingsHomeList();
            }
        }
    }

    private void miscTabsCommonC(int int_a, int int_b, boolean boolean_a, boolean boolean_b, int id, int mode, int sight){
        settingsMiscDText1.setText(textC(int_a));
        settingsMiscDText2.setText(textB(int_b));
        buttonModeC(this, settingsMiscDFrame1, settingsMiscDIcon1, boolean_a);
        buttonModeC(this, settingsMiscDFrame2, settingsMiscDIcon2, boolean_b);
        layoutParamsTypeC(settingsMiscDAngle, new int[]{0}, direction(8), id, direction(13), id);
        settingsMiscDFrame5.setVisibility(sight);
        if(mode == 0)
            settingsMiscDText3.setText(settingsTab);
        if(mode == 1)
            miscTabsOptions();
    }

    private void miscTabsCommonD(ImageView image_1, ImageView image_2, ImageView image_3, String listStyle, String type){
        imageTypeB(this, image_1, background(6), ui);
        backgroundTypeA(this, image_2, background(7), ui, 3);
        image_3.setImageDrawable(null);
        settingsMiscDText3.setText(listStyle);
        settingsMiscDText4.setText(type);
    }

    // -----[ MISC TABS METHODS ]----- //

    int settingsMiscDInt_1 = 0;
    int settingsMiscDInt_2 = 0;
    private void miscTabsMode(){
        if(settingsMiscDInt_1 == 0)
            miscTabsCommonC(54, 35, true, false, R.id.settings_misc_d_frame_1, 0, View.GONE);

        if(settingsMiscDInt_1 == 1)
            miscTabsCommonC(55, 36, false, true, R.id.settings_misc_d_frame_2, 1, View.VISIBLE);
    }

    private void miscTabsOptions(){
        if(settingsMiscDInt_2 == 0)
            miscTabsCommonD(settingsMiscDIcon4, settingsMiscDIcon5, settingsMiscDIcon5, miscListStyle, "Misc");

        if(settingsMiscDInt_2 == 1)
            miscTabsCommonD(settingsMiscDIcon5, settingsMiscDIcon4, settingsMiscDIcon4, homeListStyle, "Home");
    }

    //..........
    //-------------------- MISC -------------------- []
    //--------------------- COLORS ------------------- []
    //``````````

    View settingsMiscEView;
    RelativeLayout settingsMiscELayout, settingsMiscEFrame1, settingsMiscEFrame2, settingsMiscEFrame3;
    ImageView settingsMiscEIcon1, settingsMiscEIcon2;
    TextView settingsMiscEText1, settingsMiscEText2;
    HorizontalScrollView settingsMiscEHorizontalView;
    LinearLayout settingsMiscEColors;
    @SuppressLint({"WrongConstant", "ClickableViewAccessibility"})
    private void settingsMiscE(){
        if(settingsMiscEView == null){
            settingsMiscEView = inflater.inflate(R.layout.settings_misc_e, null);
            settingsMiscELayout = settingsMiscEView.findViewById(R.id.settings_misc_e);
            settingsMiscEFrame1 = settingsMiscEView.findViewById(R.id.settings_misc_e_frame_1);
            settingsMiscEFrame2 = settingsMiscEView.findViewById(R.id.settings_misc_e_frame_2);
            settingsMiscEFrame3 = settingsMiscEView.findViewById(R.id.settings_misc_e_frame_3);
            settingsMiscEIcon1 = settingsMiscEView.findViewById(R.id.settings_misc_e_icon_1);
            settingsMiscEIcon2 = settingsMiscEView.findViewById(R.id.settings_misc_e_icon_2);
            settingsMiscEText1 = settingsMiscEView.findViewById(R.id.settings_misc_e_text_1);
            settingsMiscEText2 = settingsMiscEView.findViewById(R.id.settings_misc_e_text_2);
            settingsMiscEHorizontalView = settingsMiscEView.findViewById(R.id.settings_misc_e_horizontal_view);
            settingsMiscEColors = settingsMiscEView.findViewById(R.id.settings_misc_e_colors);

            backgroundTypeC(this, settingsMiscEFrame1, background(3), tintA);
            backgroundTypeA(this, settingsMiscEFrame2, background(7), tintA, 3);
            backgroundTypeC(this, settingsMiscEFrame3, background(4), tintA);
            settingsMiscEFrame2.setVisibility(View.INVISIBLE);

            imageTypeA(this, settingsMiscEIcon1, icon(42), tintB, 50);
            imageTypeA(this, settingsMiscEIcon2, icon(33), tintA, 75);

            textType(this, settingsMiscEText1, textC(46), tintB, fontAStyle);
            textType(this, settingsMiscEText2, textB(37), tintB, fontBStyle);

            customTouchModeB(settingsMiscEIcon2, textC(45), 2, 2,15);
            customTouchModeB(settingsMiscEFrame3, textC(56), 2, 2,16);

            List<Bitmap> list = new ArrayList<>();
            list.add(reduceBitmap(this, icon(4), 50));
            list.add(reduceBitmap(this, icon(5), 50));
            list.add(reduceBitmap(this, icon(6), 50));
            list.add(reduceBitmap(this, icon(7), 50));
            list.add(reduceBitmap(this, icon(8), 50));
            Handler settingsMiscDHandler = new Handler();
            Runnable settingsMiscDRunnable = new Runnable() {
                @Override
                public void run() {
                    miscColorsContents(list, settingsMiscEColors);
                    settingsMiscEFrame2.setVisibility(View.VISIBLE);
                }
            };
            settingsMiscDHandler.postDelayed(settingsMiscDRunnable, 100);

            if(background != R.color.origin || ui != R.color.orange_3 || tintA != R.color.white || tintB != R.color.black){
                settingsMiscEFrame3.setVisibility(View.VISIBLE);
            } else {
                settingsMiscEFrame3.setVisibility(View.GONE);
            }
        }
        if(settingsMiscLayout.findViewById(R.id.settings_misc_e) == null){
            settingsMiscLayout.addView(settingsMiscEView);
            layoutParamsTypeA(settingsMiscELayout, new int[]{direction(2), direction(3)});
            setSize(settingsMiscELayout, size(2), size(1));
            setMargins(this, settingsMiscELayout, 20, 20, 20, 20);
        }
    }

    // -----[ MISC COLORS BUTTONS ]----- //

    private void settingsMiscPress_14(int i){
        if(i == 0)
            miscColorsCommonA(R.color.black, R.color.blue_1, R.color.white, R.color.origin);

        if(i == 1)
            miscColorsCommonA(R.color.white, R.color.teal, R.color.black, R.color.white);

        if(i == 2)
            miscColorsCommonA(R.color.grey, R.color.black, R.color.origin, R.color.white);

        if(i == 3)
            miscColorsCommonA(R.color.yellow_3, R.color.red_2, R.color.origin, R.color.white);

        if(i == 4)
            miscColorsCommonA(R.color.blue_1, R.color.green_3, R.color.white, R.color.origin);
    }

    private void settingsMiscPress_15(){
        settingsMiscCommonA(settingsMiscEView);
    }

    private void settingsMiscPress_16(){
        miscColorsCommonA(R.color.origin, R.color.orange_3, R.color.white, R.color.black);
    }

    // -----[ MISC COLORS COMMON ]----- //

    private void miscColorsCommonA(int a, int b, int c, int d){
        edit(this, "Configurations - 01", "Background - " + background, "Background - " + a);
        edit(this, "Configurations - 01", "UI - " + ui, "UI - " + b);
        edit(this, "Configurations - 01", "Tint A - " + tintA, "Tint A - " + c);
        edit(this, "Configurations - 01", "Tint B - " + tintB, "Tint B - " + d);
        restart(this);
    }

    // -----[ MISC COLORS METHODS ]----- //

    View colorTemplatesView;
    ImageView colorTemplatesIcon;
    @SuppressLint("ClickableViewAccessibility")
    private void miscColorsContents(List<Bitmap> list, LinearLayout layout){
        for (int i = 0; i < list.size(); i++) {
            colorTemplatesView = inflater.inflate(R.layout.linear_type_b, layout, false);
            colorTemplatesIcon = colorTemplatesView.findViewById(R.id.linear_type_b_icon);
            colorTemplatesIcon.setImageBitmap(list.get(i));

            customTouchModeA(colorTemplatesIcon, Integer.toString(i), -1, 4);
            layout.addView(colorTemplatesView);
        }
    }

    //..........
    //-------------------- MISC -------------------- []
    //--------------------- FONTS ------------------ []
    //``````````

    View settingsMiscFView;
    RelativeLayout settingsMiscFLayout, settingsMiscFFrame1, settingsMiscFFrame2, settingsMiscFFrame3, settingsMiscFFrame4,
            settingsMiscFFrame5, settingsMiscFFrame6;
    ImageView settingsMiscFAngle, settingsMiscFIcon1, settingsMiscFIcon2, settingsMiscFIcon3, settingsMiscFIcon4;
    TextView settingsMiscFText1, settingsMiscFText2, settingsMiscFText3, settingsMiscFText4, settingsMiscFText5,
            settingsMiscFText6;
    private void settingsMiscF(){
        if(settingsMiscFView == null){
            settingsMiscFView = inflater.inflate(R.layout.settings_misc_f, null);
            settingsMiscFLayout = settingsMiscFView.findViewById(R.id.settings_misc_f);
            settingsMiscFFrame1 = settingsMiscFView.findViewById(R.id.settings_misc_f_frame_1);
            settingsMiscFFrame2 = settingsMiscFView.findViewById(R.id.settings_misc_f_frame_2);
            settingsMiscFFrame3 = settingsMiscFView.findViewById(R.id.settings_misc_f_frame_3);
            settingsMiscFFrame4 = settingsMiscFView.findViewById(R.id.settings_misc_f_frame_4);
            settingsMiscFFrame5 = settingsMiscFView.findViewById(R.id.settings_misc_f_frame_5);
            settingsMiscFFrame6 = settingsMiscFView.findViewById(R.id.settings_misc_f_frame_6);
            settingsMiscFAngle = settingsMiscFView.findViewById(R.id.settings_misc_f_angle);
            settingsMiscFIcon1 = settingsMiscFView.findViewById(R.id.settings_misc_f_icon_1);
            settingsMiscFIcon2 = settingsMiscFView.findViewById(R.id.settings_misc_f_icon_2);
            settingsMiscFIcon3 = settingsMiscFView.findViewById(R.id.settings_misc_f_icon_3);
            settingsMiscFIcon4 = settingsMiscFView.findViewById(R.id.settings_misc_f_icon_4);
            settingsMiscFText1 = settingsMiscFView.findViewById(R.id.settings_misc_f_text_1);
            settingsMiscFText2 = settingsMiscFView.findViewById(R.id.settings_misc_f_text_2);
            settingsMiscFText3 = settingsMiscFView.findViewById(R.id.settings_misc_f_text_3);
            settingsMiscFText4 = settingsMiscFView.findViewById(R.id.settings_misc_f_text_4);
            settingsMiscFText5 = settingsMiscFView.findViewById(R.id.settings_misc_f_text_5);
            settingsMiscFText6 = settingsMiscFView.findViewById(R.id.settings_misc_f_text_6);

            backgroundTypeC(this, settingsMiscFFrame3, background(3), tintA);
            backgroundTypeA(this, settingsMiscFFrame4, background(12), tintA, 3);
            backgroundTypeA(this, settingsMiscFFrame5, background(12), tintA, 3);
            backgroundTypeC(this, settingsMiscFFrame6, background(4), tintA);

            imageTypeA(this, settingsMiscFAngle, drawable(2), tintA, 30);
            imageTypeA(this, settingsMiscFIcon1, icon(10), tintA, 50);
            imageTypeA(this, settingsMiscFIcon2, icon(9), tintA, 50);
            imageTypeA(this, settingsMiscFIcon3, icon(33), tintA, 75);
            imageTypeA(this, settingsMiscFIcon4, icon(42), tintB, 50);

            textType(this, settingsMiscFText1, "", tintB, fontAStyle);
            textType(this, settingsMiscFText2, "", tintB, fontBStyle);

            textType(this, settingsMiscFText3, textB(40), tintA, Typeface.BOLD);
            textType(this, settingsMiscFText4, "", tintA, fontBStyle);
            textType(this, settingsMiscFText5, textB(41), tintA, Typeface.BOLD);
            textType(this, settingsMiscFText6, "", tintA, fontBStyle);

            customTouchModeB(settingsMiscFIcon3, textC(48), 2, 2, 17);
            customTouchModeB(settingsMiscFFrame1, "", 2, 2, 18);
            customTouchModeB(settingsMiscFFrame2, "", 2, 2, 19);
            customTouchModeB(settingsMiscFFrame4, "", 2, 2, 20);
            customTouchModeB(settingsMiscFFrame5, "", 2, 2, 21);
            customTouchModeB(settingsMiscFFrame6, textC(56), 2, 2, 22);

            if(!fontAMode.equals("SimpliC") || fontAStyle != Typeface.BOLD || fontBStyle != Typeface.NORMAL){
                settingsMiscFFrame6.setVisibility(View.VISIBLE);
            } else {
                settingsMiscFFrame6.setVisibility(View.GONE);
            }
        }
        if(settingsMiscLayout.findViewById(R.id.settings_misc_f) == null){
            settingsMiscLayout.addView(settingsMiscFView);
            layoutParamsTypeA(settingsMiscFLayout, new int[]{direction(2), direction(3)});
            setSize(settingsMiscFLayout, size(2), size(1));
            setMargins(this, settingsMiscFLayout, 20, 20, 20, 20);
            setMargins(this, settingsMiscFAngle, 20, 0, 15, 0);
            miscFontsMode();
        }
    }

    // -----[ MISC FONTS BUTTONS ]----- //

    private void settingsMiscPress_17(){
        settingsMiscCommonA(settingsMiscFView);
    }

    private void settingsMiscPress_18(){
        if(settingsMiscFInt != 0){
            settingsMiscFInt = 0;
            miscFontsMode();
        }
    }

    private void settingsMiscPress_19(){
        if(settingsMiscFInt != 1){
            settingsMiscFInt = 1;
            miscFontsMode();
        }
    }

    private void settingsMiscPress_20(){
        if(settingsMiscFInt == 0)
            miscFontsCommonA("Font A Style - ", fontAStyle);

        if(settingsMiscFInt == 1)
            miscFontsCommonA("Font B Style - ", fontBStyle);
        restart(this);
    }

    private void settingsMiscPress_21(){
        if(fontAMode.equals("SimpliC")){
            edit(this,  "Configurations - 01", "Font A Mode - " + fontAMode, "Font A Mode - Common");
        } else {
            edit(this,  "Configurations - 01", "Font A Mode - " + fontAMode, "Font A Mode - SimpliC");
        }
        restart(this);
    }

    private void settingsMiscPress_22(){
        edit(this,  "Configurations - 01", "Font A Mode - " + fontAMode, "Font A Mode - SimpliC");
        edit(this,  "Configurations - 01", "Font A Style - " + fontAStyle, "Font A Style - " + Typeface.BOLD);
        edit(this,  "Configurations - 01", "Font B Style - " + fontBStyle, "Font B Style - " + Typeface.NORMAL);
        restart(this);
    }

    // -----[ MISC FONTS COMMON ]----- //

    private void miscFontsCommonA(String type, int style){
        if(style == Typeface.BOLD)
            edit(this,  "Configurations - 01", type + style, type + Typeface.ITALIC);

        if(style == Typeface.ITALIC)
            edit(this,  "Configurations - 01", type + style, type + Typeface.BOLD_ITALIC);

        if(style == Typeface.BOLD_ITALIC)
            edit(this,  "Configurations - 01", type + style, type + Typeface.NORMAL);

        if(style == Typeface.NORMAL)
            edit(this,  "Configurations - 01", type + style, type + Typeface.BOLD);
    }

    private void miscFontsCommonB(int style){
        if(style == Typeface.BOLD)
            settingsMiscFText4.setText("Bold");

        if(style == Typeface.ITALIC)
            settingsMiscFText4.setText("Italic A");

        if(style == Typeface.BOLD_ITALIC)
            settingsMiscFText4.setText("Italic B");

        if(style == Typeface.NORMAL)
            settingsMiscFText4.setText("Normal");
    }

    private void miscFontsCommonC(int int_a, int int_b, boolean boolean_a, boolean boolean_b, int id, int style, int sight){
        settingsMiscFText1.setText(textC(int_a));
        settingsMiscFText2.setText(textB(int_b));
        buttonModeC(this, settingsMiscFFrame1, settingsMiscFIcon1, boolean_a);
        buttonModeC(this, settingsMiscFFrame2, settingsMiscFIcon2, boolean_b);
        layoutParamsTypeC(settingsMiscFAngle, new int[]{0}, direction(8),id, direction(13), id);
        miscFontsCommonB(style);
        settingsMiscFFrame5.setVisibility(sight);
        settingsMiscFText6.setText(textC(59));
    }

    // -----[ MISC FONTS METHODS ]----- //

    private int settingsMiscFInt = 0;
    private void miscFontsMode(){
        if(settingsMiscFInt == 0)
            miscFontsCommonC(57, 38, true, false, R.id.settings_misc_f_frame_1, fontAStyle, View.VISIBLE);

        if(settingsMiscFInt == 1)
            miscFontsCommonC(58, 39, false, true, R.id.settings_misc_f_frame_2, fontBStyle, View.GONE);
    }

    //..........
    //-------------------- SETTINGS -------------------- []
    //--------------------- DRAWER --------------------- []
    //``````````

    View settingsDrawerView;
    RelativeLayout settingsDrawerLayout;
    private void settingsDrawer(){
        if(settingsDrawerView == null){
            settingsDrawerView = inflater.inflate(R.layout.settings_drawer, null);
            settingsDrawerLayout = settingsDrawerView.findViewById(R.id.settings_drawer);
        }
        if(theNestSettingsLayout.findViewById(R.id.settings_drawer) == null){
            theNestSettingsLayout.addView(settingsDrawerView);
            layoutParamsTypeC(settingsDrawerLayout, new int[]{direction(5)}, direction(8), R.id.settings_edge_a, direction(7), R.id.settings_tooltip);
            setSize(settingsDrawerLayout, size(2), size(2));
            setMargins(this, settingsDrawerLayout, -40, 20, 20, 60);
            settingsEdgeAText.setText(textC(41));
            if(settingsDrawerLayout.getChildCount() == 0)
                settingsDrawerA();
        }
    }

    View settingsDrawerAView;
    RelativeLayout settingsDrawerALayout, settingsDrawerAFrame1, settingsDrawerAFrame2, settingsDrawerAFrame3, settingsDrawerAFrame4,
            settingsDrawerAFrame5;
    ImageView settingsDrawerAIcon;
    TextView settingsDrawerAText1, settingsDrawerAText2, settingsDrawerAText3, settingsDrawerAText4, settingsDrawerAText5,
            settingsDrawerAText6, settingsDrawerAText7, settingsDrawerAText8, settingsDrawerAText9, settingsDrawerAText10,
            settingsDrawerAText11, settingsDrawerALine1, settingsDrawerALine2, settingsDrawerALine3, settingsDrawerALine4,
            settingsDrawerALine5;
    ScrollView settingsDrawerAScrollView;
    private void settingsDrawerA(){
        if(settingsDrawerAView == null){
            settingsDrawerAView = inflater.inflate(R.layout.settings_drawer_a, null);
            settingsDrawerALayout = settingsDrawerAView.findViewById(R.id.settings_drawer_a);
            settingsDrawerAScrollView = settingsDrawerAView.findViewById(R.id.settings_drawer_a_scroll_view);
            settingsDrawerAText1 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_text_1);
            settingsDrawerAText2 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_text_2);
            settingsDrawerAText3 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_text_3);
            settingsDrawerAText4 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_text_4);
            settingsDrawerAText5 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_text_5);
            settingsDrawerAText6 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_text_6);
            settingsDrawerAText7 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_text_7);
            settingsDrawerAText8 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_text_8);
            settingsDrawerAText9 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_text_9);
            settingsDrawerAText10 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_text_10);
            settingsDrawerAText11 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_text_11);
            settingsDrawerALine1 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_line_1);
            settingsDrawerALine2 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_line_2);
            settingsDrawerALine3 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_line_3);
            settingsDrawerALine4 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_line_4);
            settingsDrawerALine5 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_line_5);
            settingsDrawerAFrame1 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_frame_1);
            settingsDrawerAFrame2 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_frame_2);
            settingsDrawerAFrame3 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_frame_3);
            settingsDrawerAFrame4 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_frame_4);
            settingsDrawerAFrame5 = settingsDrawerAView.findViewById(R.id.settings_drawer_a_frame_5);
            settingsDrawerAIcon = settingsDrawerAView.findViewById(R.id.settings_drawer_a_icon);

            backgroundTypeC(this, settingsDrawerALayout, background(3), tintA);
            backgroundTypeA(this, settingsDrawerAFrame2, background(9), tintB, 3);
            backgroundTypeA(this, settingsDrawerAFrame3, background(7), tintB, 3);
            backgroundTypeA(this, settingsDrawerAFrame4, background(8), tintB, 3);
            backgroundTypeA(this, settingsDrawerAFrame5, background(12), tintB, 3);

            imageTypeA(this, settingsDrawerAIcon, icon(38), tintB, 60);

            textType(this, settingsDrawerAText1, textC(60), tintB, fontAStyle);
            textType(this, settingsDrawerAText2, textB(42), tintB, fontBStyle);
            textType(this, settingsDrawerAText3, textC(61), tintB, fontAStyle);
            textType(this, settingsDrawerAText4, textB(43), tintB, fontBStyle);
            textType(this, settingsDrawerAText5, "", tintB, fontBStyle);
            textType(this, settingsDrawerAText6, textC(62), tintB, fontAStyle);
            textType(this, settingsDrawerAText7, textB(44), tintB, fontBStyle);
            textType(this, settingsDrawerAText8, "", tintB, fontBStyle);
            textType(this, settingsDrawerAText9, textC(63), tintB, fontAStyle);
            textType(this, settingsDrawerAText10, textB(45), tintB, fontBStyle);
            textType(this, settingsDrawerAText11, "", tintB, fontBStyle);
            textType(this, settingsDrawerALine1, textA(13), tintB, fontBStyle);
            textType(this, settingsDrawerALine2, textA(13), tintB, fontBStyle);
            textType(this, settingsDrawerALine3, textA(17), tintB, fontBStyle);
            textType(this, settingsDrawerALine4, textA(18), tintB, fontBStyle);
            textType(this, settingsDrawerALine5,textA(13), tintB, fontBStyle);

            customTouchModeB(settingsDrawerAFrame2, textC(64), 2, 3, 0);
            customTouchModeB(settingsDrawerAFrame3, "", 2, 3, 5);
            customTouchModeB(settingsDrawerAFrame4, "", 2, 3, 6);
            customTouchModeB(settingsDrawerAFrame5, "", 2, 3, 7);

            configurationsC();
        }
        if(settingsDrawerLayout.findViewById(R.id.settings_drawer_a) == null){
            settingsDrawerLayout.addView(settingsDrawerAView);
            setSize(settingsDrawerALayout, size(2), size(2));
            settingsDrawerOptions();
        }
    }

    View settingsDrawerBView;
    RelativeLayout settingsDrawerBLayout, settingsDrawerBFrame1, settingsDrawerBFrame2, settingsDrawerBFrame3,
            settingsDrawerBFrame4, settingsDrawerBFrame5, settingsDrawerBFrame6, settingsDrawerBFrame7;
    ImageView settingsDrawerBIcon1, settingsDrawerBIcon2, settingsDrawerBIcon3, settingsDrawerBIcon4, settingsDrawerBIcon5;
    TextView settingsDrawerBText1, settingsDrawerBText2, settingsDrawerBText3, settingsDrawerBText4;
    LinearLayout settingsDrawerBListView;
    ScrollView settingsDrawerBScrollView;
    private void settingsDrawerB(){
        if(settingsDrawerBView == null){
            settingsDrawerBView = inflater.inflate(R.layout.settings_drawer_b, null);
            settingsDrawerBLayout = settingsDrawerBView.findViewById(R.id.settings_drawer_b);
            settingsDrawerBScrollView = settingsDrawerBView.findViewById(R.id.settings_drawer_b_scroll_view);
            settingsDrawerBFrame1 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_frame_1);
            settingsDrawerBFrame2 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_frame_2);
            settingsDrawerBFrame3 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_frame_3);
            settingsDrawerBFrame4 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_frame_4);
            settingsDrawerBFrame5 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_frame_5);
            settingsDrawerBFrame6 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_frame_6);
            settingsDrawerBFrame7 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_frame_7);
            settingsDrawerBText1 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_text_1);
            settingsDrawerBText2 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_text_2);
            settingsDrawerBText3 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_text_3);
            settingsDrawerBText4 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_text_4);
            settingsDrawerBIcon1 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_icon_1);
            settingsDrawerBIcon2 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_icon_2);
            settingsDrawerBIcon3 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_icon_3);
            settingsDrawerBIcon4 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_icon_4);
            settingsDrawerBIcon5 = settingsDrawerBView.findViewById(R.id.settings_drawer_b_icon_5);
            settingsDrawerBListView = settingsDrawerBView.findViewById(R.id.settings_drawer_b_list_view);

            backgroundTypeC(this, settingsDrawerBFrame2, background(7), tintA);
            backgroundTypeA(this, settingsDrawerBFrame4, background(9), tintA, 3);
            backgroundTypeA(this, settingsDrawerBFrame7, background(8), tintA, 3);

            imageTypeA(this, settingsDrawerBIcon1, icon(32), tintB, 40);
            imageTypeA(this, settingsDrawerBIcon2, icon(33), tintA, 75);
            imageTypeA(this, settingsDrawerBIcon4, icon(31), tintA, 85);
            imageTypeA(this, settingsDrawerBIcon5, icon(61), tintA, 50);

            textType(this, settingsDrawerBText1, "", tintA, Typeface.BOLD);
            textType(this, settingsDrawerBText2, "", tintA, fontBStyle);
            textType(this, settingsDrawerBText3, "", tintA, Typeface.BOLD);
            textType(this, settingsDrawerBText4, textC(79), tintA, fontAStyle);

            customTouchModeB(settingsDrawerBIcon2, textC(48), 2, 3, 1);
            customTouchModeB(settingsDrawerBFrame4, "", 2, 3, 3);
            customTouchModeB(settingsDrawerBIcon4, textC(49), 2, 3, 4);
            settingsDrawerBFrame3.setVisibility(View.GONE);
            settingsDrawerBFrame6.setVisibility(View.GONE);
        }
        if(settingsDrawerLayout.findViewById(R.id.settings_drawer_b) == null){
            settingsDrawerLayout.addView(settingsDrawerBView);
            setSize(settingsDrawerBLayout, size(2), size(2));
            setMargins(this, settingsDrawerBLayout, 20, 20, 20, 20);
            hiddenAppsInitialize();
        }
    }

    // -----[ SETTINGS DRAWER BUTTONS ]----- //

    private void settingsDrawerPress_0(){
        settingsDrawerLayout.removeView(settingsDrawerAView);
        settingsDrawerB();
    }

    private void settingsDrawerPress_1(){
        settingsDrawerLayout.removeView(settingsDrawerBView);
        settingsDrawerLayout.addView(settingsDrawerAView);
    }

    private void settingsDrawerPress_2(){
        settingsDrawerBFrame1.setVisibility(View.INVISIBLE);
        settingsDrawerBFrame3.setVisibility(View.VISIBLE);
        imageTypeA(this, settingsDrawerBIcon3, icon(50), tintA, 40);
        settingsDrawerBText3.setText(textB(48));
        settingsDrawerBInt = 0;
    }

    private void settingsDrawerPress_3(){
        settingsDrawerCommonB();
    }

    private void settingsDrawerPress_4(){
        if(settingsDrawerBInt == 0){
            if(fileExist(this, "Array - 03"))
                new File(getFilesDir(), "Array - 03").delete();
        }
        if(settingsDrawerBInt == 1){
            if(fileExist(this, "Array - 03")){
                edit(this, "Array - 03", settingsDrawerBString, "");
            }
        }
        hiddenAppsInitialize();
        if(drawerAppsLayout != null)
            drawerRefresh();
        settingsDrawerCommonB();
    }

    private void settingsDrawerPress_5(){
        String type = "";
        if(drawerGridStyle.equals("Tiles"))
            type = "List";

        if(drawerGridStyle.equals("List"))
            type = "Bubbles";

        if(drawerGridStyle.equals("Bubbles"))
            type = "Tiles";
        settingsDrawerCommonA("Drawer Grid Style - ", drawerGridStyle, type);
    }

    private void settingsDrawerPress_6(){
        String order = "";
        if(drawerSortingOrder.equals("Alphabetically"))
            order = "Random";

        if(drawerSortingOrder.equals("Random"))
            order = "Alphabetically";
        settingsDrawerCommonA("Drawer Sorting Order - ", drawerSortingOrder, order);
    }

    private void settingsDrawerPress_7(){
        String browseMode = "";
        if(drawerBrowseMode.equals("[ NONE ]"))
            browseMode = "Index";

        if(drawerBrowseMode.equals("Index"))
            browseMode = "[ NONE ]";
        settingsDrawerCommonA("Drawer Browse Mode - ", drawerBrowseMode, browseMode);
    }

    private void settingsDrawerPress_8(){
        settingsDrawerBInt = 1;
        settingsDrawerBFrame1.setVisibility(View.INVISIBLE);
        settingsDrawerBFrame3.setVisibility(View.VISIBLE);
        switchTint(this, settingsDrawerBIcon3, R.color.transparent);
        settingsDrawerBIcon3.setImageBitmap(appIcon(this, settingsDrawerBString, 40));
        settingsDrawerBText3.setText(appLabel(this, settingsDrawerBString));
    }

    // -----[ SETTINGS DRAWER COMMON ]----- //

    private void settingsDrawerCommonA(String string_a, String string_b, String string_c){
        edit(this, "Configurations - 03", string_a + string_b, string_a + string_c);
        configurationsC();
        settingsDrawerOptions();
        if(drawerAppsLayout != null)
            drawerRefresh();
    }

    private void settingsDrawerCommonB(){
        settingsDrawerBFrame1.setVisibility(View.VISIBLE);
        settingsDrawerBFrame3.setVisibility(View.GONE);
    }

    private void settingsDrawerCommonC(int mode, float alpha, int a){
        if(mode == 0){
            customTouchModeB(settingsDrawerBFrame2, textC(66), 2, 3, 2);
            settingsDrawerBText1.setText("••• " + hiddenListArray.size());
        } else {
            settingsDrawerBFrame2.setOnTouchListener(null);
            settingsDrawerBText1.setText(textC(65));
        }
        settingsDrawerBFrame2.setAlpha(alpha);
        settingsDrawerBText2.setText(textB(a));
    }

    // -----[ SETTINGS DRAWER METHODS ]----- //

    private int settingsDrawerBInt = 0;
    private String settingsDrawerBString = "";
    private void settingsDrawerOptions(){
        settingsDrawerAText5.setText(drawerGridStyle);
        settingsDrawerAText8.setText(drawerSortingOrder);
        if(drawerBrowseMode.equals("[ NONE ]"))
            settingsDrawerAText11.setText("None");

        if(drawerBrowseMode.equals("Index"))
            settingsDrawerAText11.setText("Index " + textA(14));
    }

    List<String> hiddenListArray;
    private void hiddenAppsInitialize(){
        if(!settingsDrawerBString.isEmpty() && !isAppInstalled(this, settingsDrawerBString))
            settingsDrawerCommonB();

        if(fileExist(this, "Array - 03")){
            Handler settingsDrawerBHandler = new Handler();
            Runnable settingsDrawerBRunnable = new Runnable() {
                @Override
                public void run() {
                    hiddenListArray = (ArrayList<String>) read(TheNest.this, "Array - 03");
                    for (Iterator<String> iterator = hiddenListArray.iterator(); iterator.hasNext();) {
                        String value = iterator.next();
                        if (value.isEmpty() || !isAppInstalled(TheNest.this, value))
                            iterator.remove();
                    }
                    if(hiddenListArray.size() != 0){
                        settingsDrawerCommonC(0, 1.0f, 46);

                        if(settingsDrawerBListView.getChildCount() != hiddenListArray.size()){
                            //settingsDrawerBFrame6.setVisibility(View.VISIBLE);
                            settingsDrawerBListView.removeAllViews();
                            hiddenAppsInitialize(hiddenListArray, settingsDrawerBListView);
                        }
                    } else {
                        settingsDrawerBListView.removeAllViews();
                        new File(getFilesDir(), "Array - 03").delete();
                        hiddenAppsInitialize();
                    }
                }
            };
            settingsDrawerBHandler.postDelayed(settingsDrawerBRunnable, 50);
        } else {
            settingsDrawerCommonB();
            settingsDrawerCommonC(1, .5f, 47);
            settingsDrawerBListView.removeAllViews();
        }
    }

    View hiddenListView;
    RelativeLayout hiddenListFrame;
    ImageView hiddenListIcon;
    TextView hiddenListText;
    @SuppressLint("ClickableViewAccessibility")
    private void hiddenAppsInitialize(List<String> list, LinearLayout layout){
        for (int i = 0; i < list.size(); i++) {
            hiddenListView = inflater.inflate(R.layout.linear_type_g, layout, false);
            hiddenListFrame = hiddenListView.findViewById(R.id.linear_type_g_frame);
            hiddenListIcon = hiddenListView.findViewById(R.id.linear_type_g_icon);
            hiddenListText = hiddenListView.findViewById(R.id.linear_type_g_text);

            hiddenListIcon.setImageBitmap(appIcon(this, list.get(i), 40));
            textType(this, hiddenListText, appLabel(this, list.get(i)), tintA, fontBStyle);

            customTouchModeA(hiddenListView, list.get(i), -1, 5);
            layout.addView(hiddenListView);

            if(i < list.size())
                settingsDrawerBFrame6.setVisibility(View.GONE);
        }
    }

    //..........
    //-------------------- SETTINGS -------------------- []
    //---------------------- HOME ---------------------- []
    //``````````

    View settingsHomeView;
    RelativeLayout settingsHomeLayout;
    private void settingsHome(){
        if(settingsHomeView == null){
            settingsHomeView = inflater.inflate(R.layout.settings_home, null);
            settingsHomeLayout = settingsHomeView.findViewById(R.id.settings_home);
        }
        if(theNestSettingsLayout.findViewById(R.id.settings_home) == null){
            theNestSettingsLayout.addView(settingsHomeView);
            layoutParamsTypeC(settingsHomeLayout, new int[]{direction(5)}, direction(8), R.id.settings_edge_a, direction(7), R.id.settings_tooltip);
            setSize(settingsHomeLayout, size(2), size(2));
            setMargins(this, settingsHomeLayout, -40, 20, 20, 60);
            settingsEdgeAText.setText(textC(40));
            if(settingsHomeLayout.getChildCount() == 0)
                settingsHomeA();
        }
    }

    View settingsHomeAView;
    RelativeLayout settingsHomeALayout;
    ListView settingsHomeAListView;
    ContentsB settingsContents;
    private void settingsHomeA(){
        if(settingsHomeAView == null){
            settingsHomeAView = inflater.inflate(R.layout.settings_home_a, null);
            settingsHomeALayout = settingsHomeAView.findViewById(R.id.settings_home_a);
            settingsHomeAListView = settingsHomeAView.findViewById(R.id.settings_home_a_list_view);

            backgroundTypeC(this, settingsHomeALayout, background(3), tintA);
            settingsHomeList();
        }
        if(settingsHomeLayout.findViewById(R.id.settings_home_a) == null){
            settingsHomeLayout.addView(settingsHomeAView);
            setSize(settingsHomeALayout, size(2), size(2));
        }
    }

    // -----[ SETTINGS HOME COMMON ]----- //

    private void settingsHomeCommonA(View view){
        settingsHomeLayout.removeView(view);
        settingsHomeAView.setVisibility(View.VISIBLE);
    }

    // -----[ SETTINGS HOME METHODS ]----- //

    private void settingsHomeList(){
        List<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(reduceBitmap(this, icon(46), 60));
        bitmaps.add(reduceBitmap(this, icon(51), 60));
        bitmaps.add(reduceBitmap(this, icon(47), 60));
        bitmaps.add(reduceBitmap(this, icon(37), 60));
        bitmaps.add(reduceBitmap(this, icon(44), 60));
        bitmaps.add(reduceBitmap(this, icon(36), 60));
        bitmaps.add(reduceBitmap(this, icon(52), 60));

        List<String> strings_a = new ArrayList<>();
        strings_a.add(textC(0));
        strings_a.add(textC(7));
        strings_a.add(textC(1));
        strings_a.add(textC(2));
        strings_a.add(textC(3));
        strings_a.add(textC(5));
        strings_a.add(textC(4));

        List<String> strings_b = new ArrayList<>();
        strings_b.add(textB(49));
        strings_b.add(textB(50));
        strings_b.add(textB(51));
        strings_b.add(textB(52));
        strings_b.add(textB(53));
        strings_b.add(textB(54));
        strings_b.add(textB(55));

        Handler settingsHomeAHandler = new Handler();
        Runnable settingsHomeARunnable = new Runnable() {
            @Override
            public void run() {
                int layout = 0;
                if(homeListStyle.equals("Square"))
                    layout = R.layout.list_style_a;

                if(homeListStyle.equals("Cylinder"))
                    layout = R.layout.list_style_b;

                if(homeListStyle.equals("Hexagon"))
                    layout = R.layout.list_style_c;

                settingsContents = new ContentsB(TheNest.this, layout, bitmaps, strings_a, strings_b, TheNest.this);
                settingsHomeAListView.setAdapter(settingsContents);
            }
        };
        settingsHomeAHandler.postDelayed(settingsHomeARunnable, 100);
    }

    @Override
    public void homeScreenOptions(int mode) {
        settingsHomeAView.setVisibility(View.GONE);
        if(mode == 0)
            settingsHomeB();
        if(mode == 1)
            settingsHomeC();
        if(mode == 2)
            settingsHomeD();
        if(mode == 3)
            settingsHomeE();
        if(mode == 4)
            settingsHomeF();
        if(mode == 5)
            settingsHomeG();
        if(mode == 6)
            settingsHomeH();
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------- WIDGETS ------------------ []
    //``````````

    View settingsHomeBView;
    RelativeLayout settingsHomeBLayout, settingsHomeBFrame1, settingsHomeBFrame2, settingsHomeBFrame3, settingsHomeBFrame4,
            settingsHomeBFrame5, settingsHomeBFrame6, settingsHomeBFrame7, settingsHomeBFrame8, settingsHomeBFrame9,
            settingsHomeBFrame10;
    TextView settingsHomeBLines, settingsHomeBText1, settingsHomeBText2, settingsHomeBText3, settingsHomeBText4,
            settingsHomeBText5, settingsHomeBText6;
    ImageView settingsHomeBImage, settingsHomeBCircle, settingsHomeBIcon1, settingsHomeBIcon2,
            settingsHomeBIcon3, settingsHomeBIcon4, settingsHomeBIcon5, settingsHomeBIcon6, settingsHomeBIcon7;
    LinearLayout settingsHomeBListView;
    ScrollView settingsHomeBScrollView;
    private void settingsHomeB(){
        if(settingsHomeBView == null){
            settingsHomeBView = inflater.inflate(R.layout.settings_home_b, null);
            settingsHomeBLayout = settingsHomeBView.findViewById(R.id.settings_home_b);
            settingsHomeBScrollView = settingsHomeBView.findViewById(R.id.settings_home_b_scroll_view);
            settingsHomeBCircle = settingsHomeBView.findViewById(R.id.settings_home_b_circle);
            settingsHomeBImage = settingsHomeBView.findViewById(R.id.settings_home_b_image);
            settingsHomeBIcon1 = settingsHomeBView.findViewById(R.id.settings_home_b_icon_1);
            settingsHomeBIcon2 = settingsHomeBView.findViewById(R.id.settings_home_b_icon_2);
            settingsHomeBIcon3 = settingsHomeBView.findViewById(R.id.settings_home_b_icon_3);
            settingsHomeBIcon4 = settingsHomeBView.findViewById(R.id.settings_home_b_icon_4);
            settingsHomeBIcon5 = settingsHomeBView.findViewById(R.id.settings_home_b_icon_5);
            settingsHomeBIcon6 = settingsHomeBView.findViewById(R.id.settings_home_b_icon_6);
            settingsHomeBIcon7 = settingsHomeBView.findViewById(R.id.settings_home_b_icon_7);
            settingsHomeBFrame1 = settingsHomeBView.findViewById(R.id.settings_home_b_frame_1);
            settingsHomeBFrame2 = settingsHomeBView.findViewById(R.id.settings_home_b_frame_2);
            settingsHomeBFrame3 = settingsHomeBView.findViewById(R.id.settings_home_b_frame_3);
            settingsHomeBFrame4 = settingsHomeBView.findViewById(R.id.settings_home_b_frame_4);
            settingsHomeBFrame5 = settingsHomeBView.findViewById(R.id.settings_home_b_frame_5);
            settingsHomeBFrame6 = settingsHomeBView.findViewById(R.id.settings_home_b_frame_6);
            settingsHomeBFrame7 = settingsHomeBView.findViewById(R.id.settings_home_b_frame_7);
            settingsHomeBFrame8 = settingsHomeBView.findViewById(R.id.settings_home_b_frame_8);
            settingsHomeBFrame9 = settingsHomeBView.findViewById(R.id.settings_home_b_frame_9);
            settingsHomeBFrame10 = settingsHomeBView.findViewById(R.id.settings_home_b_frame_10);
            settingsHomeBText1 = settingsHomeBView.findViewById(R.id.settings_home_b_text_1);
            settingsHomeBText2 = settingsHomeBView.findViewById(R.id.settings_home_b_text_2);
            settingsHomeBText3 = settingsHomeBView.findViewById(R.id.settings_home_b_text_3);
            settingsHomeBText4 = settingsHomeBView.findViewById(R.id.settings_home_b_text_4);
            settingsHomeBText5 = settingsHomeBView.findViewById(R.id.settings_home_b_text_5);
            settingsHomeBText6 = settingsHomeBView.findViewById(R.id.settings_home_b_text_6);
            settingsHomeBLines = settingsHomeBView.findViewById(R.id.settings_home_b_lines);
            settingsHomeBListView = settingsHomeBView.findViewById(R.id.settings_home_b_list_view);

            backgroundTypeA(this, settingsHomeBFrame2, background(7), ui, 3);
            backgroundTypeA(this, settingsHomeBFrame4, background(5), tintA, 3);
            backgroundTypeC(this, settingsHomeBFrame5, background(3), tintA);
            backgroundTypeC(this, settingsHomeBFrame6, background(10), tintA);
            backgroundTypeC(this, settingsHomeBFrame7, background(3), tintA);
            backgroundTypeC(this, settingsHomeBFrame8, background(5), tintB);
            backgroundTypeC(this, settingsHomeBFrame9, background(5), tintB);
            backgroundTypeC(this, settingsHomeBFrame10, background(5), ui);

            imageTypeB(this, settingsHomeBCircle, background(4), ui);
            imageTypeA(this, settingsHomeBIcon2, icon(33), tintA, 75);
            imageTypeA(this, settingsHomeBIcon3, icon(31), tintA, 30);
            imageTypeA(this, settingsHomeBIcon4, icon(33), tintB, 65);
            imageTypeA(this, settingsHomeBIcon6, icon(48), tintA, 30);
            imageTypeA(this, settingsHomeBIcon7, icon(33), tintA, 65);

            textType(this, settingsHomeBText1, textC(0), tintA, fontAStyle);
            textType(this, settingsHomeBText2, "", tintA, fontBStyle);
            textType(this, settingsHomeBText3, "", tintB, fontBStyle);
            textType(this, settingsHomeBText4, textB(60), tintA, fontBStyle);
            textType(this, settingsHomeBText5, "", tintB, fontAStyle);
            textType(this, settingsHomeBText6, "", tintB, fontBStyle);
            textType(this, settingsHomeBLines, textA(9), tintB, Typeface.BOLD);

            customTouchModeB(settingsHomeBIcon2, textC(48), 2, 4, 0);
            customTouchModeB(settingsHomeBIcon4, textC(48), 2, 4, 5);
            customTouchModeB(settingsHomeBIcon7, textC(48), 2, 4, 42);
            customTouchModeB(settingsHomeBFrame2, "", 2, 4, 1);
            customTouchModeB(settingsHomeBFrame4, "", 2, 4, 2);
            customTouchModeB(settingsHomeBFrame5, "", 2, 4, 3);
            customTouchModeB(settingsHomeBFrame9, textC(49), 2, 4, 4);
            customTouchModeB(settingsHomeBFrame10, textC(10), 2, 4, 6);

            homeWidgetCommonD();
        }
        if(settingsHomeLayout.findViewById(R.id.settings_home_b) == null){
            settingsHomeLayout.addView(settingsHomeBView);
            setSize(settingsHomeBLayout, size(2), size(2));
            setMargins(this, settingsHomeBLayout, 20, 20, 20, 20);
            homeWidgetState();
        }
    }

    // -----[ HOME WIDGETS BUTTONS ]----- //

    private void settingsHomePress_0(){
        settingsHomeCommonA(settingsHomeBView);
    }

    private void settingsHomePress_1(){
        if(widgetState.equals("Enabled")) {
            edit(this, "Configurations - 02", "Widget State - " + widgetState, "Widget State - Disabled");
        } else {
            edit(this, "Configurations - 02", "Widget State - " + widgetState, "Widget State - Enabled");
        }
        homeWidgetState();
        homeWidget();
        homeAppletPositions();
    }

    private void settingsHomePress_2(){
        if(settingsHomeBInt == 0){
            settingsHomeBInt = 1;
        } else {
            if (settingsHomeBInt == 1) {
                settingsHomeBInt = 0;
            }
        }
        homeWidgetTabs();
        homeWidgetInitialize();
    }

    private void settingsHomePress_3(){
        settingsHomeBFrame6.setVisibility(View.VISIBLE);
        settingsHomeBFrame1.setVisibility(View.INVISIBLE);
        if(settingsHomeBListView.getChildCount() == 0)
            homeWidgetInitialize();
    }

    private void settingsHomePress_42(){
        settingsHomeBFrame6.setVisibility(View.INVISIBLE);
        settingsHomeBFrame1.setVisibility(View.VISIBLE);
    }

    private void settingsHomePress_4(){
        if(settingsHomeBInt == 0){
            edit(this, "Configurations - 02", "Widget Mode - " + widgetMode, "Widget Mode - SimpliC");
            if(settingsHomeBString.equals("Analog Clock")){
                edit(this, "Configurations - 02", "Widget Component - " + widgetComponent, "Widget Component - Clock 1");
            } else {
                edit(this, "Configurations - 02", "Widget Component - " + widgetComponent, "Widget Component - Clock 2");
            }
            configurationsB();
            homeWidget();
        } else {
            edit(this, "Configurations - 02", "Widget Mode - " + widgetMode, "Widget Mode - Device");
            homeWidgetConfigure();
        }
        settingsHomePress_5();
        touchTip(this, textC(20), 2);
    }

    private void settingsHomePress_5(){
        settingsHomeBFrame6.setVisibility(View.VISIBLE);
        settingsHomeBFrame7.setVisibility(View.GONE);
        settingsHomeBText4.setVisibility(View.GONE);
    }

    private void settingsHomePress_6(){
        homeWidgetRemove();
        settingsHomePress_5();
        touchTip(this, textC(20), 2);
    }

    private void settingsHomePress_37(){
        settingsHomeBFrame6.setVisibility(View.GONE);
        settingsHomeBFrame7.setVisibility(View.VISIBLE);
        settingsHomeBText4.setVisibility(View.VISIBLE);
        settingsHomeBText5.setText(settingsHomeBString);

        if(settingsHomeBInt == 0){
            switchTint(this, settingsHomeBIcon5, tintB);
            if(settingsHomeBString.equals("Analog Clock")){
                settingsHomeBIcon5.setImageBitmap(reduceBitmap(this, icon(53), 50));
                settingsHomeBImage.setImageDrawable(new BitmapDrawable(getResources(), reduceBitmap(this, drawable(4), 120)));
            }
            if(settingsHomeBString.equals("Simple Clock")){
                settingsHomeBIcon5.setImageBitmap(reduceBitmap(this, icon(54), 50));
                settingsHomeBImage.setImageDrawable(new BitmapDrawable(getResources(), reduceBitmap(this, drawable(5), 120)));
            }
            settingsHomeBText6.setText("By - " + getPackageName());
        } else {
            switchTint(this, settingsHomeBIcon5, R.color.transparent);
            for (AppWidgetProviderInfo info : homeWidgetProvider){
                if(info.loadLabel(getPackageManager()).equals(settingsHomeBString)){
                    settingsHomeBIcon5.setImageBitmap(roundedBitmap(adaptiveIcon(TheNest.this, info.provider.getPackageName(), 40)));
                    settingsHomeBImage.setImageDrawable(info.loadPreviewImage(this, 100));
                    settingsHomeBText6.setText("By - " + info.provider.getPackageName());
                }
            }
        }
        if((settingsHomeBString.equals("Analog Clock") && widgetComponent.equals("Clock 1"))
                || (settingsHomeBString.equals("Simple Clock") && widgetComponent.equals("Clock 2"))){
            settingsHomeBFrame9.setVisibility(View.INVISIBLE);
            settingsHomeBFrame10.setVisibility(View.VISIBLE);
        } else {
            settingsHomeBFrame9.setVisibility(View.VISIBLE);
            settingsHomeBFrame10.setVisibility(View.GONE);
        }
    }

    // -----[ HOME WIDGET COMMON ]----- //

    private boolean settingsHomeBBoolean;
    private void homeWidgetCommonA(AppWidgetProviderInfo info) {
        configurationsB();
        if(homeWidgetLayout.findViewById(R.id.home_widget_b) == null)
            homeWidget();

        homeWidgetInitiate(info);
        if(hostView != null)
            hostView.removeAllViews();

        if(homeWidgetResource != null)
            homeWidgetResource.removeAllViews();

        hostView = null;
        settingsHomeBBoolean = true;
        //one time run fix
        if(widgetCreation.equals("Denied")){
            Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_BIND);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER, info.provider);
            startActivityForResult(intent, 1000);
        }
    }

    private void homeWidgetCommonB(int int_a, int int_b){
        settingsHomeBText3.setText(textB(int_a));
        imageTypeA(this, settingsHomeBIcon1, icon(int_b), tintA, 65);
    }

    private void homeWidgetCommonC(boolean state, int text){
        toggleMode(this, settingsHomeBCircle, state);
        settingsHomeBText2.setText(textB(text));
    }

    private void homeWidgetCommonD(){
        settingsHomeBFrame6.setVisibility(View.GONE);
        settingsHomeBFrame7.setVisibility(View.GONE);
        settingsHomeBText4.setVisibility(View.GONE);
    }

    // -----[ HOME WIDGET METHODS ]----- //

    private void homeWidgetState(){
        configurationsB();
        if(widgetState.equals("Enabled")) {
            homeWidgetCommonC(true, 57);
            settingsHomeBFrame1.setVisibility(View.VISIBLE);
            settingsHomeBFrame3.setVisibility(View.VISIBLE);

            if(settingsHomeBFrame6.getVisibility() == View.VISIBLE
                    || settingsHomeBFrame7.getVisibility() == View.VISIBLE)
                settingsHomeBFrame1.setVisibility(View.GONE);

            homeWidgetTabs();
        } else {
            homeWidgetCommonC(false, 56);
            settingsHomeBFrame1.setVisibility(View.VISIBLE);
            settingsHomeBFrame3.setVisibility(View.GONE);
            homeWidgetCommonD();
        }
    }

    private String settingsHomeBString;
    private int settingsHomeBInt;
    private void homeWidgetTabs(){
        if(settingsHomeBInt == 0)
            homeWidgetCommonB(58, 56);

        if(settingsHomeBInt == 1)
            homeWidgetCommonB(59, 55);
    }

    List<String> homeWidgetsArray;
    private List<AppWidgetProviderInfo> homeWidgetProvider;
    private void homeWidgetInitialize(){
        if(homeWidgetManager == null)
            homeWidgetManager = AppWidgetManager.getInstance(getApplicationContext());

        Handler settingsHomeBHandler =  new Handler();
        Runnable settingsHomeBRunnable =  new Runnable() {
            @Override
            public void run() {
                homeWidgetsArray = new ArrayList<>();
                if(settingsHomeBInt == 0){
                    homeWidgetsArray.add("Analog Clock");
                    homeWidgetsArray.add("Simple Clock");
                }
                if(settingsHomeBInt == 1){
                    homeWidgetProvider = homeWidgetManager.getInstalledProviders();
                    for (AppWidgetProviderInfo info : homeWidgetProvider)
                        homeWidgetsArray.add(info.loadLabel(getPackageManager()));
                }
                settingsHomeBListView.removeAllViews();
                homeWidgetInitialize(homeWidgetsArray, settingsHomeBListView);
            }
        };
        settingsHomeBHandler.postDelayed(settingsHomeBRunnable, 15);
    }

    View widgetsListView;
    RelativeLayout widgetsListFrame;
    ImageView widgetsListIcon;
    TextView widgetsListText;
    @SuppressLint("ClickableViewAccessibility")
    private void homeWidgetInitialize(List<String> list, LinearLayout layout){
        for (int i = 0; i < list.size(); i++) {
            widgetsListView = inflater.inflate(R.layout.linear_type_g, layout, false);
            widgetsListFrame = widgetsListView.findViewById(R.id.linear_type_g_frame);
            widgetsListIcon = widgetsListView.findViewById(R.id.linear_type_g_icon);
            widgetsListText = widgetsListView.findViewById(R.id.linear_type_g_text);

            textType(this, widgetsListText, "", tintA, fontBStyle);

            if(list.get(i).equals("Analog Clock")){
                widgetsListIcon.setImageBitmap(reduceBitmap(this, icon(53), 50));
            } else if(list.get(i).equals("Simple Clock")){
                widgetsListIcon.setImageBitmap(reduceBitmap(this, icon(54), 50));
            } else {
                for (AppWidgetProviderInfo info : homeWidgetProvider){
                    if(list.get(i).equals(info.loadLabel(getPackageManager())))
                        widgetsListIcon.setImageBitmap(appIcon(this, info.provider.getPackageName(), 40));
                }
            }
            widgetsListText.setText(list.get(i));
            customTouchModeA(widgetsListView, list.get(i), -1, 6);
            layout.addView(widgetsListView);
        }
    }

    private void homeWidgetConfigure(){
        if(homeWidgetHost == null)
            homeWidgetHost = new AppWidgetHost(getApplicationContext(), 2062);

        for (AppWidgetProviderInfo info : homeWidgetProvider) {
            if(info.loadLabel(getPackageManager()).equals(settingsHomeBString)){

                edit(this, "Configurations - 02", "Widget Component - " + widgetComponent, "Widget Component - " + info.provider.getPackageName());
                int widget_ID = homeWidgetHost.allocateAppWidgetId();

                edit(this, "Configurations - 02", "Widget ID - " + widgetID, "Widget ID - " + widget_ID);
                widgetID = widget_ID;

                if(info.configure != null){
                    Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
                    intent.setComponent(info.configure);
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
                    if(widgetCreation.equals("Denied")){
                        Intent bind = new Intent(AppWidgetManager.ACTION_APPWIDGET_BIND);
                        bind.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
                        bind.putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER, info.provider);
                        startActivityForResult(bind, 1000);
                    }
                    try {
                        homeWidgetManager.bindAppWidgetIdIfAllowed(widgetID, info.provider);
                    } catch (Exception e){}
                    try {
                        homeWidgetHost.startAppWidgetConfigureActivityForResult(this, widgetID, intent.getFlags(), 2062, null);
                    } catch (Exception e){}
                    //startActivityForResult(intent, 2062);
                } else {
                    homeWidgetCommonA(info);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == 2062)
                homeWidgetCreate(data);
        }
        if (requestCode == 1000){
            edit(this, "Configurations - 02", "Widget Creation - " + widgetCreation,
                    "Widget Creation - Allowed");
            configurationsB();
        }
    }

    private void homeWidgetCreate(Intent data) {
        widgetID = data.getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
        for (AppWidgetProviderInfo info : homeWidgetProvider) {
            if(info.loadLabel(getPackageManager()).equals(settingsHomeBString) && info.configure != null)
                homeWidgetCommonA(info);
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------- APPLET ------------------ []
    //``````````

    View settingsHomeCView;
    RelativeLayout settingsHomeCLayout, settingsHomeCFrame1, settingsHomeCFrame2, settingsHomeCFrame3, settingsHomeCFrame4;
    TextView settingsHomeCText1, settingsHomeCText2, settingsHomeCText3;
    ImageView settingsHomeCIcon1, settingsHomeCIcon2, settingsHomeCIcon3;
    EditText settingsHomeCEditText;
    private void settingsHomeC(){
        if(settingsHomeCView == null){
            settingsHomeCView = inflater.inflate(R.layout.settings_home_c, null);
            settingsHomeCLayout = settingsHomeCView.findViewById(R.id.settings_home_c);
            settingsHomeCIcon1 = settingsHomeCView.findViewById(R.id.settings_home_c_icon_1);
            settingsHomeCIcon2 = settingsHomeCView.findViewById(R.id.settings_home_c_icon_2);
            settingsHomeCIcon3 = settingsHomeCView.findViewById(R.id.settings_home_c_icon_3);
            settingsHomeCFrame1 = settingsHomeCView.findViewById(R.id.settings_home_c_frame_1);
            settingsHomeCFrame2 = settingsHomeCView.findViewById(R.id.settings_home_c_frame_2);
            settingsHomeCFrame3 = settingsHomeCView.findViewById(R.id.settings_home_c_frame_3);
            settingsHomeCFrame4 = settingsHomeCView.findViewById(R.id.settings_home_c_frame_4);
            settingsHomeCText1 = settingsHomeCView.findViewById(R.id.settings_home_c_text_1);
            settingsHomeCText2 = settingsHomeCView.findViewById(R.id.settings_home_c_text_2);
            settingsHomeCText3 = settingsHomeCView.findViewById(R.id.settings_home_c_text_3);
            settingsHomeCEditText = settingsHomeCView.findViewById(R.id.settings_home_c_edittext);

            backgroundTypeA(this, settingsHomeCFrame3, background(5), tintA, 3);
            backgroundTypeC(this, settingsHomeCFrame4, background(3), tintA);

            imageTypeA(this, settingsHomeCIcon2, icon(42), tintA, 50);
            imageTypeA(this, settingsHomeCIcon3, icon(33), tintA, 75);

            textType(this, settingsHomeCText1, textC(7), tintA, fontAStyle);
            textType(this, settingsHomeCText2, textB(62), tintA, fontBStyle);
            textType(this, settingsHomeCText3, "", tintB, fontAStyle);

            customTypeB(this, settingsHomeCEditText, textA(6), tintB, fontBStyle);

            customTouchModeB(settingsHomeCIcon3, textC(48), 2, 4, 7);
            customTouchModeB(settingsHomeCFrame3, textC(22), 2, 4, 10);

            homeAppletCommonA(View.GONE, View.VISIBLE, 45, tintA, 14, 8, 1);
            settingsHomeApplet();
        }
        if(settingsHomeLayout.findViewById(R.id.settings_home_c) == null){
            settingsHomeLayout.addView(settingsHomeCView);
            setSize(settingsHomeCLayout, size(2), size(2));
            setMargins(this, settingsHomeCLayout, 20, 20, 20, 20);
        }
    }

    // -----[ HOME APPLET BUTTONS ]----- //

    private void settingsHomePress_7(){
        settingsHomeCommonA(settingsHomeCView);
    }

    private void settingsHomePress_8(){
        homeAppletCommonA(View.VISIBLE, View.GONE, 31, tintB, 13, 9, 0);
    }

    private void settingsHomePress_9(){
        homeAppletCommonA(View.GONE, View.VISIBLE, 45, tintA, 14, 8, 1);
        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

        edit(this, "Configurations - 02", "Applet Text - " + appletText, "Applet Text - " + settingsHomeCEditText.getText().toString().trim());
        appletText = settingsHomeCEditText.getText().toString().trim();
        homeAppletCommonB();
    }

    private void settingsHomePress_10(){
        if(settingsHomeCText3.getVisibility() != View.VISIBLE)
            homeAppletCommonA(View.GONE, View.VISIBLE, 45, tintA, 14, 8, 1);

        edit(this, "Configurations - 02", "Applet Text - " + appletText, "Applet Text - ∆×••⟨[ ]⟩");
        appletText = "∆×••⟨[ ]⟩";
        homeAppletCommonB();
    }

    // -----[ HOME APPLET COMMON ]----- //

    private void homeAppletCommonA(int sight_a, int sight_b, int icon, int tint, int text, int count,  int mode){
        settingsHomeCEditText.setVisibility(sight_a);
        settingsHomeCText3.setVisibility(sight_b);
        imageTypeA(this, settingsHomeCIcon1, icon(icon), tint, 50);
        customTouchModeB(settingsHomeCFrame2, textC(text), 2, 4, count);
        if(mode == 0){
            backgroundTypeC(this, settingsHomeCFrame2, background(5), ui);
        } else {
            backgroundTypeA(this, settingsHomeCFrame2, background(5), tintA, 3);
        }
    }

    private void homeAppletCommonB(){
        homeAppletAText.setText(appletText);
        homeAppletText();
        settingsHomeApplet();
    }

    // -----[ HOME APPLET METHODS ]----- //

    private void settingsHomeApplet(){
        settingsHomeCEditText.setText(appletText);
        settingsHomeCText3.setText(appletText);
        if(!appletText.equals("∆×••⟨[ ]⟩")){
            settingsHomeCFrame3.setVisibility(View.VISIBLE);
        } else {
            settingsHomeCFrame3.setVisibility(View.GONE);
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------ WALLPAPER ----------------- []
    //``````````

    View settingsHomeDView;
    RelativeLayout settingsHomeDLayout, settingsHomeDFrame1, settingsHomeDFrame2, settingsHomeDFrame3, settingsHomeDFrame4,
            settingsHomeDFrame5, settingsHomeDFrame6, settingsHomeDFrame7, settingsHomeDFrame8, settingsHomeDFrame9;
    TextView settingsHomeDText1, settingsHomeDText2, settingsHomeDText3, settingsHomeDText4;
    ImageView settingsHomeDImage, settingsHomeDCircle, settingsHomeDIcon1, settingsHomeDIcon2, settingsHomeDIcon3,
            settingsHomeDIcon4;
    ScrollView settingsHomeDScrollView;
    private void settingsHomeD(){
        if(settingsHomeDView == null){
            settingsHomeDView = inflater.inflate(R.layout.settings_home_d, null);
            settingsHomeDLayout = settingsHomeDView.findViewById(R.id.settings_home_d);
            settingsHomeDScrollView = settingsHomeDView.findViewById(R.id.settings_home_d_scroll_view);
            settingsHomeDCircle = settingsHomeDView.findViewById(R.id.settings_home_d_circle);
            settingsHomeDImage = settingsHomeDView.findViewById(R.id.settings_home_d_image);
            settingsHomeDIcon1 = settingsHomeDView.findViewById(R.id.settings_home_d_icon_1);
            settingsHomeDIcon2 = settingsHomeDView.findViewById(R.id.settings_home_d_icon_2);
            settingsHomeDIcon3 = settingsHomeDView.findViewById(R.id.settings_home_d_icon_3);
            settingsHomeDIcon4 = settingsHomeDView.findViewById(R.id.settings_home_d_icon_4);
            settingsHomeDFrame1 = settingsHomeDView.findViewById(R.id.settings_home_d_frame_1);
            settingsHomeDFrame2 = settingsHomeDView.findViewById(R.id.settings_home_d_frame_2);
            settingsHomeDFrame3 = settingsHomeDView.findViewById(R.id.settings_home_d_frame_3);
            settingsHomeDFrame4 = settingsHomeDView.findViewById(R.id.settings_home_d_frame_4);
            settingsHomeDFrame5 = settingsHomeDView.findViewById(R.id.settings_home_d_frame_5);
            settingsHomeDFrame6 = settingsHomeDView.findViewById(R.id.settings_home_d_frame_6);
            settingsHomeDFrame7 = settingsHomeDView.findViewById(R.id.settings_home_d_frame_7);
            settingsHomeDFrame8 = settingsHomeDView.findViewById(R.id.settings_home_d_frame_8);
            settingsHomeDFrame9 = settingsHomeDView.findViewById(R.id.settings_home_d_frame_9);
            settingsHomeDText1 = settingsHomeDView.findViewById(R.id.settings_home_d_text_1);
            settingsHomeDText2 = settingsHomeDView.findViewById(R.id.settings_home_d_text_2);
            settingsHomeDText3 = settingsHomeDView.findViewById(R.id.settings_home_d_text_3);
            settingsHomeDText4 = settingsHomeDView.findViewById(R.id.settings_home_d_text_4);

            backgroundTypeA(this, settingsHomeDFrame2, background(7), ui, 3);
            backgroundTypeA(this, settingsHomeDFrame4, background(5), tintA, 3);
            backgroundTypeC(this, settingsHomeDFrame5, background(3), tintA);
            backgroundTypeC(this, settingsHomeDFrame6, background(10), tintA);

            imageTypeA(this, settingsHomeDImage, drawable(1), R.color.transparent, 70);
            imageTypeB(this, settingsHomeDCircle, background(4), ui);
            imageTypeA(this, settingsHomeDIcon1, icon(33), tintA, 75);
            imageTypeA(this, settingsHomeDIcon4, icon(33), tintA, 65);

            textType(this, settingsHomeDText1, textC(1), tintA, fontAStyle);
            textType(this, settingsHomeDText2, "", tintA, fontBStyle);
            textType(this, settingsHomeDText3, "", tintB, fontBStyle);
            textType(this, settingsHomeDText4, "", tintA, fontBStyle);

            customTouchModeB(settingsHomeDIcon1, textC(48), 2, 4, 11);
            customTouchModeB(settingsHomeDIcon4, textC(48), 2, 4, 43);
            customTouchModeB(settingsHomeDFrame2, "", 2, 4, 12);
            customTouchModeB(settingsHomeDFrame4, "", 2, 4, 13);
            customTouchModeB(settingsHomeDFrame5, "", 2, 4, 14);
            customTouchModeB(settingsHomeDImage, "", 2, 4, 38);

            settingsHomeDFrame6.setVisibility(View.GONE);
        }
        if(settingsHomeLayout.findViewById(R.id.settings_home_d) == null){
            settingsHomeLayout.addView(settingsHomeDView);
            setSize(settingsHomeDLayout, size(2), size(2));
            setMargins(this, settingsHomeDLayout, 20, 20, 20, 20);
            homeWallpaperState();
        }
    }

    // -----[ HOME WALLPAPER BUTTONS ]----- //

    private void settingsHomePress_11(){
        settingsHomeCommonA(settingsHomeDView);
    }

    private void settingsHomePress_12(){
        if(wallpaperState.equals("Enabled")) {
            edit(this, "Configurations - 02", "Wallpaper State - " + wallpaperState, "Wallpaper State - Disabled");
        } else {
            edit(this, "Configurations - 02", "Wallpaper State - " + wallpaperState, "Wallpaper State - Enabled");
        }
        homeWallpaperState();
        homeWallpaper();
        centerPositions();
    }

    private void settingsHomePress_13(){
        if(settingsHomeDInt == 0){
            settingsHomeDInt = 1;
        } else {
            if (settingsHomeDInt == 1){
                settingsHomeDInt = 0;
            }
        }
        homeWallpaperTabs();
        homeWallpaperInitialize();
    }

    private void settingsHomePress_14(){
        settingsHomeDFrame6.setVisibility(View.VISIBLE);
        settingsHomeDFrame1.setVisibility(View.INVISIBLE);
        if(!initialized)
            homeWallpaperInitialize();
    }

    private void settingsHomePress_43(){
        settingsHomeDFrame6.setVisibility(View.INVISIBLE);
        settingsHomeDFrame1.setVisibility(View.VISIBLE);
    }

    private void settingsHomePress_15(){
        String mode = "";
        String component = "";
        if(settingsHomeDInt == 0){
            mode = "SimpliC";
            component = "Blocks";
        } else {
            mode = "Device";
            component = "Unknown";
        }

        edit(this, "Configurations - 02", "Wallpaper Mode - " + wallpaperMode, "Wallpaper Mode - " + mode);
        edit(this, "Configurations - 02", "Wallpaper Component - " + wallpaperComponent, "Wallpaper Component - " + component);
        configurationsB();
        homeWallpaper();
        homeWallpaperInitialize();
        touchTip(this, textC(20), 2);
    }

    private void settingsHomePress_16(){
        homeWallpaperRemove();
        homeWallpaperInitialize();
        touchTip(this, textC(20), 2);
    }

    private void settingsHomePress_38(){
        if(settingsHomeDFrame7.getVisibility() != View.VISIBLE){
            settingsHomeDFrame7.setVisibility(View.VISIBLE);
            if(wallpaperComponent.equals("Blocks")){
                homeWallpaperCommonB(48, ui, 10, 16);
            } else {
                homeWallpaperCommonB(31, tintA, 49, 15);
            }
        }
    }

    // -----[ HOME WALLPAPER COMMON ]----- //

    private void homeWallpaperCommonA(){
        settingsHomeDFrame1.setVisibility(View.VISIBLE);
        settingsHomeDFrame3.setVisibility(View.VISIBLE);
        settingsHomeDFrame6.setVisibility(View.GONE);
    }

    private void homeWallpaperCommonB(int icon, int tint, int text, int count){
        imageTypeA(this, settingsHomeDIcon3, icon(icon), tintB, 40);
        backgroundTypeC(this, settingsHomeDFrame7, background(4), tint);
        customTouchModeB(settingsHomeDFrame7, textC(text), 2, 4, count);
    }

    private void homeWallpaperCommonC(int text, int icon){
        settingsHomeDText3.setText(textB(text));
        imageTypeA(this, settingsHomeDIcon2, icon(icon), tintA, 65);
    }

    // -----[ HOME WALLPAPER METHODS ]----- //

    private void homeWallpaperState(){
        configurationsB();
        if(wallpaperState.equals("Enabled")){
            toggleMode(this, settingsHomeDCircle, true);
            settingsHomeDText2.setText(textB(64));

            if(settingsHomeDFrame6.getVisibility() != View.VISIBLE){
                homeWallpaperCommonA();
            } else {
                homeWallpaperInitialize();
            }
            homeWallpaperTabs();
        } else {
            toggleMode(this, settingsHomeDCircle, false);
            settingsHomeDText2.setText(textB(63));

            settingsHomeDFrame1.setVisibility(View.VISIBLE);
            settingsHomeDFrame3.setVisibility(View.GONE);
            settingsHomeDFrame6.setVisibility(View.GONE);
        }
    }

    private int settingsHomeDInt;
    private void homeWallpaperTabs(){
        if(settingsHomeDInt == 0)
            homeWallpaperCommonC(65, 56);

        if(settingsHomeDInt == 1)
            homeWallpaperCommonC(66, 55);
    }

    boolean initialized;
    @SuppressLint("ClickableViewAccessibility")
    private void homeWallpaperInitialize(){
        if(!initialized)
            initialized = true;
        settingsHomeDFrame7.setVisibility(View.GONE);
        if(settingsHomeDInt == 0){
            settingsHomeDScrollView.setVisibility(View.VISIBLE);
            settingsHomeDText4.setText(textB(68));
        }
        if(settingsHomeDInt == 1){
            settingsHomeDScrollView.setVisibility(View.GONE);
            if(wallpaperMode.equals("Device")){
                settingsHomeDText4.setText(textB(69));
            } else {
                homeWallpaperCommonB(31, tintA, 49, 15);
                settingsHomeDFrame7.setVisibility(View.VISIBLE);
                settingsHomeDText4.setText(textB(67));
            }
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------- FOLDER ------------------- []
    //``````````

    View settingsHomeEView;
    RelativeLayout settingsHomeELayout, settingsHomeEFrame1, settingsHomeEFrame2, settingsHomeEFrame3, settingsHomeEFrame4,
            settingsHomeEFrame5, settingsHomeEFrame6, settingsHomeEFrame7, settingsHomeEFrame8;
    TextView settingsHomeEText1, settingsHomeEText2, settingsHomeEText3, settingsHomeEText4, settingsHomeEText5,
            settingsHomeEText6;
    ImageView settingsHomeECircle, settingsHomeEIcon1, settingsHomeEIcon2, settingsHomeEIcon3, settingsHomeEIcon4;
    LinearLayout settingsHomeEListView;
    ScrollView settingsHomeEScrollView;
    private void settingsHomeE(){
        if(settingsHomeEView == null){
            settingsHomeEView = inflater.inflate(R.layout.settings_home_e, null);
            settingsHomeELayout = settingsHomeEView.findViewById(R.id.settings_home_e);
            settingsHomeEScrollView = settingsHomeEView.findViewById(R.id.settings_home_e_scroll_view);
            settingsHomeECircle = settingsHomeEView.findViewById(R.id.settings_home_e_circle);
            settingsHomeEIcon1 = settingsHomeEView.findViewById(R.id.settings_home_e_icon_1);
            settingsHomeEIcon2 = settingsHomeEView.findViewById(R.id.settings_home_e_icon_2);
            settingsHomeEIcon3 = settingsHomeEView.findViewById(R.id.settings_home_e_icon_3);
            settingsHomeEIcon4 = settingsHomeEView.findViewById(R.id.settings_home_e_icon_4);
            settingsHomeEFrame1 = settingsHomeEView.findViewById(R.id.settings_home_e_frame_1);
            settingsHomeEFrame2 = settingsHomeEView.findViewById(R.id.settings_home_e_frame_2);
            settingsHomeEFrame3 = settingsHomeEView.findViewById(R.id.settings_home_e_frame_3);
            settingsHomeEFrame4 = settingsHomeEView.findViewById(R.id.settings_home_e_frame_4);
            settingsHomeEFrame5 = settingsHomeEView.findViewById(R.id.settings_home_e_frame_5);
            settingsHomeEFrame6 = settingsHomeEView.findViewById(R.id.settings_home_e_frame_6);
            settingsHomeEFrame7 = settingsHomeEView.findViewById(R.id.settings_home_e_frame_7);
            settingsHomeEFrame8 = settingsHomeEView.findViewById(R.id.settings_home_e_frame_8);
            settingsHomeEText1 = settingsHomeEView.findViewById(R.id.settings_home_e_text_1);
            settingsHomeEText2 = settingsHomeEView.findViewById(R.id.settings_home_e_text_2);
            settingsHomeEText3 = settingsHomeEView.findViewById(R.id.settings_home_e_text_3);
            settingsHomeEText4 = settingsHomeEView.findViewById(R.id.settings_home_e_text_4);
            settingsHomeEText5 = settingsHomeEView.findViewById(R.id.settings_home_e_text_5);
            settingsHomeEText6 = settingsHomeEView.findViewById(R.id.settings_home_e_text_6);
            settingsHomeEListView = settingsHomeEView.findViewById(R.id.settings_home_e_list_view);

            backgroundTypeA(this, settingsHomeEFrame2, background(7), ui, 3);
            backgroundTypeA(this, settingsHomeEFrame4, background(5), tintA, 3);
            backgroundTypeA(this, settingsHomeEFrame5, background(7), tintA, 10);
            backgroundTypeC(this, settingsHomeEFrame6, background(10), tintA);
            backgroundTypeA(this, settingsHomeEFrame8, background(8), tintA, 3);

            imageTypeB(this, settingsHomeECircle, background(4), ui);
            imageTypeA(this, settingsHomeEIcon1, icon(33), tintA, 75);
            imageTypeA(this, settingsHomeEIcon2, icon(57), tintA, 50);
            imageTypeA(this, settingsHomeEIcon3, icon(33), tintA, 65);
            imageTypeA(this, settingsHomeEIcon4, icon(61), tintA, 50);

            textType(this, settingsHomeEText1, textC(2), tintA, fontAStyle);
            textType(this, settingsHomeEText2, "", tintA, fontBStyle);
            textType(this, settingsHomeEText3, textC(8), tintA, fontBStyle);
            textType(this, settingsHomeEText4, textB(72), tintA, fontBStyle);
            textType(this, settingsHomeEText5, folderSortingOrder, tintA, fontBStyle);
            textType(this, settingsHomeEText6, textC(79), tintA, fontAStyle);

            customTouchModeB(settingsHomeEIcon1, textC(48), 2, 4, 17);
            customTouchModeB(settingsHomeEIcon3, textC(48), 2, 4, 21);
            customTouchModeB(settingsHomeEFrame2, "", 2, 4, 18);
            customTouchModeB(settingsHomeEFrame4, textC(68), 2, 4, 19);
            customTouchModeB(settingsHomeEFrame5, textC(62), 2, 4, 20);

            settingsHomeEFrame6.setVisibility(View.GONE);
        }
        if(settingsHomeLayout.findViewById(R.id.settings_home_e) == null){
            settingsHomeLayout.addView(settingsHomeEView);
            setSize(settingsHomeELayout, size(2), size(2));
            setMargins(this, settingsHomeELayout, 20, 20, 20, 20);
            homeFolderState();
        }
    }

    // -----[ HOME FOLDER BUTTONS ]----- //

    private void settingsHomePress_17(){
        settingsHomeCommonA(settingsHomeEView);
    }

    private void settingsHomePress_18(){
        if(folderState.equals("Enabled")) {
            edit(this, "Configurations - 02", "Folder State - " + folderState, "Folder State - Disabled");
        } else {
            edit(this, "Configurations - 02", "Folder State - " + folderState, "Folder State - Enabled");
        }
        homeFolderState();
        homeFolder();
        centerPositions();
    }

    private void settingsHomePress_19(){
        settingsHomeEFrame1.setVisibility(View.GONE);
        settingsHomeEFrame3.setVisibility(View.GONE);
        settingsHomeEFrame6.setVisibility(View.VISIBLE);
        homeFolderInitialize();
    }

    private void settingsHomePress_20(){
        String sort = "";
        if(folderSortingOrder.equals("Alphabetically")){
            sort = "Random";
        } else {
            sort = "Alphabetically";
        }
        edit(this, "Configurations - 02", "Folder Sorting Order - "
                + folderSortingOrder, "Folder Sorting Order - " + sort);
        configurationsB();
        settingsHomeEText5.setText(folderSortingOrder);
        homeFolderCommonB(settingsHomeEListView, homeFolderArray, 0);
        homeFolderCommonB(homeFolderDListView, folderListArray, 1);
    }

    private void settingsHomePress_21(){
        homeFolderCommonC();
    }

    private void settingsHomePress_39(String folderApp){
        if(isAppInstalled(this, folderApp)){
            create(this, "Array - 01", folderApp);
            touchTip(this, textC(20), 2);
        } else {
            touchTip(this, textC(67), 2);
        }
        homeFolderArray.remove(folderApp);
        for(int i = 0; i < settingsHomeEListView.getChildCount(); ++i){
            TextView textView = settingsHomeEListView.getChildAt(i).findViewById(R.id.linear_type_g_text);
            if(textView.getText().equals(appLabel(this, folderApp)))
                settingsHomeEListView.removeView(settingsHomeEListView.getChildAt(i));
        }
    }

    // -----[ HOME FOLDER COMMON ]----- //

    private void homeFolderCommonA(boolean state, int text){
        toggleMode(this, settingsHomeECircle, state);
        settingsHomeEText2.setText(textB(text));
    }

    private void homeFolderCommonB(LinearLayout view, List<String> list, int mode){
        if(view != null && list != null) {
            if (folderSortingOrder.equals("Alphabetically")) {
                Collections.sort(list, new Comparator<String>() {
                    @Override
                    public int compare(String a, String b) {
                        return appLabel(TheNest.this, a).compareTo(appLabel(TheNest.this, b));
                    }
                });
            } else {
                Collections.sort(list);
            }
            view.removeAllViews();
            if (mode == 0) {
                homeFolderList(list, view);
            } else {
                homeFolderInitialize(list, view);
            }
        }
    }

    private void homeFolderCommonC(){
        settingsHomeEFrame1.setVisibility(View.VISIBLE);
        settingsHomeEFrame3.setVisibility(View.VISIBLE);
        settingsHomeEFrame6.setVisibility(View.GONE);
    }

    // -----[ HOME FOLDER METHODS ]----- //

    private void homeFolderState(){
        configurationsB();
        if(folderState.equals("Enabled")){
            homeFolderCommonA(true, 71);

            if(settingsHomeEFrame6.getVisibility() != View.VISIBLE){
                homeFolderCommonC();
            } else {
                homeFolderInitialize();
            }
        } else {
            homeFolderCommonA(false, 70);

            settingsHomeEFrame1.setVisibility(View.VISIBLE);
            settingsHomeEFrame3.setVisibility(View.GONE);
            settingsHomeEFrame6.setVisibility(View.GONE);
        }
    }

    List<String> homeFolderArray;
    private int settingsHomeEInt = 0;
    private void homeFolderInitialize(){
        Handler settingsHomeEHandler = new Handler();
        Runnable settingsHomeERunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> apps = getPackageManager().queryIntentActivities(intent, PackageManager.GET_META_DATA);
                if(settingsHomeEInt != apps.size()){
                    settingsHomeEInt = apps.size();

                    if(folderSortingOrder.equals("Alphabetically")){
                        Collections.sort(apps, new Comparator<ResolveInfo>() {
                            @Override
                            public int compare(ResolveInfo resolveInfo_1, ResolveInfo resolveInfo_2) {
                                return resolveInfo_1.loadLabel(getPackageManager()).toString().compareTo(resolveInfo_2.loadLabel(getPackageManager()).toString());
                            }
                        });
                    }
                    homeFolderArray = new ArrayList<>();
                    for (ResolveInfo resolveInfo : apps){
                        if(!homeFolderArray.contains(resolveInfo.activityInfo.packageName))
                            homeFolderArray.add(resolveInfo.activityInfo.packageName);
                    }

                    if(fileExist(TheNest.this, "Array - 01")) {
                        List<String> readValues = read(TheNest.this, "Array - 01");
                        for (Iterator<String> iterator = homeFolderArray.iterator(); iterator.hasNext();) {
                            String value = iterator.next();
                            if (readValues.contains(value))
                                iterator.remove();
                        }
                    }
                    settingsHomeEListView.removeAllViews();
                    homeFolderList(homeFolderArray, settingsHomeEListView);
                }
            }
        };
        settingsHomeEHandler.postDelayed(settingsHomeERunnable, 50);
    }

    View homeFolderView;
    RelativeLayout homeFolderFrame;
    ImageView homeFolderIcon;
    TextView homeFolderText;
    @SuppressLint("ClickableViewAccessibility")
    private void homeFolderList(List<String> list, LinearLayout layout){
        for (int i = 0; i < list.size(); i++) {
            homeFolderView = inflater.inflate(R.layout.linear_type_g, layout, false);
            homeFolderFrame = homeFolderView.findViewById(R.id.linear_type_g_frame);
            homeFolderIcon = homeFolderView.findViewById(R.id.linear_type_g_icon);
            homeFolderText = homeFolderView.findViewById(R.id.linear_type_g_text);

            homeFolderIcon.setImageBitmap(appIcon(this, list.get(i), 40));
            textType(this, homeFolderText, appLabel(this, list.get(i)), tintA, fontBStyle);

            customTouchModeA(homeFolderView, list.get(i), -1, 7);
            layout.addView(homeFolderView);

            if(i < list.size())
                settingsHomeEFrame7.setVisibility(View.GONE);
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------- STATUS ------------------- []
    //``````````

    View settingsHomeFView;
    RelativeLayout settingsHomeFLayout, settingsHomeFFrame1, settingsHomeFFrame2, settingsHomeFFrame3, settingsHomeFFrame4,
            settingsHomeFFrame5, settingsHomeFFrame6, settingsHomeFFrame7;
    TextView settingsHomeFLines, settingsHomeFText1, settingsHomeFText2, settingsHomeFText3, settingsHomeFText4;
    ImageView settingsHomeFCircle, settingsHomeFIcon1, settingsHomeFIcon2, settingsHomeFIcon3, settingsHomeFIcon4,
            settingsHomeFIcon5;
    LinearLayout settingsHomeFListView;
    ScrollView settingsHomeFScrollView;
    private void settingsHomeF(){
        if(settingsHomeFView == null){
            settingsHomeFView = inflater.inflate(R.layout.settings_home_f, null);
            settingsHomeFLayout = settingsHomeFView.findViewById(R.id.settings_home_f);
            settingsHomeFScrollView = settingsHomeFView.findViewById(R.id.settings_home_f_scroll_view);
            settingsHomeFCircle = settingsHomeFView.findViewById(R.id.settings_home_f_circle);
            settingsHomeFIcon1 = settingsHomeFView.findViewById(R.id.settings_home_f_icon_1);
            settingsHomeFIcon2 = settingsHomeFView.findViewById(R.id.settings_home_f_icon_2);
            settingsHomeFIcon3 = settingsHomeFView.findViewById(R.id.settings_home_f_icon_3);
            settingsHomeFIcon4 = settingsHomeFView.findViewById(R.id.settings_home_f_icon_4);
            settingsHomeFIcon5 = settingsHomeFView.findViewById(R.id.settings_home_f_icon_5);
            settingsHomeFFrame1 = settingsHomeFView.findViewById(R.id.settings_home_f_frame_1);
            settingsHomeFFrame2 = settingsHomeFView.findViewById(R.id.settings_home_f_frame_2);
            settingsHomeFFrame3 = settingsHomeFView.findViewById(R.id.settings_home_f_frame_3);
            settingsHomeFFrame4 = settingsHomeFView.findViewById(R.id.settings_home_f_frame_4);
            settingsHomeFFrame5 = settingsHomeFView.findViewById(R.id.settings_home_f_frame_5);
            settingsHomeFFrame6 = settingsHomeFView.findViewById(R.id.settings_home_f_frame_6);
            settingsHomeFFrame7 = settingsHomeFView.findViewById(R.id.settings_home_f_frame_7);
            settingsHomeFText1 = settingsHomeFView.findViewById(R.id.settings_home_f_text_1);
            settingsHomeFText2 = settingsHomeFView.findViewById(R.id.settings_home_f_text_2);
            settingsHomeFText3 = settingsHomeFView.findViewById(R.id.settings_home_f_text_3);
            settingsHomeFText4 = settingsHomeFView.findViewById(R.id.settings_home_f_text_4);
            settingsHomeFLines = settingsHomeFView.findViewById(R.id.settings_home_f_lines);
            settingsHomeFListView = settingsHomeFView.findViewById(R.id.settings_home_f_list_view);

            backgroundTypeA(this, settingsHomeFFrame2, background(7), ui, 3);
            backgroundTypeA(this, settingsHomeFFrame4, background(5), tintA, 3);
            backgroundTypeC(this, settingsHomeFFrame5, background(10), tintA);
            backgroundTypeC(this, settingsHomeFFrame7, background(10), tintA);

            imageTypeB(this, settingsHomeFCircle, background(4), ui);
            imageTypeA(this, settingsHomeFIcon1, icon(33), tintA, 75);
            imageTypeA(this, settingsHomeFIcon2, icon(59), tintA, 50);
            imageTypeA(this, settingsHomeFIcon3, icon(33), tintA, 65);
            imageTypeA(this, settingsHomeFIcon5, icon(31), tintA, 65);

            textType(this, settingsHomeFText1, textC(3), tintA, fontAStyle);
            textType(this, settingsHomeFText2, "", tintA, fontBStyle);
            textType(this, settingsHomeFText3, textB(75), tintA, fontBStyle);
            textType(this, settingsHomeFText4, "", tintA, fontAStyle);
            textType(this, settingsHomeFLines, textA(9), tintA, Typeface.BOLD);

            customTouchModeB(settingsHomeFIcon1, textC(48), 2, 4, 22);
            customTouchModeB(settingsHomeFIcon3, textC(48), 2, 4, 25);
            customTouchModeB(settingsHomeFIcon5, textC(49), 2, 4, 26);
            customTouchModeB(settingsHomeFFrame2, "", 2, 4, 23);
            customTouchModeB(settingsHomeFFrame4, textC(69), 2, 4, 24);
            customTouchModeB(settingsHomeFFrame7, "", 2, 4, 27);

            homeStatusCommonA(settingsHomeFFrame5, settingsHomeFFrame6, View.GONE, View.GONE);
        }
        if(settingsHomeLayout.findViewById(R.id.settings_home_f) == null){
            settingsHomeLayout.addView(settingsHomeFView);
            setSize(settingsHomeFLayout, size(2), size(2));
            setMargins(this, settingsHomeFLayout, 20, 20, 20, 20);
            homeStatusState();
        }
    }

    // -----[ HOME STATUS BUTTONS ]----- //

    private void settingsHomePress_22(){
        settingsHomeCommonA(settingsHomeFView);
    }

    private void settingsHomePress_23(){
        if(statusState.equals("Enabled")) {
            edit(this, "Configurations - 02", "Status State - " + statusState, "Status State - Disabled");
        } else {
            edit(this, "Configurations - 02", "Status State - " + statusState, "Status State - Enabled");
        }
        homeStatusState();
        homeStatus();
        centerPositions();
    }

    private void settingsHomePress_24(){
        homeStatusCommonA(settingsHomeFFrame1, settingsHomeFFrame5, View.GONE, View.VISIBLE);
        homeStatusInitialize();
    }

    private void settingsHomePress_25(){
        homeStatusCommonA(settingsHomeFFrame1, settingsHomeFFrame5, View.VISIBLE, View.GONE);
    }

    private void settingsHomePress_26(){
        edit(this, "Configurations - 02", "Status Mode - " + statusMode, "Status Mode - " + settingsHomeFString);
        configurationsB();
        homeStatus();
        homeStatusCommonA(settingsHomeFFrame6, settingsHomeFListView, View.GONE, View.VISIBLE);
    }

    private void settingsHomePress_27(){
        homeStatusCommonA(settingsHomeFFrame6, settingsHomeFListView, View.GONE, View.VISIBLE);
    }

    private void settingsHomePress_40(String status){
        homeStatusCommonA(settingsHomeFFrame6, settingsHomeFListView, View.VISIBLE, View.INVISIBLE);
        if(status.equals("Battery Status"))
            imageTypeA(this, settingsHomeFIcon4, icon(58), tintA, 50);

        if(statusMode.equals(status)){
            settingsHomeFText4.setText(textC(71));
            settingsHomeFString = "[ NONE ]";
        } else {
            settingsHomeFText4.setText(textC(70));
            settingsHomeFString = status;
        }
    }

    // -----[ HOME STATUS COMMON ]----- //

    private void homeStatusCommonA(View view_a, View view_b, int sight_a, int sight_b){
        view_a.setVisibility(sight_a);
        view_b.setVisibility(sight_b);
    }

    private void homeStatusCommonB(boolean stats, int text){
        toggleMode(this, settingsHomeFCircle, stats);
        settingsHomeFText2.setText(textB(text));
    }

    private void homeStatusCommonC(int sight){
        homeStatusCommonA(settingsHomeFFrame1, settingsHomeFFrame5, View.VISIBLE, View.GONE);
        settingsHomeFFrame4.setVisibility(sight);
        if(settingsHomeFFrame6.getVisibility() != View.VISIBLE)
            settingsHomeFFrame6.setVisibility(View.GONE);
    }

    // -----[ HOME STATUS METHODS ]----- //

    private void homeStatusState(){
        configurationsB();
        if(statusState.equals("Enabled")){
            homeStatusCommonB(true, 74);
            if(settingsHomeFFrame5.getVisibility() != View.VISIBLE)
                homeStatusCommonC(View.VISIBLE);
        } else {
            homeStatusCommonB(false, 73);
            homeStatusCommonC(View.GONE);
        }
    }

    private String settingsHomeFString;
    private void homeStatusInitialize(){
        if(settingsHomeFListView.getChildCount() == 0){
            List<String> strings_a = new ArrayList<>();
            strings_a.add(textB(76));

            List<String> strings_b = new ArrayList<>();
            strings_b.add(textB(77));

            List<Integer> icons = new ArrayList<>();
            icons.add(icon(58));

            settingsHomeFListView.removeAllViews();
            homeStatusInitialize(strings_a, strings_b, icons, settingsHomeFListView);
        }
    }

    View statusTypesView;
    ImageView statusTypesIcon;
    TextView statusTypesText1, statusTypesText2;
    @SuppressLint("ClickableViewAccessibility")
    private void homeStatusInitialize(List<String> string_a, List<String> string_b, List<Integer> icons, LinearLayout layout){
        for (int i = 0; i < string_a.size(); i++) {
            statusTypesView = inflater.inflate(R.layout.list_style_d, layout, false);
            statusTypesIcon = statusTypesView.findViewById(R.id.list_style_d_icon);
            statusTypesText1 = statusTypesView.findViewById(R.id.list_style_d_text_1);
            statusTypesText2 = statusTypesView.findViewById(R.id.list_style_d_text_2);

            backgroundTypeC(this, statusTypesView, background(3), tintA);
            imageTypeA(this, statusTypesIcon, icons.get(i), tintB, 60);
            textType(this, statusTypesText1, string_a.get(i), tintB, fontBStyle);
            textType(this, statusTypesText2, string_b.get(i), tintB, fontBStyle);

            customTouchModeA(statusTypesView, string_a.get(i), -1, 8);
            layout.addView(statusTypesView);
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------- DRAWER ------------------- []
    //``````````

    View settingsHomeGView;
    RelativeLayout settingsHomeGLayout, settingsHomeGFrame1, settingsHomeGFrame2, settingsHomeGFrame3, settingsHomeGFrame4,
            settingsHomeGFrame5, settingsHomeGFrame6;
    TextView settingsHomeGText1, settingsHomeGText2, settingsHomeGText3;
    ImageView settingsHomeGIcon1, settingsHomeGIcon2, settingsHomeGIcon3, settingsHomeGIcon4, settingsHomeGIcon5;
    private void settingsHomeG(){
        if(settingsHomeGView == null){
            settingsHomeGView = inflater.inflate(R.layout.settings_home_g, null);
            settingsHomeGLayout = settingsHomeGView.findViewById(R.id.settings_home_g);
            settingsHomeGIcon1 = settingsHomeGView.findViewById(R.id.settings_home_g_icon_1);
            settingsHomeGIcon2 = settingsHomeGView.findViewById(R.id.settings_home_g_icon_2);
            settingsHomeGIcon3 = settingsHomeGView.findViewById(R.id.settings_home_g_icon_3);
            settingsHomeGIcon4 = settingsHomeGView.findViewById(R.id.settings_home_g_icon_4);
            settingsHomeGIcon5 = settingsHomeGView.findViewById(R.id.settings_home_g_icon_5);
            settingsHomeGFrame1 = settingsHomeGView.findViewById(R.id.settings_home_g_frame_1);
            settingsHomeGFrame2 = settingsHomeGView.findViewById(R.id.settings_home_g_frame_2);
            settingsHomeGFrame3 = settingsHomeGView.findViewById(R.id.settings_home_g_frame_3);
            settingsHomeGFrame4 = settingsHomeGView.findViewById(R.id.settings_home_g_frame_4);
            settingsHomeGFrame5 = settingsHomeGView.findViewById(R.id.settings_home_g_frame_5);
            settingsHomeGFrame6 = settingsHomeGView.findViewById(R.id.settings_home_g_frame_6);
            settingsHomeGText1 = settingsHomeGView.findViewById(R.id.settings_home_g_text_1);
            settingsHomeGText2 = settingsHomeGView.findViewById(R.id.settings_home_g_text_2);
            settingsHomeGText3 = settingsHomeGView.findViewById(R.id.settings_home_g_text_3);

            backgroundTypeA(this, settingsHomeGFrame2, background(8), tintA, 3);
            backgroundTypeA(this, settingsHomeGFrame3, background(8), tintA, 3);
            backgroundTypeA(this, settingsHomeGFrame4, background(8), tintA, 3);
            backgroundTypeC(this, settingsHomeGFrame6, background(5), tintA);

            imageTypeA(this, settingsHomeGIcon2, icon(49), tintA, 40);
            imageTypeA(this, settingsHomeGIcon3, icon(60), tintA, 40);
            imageTypeA(this, settingsHomeGIcon4, icon(42), tintB, 50);
            imageTypeA(this, settingsHomeGIcon5, icon(33), tintA, 75);

            textType(this, settingsHomeGText1, textC(5), tintA, fontAStyle);
            textType(this, settingsHomeGText2, textB(78), tintA, fontBStyle);
            textType(this, settingsHomeGText3, "", tintA, fontBStyle);

            customTouchModeB(settingsHomeGIcon5, textC(48), 2, 4, 28);
            customTouchModeB(settingsHomeGFrame2, textC(74), 2, 4, 29);
            customTouchModeB(settingsHomeGFrame3, textC(73), 2, 4, 30);
            customTouchModeB(settingsHomeGFrame4, textC(72), 2, 4, 31);
            customTouchModeB(settingsHomeGFrame6, textC(22), 2, 4, 32);
        }
        if(settingsHomeLayout.findViewById(R.id.settings_home_g) == null){
            settingsHomeLayout.addView(settingsHomeGView);
            setSize(settingsHomeGLayout, size(2), size(2));
            setMargins(this, settingsHomeGLayout, 20, 20, 20, 20);
            settingsHomeDrawer();
        }
    }

    // -----[ HOME DRAWER BUTTONS ]----- //

    private void settingsHomePress_28(){
        settingsHomeCommonA(settingsHomeGView);
    }

    private void settingsHomePress_29(){
        String icon = "";
        if(drawerIcon.equals("BeeHive"))
            icon = "SimpliC";

        if(drawerIcon.equals("SimpliC"))
            icon = "BeeHive";

        edit(this, "Configurations - 02", "Drawer Icon - " + drawerIcon, "Drawer Icon - " + icon);
        homeDrawerCommonA();
    }

    private void settingsHomePress_30(){
        String size = "";
        if(drawerSize.equals("A"))
            size = "B";

        if(drawerSize.equals("B"))
            size = "C";

        if(drawerSize.equals("C"))
            size = "D";

        if(drawerSize.equals("D"))
            size = "E";

        if(drawerSize.equals("E"))
            size = "A";

        edit(this, "Configurations - 02", "Drawer Size - " + drawerSize, "Drawer Size - " + size);
        homeDrawerCommonA();
    }

    private void settingsHomePress_31(){
        float alpha = 0.0f;
        if(drawerAlpha == 1.0f)
            alpha = 0.0f;

        if(drawerAlpha == 0.0f)
            alpha = 0.5f;

        if(drawerAlpha == 0.5f)
            alpha = 1.0f;

        edit(this, "Configurations - 02", "Drawer Alpha - " + drawerAlpha, "Drawer Alpha - " + alpha);
        homeDrawerCommonA();
    }

    private void settingsHomePress_32(){
        edit(this, "Configurations - 02", "Drawer Icon - " + drawerIcon, "Drawer Icon - BeeHive");
        edit(this, "Configurations - 02", "Drawer Size - " + drawerSize, "Drawer Size - C");
        edit(this, "Configurations - 02", "Drawer Alpha - " + drawerAlpha, "Drawer Alpha - 1.0");
        homeDrawerCommonA();
    }

    // -----[ HOME DRAWER COMMON ]----- //

    private void homeDrawerCommonA(){
        settingsHomeDrawer();
        homeDrawerPresets(homeDrawerAIcon);
    }

    // -----[ HOME DRAWER METHODS ]----- //

    private void settingsHomeDrawer(){
        configurationsB();
        homeDrawerPresets(settingsHomeGIcon1);
        settingsHomeGText3.setText(drawerIcon);
        if(!drawerIcon.equals("BeeHive") || !drawerSize.equals("C") || drawerAlpha != 1.0){
            settingsHomeGFrame6.setVisibility(View.VISIBLE);
        } else {
            settingsHomeGFrame6.setVisibility(View.GONE);
        }
    }

    //..........
    //-------------------- HOME -------------------- []
    //------------------ SHORTCUT ------------------ []
    //``````````

    View settingsHomeHView;
    RelativeLayout settingsHomeHLayout, settingsHomeHFrame1, settingsHomeHFrame2, settingsHomeHFrame3, settingsHomeHFrame4,
            settingsHomeHFrame5, settingsHomeHFrame6, settingsHomeHFrame7;
    TextView settingsHomeHText1, settingsHomeHText2, settingsHomeHText3, settingsHomeHText4, settingsHomeHText5;
    ImageView settingsHomeHCircle, settingsHomeHIcon1, settingsHomeHIcon2, settingsHomeHIcon3, settingsHomeHIcon4;
    LinearLayout settingsHomeHListView;
    ScrollView settingsHomeHScrollView;
    private void settingsHomeH(){
        if(settingsHomeHView == null){
            settingsHomeHView = inflater.inflate(R.layout.settings_home_h, null);
            settingsHomeHLayout = settingsHomeHView.findViewById(R.id.settings_home_h);
            settingsHomeHScrollView = settingsHomeHView.findViewById(R.id.settings_home_h_scroll_view);
            settingsHomeHCircle = settingsHomeHView.findViewById(R.id.settings_home_h_circle);
            settingsHomeHIcon1 = settingsHomeHView.findViewById(R.id.settings_home_h_icon_1);
            settingsHomeHIcon2 = settingsHomeHView.findViewById(R.id.settings_home_h_icon_2);
            settingsHomeHIcon3 = settingsHomeHView.findViewById(R.id.settings_home_h_icon_3);
            settingsHomeHIcon4 = settingsHomeHView.findViewById(R.id.settings_home_h_icon_4);
            settingsHomeHFrame1 = settingsHomeHView.findViewById(R.id.settings_home_h_frame_1);
            settingsHomeHFrame2 = settingsHomeHView.findViewById(R.id.settings_home_h_frame_2);
            settingsHomeHFrame3 = settingsHomeHView.findViewById(R.id.settings_home_h_frame_3);
            settingsHomeHFrame4 = settingsHomeHView.findViewById(R.id.settings_home_h_frame_4);
            settingsHomeHFrame5 = settingsHomeHView.findViewById(R.id.settings_home_h_frame_5);
            settingsHomeHFrame6 = settingsHomeHView.findViewById(R.id.settings_home_h_frame_6);
            settingsHomeHFrame7 = settingsHomeHView.findViewById(R.id.settings_home_h_frame_7);
            settingsHomeHText1 = settingsHomeHView.findViewById(R.id.settings_home_h_text_1);
            settingsHomeHText2 = settingsHomeHView.findViewById(R.id.settings_home_h_text_2);
            settingsHomeHText3 = settingsHomeHView.findViewById(R.id.settings_home_h_text_3);
            settingsHomeHText4 = settingsHomeHView.findViewById(R.id.settings_home_h_text_4);
            settingsHomeHText5 = settingsHomeHView.findViewById(R.id.settings_home_h_text_5);
            settingsHomeHListView = settingsHomeHView.findViewById(R.id.settings_home_h_list_view);

            backgroundTypeA(this, settingsHomeHFrame2, background(7), ui, 3);
            backgroundTypeA(this, settingsHomeHFrame4, background(5), tintA, 3);
            backgroundTypeC(this, settingsHomeHFrame5, background(10), tintA);
            backgroundTypeA(this, settingsHomeHFrame7, background(8), tintA, 3);

            imageTypeB(this, settingsHomeHCircle, background(4), ui);
            imageTypeA(this, settingsHomeHIcon1, icon(33), tintA, 75);
            imageTypeA(this, settingsHomeHIcon2, icon(57), tintA, 50);
            imageTypeA(this, settingsHomeHIcon3, icon(33), tintA, 65);
            imageTypeA(this, settingsHomeHIcon4, icon(61), tintA, 50);

            textType(this, settingsHomeHText1, textC(4), tintA, fontAStyle);
            textType(this, settingsHomeHText2, "", tintA, fontBStyle);
            textType(this, settingsHomeHText3, textC(8), tintA, fontBStyle);
            textType(this, settingsHomeHText4, textB(81), tintA, fontBStyle);
            textType(this, settingsHomeHText5, textC(79), tintA, fontAStyle);

            customTouchModeB(settingsHomeHIcon1, textC(48), 2, 4, 33);
            customTouchModeB(settingsHomeHIcon3, textC(48), 2, 4, 36);
            customTouchModeB(settingsHomeHFrame2, "", 2, 4, 34);
            customTouchModeB(settingsHomeHFrame4, textC(68), 2, 4, 35);

            settingsHomeHFrame5.setVisibility(View.GONE);
        }
        if(settingsHomeLayout.findViewById(R.id.settings_home_h) == null){
            settingsHomeLayout.addView(settingsHomeHView);
            setSize(settingsHomeHLayout, size(2), size(2));
            setMargins(this, settingsHomeHLayout, 20, 20, 20, 20);
            homeShortcutState();
        }
    }

    // -----[ HOME SHORTCUT BUTTONS ]----- //

    private void settingsHomePress_33(){
        settingsHomeCommonA(settingsHomeHView);
    }

    private void settingsHomePress_34(){
        if(shortcutState.equals("Enabled")) {
            edit(this, "Configurations - 02", "Shortcut State - " + shortcutState, "Shortcut State - Disabled");
        } else {
            edit(this, "Configurations - 02", "Shortcut State - " + shortcutState, "Shortcut State - Enabled");
        }
        homeShortcutState();
        homeShortcut();
    }

    private void settingsHomePress_35(){
        settingsHomeHFrame1.setVisibility(View.GONE);
        settingsHomeHFrame5.setVisibility(View.VISIBLE);
        homeShortcutInitialize();
    }

    private void settingsHomePress_36(){
        homeShortcutCommonA(View.VISIBLE);
    }

    private void settingsHomePress_41(String shortcutApp){
        if(isAppInstalled(this, shortcutApp)){
            create(this, "Array - 02", shortcutApp);
            touchTip(this, textC(20), 2);
        } else {
            touchTip(this, textC(67), 2);
        }
        homeShortcutArray.remove(shortcutApp);
        for(int i = 0; i < settingsHomeHListView.getChildCount(); ++i){
            TextView textView = settingsHomeHListView.getChildAt(i).findViewById(R.id.linear_type_g_text);
            if(textView.getText().equals(appLabel(this, shortcutApp)))
                settingsHomeHListView.removeView(settingsHomeHListView.getChildAt(i));
        }

        if(homeShortcutBLayout == null || homeShortcutLayout.findViewById(R.id.home_shortcut_b) == null) {
            homeShortcutLayout.removeAllViews();
            homeShortcutB();
        }
    }

    // -----[ HOME SHORTCUT COMMON ]----- //

    private void homeShortcutCommonA(int sight){
        settingsHomeHFrame1.setVisibility(View.VISIBLE);
        settingsHomeHFrame4.setVisibility(sight);
        settingsHomeHFrame5.setVisibility(View.GONE);
    }

    private void homeShortcutCommonB(boolean state, int text){
        toggleMode(this, settingsHomeHCircle, state);
        settingsHomeHText2.setText(textB(text));
    }

    // -----[ HOME SHORTCUT METHODS ]----- //

    private void homeShortcutState(){
        configurationsB();
        if(shortcutState.equals("Enabled")){
            homeShortcutCommonB(true, 80);

            if(settingsHomeHFrame5.getVisibility() != View.VISIBLE){
                homeShortcutCommonA(View.VISIBLE);
            } else {
                homeShortcutInitialize();
            }
        } else {
            homeShortcutCommonB(false, 79);
            homeShortcutCommonA(View.GONE);
        }
    }

    private int settingsHomeHInt = 0;
    List<String> homeShortcutArray;
    private void homeShortcutInitialize(){
        Handler settingsHomeHHandler = new Handler();
        Runnable settingsHomeHRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> apps = getPackageManager().queryIntentActivities(intent, PackageManager.GET_META_DATA);

                if(settingsHomeHInt != apps.size()){
                    settingsHomeHInt = apps.size();
                    homeShortcutArray = new ArrayList<>();

                    for (ResolveInfo resolveInfo : apps) {
                        if(!homeShortcutArray.contains(resolveInfo.activityInfo.packageName))
                            homeShortcutArray.add(resolveInfo.activityInfo.packageName);
                    }

                    if(fileExist(TheNest.this, "Array - 02")) {
                        List<String> readValues = read(TheNest.this, "Array - 02");
                        for(int i = 0; i < homeShortcutArray.size(); ++i){
                            if(readValues.contains(homeShortcutArray.get(i)))
                                homeShortcutArray.remove(homeShortcutArray.get(i));
                        }
                    }
                    settingsHomeHListView.removeAllViews();
                    homeShortcutList(homeShortcutArray, settingsHomeHListView);
                }
            }
        };
        settingsHomeHHandler.postDelayed(settingsHomeHRunnable, 50);
    }

    View shortcutContentsView;
    RelativeLayout shortcutContentsFrame;
    ImageView shortcutContentsIcon;
    TextView shortcutContentsText;
    @SuppressLint("ClickableViewAccessibility")
    private void homeShortcutList(List<String> list, LinearLayout layout){
        for (int i = 0; i < list.size(); i++) {
            shortcutContentsView = inflater.inflate(R.layout.linear_type_g, layout, false);
            shortcutContentsFrame = shortcutContentsView.findViewById(R.id.linear_type_g_frame);
            shortcutContentsIcon = shortcutContentsView.findViewById(R.id.linear_type_g_icon);
            shortcutContentsText = shortcutContentsView.findViewById(R.id.linear_type_g_text);

            shortcutContentsIcon.setImageBitmap(appIcon(this, list.get(i), 40));
            textType(this, shortcutContentsText, appLabel(this, list.get(i)), tintA, fontBStyle);

            customTouchModeA(shortcutContentsView, list.get(i), -1, 9);
            layout.addView(shortcutContentsView);

            if(i < list.size())
                settingsHomeHFrame6.setVisibility(View.GONE);
        }
    }
}