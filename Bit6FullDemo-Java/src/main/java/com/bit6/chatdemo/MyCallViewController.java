package com.bit6.chatdemo;


import com.intel.bit6.Bit6;
import com.intel.bit6.Bit6CallController;
import com.intel.bit6.Bit6CallViewController;
import com.intel.bit6.Bit6Utils;
import com.intel.bit6.Bit6VideoFeedView;
import com.intel.bit6.enums.Bit6CallState;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.SEL;
import com.intel.inde.moe.natj.objc.ann.IBAction;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.ann.Property;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.foundation.NSArray;
import ios.foundation.NSBundle;
import ios.foundation.NSCoder;
import ios.foundation.NSString;
import ios.uikit.UIAlertAction;
import ios.uikit.UIAlertController;
import ios.uikit.UIDevice;
import ios.uikit.UIImage;
import ios.uikit.UILabel;
import ios.uikit.UITapGestureRecognizer;
import ios.uikit.UIView;
import ios.uikit.enums.UIAlertActionStyle;
import ios.uikit.enums.UIAlertControllerStyle;
import ios.uikit.enums.UIControlState;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("MyCallViewController")
@RegisterOnStartup
public class MyCallViewController extends Bit6CallViewController {
	static {
		NatJ.register();
	}

	@Generated
	protected MyCallViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native MyCallViewController alloc();

	@Generated
	@Selector("bluetoothButton")
    @Property
    public native CircleButton bluetoothButton();

	@Selector("callController")
	public Bit6CallController callController() {
        return Bit6.callControllers().get(0);
    }

	@Generated
	@Selector("cameraButton")
    @Property
    public native CircleButton cameraButton();

	@Generated
	@Selector("controlsView")
    @Property
    public native UIView controlsView();

	@Selector("createForCall:")
	public static Bit6CallViewController createForCall(Bit6CallController callController) {
        return MyCallViewController.alloc().initWithNibNameBundle("MyCallViewController",null);
    }

	@Generated
	@Selector("init")
	public native MyCallViewController init();

	@Generated
	@Selector("initWithCoder:")
	public native MyCallViewController initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithNibName:bundle:")
	public native MyCallViewController initWithNibNameBundle(
			String nibNameOrNil, NSBundle nibBundleOrNil);

    @Generated
    @Selector("muteAudioButton")
    @Property
    public native CircleButton muteAudioButton();

	@Selector("overlayTapped:")
	public void overlayTapped(UITapGestureRecognizer tgr) {

        boolean atLeastOneCallConnected = false;
        boolean atLeastOneCallHasVideo = false;
        NSArray<Bit6CallController> callControllers = (NSArray<Bit6CallController>)Bit6.callControllers().copy();
        for (Bit6CallController call : callControllers) {
            if (call.state() >= Bit6CallState.CONNECTED) {
                atLeastOneCallConnected = true;
            }
            if (call.hasVideo()) {
                atLeastOneCallHasVideo = true;
            }
        }

        if (atLeastOneCallHasVideo) {
            if (!overlayView().isHidden()) {
                if (atLeastOneCallConnected) {
                    overlayView().setHidden(!overlayView().isHidden());
                }
            }
            else {
                overlayView().setHidden(!overlayView().isHidden());
            }
        }
    }

    @Generated
	@Selector("overlayView")
    @Property
    public native UIView overlayView();

	@Selector("prefersStatusBarHidden")
	public boolean prefersStatusBarHidden() {
        return true;
    }

	@Generated
	@Selector("setBluetoothButton:")
	public native void setBluetoothButton(CircleButton value);

	@Generated
	@Selector("setCameraButton:")
	public native void setCameraButton(CircleButton value);

	@Generated
	@Selector("setControlsView:")
	public native void setControlsView_unsafe(UIView value);

	@Generated
	public void setControlsView(UIView value) {
		Object __old = controlsView();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setControlsView_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}

	@Generated
	@Selector("setMuteAudioButton:")
	public native void setMuteAudioButton(CircleButton value);

	@Generated
	@Selector("setOverlayView:")
	public native void setOverlayView_unsafe(UIView value);

	@Generated
	public void setOverlayView(UIView value) {
		Object __old = overlayView();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setOverlayView_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}

	@Generated
	@Selector("setSpeakerButton:")
	public native void setSpeakerButton(CircleButton value);

	@Generated
	@Selector("setTimerLabel:")
	public native void setTimerLabel_unsafe(UILabel value);

	@Generated
	public void setTimerLabel(UILabel value) {
		Object __old = timerLabel();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setTimerLabel_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}

	@Generated
	@Selector("setUsernameLabel:")
	public native void setUsernameLabel_unsafe(UILabel value);

	@Generated
	public void setUsernameLabel(UILabel value) {
		Object __old = usernameLabel();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setUsernameLabel_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}

	@Generated
	@Selector("speakerButton")
    @Property
    public native CircleButton speakerButton();

	@Generated
	@Selector("timerLabel")
    @Property
    public native UILabel timerLabel();

	@Generated
	@Selector("usernameLabel")
    @Property
    public native UILabel usernameLabel();

	@Selector("viewDidLoad")
	public void viewDidLoad() {
        super.viewDidLoad();

        refreshState();

        usernameLabel().setText(Bit6.callControllers().count() > 1 ? "Many Destinations" : callController().otherDisplayName());

        bluetoothButton().setTitleForState("", UIControlState.Normal);
        bluetoothButton().setBackgroundImageForState(UIImage.imageNamed("bluetooth"), UIControlState.Normal);

        speakerButton().setTitleForState("", UIControlState.Normal);
        speakerButton().setBackgroundImageForState(UIImage.imageNamed("speaker"), UIControlState.Normal);

        muteAudioButton().setTitleForState("", UIControlState.Normal);
        muteAudioButton().setBackgroundImageForState(UIImage.imageNamed("mute"), UIControlState.Normal);

        cameraButton().setTitleForState("", UIControlState.Normal);
        cameraButton().setBackgroundImageForState(UIImage.imageNamed("camera"), UIControlState.Normal);

        UITapGestureRecognizer tgr = UITapGestureRecognizer.alloc().initWithTargetAction(this,new SEL("overlayTapped:"));
        overlayView().addGestureRecognizer(tgr);
        UITapGestureRecognizer tgr2 = UITapGestureRecognizer.alloc().initWithTargetAction(this,new SEL("overlayTapped:"));
        view().addGestureRecognizer(tgr2);

        boolean atLeastOneCallConnected = false;
        NSArray<Bit6CallController> callControllers = (NSArray<Bit6CallController>)Bit6.callControllers().copy();
        for (Bit6CallController call : callControllers) {
            if (call.state() >= Bit6CallState.CONNECTED) {
                atLeastOneCallConnected = true; break;
            }
        }
        controlsView().setHidden(!atLeastOneCallConnected);
    }


    /**************** Bit6CallViewController methods ****************/

    @Selector("callStateChangedNotificationForCall:")
    public void callStateChangedNotificationForCall(Bit6CallController callController)
    {
        if (callController.state() == Bit6CallState.ERROR) {
            UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("An Error Occurred", callController.error().localizedDescription(), UIAlertControllerStyle.Alert);
            errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
            presentViewControllerAnimatedCompletion(errorAlert, true, null);
        }

        refreshState();

        secondsChangedNotificationForCall(callController);
    }

    @Selector("secondsChangedNotificationForCall:")
    public void secondsChangedNotificationForCall(Bit6CallController callController)
    {
        long longerCall = 0;
        NSArray<Bit6CallController> callControllers = (NSArray<Bit6CallController>)Bit6.callControllers().copy();
        for (Bit6CallController call : callControllers) {
            if (call.seconds() > longerCall) {
                longerCall = call.seconds();
            }
        }

        if (callController.state() == Bit6CallState.CONNECTED) {
            timerLabel().setText( Bit6Utils.clockFormatForSeconds(longerCall) );
        }
    }

    @Selector("refreshState")
    public void refreshState()
    {
        boolean atLeastOneCallConnected = false;
        Bit6CallController smallerCall = null;
        int smallerState = (int)Bit6CallState.MISSED;

        NSArray<Bit6CallController> callControllers = (NSArray<Bit6CallController>)Bit6.callControllers().copy();
        for (Bit6CallController call : callControllers) {
            if (call.state() < smallerState) {
                smallerState = (int)call.state();
                smallerCall = call;
            }
            if (call.state() >= Bit6CallState.CONNECTED) {
                atLeastOneCallConnected = true;
            }
        }

        controlsView().setHidden(!atLeastOneCallConnected);

        if (smallerCall != null) {
            if (smallerCall.incoming()) {
                switch (smallerState) {
                    case (int)Bit6CallState.NEW:
                    case (int)Bit6CallState.ACCEPTING_CALL:
                        timerLabel().setText("Answering Call..."); break;
                    case (int)Bit6CallState.WAITING_SDP:
                        timerLabel().setText("Waiting SDP..."); break;
                    case (int)Bit6CallState.GATHERING_CANDIDATES:
                        timerLabel().setText("Gathering Candidates..."); break;
                    case (int)Bit6CallState.SENDING_SDP:
                        timerLabel().setText("Sending SDP..."); break;
                    case (int)Bit6CallState.CONNECTING:
                        timerLabel().setText("Connecting..."); break;
                    case (int)Bit6CallState.CONNECTED: break;
                    case (int)Bit6CallState.DISCONNECTED:
                        timerLabel().setText("Disconnected"); break;
                    default: break;
                }
            }
            else {
                switch (smallerState) {
                    case (int)Bit6CallState.NEW:
                    case (int)Bit6CallState.GATHERING_CANDIDATES:
                        timerLabel().setText("Gathering Candidates..."); break;
                    case (int)Bit6CallState.SENDING_SDP:
                        timerLabel().setText("Sending SDP..."); break;
                    case (int)Bit6CallState.WAITING_SDP:
                        timerLabel().setText("Waiting for Answer..."); break;
                    case (int)Bit6CallState.CONNECTING:
                        timerLabel().setText("Connecting..."); break;
                    case (int)Bit6CallState.CONNECTED: break;
                    case (int)Bit6CallState.DISCONNECTED:
                        timerLabel().setText("Disconnected"); break;
                    case (int)Bit6CallState.ERROR:
                    case (int)Bit6CallState.END:
                    default: break;
                }
            }
        }
    }

    @Selector("refreshControlsView")
    public void refreshControlsView()
    {
        boolean atLeastOneCallHasVideo = false;
        boolean atLeastOneCallHasAudio = false;
        boolean atLeastOneCallHasRemoteAudio = false;

        NSArray<Bit6CallController> callControllers = (NSArray<Bit6CallController>)Bit6.callControllers().copy();
        for (Bit6CallController call : callControllers) {
            if (call.hasVideo()) {
                atLeastOneCallHasVideo = true;
            }
            if (call.hasAudio()) {
                atLeastOneCallHasAudio = true;
            }
            if (call.hasRemoteAudio()) {
                atLeastOneCallHasRemoteAudio = true;
            }
        }

//        if (TARGET_OS_SIMULATOR) {
//            cameraButton().setEnabled(false);
//            bluetoothButton().setEnabled(false);
//            speakerButton().setEnabled(false);
//        }
//        else {
            cameraButton().setEnabled(atLeastOneCallHasVideo);
            bluetoothButton().setEnabled(atLeastOneCallHasRemoteAudio);
            speakerButton().setEnabled(atLeastOneCallHasRemoteAudio);
//        }

        muteAudioButton().setEnabled(atLeastOneCallHasAudio);

        String deviceType = UIDevice.currentDevice().model();
        if(!deviceType.equals("iPhone")){
            speakerButton().setEnabled(false);
        }

        if (muteAudioButton().isEnabled()) {
            muteAudioButton().setOn(Bit6CallController.audioMuted());
        }
        if (bluetoothButton().isEnabled()) {
            bluetoothButton().setOn(Bit6CallController.bluetoothEnabled());
        }
        if (speakerButton().isEnabled()) {
            speakerButton().setOn(Bit6CallController.speakerEnabled());
        }
    }

    @Selector("updateLayoutForVideoFeedViews:")
    public void updateLayoutForVideoFeedViews(NSArray<? extends Bit6VideoFeedView> videoFeedViews)
    {
        usernameLabel().setText(Bit6.callControllers().count() > 1 ? "Many Destinations" : callController().otherDisplayName());

        super.updateLayoutForVideoFeedViews(videoFeedViews);
    }

    /**************** Actions ****************/

    @Selector("muteAudioCall:")
    public void muteAudioCall(@Mapped(ObjCObjectMapper.class) Object sender)
    {
        Bit6CallController.switchMuteAudio();
    }

    @Selector("bluetooth:")
    public void bluetooth(@Mapped(ObjCObjectMapper.class) Object sender)
    {
        Bit6CallController.switchBluetooth();
    }

    @Selector("speaker:")
    public void speaker(@Mapped(ObjCObjectMapper.class) Object sender)
    {
        Bit6CallController.switchSpeaker();
    }

    @Selector("muteVideoCall:")
    public void muteVideoCall(@Mapped(ObjCObjectMapper.class) Object sender)
    {
        Bit6CallController.switchMuteVideo();
    }

    @Selector("switchCamera:")
    public void switchCamera(@Mapped(ObjCObjectMapper.class) Object sender)
    {
        Bit6CallController.switchCamera();
    }

    @Selector("hangup:")
    public void hangup(@Mapped(ObjCObjectMapper.class) Object sender)
    {
        Bit6CallController.hangupAll();
    }

}