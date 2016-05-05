package com.bit6.chatdemo;


import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Generated;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.general.ann.Runtime;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Selector;
import ios.foundation.NSBundle;
import ios.foundation.NSCoder;
import ios.foundation.NSDictionary;
import ios.foundation.NSError;
import ios.uikit.UIAlertAction;
import ios.uikit.UIAlertController;
import ios.uikit.UIAlertView;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UITextField;
import ios.uikit.UIViewController;
import ios.uikit.enums.UIAlertActionStyle;
import ios.uikit.enums.UIAlertControllerStyle;

import com.intel.bit6.Bit6;
import com.intel.bit6.Bit6Address;
import com.intel.bit6.Bit6Session;

import static ios.uikit.enums.UIAlertActionStyle.Default;
import static ios.uikit.enums.UIAlertControllerStyle.Alert;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("LoginViewController")
@RegisterOnStartup
public class LoginViewController extends UIViewController {
	static {
		NatJ.register();
	}

	@Generated
	protected LoginViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native LoginViewController alloc();

	@Generated
	@Selector("init")
	public native LoginViewController init();

	@Generated
	@Selector("initWithCoder:")
	public native LoginViewController initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithNibName:bundle:")
	public native LoginViewController initWithNibNameBundle(
			String nibNameOrNil, NSBundle nibBundleOrNil);

	@Selector("touchedLoginBarButton:")
	private void touchedLoginBarButton(UIBarButtonItem sender)
	{
        final UIAlertController alert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Login", "Enter your username and password", Alert);
        alert.addTextFieldWithConfigurationHandler( new UIAlertController.Block_addTextFieldWithConfigurationHandler() {
            public void call_addTextFieldWithConfigurationHandler(UITextField var1) {
                var1.setPlaceholder("Username");
            }
        });
        alert.addTextFieldWithConfigurationHandler( new UIAlertController.Block_addTextFieldWithConfigurationHandler() {
            public void call_addTextFieldWithConfigurationHandler(UITextField var1) {
                var1.setPlaceholder("Password");
                var1.setSecureTextEntry(true);
            }
        });
        alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Done", Default, new UIAlertAction.Block_actionWithTitleStyleHandler() {
            public void call_actionWithTitleStyleHandler(UIAlertAction var1) {
                UITextField userTextField = alert.textFields().get(0);
                String user = userTextField.text();
                UITextField passTextField = alert.textFields().get(1);
                String pass = passTextField.text();

                //doing the login
                Bit6Address myUser = Bit6Address.addressWithUsername(user);
                Bit6.session().loginWithUserIdentityPasswordCompletionHandler(myUser, pass, new Bit6Session.Block_loginWithUserIdentityPasswordCompletionHandler() {
                    public void call_loginWithUserIdentityPasswordCompletionHandler(NSDictionary<?, ?> response, NSError error) {
                        if (error != null) {
                            UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Error", error.localizedDescription(), UIAlertControllerStyle.Alert);
                            errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
                            navigationController().presentViewControllerAnimatedCompletion(errorAlert, true, null);
                        } else {
                            presentConversations();

                            UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Logged as " + Bit6.session().activeIdentity().uri(), null, UIAlertControllerStyle.Alert);
                            errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
                            navigationController().presentViewControllerAnimatedCompletion(errorAlert, true, null);
                        }
                    }
                });
            }
        }));

        alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Cancel", UIAlertActionStyle.Cancel, null));
        navigationController().presentViewControllerAnimatedCompletion(alert, true, null);
    }

    @Selector("touchedSignUpButton:")
    private void touchedSignUpButton(UIBarButtonItem sender) {
        final UIAlertController alert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Sign Up", "Enter your username and password", Alert);
        alert.addTextFieldWithConfigurationHandler( new UIAlertController.Block_addTextFieldWithConfigurationHandler() {
            public void call_addTextFieldWithConfigurationHandler(UITextField var1) {
                var1.setPlaceholder("Username");
            }
        });
        alert.addTextFieldWithConfigurationHandler( new UIAlertController.Block_addTextFieldWithConfigurationHandler() {
            public void call_addTextFieldWithConfigurationHandler(UITextField var1) {
                var1.setPlaceholder("Password");
            }
        });
        alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Done", Default, new UIAlertAction.Block_actionWithTitleStyleHandler() {
            public void call_actionWithTitleStyleHandler(UIAlertAction var1) {
                UITextField userTextField = alert.textFields().get(0);
                String user = userTextField.text();
                UITextField passTextField = alert.textFields().get(1);
                String pass = passTextField.text();

                //doing the login
                Bit6Address myUser = Bit6Address.addressWithUsername(user);
                Bit6.session().signUpWithUserIdentityPasswordCompletionHandler(myUser, pass, new Bit6Session.Block_signUpWithUserIdentityPasswordCompletionHandler() {
                    public void call_signUpWithUserIdentityPasswordCompletionHandler(NSDictionary<?, ?> response, NSError error) {
                        if (error != null) {
                            UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Error", error.localizedDescription(), UIAlertControllerStyle.Alert);
                            errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
                            navigationController().presentViewControllerAnimatedCompletion(errorAlert, true, null);
                        } else {
                            presentConversations();

                            UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Logged as " + Bit6.session().activeIdentity().uri(), null, UIAlertControllerStyle.Alert);
                            errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
                            navigationController().presentViewControllerAnimatedCompletion(errorAlert, true, null);
                        }
                    }
                });
            }
        }));

        alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Cancel", UIAlertActionStyle.Cancel, null));
        navigationController().presentViewControllerAnimatedCompletion(alert, true, null);
    }

    @Generated
    @Selector("passwordTextField")
    public native UITextField passwordTextField();

    @Generated
    @Selector("setPasswordTextField:")
    public native void setPasswordTextField_unsafe(UITextField value);

    @Generated
    public void setPasswordTextField(UITextField value) {
        Object __old = passwordTextField();
        if (value != null) {
            com.intel.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
                    value);
        }
        setPasswordTextField_unsafe(value);
        if (__old != null) {
            com.intel.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
                    __old);
        }
    }

    @Generated
    @Selector("setUsernameTextField:")
    public native void setUsernameTextField_unsafe(UITextField value);

    @Generated
    public void setUsernameTextField(UITextField value) {
        Object __old = usernameTextField();
        if (value != null) {
            com.intel.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
                    value);
        }
        setUsernameTextField_unsafe(value);
        if (__old != null) {
            com.intel.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
                    __old);
        }
    }

    @Generated
    @Selector("usernameTextField")
    public native UITextField usernameTextField();

    @Selector("viewDidLoad")
    public void viewDidLoad() {
        if (Bit6.session().authenticated()) {
            presentConversations();
        }

    }

    //we present the conversations list
    public void presentConversations() {
        performSegueWithIdentifierSender("loginCompleted", null);
    }
}