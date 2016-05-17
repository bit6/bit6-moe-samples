# bit6-moe-samples

1. Install [Multi-OS Engine](https://software.intel.com/en-us/multi-os-engine) v1.0.648
2. In Android Studio choose the option "import project (Eclipse ADT, Graddle, etc.) to import the sample project.

3. Select "Edit Configurations" in the Run menu in Android Studio. Add a new "Multi-OS Engine iOS Application" configuration to run on a Device.
	
	![](img/configuration1.png)
	
	![](img/configuration2.png)
	
	![](img/configuration3.png)

4. Get your Bit6 api key in [Bit6 Dashboard](https://dashboard.bit6.com).

5. Open the file com.bit6.chatdemo.Main.java and set your api key in

	`Bit6.startWithApiKey("");`
	
6. Open the file /ios/build.gradle and set `signingIdentity`, `provisioningProfile` and `bundleID` (bundle identifier) to match your Apple account.
	
7. Run the project

8. Open the Xcode project in /ios/build/xcode/ChatDemo.xcodeproj 

9. Check the background modes in your target.

![](img/background_modes.png)

10. Connect your iOS device and run it using Xcode.

####Enable the Push Notification

1. Generate the APNS Certificates for the bundle identifier you set in step8. https://developer.apple.com/account/. Install these certificates in your Mac.

2. Open the Keychain app in Mac and export the APNS Certificates as .p12 files. Upload these two .p12 files to [Bit6 Dashboard](https://dashboard.bit6.com).

####Try the Sample Project

1. Install the sample project in one device

2. Enter an username and a password. 

3. Tap "Sign Up" to create the account, or "Login" if the account already existed.

4. Repeat steps 1-3 in another device, using a different account.

#####To start a conversation

1. Tap the + sign in the demo running in device1

2. Enter the username of the account you are using in device2

3. Once you are inside the conversation you can:
	* use the "Call" button to start an audio or an audio+video call to deviceB
	* use the "compose" button to send a text message