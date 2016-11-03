package com.bit6.chatdemo;

import ios.NSObject;
import ios.foundation.NSArray;
import ios.foundation.NSData;
import ios.foundation.NSDictionary;
import ios.foundation.NSError;
import ios.foundation.NSMutableArray;
import ios.foundation.NSNotification;
import ios.foundation.NSNotificationCenter;
import ios.foundation.NSNumber;
import ios.foundation.NSString;
import ios.foundation.NSUserDefaults;
import ios.uikit.UIAlertAction;
import ios.uikit.UIAlertController;
import ios.uikit.UIApplication;
import ios.uikit.UILocalNotification;
import ios.uikit.UITextField;
import ios.uikit.UIUserNotificationSettings;
import ios.uikit.UIWindow;
import ios.uikit.c.UIKit;
import ios.uikit.enums.UIAlertActionStyle;
import ios.uikit.enums.UIAlertControllerStyle;
import ios.uikit.protocol.UIApplicationDelegate;

import com.intel.bit6.Bit6;
import com.intel.bit6.Bit6Address;
import com.intel.bit6.Bit6CallController;
import com.intel.bit6.Bit6CallViewController;
import com.intel.bit6.Bit6Conversation;
import com.intel.bit6.Bit6Group;
import com.intel.bit6.Bit6GroupMember;
import com.intel.bit6.Bit6Message;
import com.intel.bit6.Bit6PushNotificationCenter;
import com.intel.bit6.enums.Bit6CallStreams;
import com.intel.moe.natj.c.ann.CFunction;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Generated;
import com.intel.moe.natj.general.ann.NUInt;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.general.ptr.VoidPtr;
import com.intel.moe.natj.objc.*;
import com.intel.moe.natj.objc.ann.IsOptional;
import com.intel.moe.natj.objc.ann.ObjCBlock;
import com.intel.moe.natj.objc.ann.Selector;

import java.lang.Class;

import static com.bit6.chatdemo.Constants.*;
import static ios.uikit.enums.UIAlertActionStyle.Cancel;
import static ios.uikit.enums.UIAlertActionStyle.Default;
import static ios.uikit.enums.UIAlertControllerStyle.Alert;

@RegisterOnStartup
public class Main extends NSObject implements UIApplicationDelegate {

    public static void main(String[] args) {
        UIKit.UIApplicationMain(0, null, null, Main.class.getName());
    }

    @Selector("alloc")
    public static native Main alloc();

    protected Main(Pointer peer) {
        super(peer);
    }

    private UIWindow window;

    static {
        try {
            Class.forName(Bit6CallController.class.getName());
            Class.forName(Bit6CallViewController.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean applicationDidFinishLaunchingWithOptions(UIApplication application, NSDictionary launchOptions) {

        Class c = MyCallViewController.class;
        Bit6.setInCallClass(com.intel.moe.natj.objc.Class.fromJavaClass(c));

        //prepare to receive incoming calls
        NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this,new SEL("incomingCallNotification:"),Bit6IncomingCallNotification,null);
        NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this,new SEL("callAddedNotification:"),Bit6CallAddedNotification,null);
        NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this,new SEL("callPermissionsMissingNotification:"),Bit6CallPermissionsMissingNotification,null);
        NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this,new SEL("callMissedNotification:"),Bit6CallMissedNotification,null);

        //starting Bit6 SDK
        Bit6.startWithApiKey("SET YOUR API KEY");

        return true;
    }

    @Override
    public void setWindow(UIWindow value) {
        window = value;
    }

    @Override
    public UIWindow window() {
        return window;
    }

    
    /* Notifications */
    
    @Selector("application:didRegisterUserNotificationSettings:")
    public void applicationDidRegisterUserNotificationSettings(UIApplication application, UIUserNotificationSettings notificationSettings) {
        application.registerForRemoteNotifications();
    }

    @Selector("application:handleActionWithIdentifier:forRemoteNotification:completionHandler:")
    public void applicationHandleActionWithIdentifierForRemoteNotificationCompletionHandler(UIApplication application, String identifier, NSDictionary<?, ?> userInfo, final @ObjCBlock(
            name = "call_applicationHandleActionWithIdentifierForRemoteNotificationCompletionHandler"
    ) UIApplicationDelegate.Block_applicationHandleActionWithIdentifierForRemoteNotificationCompletionHandler completionHandler) {
        Bit6.pushNotification().handleActionWithIdentifierForRemoteNotificationCompletionHandler(identifier, userInfo, new Bit6PushNotificationCenter.Block_handleActionWithIdentifierForRemoteNotificationCompletionHandler() {
            public void call_handleActionWithIdentifierForRemoteNotificationCompletionHandler() {
                completionHandler.call_applicationHandleActionWithIdentifierForRemoteNotificationCompletionHandler();
            }
        });
    }

    @Selector("application:handleActionWithIdentifier:forRemoteNotification:withResponseInfo:completionHandler:")
    public void applicationHandleActionWithIdentifierForRemoteNotificationWithResponseInfoCompletionHandler(UIApplication application, String identifier, NSDictionary<?, ?> userInfo, NSDictionary<?, ?> responseInfo, @ObjCBlock(
            name = "call_applicationHandleActionWithIdentifierForRemoteNotificationWithResponseInfoCompletionHandler"
    ) final UIApplicationDelegate.Block_applicationHandleActionWithIdentifierForRemoteNotificationWithResponseInfoCompletionHandler completionHandler) {
        Bit6.pushNotification().handleActionWithIdentifierForRemoteNotificationWithResponseInfoCompletionHandler(identifier, userInfo, responseInfo, new Bit6PushNotificationCenter.Block_handleActionWithIdentifierForRemoteNotificationWithResponseInfoCompletionHandler() {
            public void call_handleActionWithIdentifierForRemoteNotificationWithResponseInfoCompletionHandler() {
                completionHandler.call_applicationHandleActionWithIdentifierForRemoteNotificationWithResponseInfoCompletionHandler();
            }
        });
    }

    @Selector("application:didReceiveRemoteNotification:fetchCompletionHandler:")
    public void applicationDidReceiveRemoteNotificationFetchCompletionHandler(UIApplication application, NSDictionary<?, ?> userInfo, @ObjCBlock(
            name = "call_applicationDidReceiveRemoteNotificationFetchCompletionHandler"
    ) final UIApplicationDelegate.Block_applicationDidReceiveRemoteNotificationFetchCompletionHandler completionHandler) {
        Bit6.pushNotification().didReceiveRemoteNotificationFetchCompletionHandler(userInfo, new Bit6PushNotificationCenter.Block_didReceiveRemoteNotificationFetchCompletionHandler() {
            public void call_didReceiveRemoteNotificationFetchCompletionHandler(@NUInt long var1){
                completionHandler.call_applicationDidReceiveRemoteNotificationFetchCompletionHandler(var1);
            }
        });
    }

    @Selector("application:didRegisterForRemoteNotificationsWithDeviceToken:")
    public void applicationDidRegisterForRemoteNotificationsWithDeviceToken(UIApplication application, NSData deviceToken) {
        Bit6.pushNotification().didRegisterForRemoteNotificationsWithDeviceToken(deviceToken);
    }

    @Selector("application:didFailToRegisterForRemoteNotificationsWithError:")
    public void applicationDidFailToRegisterForRemoteNotificationsWithError(UIApplication application, NSError error) {
        Bit6.pushNotification().didFailToRegisterForRemoteNotificationsWithError(error);
    }

    /* Call Listener */

    private Bit6CallController callController;

    @Selector("incomingCallNotification:")
    private void incomingCallNotification(NSNotification notification) {

        final Bit6CallController callController = (Bit6CallController)notification.object();

        //there's a call prompt showing at the time
        if (this.callController != null) {
            //if this is not the same call as the one being shown for the prompt then we reject it
            if (!this.callController.isEqual(callController)) {
                callController.decline();
            }
        }
        else {
            //the call was answered by taping the push notification
            if (callController.answered()) {
                callController.setLocalStreams(callController.remoteStreams());
                callController.start();
            }
            else {
                this.callController = callController;

                //show a prompt to answer the call
                UIAlertController alert = UIAlertController.alertControllerWithTitleMessagePreferredStyle(callController.incomingAlert(), null, Alert);

                if (callController.hasRemoteAudio()) {
                    alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Audio", Default, new UIAlertAction.Block_actionWithTitleStyleHandler() {
                        public void call_actionWithTitleStyleHandler(UIAlertAction var1){
                            callController.setLocalStreams(Bit6CallStreams.Audio);
                            callController.start();
                            Main.this.callController = null;
                        }
                    }));
                }
                if (callController.hasRemoteVideo()) {
                    alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Video", Default, new UIAlertAction.Block_actionWithTitleStyleHandler() {
                        public void call_actionWithTitleStyleHandler(UIAlertAction var1){
                            callController.setLocalStreams(Bit6CallStreams.Audio|Bit6CallStreams.Video);
                            callController.start();
                            Main.this.callController = null;
                        }
                    }));
                }
                alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Decline", Cancel, new UIAlertAction.Block_actionWithTitleStyleHandler() {
                    public void call_actionWithTitleStyleHandler(UIAlertAction var1) {
                        callController.decline();
                        Main.this.callController = null;
                    }
                }));

                window().rootViewController().presentViewControllerAnimatedCompletion(alert, true, null);

                //play the ringtone
                callController.playRingtone();
            }
        }

    }

    //ready to start the call, lets show the UI
    @Selector("callAddedNotification:")
    private void callAddedNotification(NSNotification notification) {
        Bit6CallController callController = (Bit6CallController)notification.object();
        Bit6CallViewController callViewController = Bit6.createViewControllerForCall(callController);
        callViewController.show();
    }

    //there's restricted access to microphone or camera
    @Selector("callPermissionsMissingNotification:")
    private void callPermissionsMissingNotification(NSNotification notification) {
        Bit6CallController callController = (Bit6CallController)notification.object();
        NSError error = callController.error;
        UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle(error.localizedDescription(), null, UIAlertControllerStyle.Alert);
        errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
        window().rootViewController().presentViewControllerAnimatedCompletion(errorAlert, true, null);
    }

    //missed call
    @Selector("callMissedNotification:")
    private void callMissedNotification(NSNotification notification) {
        
        UIApplicationState appState = [UIApplication sharedApplication].applicationState;
        if (appState == UIApplicationStateActive) {
        }
        
        Bit6CallController callController = (Bit6CallController)notification.object();
        if (this.callController == callController) {
            this.callController = null;
            window().rootViewController().dismissViewControllerAnimatedCompletion(true,null);
        }

        String title = String.format("Missed Call from %s",callController.otherDisplayName());

        UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle(title, null, UIAlertControllerStyle.Alert);
        errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
        window().rootViewController().presentViewControllerAnimatedCompletion(errorAlert, true, null);
    }

}
