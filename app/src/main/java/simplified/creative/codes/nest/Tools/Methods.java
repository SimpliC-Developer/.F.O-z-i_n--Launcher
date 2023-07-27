package simplified.creative.codes.nest.Tools;

import android.annotation.SuppressLint;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.w3c.dom.Text;
import simplified.creative.codes.nest.R;
import simplified.creative.codes.nest.TheNest;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static simplified.creative.codes.nest.TheNest.*;

public class Methods {

    public static String textA(int value) {
        if(value == 0)
            return "[ _ />";
        if(value == 1)
            return "∆×••⟨[]⟩";
        if(value == 2)
            return "W";
        if(value == 3)
            return "H";
        if(value == 4)
            return "#";
        if(value == 5)
            return "#-.";
        if(value == 6)
            return "(^o^)/";
        if(value == 7)
            return "(> ^)/ Empty";
        if(value == 8)
            return "•";
        if(value == 9)
            return "--------------------------------------------------";
        if(value == 10)
            return "</[)>-";
        if(value == 11)
            return "(..\\}";
        if(value == 12)
            return "<_.---/\\>";
        if(value == 13)
            return "----------";
        if(value == 14)
            return "(#, A..)";
        if(value == 15)
            return "••";
        if(value == 16)
            return "...";
        if(value == 17)
            return "⟩>";
        if(value == 18)
            return "<[]";
        return "";
    }

    public static String textB(int value) {
        if(value == 0)
            return "Its Night Time :]";
        if(value == 1)
            return "Its Morning ;)";
        if(value == 2)
            return "Its Afternoon :)";
        if(value == 3)
            return "It's Evening :}";
        if(value == 4)
            return "If you want you can show / hide widgets panel from home screen.";
        if(value == 5)
            return "Yep! you can also show / hide the wallpaper panel too.";
        if(value == 6)
            return "Alrighty!, now it's folder's turn, do you want to show it or hide it.";
        if(value == 7)
            return "Hmm... status can also be shown / hidden it's your choice.";
        if(value == 8)
            return "Now it's the last one, shortcuts. Wanna show / hide on home screen.";
        if(value == 9)
            return "You can edit widget panel from here.";
        if(value == 10)
            return "Some tweaks related to wallpaper panel.";
        if(value == 11)
            return "Hi There!! (^^). It seems like the current Device Wallpaper is a live " +
                    "wallpaper which is at this moment not supported by the app. If you want then you can " +
                    "change the wallpaper from the settings...";
        if(value == 12)
            return "Charging";
        if(value == 13)
            return "Hi there (^ ^), it's me just your everyday indie dev!!. S-So you wanna know about me hmm... ;)" +
                    " well i don't know what to say. Oh! i know i will tell you a bit about this app and my goals. As" +
                    " you can see this is a Custom Home Screen Launcher APP Yay!!, ( clapping* sounds) cough* and it's" +
                    " also my first app. Yup! so, please go easy on me. :) Though sometimes it gets a bit annoying i" +
                    " still plan to keep working on this small project of mine for a bit, maybe around a year or so." +
                    " Actually this is not the only project i plan to work on, the truth of the matter is i want to" +
                    " learn more so right now you can say i am just experimenting around a bit. I want to become an" +
                    " indie developer after all With my own skills ;) and that's where my Fozin comes into play." +
                    " Anyways enough talking about me. I will appreciate it a lot if you can tell me any suggestions" +
                    " you have or if you experienced any problems while using this app. \\(^ ^) That's all for now." +
                    " Oh!! right, also Thanks for using my Fozin. So See you around until next time...";
        if(value == 14)
            return "App Name - Fozin";
        if(value == 15)
            return "App Version - " + textB(101);
        if(value == 16)
            return "App Codename - The Nest";
        if(value == 17)
            return "App Package - simplified.creative.codes.nest";
        if(value == 18)
            return "Min Android Version - 5.1 Lollipop";
        if(value == 19)
            return "Max Android Version - 11";
        if(value == 20)
            return "Developers - SimpliC";
        if(value == 21)
            return "Maintainer - ∆×••⟨[]⟩";
        if(value == 22)
            return "So you want to know what are the things this app can do. Oh! well, that's a SECRET ;) Yup! not gonna tell.";
        if(value == 23)
            return "• Storage permission is needed for creating apk backups & to view device wallpapers. So if" +
                    " you don't want to you can just disable the storage permissions. However in return you won't be" +
                    " able to make use of apk backup feature or view current device wallpaper. (Q Q)";
        if(value == 24)
            return "Change default Home Launcher.";
        if(value == 25)
            return "Well you know what restart does right?.";
        if(value == 26)
            return "Wanna start over!, it's fine.";
        if(value == 27)
            return "Some settings related to system ui bars.";
        if(value == 28)
            return "You can customize the settings tab from here.";
        if(value == 29)
            return "Alright! time to theme the app";
        if(value == 30)
            return "Font's tweaks can be found over here if you are interested.";
        if(value == 31)
            return "It won't bring end of the world but it will just reverse all the settings to default and restart the app.";
        if(value == 32)
            return "Careful now, if you accept it will remove all saved files, settings, cache data, etc and you will go back to app welcome screen.";
        if(value == 33)
            return "Decide the behaviour of status bar in the app.";
        if(value == 34)
            return "What do you wanna do about navigation bar. Show or Hide.";
        if(value == 35)
            return "Well then, what do you want to see first after opening the settings screen. NOTE - Auto means the last opened tab will be shown. ";
        if(value == 36)
            return "Guess what! you can also change the list styles of settings tabs. Surprised right?.";
        if(value == 37)
            return "Take your time to chose the color style of your liking however, please note that the " +
                    "app will [ Restart ] itself for the changes to take effect.";
        if(value == 38)
            return "Though you can customize how the title text looks but note that it needs app [ Restart ]";
        if(value == 39)
            return "Paragraph Texts or should i say small texts can also be customized. But Again the app will [ Restart] itself.";
        if(value == 40)
            return "Style - ";
        if(value == 41)
            return "Mode - ";
        if(value == 42)
            return "To simply put, the apps which were hidden from the drawers screen.";
        if(value == 43)
            return "You know what?, you can change how the apps are shown in the drawer screen.";
        if(value == 44)
            return "I guess you already know what's sorting but just in case, it basically means the order in which the apps are shown. NOTE - only for drawer screen.";
        if(value == 45)
            return "Ok so here's the thing, you can only select one type of quick app search mode for the drawer screen. Although completely disabling it is also an option.";
        if(value == 46)
            return "Below are the currently hidden apps, to un hide an app tap on it and select ok. It's simple right?.";
        if(value == 47)
            return "Hmm... it seems like no apps are currently hidden.";
        if(value == 48)
            return "Un-hide all apps.";
        if(value == 49)
            return "Home screen widgets can be customised from here. Wanna see how?.";
        if(value == 50)
            return "Some settings related to home screen applet tab. Nothing big really.";
        if(value == 51)
            return "So u-um okay, wallpapers customizations are over here.";
        if(value == 52)
            return "Again just some minor tweaks, but it is related to home screen folder tab.";
        if(value == 53)
            return "I bet you didn't expected something like this huh?";
        if(value == 54)
            return "Surprised!! drawer button can be customized too.";
        if(value == 55)
            return "Whew! last one i guess, shortcuts can be edited from this tab.";
        if(value == 56)
            return "It seems like the widgets are currently disabled so if you want to see the hidden settings then you will have to enable it first.";
        if(value == 57)
            return "There are two types of widgets available for you to chose from 1 - simplic's inbuilt widgets and 2 - installed widgets on the device.";
        if(value == 58)
            return "SimpliC's - Widgets";
        if(value == 59)
            return "Device's - Widgets";
        if(value == 60)
            return "NOTE - Selecting a new widget will replace any existing widget on the home screen.";
        if(value == 61)
            return "Okay! so here's the thing, it seems that the currently added widget was very big for the home screen, so that's why it was automatically" +
                    " removed from the widget panel.";
        if(value == 62)
            return "Well like i said before, currently there isn't much customization for the applet tab. The most you can do right now is change the message" +
                    " shown in the applet. Oh! and, you can also leave it empty.";
        if(value == 63)
            return "Yep! the wallpaper is 99% disabled alright! And so if you want to see some lightly-suspicious settings then you will have to enable it first.";
        if(value == 64)
            return "Again there are two modes of wallpaper available for you to chose from, 1 - simplic's inbuilt wallpapers and 2 - device's wallpaper.";
        if(value == 65)
            return "SimpliC's - Wallpapers";
        if(value == 66)
            return "Device's - Wallpapers";
        if(value == 67)
            return "NOTE - Selecting this option will show the current device wallpaper on the wallpaper panel.";
        if(value == 68)
            return "Your current options, take your pick... NOTE - To enable / disable a wallpaper just tap on it.";
        if(value == 69)
            return "The device wallpaper mode has already been enabled so there is no need to enable it again. NOTE - You can disable it " +
                    "from the wallpaper panel on home screen.";
        if(value == 70)
            return "As you can see folder is disabled ( Hidden ) right now, so adding and removing apps is not possible unless you enable it back on.";
        if(value == 71)
            return "Not much customization are there for the folder at this moment. So U-UM YEAH! but still you can at least change the sorting order of the list contents.";
        if(value == 72)
            return "These are the currently installed apps on your device. To add them to the folder's list just click on it. NOTE - If an app has already been added" +
                    " then it won't appear here.";
        if(value == 73)
            return "Well as you know the usual drill, you will have to enable the status first to see the hidden options.";
        if(value == 74)
            return "You can use status as a replacement option for system status bar or you can just enable it as a separate feature" +
                    " it's your choice. Now to enable a status type just select one from the list button below.";
        if(value == 75)
            return "As you can see, these are the available statuses for you to chose from. NOTE - You can only enable one status" +
                    " type at a time. If you select and enable some other status, then it will automatically replace it with the" +
                    " previous one.";
        if(value == 76)
            return "Battery Status";
        if(value == 77)
            return "Displays current device battery details like - usage, state etc";
        if(value == 78)
            return "Right now the things which you can customize about the drawer icon are alpha, size and of course the icon itself.";
        if(value == 79)
            return "Hmmm... nope i won't say it hehe, well i think at this point you should already know what i want to say right?.";
        if(value == 80)
            return "Now that the shortcuts are enabled you can proceed with adding the apps to it from below. And again there isn't much to customize here." +
                    " cough* ( looking away* ).";
        if(value == 81)
            return "Yup| as you can see these are the installed apps which you can add to the shortcuts. Though in case if an app has already been" +
                    " added then it won't appear here.";
        if(value == 82)
            return "Ahem!, well i know it sounds weird but to see the device wallpaper you have to grant STORAGE permission first. And" +
                    " the reason you are seeing this message is cause the app doesn't have that permission yet. So WHY? do you need that" +
                    " permission? Oh! well i don't know either. Sigh* complicated stuff.";
        if(value == 83)
            return "U-um Hi! ... huh? no response? well that's no fun. pfft!. Alright i won't beat around the bush. So the thing is, as the" +
                    " name suggests backup, it means Fozin will take the backup of the selected app in the .apk format. And to do that Fozin needs" +
                    " the read and write STORAGE permission. If you won't grant Fozin that permission than you can't use this feature.";
        if(value == 84)
            return "simplicdeveloper2062@gmail.com";
        if(value == 85)
            return "(^o^)/ Over Here...";
        if(value == 86)
            return "Is something wrong (*.*)...";
        if(value == 87)
            return "https://github.com/SimpliC-Developer";
        if(value == 88)
            return "Welcome";
        if(value == 89)
            return "To";
        if(value == 90)
            return "Launcher";
        if(value == 91)
            return "Next.....";
        if(value == 92)
            return "Simplified Creative Codes";
        if(value == 93)
            return "Version ------------------- " + textB(101);
        if(value == 94)
            return "Hi there! (^o^)/ it's me the developer of this app. Now i know this isn't the right place to say this but first" +
                    " of all i am really happy that you decided to give this app a shot. And i am thankful for that. I know that you" +
                    " are in rush to try out the app so i will keep this intro short...\n"+
                    "\n1. Here's the thing you don't need to grant the app some kind of special permission to use it, however there are" +
                    " some feature which use specific permissions so to use those features the app will remind you to grant it at" +
                    " that moment.\n" +
                    "\n2. There isn't any special terms and conditions for the app at this moment however if i feel like adding it than" +
                    " you might find them in the future updates.\n" +
                    "\n3. Custom apps like these are a bit annoying to make which follows the different ui than other apps so there might" +
                    " be some bugs which i may not be aware of in that case if you do encounter some app breaking bug than i would" +
                    " appreciate it if you could let me know by github or email.\n" +
                    "\n4. This app is totally free so you don't have to worry about ads or in - app purchases.\n" +
                    "\nAnd that's all for now thanks for bearing with me and reading through it. I hope you will love the app. Now then go" +
                    " ahead!";
        if(value == 95)
            return "Now you can press this button to end the setup and launch the home screen.";
        if(value == 96)
            return "Drawer Button";
        if(value == 97)
            return "Settings Button";
        if(value == 98)
            return "Okay so u-um please don't panic and listen to me carefully, it seems like there are somethings which doesn't add" +
                    " up here. What i mean is that somethings or in technical terms some configurations which the app was expecting" +
                    " were different on your device so that's the reason you cannot proceed any further. I am really sorry for this" +
                    " but i have to confirm few things first...\n" +
                    "\n1. Is your android version below lollipop or above android 11? Cause at this moment only the android versions" +
                    " from lollipop to android 11 are supported.\n" +
                    "\n2. Did you download my Fozin from the internet ( some other website )? Cause in that case i would recommend you to download it only from" +
                    " my github repo.\n" +
                    "\nif you are sure that there weren't any problem from your side eg- before the app was working but suddenly now" +
                    " you are seeing this message than in that case please send me an email or tell me on the github and i will look into" +
                    " it. How? and What? you should tell? well here is an example...\n" +
                    "\n1. Device Name - \n" +
                    "\n2. Android Version - \n" +
                    "\n3. App Version - \n" +
                    "\n4. Message - (if you want to say something)\n" +
                    "\nAnd that's all i hope you will follow this format it will make things a lot easier for me. Once again i am truly" +
                    " sorry for this, so for now if you want you can uninstall the app from the above button. It's fine no problem at all. :)\n" +
                    "\n Oh! right i forgot something again. What? you ask? pfft! well of course my gmail and github link. Here...\n" +
                    "\nGmail - " + textB(84) + "\n" +
                    "\nGithub - " + textB(87);
        if(value == 99)
            return "simplified.creative.codes.nest";
        if(value == 100)
            return "14CCD7F213309C3923F76476847B04C8BEA3B7B7";
        if(value == 101)
            return "0.1.F.S";
        return "";
    }

    public static String textC(int value){
        if(value == 0)
            if(fontAMode.equals("SimpliC")){
                return "W-i/D_g.e.-TS";
            } else {
                return "Widgets";
            }
        if(value == 1)
            if(fontAMode.equals("SimpliC")){
                return "w.a-ll_P/-..Aper";
            } else {
                return "Wallpaper";
            }
        if(value == 2)
            if(fontAMode.equals("SimpliC")){
                return "F-o-.l_D/E.r";
            } else {
                return "Folder";
            }
        if(value == 3)
            if(fontAMode.equals("SimpliC")){
                return "s.ta-T/_u.s";
            } else {
                return "Status";
            }
        if(value == 4)
            if(fontAMode.equals("SimpliC")){
                return "s-h_o.r/t-cuts";
            } else {
                return "Shortcuts...";
            }
        if(value == 5)
            if(fontAMode.equals("SimpliC")){
                return "D-r_a..w_e-r";
            } else {
                return "Drawer";
            }
        if(value == 6)
            if(fontAMode.equals("SimpliC")){
                return "S/e_t..t--ings";
            } else {
                return "Settings";
            }
        if(value == 7)
            if(fontAMode.equals("SimpliC")){
                return "-_A.P/p-l.e.t";
            } else {
                return "Applet";
            }
        if(value == 8)
            if(fontAMode.equals("SimpliC")){
                return "_.APPS/-";
            } else {
                return "Apps";
            }
        if (value == 9)
            if(fontAMode.equals("SimpliC")){
                return ".o_p-t/i/o-n_s.";
            } else {
                return "Options";
            }
        if(value == 10)
            if(fontAMode.equals("SimpliC")){
                return "re-M._._.O-ve";
            } else {
                return "Remove";
            }
        if(value == 11)
            if(fontAMode.equals("SimpliC")){
                return "i_N-f..O";
            } else {
                return "Info";
            }
        if(value == 12)
            if(fontAMode.equals("SimpliC")){
                return "un.-/INSTalL";
            } else {
                return "Uninstall";
            }
        if(value == 13)
            if(fontAMode.equals("SimpliC")){
                return "sa_/-ve";
            } else {
                return "Save";
            }
        if(value == 14)
            if(fontAMode.equals("SimpliC")){
                return "r__E--n.a/Me";
            } else {
                return "Rename";
            }
        if(value == 15)
            if(fontAMode.equals("SimpliC")){
                return "MIN..i-m-i/s_e";
            } else {
                return "Minimise";
            }
        if(value == 16)
            if(fontAMode.equals("SimpliC")){
                return "._/RE-Size";
            } else {
                return "Resize";
            }
        if(value == 17)
            if(fontAMode.equals("SimpliC")){
                return "H-i-D_.e";
            } else {
                return "Hide";
            }
        if(value == 18)
            if(fontAMode.equals("SimpliC")){
                return "s.ta-t/u.s  [Battery]";
            } else {
                return "Status - Battery";
            }
        if(value == 19)
            if(fontAMode.equals("SimpliC")){
                return "r..E-F_r/eS_h";
            } else {
                return "Refresh";
            }
        if(value == 20)
            if(fontAMode.equals("SimpliC")){
                return "d-o_n-e (> •)/";
            } else {
                return "Done (> •)/";
            }
        if(value == 21)
            if(fontAMode.equals("SimpliC")){
                return "_/CLOS.-E_";
            } else {
                return "Close";
            }
        if(value == 22)
            if(fontAMode.equals("SimpliC")){
                return "RE.s/-ET";
            } else {
                return "Reset";
            }
        if(value == 23)
            if(fontAMode.equals("SimpliC")){
                return "BACK--/_u..p";
            } else {
                return "Backup";
            }
        if(value == 24)
            if(fontAMode.equals("SimpliC")){
                return "_" + "\\" + "SIMPLI••⟨ C'' ⟩";
            } else {
                return "SimpliC";
            }
        if(value == 25)
            if(fontAMode.equals("SimpliC")){
                return "simplifiED --</> creatiVE..CodES";
            } else {
                return "Simplified Creative Codes";
            }
        if(value == 26)
            if(fontAMode.equals("SimpliC")){
                return ".F.O/z-i_n\\";
            } else {
                return "Fozin";
            }
        if(value == 27)
            if(fontAMode.equals("SimpliC")){
                return "no [ DEEP ] ..m/eaning -it- just\\ _sounds_ <CooL>";
            } else {
                return "No deep meaning it just sounds cool ;)";
            }
        if(value == 28)
            if(fontAMode.equals("SimpliC")){
                return "[ EMAIL ]  c-o_p.IED";
            } else {
                return "Email Copied";
            }
        if(value == 29)
            if(fontAMode.equals("SimpliC")){
                return "L../ink  [ Copied ]";
            } else {
                return "Link Copied";
            }
        if(value == 30)
            if(fontAMode.equals("SimpliC")){
                return "if\\ you want( ) you _can/ reach[ ] me BY";
            } else {
                return "If you want you can reach me by...";
            }
        if(value == 31)
            if(fontAMode.equals("SimpliC")){
                return "@/ G.mail _com_";
            } else {
                return "@ gmail.com";
            }
        if(value == 32)
            if(fontAMode.equals("SimpliC")){
                return "(@ (git\\HUB-com))";
            } else {
                return "@ github.com";
            }
        if(value == 33)
            if(fontAMode.equals("SimpliC")){
                return "_somethings .about/ the \\app";
            } else {
                return "Somethings about the app...";
            }
        if(value == 34)
            if(fontAMode.equals("SimpliC")){
                return "./currenT _Features\\";
            } else {
                return "Current Features...";
            }
        if(value == 35)
            if(fontAMode.equals("SimpliC")){
                return "p.E-R_miss/IONS  N.ee\\ded";
            } else {
                return "Permissions Needed...";
            }
        if(value == 36)
            if(fontAMode.equals("SimpliC")){
                return "/-- storage _READ ..WRITE _permission";
            } else {
                return "<" + "\\" + "> Storage Read/Write Permission";
            }
        if(value == 37)
            if(fontAMode.equals("SimpliC")){
                return "./launcher(app by){";
            } else {
                return "Launcher App By";
            }
        if(value == 38)
            if(fontAMode.equals("SimpliC")){
                return "a_B./Ou-t";
            } else {
                return "About";
            }
        if(value == 39)
            if(fontAMode.equals("SimpliC")){
                return "misc.-_./";
            } else {
                return "Misc";
            }
        if(value == 40)
            if(fontAMode.equals("SimpliC")){
                return "-H.O/M.E-";
            } else {
                return "Home";
            }
        if(value == 41)
            if(fontAMode.equals("SimpliC")){
                return "D/r.a-W.e-r";
            } else {
                return "Drawer";
            }
        if(value == 42)
            if(fontAMode.equals("SimpliC")){
                return "L.aunchE-R";
            } else {
                return "Launcher";
            }
        if(value == 43)
            if(fontAMode.equals("SimpliC")){
                return "Restart(< >)";
            } else {
                return "Restart";
            }
        if(value == 44)
            if(fontAMode.equals("SimpliC")){
                return "B<A.\\R_/S";
            } else {
                return "Bars";
            }
        if(value == 45)
            if(fontAMode.equals("SimpliC")){
                return "./tab\\-S._";
            } else {
                return "Tabs";
            }
        if(value == 46)
            if(fontAMode.equals("SimpliC")){
                return "c..o.L-O/R_s";
            } else {
                return "Colors";
            }
        if(value == 47)
            if(fontAMode.equals("SimpliC")){
                return "f-O./n_t-S";
            } else {
                return "Fonts";
            }
        if(value == 48)
            if(fontAMode.equals("SimpliC")){
                return "./B_a-c\\K";
            } else {
                return "Back";
            }
        if(value == 49)
            if(fontAMode.equals("SimpliC")){
                return "ac-_./cept";
            } else {
                return "Accept";
            }
        if(value == 50)
            if(fontAMode.equals("SimpliC")){
                return ".\\" + "soft_reset/>-";
            } else {
                return "Soft Reset";
            }
        if(value == 51)
            if(fontAMode.equals("SimpliC")){
                return "fullReset[ ];";
            } else {
                return "Full Reset";
            }
        if(value == 52)
            if(fontAMode.equals("SimpliC")){
                return "STA/tus__BAR";
            } else {
                return "Status Bar";
            }
        if(value == 53)
            if(fontAMode.equals("SimpliC")){
                return "nAv--BAR";
            } else {
                return "Nav Bar";
            }
        if(value == 54)
            if(fontAMode.equals("SimpliC")){
                return "def\\-AU._l-t  ./Tab";
            } else {
                return "Default Tab";
            }
        if(value == 55)
            if(fontAMode.equals("SimpliC")){
                return "L-.i/st  STY_.l.e_s-";
            } else {
                return "List Styles";
            }
        if(value == 56)
            if(fontAMode.equals("SimpliC")){
                return "--\\D.E_F.ault";
            } else {
                return "Default";
            }
        if(value == 57)
            if(fontAMode.equals("SimpliC")){
                return "TI\\T..l_e-s";
            } else {
                return "Titles";
            }
        if(value == 58)
            if(fontAMode.equals("SimpliC")){
                return ".p_a-r-a./GRAPH.s";
            } else {
                return "Paragraphs";
            }
        if(value == 59)
            if(fontAMode.equals("SimpliC")){
                return "_S.i/M-p\\L.i-C";
            } else {
                return "Common";
            }
        if(value == 60)
            if(fontAMode.equals("SimpliC")){
                return "..h/i-d-d_e.n  AP.\\.PS";
            } else {
                return "Hidden Apps";
            }
        if(value == 61)
            if(fontAMode.equals("SimpliC")){
                return "D_-R.A/w-e-r  _st/y-LE";
            } else {
                return "Drawer Style";
            }
        if(value == 62)
            if(fontAMode.equals("SimpliC")){
                return "s./O-R.T  By-_";
            } else {
                return "Sort By";
            }
        if(value == 63)
            if(fontAMode.equals("SimpliC")){
                return "Qui\\..C_K  .b-r/o-w.SE";
            } else {
                return "Quick Browse";
            }
        if(value == 64)
            if(fontAMode.equals("SimpliC")){
                return "C_o/n-t.e-NTS";
            } else {
                return "Contents";
            }
        if(value == 65)
            if(fontAMode.equals("SimpliC")){
                return "e./MP-_t-y";
            } else {
                return "Empty";
            }
        if(value == 66)
            if(fontAMode.equals("SimpliC")){
                return "_un ..HI/d-e";
            } else {
                return "Un Hide";
            }
        if(value == 67)
            if(fontAMode.equals("SimpliC")){
                return "!(0 0) E/r--ror";
            } else {
                return "!(0 0) - Error";
            }
        if(value == 68)
            if(fontAMode.equals("SimpliC")){
                return "A..DD/_";
            } else {
                return "Add";
            }
        if(value == 69)
            if(fontAMode.equals("SimpliC")){
                return ".mod-/-es.";
            } else {
                return "Modes";
            }
        if(value == 70)
            if(fontAMode.equals("SimpliC")){
                return "en-_A/B-L.e.";
            } else {
                return "Enable";
            }
        if(value == 71)
            if(fontAMode.equals("SimpliC")){
                return "DIS-/_a.b-l-e";
            } else {
                return "Disable";
            }
        if(value == 72)
            if(fontAMode.equals("SimpliC")){
                return "_.Al-P-HA";
            } else {
                return "Alpha";
            }
        if(value == 73)
            if(fontAMode.equals("SimpliC")){
                return "/.s-iz_e";
            } else {
                return "Size";
            }
        if(value == 74)
            if(fontAMode.equals("SimpliC")){
                return "i-/CO_n.";
            } else {
                return "Icon";
            }
        if(value == 75)
            if(fontAMode.equals("SimpliC")){
                return "per./mis_-sio.NS";
            } else {
                return "Permissions";
            }
        if(value == 76)
            if(fontAMode.equals("SimpliC")){
                return "de.ve_lo-pe/R   ./NOTES";
            } else {
                return "Developer Notes";
            }
        if(value == 77)
            if(fontAMode.equals("SimpliC")){
                return "/A.ll_   D.o-n_E..";
            } else {
                return "All Done...";
            }
        if(value == 78)
            if(fontAMode.equals("SimpliC")){
                return "T-i_p/s...";
            } else {
                return "Tips...";
            }
        if(value == 79)
            if(fontAMode.equals("SimpliC")){
                return "L--oA/d_ing";
            } else {
                return "Loading";
            }
        return "";
    }


    public static int icon(int value) {
        if (value == 1)
            return R.drawable.icon_1;
        if (value == 2)
            return R.drawable.icon_2;
        if (value == 3)
            return R.drawable.icon_3;
        if (value == 4)
            return R.drawable.icon_4;
        if (value == 5)
            return R.drawable.icon_5;
        if (value == 6)
            return R.drawable.icon_6;
        if (value == 7)
            return R.drawable.icon_7;
        if (value == 8)
            return R.drawable.icon_8;
        if (value == 9)
            return R.drawable.icon_9;
        if (value == 10)
            return R.drawable.icon_10;
        if (value == 11)
            return R.drawable.icon_11;
        if (value == 12)
            return R.drawable.icon_12;
        if (value == 13)
            return R.drawable.icon_13;
        if (value == 14)
            return R.drawable.icon_14;
        if (value == 15)
            return R.drawable.icon_15;
        if (value == 16)
            return R.drawable.icon_16;
        if (value == 17)
            return R.drawable.icon_17;
        if (value == 18)
            return R.drawable.icon_18;
        if (value == 19)
            return R.drawable.icon_19;
        if (value == 20)
            return R.drawable.icon_20;
        if (value == 21)
            return R.drawable.icon_21;
        if (value == 22)
            return R.drawable.icon_22;
        if (value == 23)
            return R.drawable.icon_23;
        if (value == 24)
            return R.drawable.icon_24;
        if (value == 25)
            return R.drawable.icon_25;
        if (value == 26)
            return R.drawable.icon_26;
        if (value == 27)
            return R.drawable.icon_27;
        if (value == 28)
            return R.drawable.icon_28;
        if (value == 29)
            return R.drawable.icon_29;
        if (value == 30)
            return R.drawable.icon_30;
        if (value == 31)
            return R.drawable.icon_31;
        if (value == 32)
            return R.drawable.icon_32;
        if (value == 33)
            return R.drawable.icon_33;
        if (value == 34)
            return R.drawable.icon_34;
        if (value == 35)
            return R.drawable.icon_35;
        if (value == 36)
            return R.drawable.icon_36;
        if (value == 37)
            return R.drawable.icon_37;
        if (value == 38)
            return R.drawable.icon_38;
        if (value == 39)
            return R.drawable.icon_39;
        if (value == 40)
            return R.drawable.icon_40;
        if (value == 41)
            return R.drawable.icon_41;
        if (value == 42)
            return R.drawable.icon_42;
        if (value == 43)
            return R.drawable.icon_43;
        if (value == 44)
            return R.drawable.icon_44;
        if (value == 45)
            return R.drawable.icon_45;
        if (value == 46)
            return R.drawable.icon_46;
        if (value == 47)
            return R.drawable.icon_47;
        if (value == 48)
            return R.drawable.icon_48;
        if (value == 49)
            return R.drawable.icon_49;
        if (value == 50)
            return R.drawable.icon_50;
        if (value == 51)
            return R.drawable.icon_51;
        if (value == 52)
            return R.drawable.icon_52;
        if (value == 53)
            return R.drawable.icon_53;
        if (value == 54)
            return R.drawable.icon_54;
        if (value == 55)
            return R.drawable.icon_55;
        if (value == 56)
            return R.drawable.icon_56;
        if (value == 57)
            return R.drawable.icon_57;
        if (value == 58)
            return R.drawable.icon_58;
        if (value == 59)
            return R.drawable.icon_59;
        if (value == 60)
            return R.drawable.icon_60;
        if (value == 61)
            return R.drawable.icon_61;
        return 0;
    }

    public static int drawable(int value){
        if(value == 1)
            return R.drawable.drawable_1;
        if(value == 2)
            return R.drawable.drawable_2;
        if(value == 3)
            return R.drawable.drawable_3;
        if(value == 4)
            return R.drawable.drawable_4;
        if(value == 5)
            return R.drawable.drawable_5;
        return 0;
    }

    public static int background(int value){
        if(value == 1)
            return R.drawable.background_1;
        if(value == 2)
            return R.drawable.background_2;
        if(value == 3)
            return R.drawable.background_3;
        if(value == 4)
            return R.drawable.background_4;
        if(value == 5)
            return R.drawable.background_5;
        if(value == 6)
            return R.drawable.background_6;
        if(value == 7)
            return R.drawable.background_7;
        if(value == 8)
            return R.drawable.background_8;
        if(value == 9)
            return R.drawable.background_9;
        if(value == 10)
            return R.drawable.background_10;
        if(value == 11)
            return R.drawable.background_11;
        if(value == 12)
            return R.drawable.background_12;
        if(value == 13)
            return R.drawable.background_13;
        return 0;
    }

    public static int size(int value){
        if(value == 1)
            return ViewGroup.LayoutParams.WRAP_CONTENT;
        if(value == 2)
            return ViewGroup.LayoutParams.MATCH_PARENT;
        return 0;
    }

    public static int direction(int value){
        if(value == 1)
            return RelativeLayout.CENTER_HORIZONTAL;
        if(value == 2)
            return RelativeLayout.CENTER_IN_PARENT;
        if(value == 3)
            return RelativeLayout.ALIGN_PARENT_TOP;
        if(value == 4)
            return RelativeLayout.ALIGN_PARENT_BOTTOM;
        if(value == 5)
            return RelativeLayout.ALIGN_PARENT_START;
        if(value == 6)
            return RelativeLayout.ALIGN_PARENT_END;
        if(value == 7)
            return RelativeLayout.ABOVE;
        if(value == 8)
            return RelativeLayout.BELOW;
        if(value == 9)
            return RelativeLayout.START_OF;
        if(value == 10)
            return RelativeLayout.END_OF;
        if(value == 11)
            return RelativeLayout.ALIGN_TOP;
        if(value == 12)
            return RelativeLayout.ALIGN_BOTTOM;
        if(value == 13)
            return RelativeLayout.ALIGN_START;
        if(value == 14)
            return RelativeLayout.ALIGN_END;
        return 0;
    }

    public static void backgroundTypeA(Context context, View view, int background, int color, int width) {
        view.setBackground(context.getDrawable(background));
        GradientDrawable widgetBorder = (GradientDrawable) view.getBackground();
        widgetBorder.setStroke(width, ContextCompat.getColor(context, color));
    }

    public static void backgroundTypeB(Context context, View view, int drawable, int color, int dimen) {
        Bitmap source = BitmapFactory.decodeResource(context.getResources(), drawable);
        Bitmap bitmap = Bitmap.createScaledBitmap(source, dimen, dimen, true);
        source.recycle();
        view.setBackground(new BitmapDrawable(context.getResources(), bitmap));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            view.getBackground().setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, color), BlendMode.SRC_ATOP));
        } else {
            view.getBackground().setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);
        }
    }
    public static void backgroundTypeC(Context context, View view, int background, int color) {
        view.setBackground(context.getDrawable(background));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            view.getBackground().setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, color), BlendMode.SRC_ATOP));
        } else {
            view.getBackground().setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static void textType(Context context, TextView textView, String text, int color, int style) {
        textView.setText(text);
        textView.setTextColor(ContextCompat.getColor(context, color));
        textView.setTypeface(null, style);
    }

    public static void imageTypeA(Context context, ImageView imageView, int drawable, int color, int dimen) {
        Bitmap source = BitmapFactory.decodeResource(context.getResources(), drawable);
        Bitmap bitmap = Bitmap.createScaledBitmap(source, dimen, dimen, true);
        source.recycle();
        imageView.setImageBitmap(bitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            imageView.setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, color), BlendMode.SRC_ATOP));
        } else {
            imageView.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static void imageTypeB(Context context, ImageView imageView, int drawable, int color) {
        imageView.setImageDrawable(context.getDrawable(drawable));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            imageView.setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, color), BlendMode.SRC_ATOP));
        } else {
            imageView.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static void imageTypeC(Context context, ImageView imageView, int drawable, int color, int width, int height) {
        Bitmap source = BitmapFactory.decodeResource(context.getResources(), drawable);
        Bitmap bitmap = Bitmap.createScaledBitmap(source, width, height, true);
        source.recycle();
        imageView.setImageBitmap(bitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            imageView.setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, color), BlendMode.SRC_ATOP));
        } else {
            imageView.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static void imageTypeD(ImageView imageView, Drawable drawable, int dimen) {
        Bitmap source = (((BitmapDrawable) drawable).getBitmap());
        Bitmap bitmap = Bitmap.createScaledBitmap(source, dimen, dimen, true);
        source.recycle();
        imageView.setImageBitmap(bitmap);
    }

    public static void customTypeB(Context context, EditText editText, String hint, int color, int style){
        editText.setHint(hint);
        editText.setHintTextColor(ContextCompat.getColor(context, color));
        editText.setTextColor(ContextCompat.getColor(context, color));
        editText.setTypeface(null, style);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                editText.getTextCursorDrawable().setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, color), BlendMode.SRC_ATOP));
            } else {
                editText.getTextCursorDrawable().setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);
            }
        } else {
            try {
                Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
                field.setAccessible(true);

                field = TextView.class.getDeclaredField("mEditor");
                field.setAccessible(true);
                Object editor = field.get(editText);

                Drawable drawable = ContextCompat.getDrawable(editText.getContext(), drawable(3));
                drawable.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);
                Drawable[] drawables = {drawable, drawable};

                field = editor.getClass().getDeclaredField("mCursorDrawable");
                field.setAccessible(true);
                field.set(editor, drawables);
            } catch (Exception ignored) {}
        }
    }

    public static void switchTint(Context context, ImageView imageView, int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            imageView.setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, color), BlendMode.SRC_ATOP));
        } else {
            imageView.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static Bitmap reduceBitmap(Context context, int source, int dimen){
        Bitmap onSource = BitmapFactory.decodeResource(context.getResources(), source);
        Bitmap out = Bitmap.createScaledBitmap(onSource, dimen, dimen, true);
        onSource.recycle();
        return out;
    }

    public static void layoutParamsTypeA(View view, int[] params){
        if(view != null){
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            for (int i = 0; i < layoutParams.getRules().length; i++) {
                layoutParams.removeRule(i);
            }
            if(params != null)
                for (int i : params) {
                    layoutParams.addRule(i);
                }
            view.setLayoutParams(layoutParams);
        }
    }

    public static void layoutParamsTypeB(View view, int[] params, int direction, int id){
        if(view != null){
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            for (int i = 0; i < layoutParams.getRules().length; i++) {
                layoutParams.removeRule(i);
            }
            if(params != null)
                for (int i : params) {
                    layoutParams.addRule(i);
                }
            layoutParams.addRule(direction, id);
            view.setLayoutParams(layoutParams);
        }
    }

    public static void layoutParamsTypeC(View view, int[] params, int direction_a, int id_a, int direction_b, int id_b){
        if(view != null){
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            for (int i = 0; i < layoutParams.getRules().length; i++) {
                layoutParams.removeRule(i);
            }
            if(params != null)
                for (int i : params) {
                    layoutParams.addRule(i);
                }
            layoutParams.addRule(direction_a, id_a);
            layoutParams.addRule(direction_b, id_b);
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setMargins(Context context, View view, int top, int bottom, int start, int end){
        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginParams.setMargins(density(context, start), density(context, top), density(context, end), density(context, bottom));
    }

    public static void setSize(View view, int width, int height){
        view.getLayoutParams().width = width;
        view.getLayoutParams().height = height;
        view.requestLayout();
    }

    public static void buttonModeA(Context context, RelativeLayout frame, ImageView icon, int drawable, boolean state){
        if(state){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                frame.getBackground().setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, ui), BlendMode.SRC_ATOP));
                icon.setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, tintB), BlendMode.SRC_ATOP));
            } else {
                frame.getBackground().setColorFilter(ContextCompat.getColor(context, ui), PorterDuff.Mode.SRC_ATOP);
                icon.setColorFilter(ContextCompat.getColor(context, tintB), PorterDuff.Mode.SRC_ATOP);
            }
        } else {
            backgroundTypeA(context, frame, drawable, tintA, 3);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                icon.setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, tintA), BlendMode.SRC_ATOP));
            } else {
                icon.setColorFilter(ContextCompat.getColor(context, tintA), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    public static void buttonModeB(Context context, RelativeLayout frame, TextView textView, int drawable, boolean state){
        if(state){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                frame.getBackground().setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, ui), BlendMode.SRC_ATOP));
            } else {
                frame.getBackground().setColorFilter(ContextCompat.getColor(context, ui), PorterDuff.Mode.SRC_ATOP);
            }
            textView.setTextColor(ContextCompat.getColor(context, tintB));
        } else {
            backgroundTypeA(context, frame, drawable, tintA, 3);
            textView.setTextColor(ContextCompat.getColor(context, tintA));
        }
    }

    public static void buttonModeC(Context context, RelativeLayout frame, ImageView icon, boolean state){
        int drawable = 0;
        int dimen = 0;
        if(state){
            drawable = background(1);
            dimen = 60;
        } else {
            drawable = background(2);
            dimen = 75;
        }
        backgroundTypeB(context, frame, drawable, tintA, dimen);
        if(state){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                frame.getBackground().setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, ui), BlendMode.SRC_ATOP));
                icon.setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, tintB), BlendMode.SRC_ATOP));
            } else {
                frame.getBackground().setColorFilter(ContextCompat.getColor(context, ui), PorterDuff.Mode.SRC_ATOP);
                icon.setColorFilter(ContextCompat.getColor(context, tintB), PorterDuff.Mode.SRC_ATOP);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                icon.setColorFilter(new BlendModeColorFilter(ContextCompat.getColor(context, tintA), BlendMode.SRC_ATOP));
            } else {
                icon.setColorFilter(ContextCompat.getColor(context, tintA), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    public static void toggleMode(Context context, ImageView imageView, boolean state){
        if(state){
            imageView.setAlpha(1.0f);
            imageTypeB(context, imageView, background(4), ui);
            layoutParamsTypeA(imageView, new int[]{direction(2), direction(5)});
            setMargins(context, imageView, 0, 0, 5, 0);
        } else {
            imageView.setAlpha(.5f);
            imageTypeB(context, imageView, background(4), ui);
            layoutParamsTypeA(imageView, new int[]{direction(2), direction(6)});
            setMargins(context, imageView, 0, 0, 0, 5);
        }
    }

    public static void switchLauncher(Context context){
        if(defaultApp(context).equals(context.getPackageName()) || defaultApp(context).equals("android")){
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                RoleManager roleManager = (RoleManager) context.getSystemService(ROLE_SERVICE);
                Intent home = roleManager.createRequestRoleIntent(RoleManager.ROLE_HOME);
                context.startActivity(home);
            } else {
                context.getPackageManager().clearPackagePreferredActivities(context.getPackageName());
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(startMain);
            }
        } else {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", context.getPackageName(), null));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public static void restart(Context context){
        Intent intent = new Intent(context, TheNest.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        Runtime.getRuntime().exit(0);
    }

    public static String defaultApp(Context context){
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        return context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY).activityInfo.packageName;
    }

    public static String appLabel(Context context, String string){;
        try {
            return (String) context.getPackageManager().getApplicationInfo(string, 0).loadLabel(context.getPackageManager());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Bitmap appIcon(Context context, String appPackage, int dimen){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return roundedBitmap(adaptiveIcon(context, appPackage, dimen));
        } else {
            return roundedBitmap(drawableIcon(context, appPackage, dimen));
        }
    }

    public static Bitmap roundedBitmap(Bitmap bitmap) {
        if(bitmap != null){
            int dimen = Math.min(bitmap.getWidth(), bitmap.getHeight());

            Bitmap out = Bitmap.createBitmap (dimen, dimen, Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(out);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, dimen, dimen);
            RectF rectF = new RectF(rect);

            float left = (dimen - bitmap.getWidth()) / 2;
            float top = (dimen - bitmap.getHeight()) / 2;

            paint.setAntiAlias(true);
            canvas.drawOval(rectF, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, left, top, paint);

            bitmap.recycle();
            return out;
        }
        return null;
    }

    public static Bitmap drawableIcon(Context context, String packageName, int dimension) {
        try {
            Drawable drawable = context.getPackageManager().getApplicationIcon(packageName);
            Bitmap bitmap;
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                if(bitmapDrawable.getBitmap() != null) {
                    return Bitmap.createScaledBitmap(((BitmapDrawable) drawable).getBitmap(), dimension,dimension, true);
                }
            }
            if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
                bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return Bitmap.createScaledBitmap(bitmap, dimension,dimension, true);
        } catch (Exception e){}
        return null;
    }

    @SuppressLint("NewApi")
    public static Bitmap adaptiveIcon(Context context, String packageName, int dimension) {
        try {
            Drawable drawable = context.getPackageManager().getApplicationIcon(packageName);

            if (drawable instanceof BitmapDrawable) {
                return Bitmap.createScaledBitmap(((BitmapDrawable) drawable).getBitmap(), dimension,dimension, true);
            } else if (drawable instanceof AdaptiveIconDrawable) {
                Drawable backgroundDr = ((AdaptiveIconDrawable) drawable).getBackground();
                Drawable foregroundDr = ((AdaptiveIconDrawable) drawable).getForeground();

                Drawable[] drr = new Drawable[2];
                drr[0] = backgroundDr;
                drr[1] = foregroundDr;

                LayerDrawable layerDrawable = new LayerDrawable(drr);

                int width = layerDrawable.getIntrinsicWidth();
                int height = layerDrawable.getIntrinsicHeight();

                Bitmap icon_1 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                Bitmap icon_2 = Bitmap.createScaledBitmap(icon_1, dimension,dimension, true);
                icon_1.recycle();

                Canvas canvas = new Canvas(icon_2);

                layerDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                layerDrawable.draw(canvas);

                return icon_2;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long time;
    public static Handler onTouchHandler = new Handler();
    public static Runnable onTouchRunnable;
    public static void touchStart(View view){
        view.setAlpha(0.0f);
        try {
            onTouchHandler.removeCallbacks(onTouchRunnable);
        } catch (Exception e){}
        time = (Long) System.currentTimeMillis();
    }

    public static void touchTip(Context context, String text, int layout){
        if(layout == 0){
            try {
                homeTooltipHandler.removeCallbacks(homeTooltipRunnable);
            } catch (Exception e){}
            homeTooltipStateB(context, text);
            homeTooltipHandler.postDelayed(homeTooltipRunnable, 2500);
        }
        if(layout == 1){
            try {
                drawerTooltipHandler.removeCallbacks(drawerTooltipRunnable);
            } catch (Exception e){}
            drawerTooltipStateB(context, text);
            drawerTooltipHandler.postDelayed(drawerTooltipRunnable, 2500);
        }
        if(layout == 2){
            try {
                settingsTooltipHandler.removeCallbacks(settingsTooltipRunnable);
            } catch (Exception e){}
            settingsTooltipStateB(context, text);
            settingsTooltipHandler.postDelayed(settingsTooltipRunnable, 2500);
        }
    }

    public static void touchVibrate(Context context, View view){
        view.setAlpha(1.0f);
        vibrate(context);
    }

    public static void touchStop(View view){
        view.setAlpha(1.0f);
        try {
            onTouchHandler.removeCallbacks(onTouchRunnable);
        } catch (Exception e){}
    }

    public static void touchEnd(MotionEvent event, View view){
        if(event.getAction() == MotionEvent.ACTION_CANCEL){
            view.setAlpha(1.0f);
            try {
                onTouchHandler.removeCallbacks(onTouchRunnable);
            } catch (Exception e){}
        }
    }

    public static void vibrate(Context context){
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(100);
        }
    }

    public static boolean permission(Context context, String permission){
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean systemApp(Context context, String appPackage){
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(appPackage, 0);
            return ((ai.flags & ApplicationInfo.FLAG_SYSTEM | ai.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int density(Context context, int value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    public static int textSize(Context context, int value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, context.getResources().getDisplayMetrics());
    }

    public static void toast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static boolean isAppInstalled(Context context, String appPackage) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo("" + appPackage, PackageManager.GET_META_DATA);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void startActivity(Context context, String packageName, String type){
        Uri uri = Uri.fromParts( "package", packageName, null);
        Intent intent = new Intent(type, uri);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean fileExist(Context context, String fileName){
        File file = new File(context.getFilesDir(), fileName);
        if(file.exists()){
            return true;
        } else {
            return false;
        }
    }

    public static List<String> read(Context context, String fileName){
        List<String> readValues = new ArrayList<>();
        File file = new File(context.getFilesDir(), fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                readValues.add(line);
            }
            reader.close();
        } catch (Exception e){}
        return readValues;
    }

    public static void create(Context context, String fileName, String value){
        File file = new File(context.getFilesDir(), fileName);
        try {
            FileWriter writer = new FileWriter(file,true);
            writer.write(value + "\r\n");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void edit(Context context, String fileName, String oldValue, String newValue){
        File file = new File(context.getFilesDir(), fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();

            String value;
            while ((value = reader.readLine()) != null) {
                builder.append(value);
                builder.append('\n');
            }
            reader.close();

            String inputStr = builder.toString();
            inputStr = inputStr.replace(oldValue, newValue);

            FileOutputStream out = new FileOutputStream(file);
            out.write(inputStr.getBytes());
            out.close();
        } catch (Exception e){}
    }
}
