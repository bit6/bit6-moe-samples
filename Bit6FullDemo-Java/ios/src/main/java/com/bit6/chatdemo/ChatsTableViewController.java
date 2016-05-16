package com.bit6.chatdemo;


import com.intel.bit6.Bit6;
import com.intel.bit6.Bit6Address;
import com.intel.bit6.Bit6CallController;
import com.intel.bit6.Bit6CallViewController;
import com.intel.bit6.Bit6Conversation;
import com.intel.bit6.Bit6FileDownloader;
import com.intel.bit6.Bit6Group;
import com.intel.bit6.Bit6GroupMember;
import com.intel.bit6.Bit6Message;
import com.intel.bit6.Bit6OutgoingMessage;
import com.intel.bit6.enums.Bit6CallStreams;
import com.intel.bit6.enums.Bit6MessageCallChannel;
import com.intel.bit6.enums.Bit6MessageCallStatus;
import com.intel.bit6.enums.Bit6MessageFileType;
import com.intel.bit6.enums.Bit6MessageStatus;
import com.intel.bit6.enums.Bit6MessageType;
import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Generated;
import com.intel.moe.natj.general.ann.Mapped;
import com.intel.moe.natj.general.ann.NInt;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.general.ann.Runtime;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.SEL;
import com.intel.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Property;
import com.intel.moe.natj.objc.ann.Selector;
import com.intel.moe.natj.objc.map.ObjCObjectMapper;

import ios.coregraphics.struct.CGPoint;
import ios.foundation.NSArray;
import ios.foundation.NSBundle;
import ios.foundation.NSCoder;
import ios.foundation.NSData;
import ios.foundation.NSDictionary;
import ios.foundation.NSError;
import ios.foundation.NSIndexPath;
import ios.foundation.NSMutableArray;
import ios.foundation.NSNotification;
import ios.foundation.NSNotificationCenter;
import ios.foundation.NSString;
import ios.foundation.enums.NSComparisonResult;
import ios.uikit.UIAlertAction;
import ios.uikit.UIAlertController;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UIButton;
import ios.uikit.UIColor;
import ios.uikit.UIImage;
import ios.uikit.UIImageView;
import ios.uikit.UILabel;
import ios.uikit.UIStoryboardSegue;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewController;
import ios.uikit.UITapGestureRecognizer;
import ios.uikit.UITextField;
import ios.uikit.enums.UIAlertActionStyle;
import ios.uikit.enums.UIAlertControllerStyle;
import ios.uikit.enums.UIBarButtonItemStyle;
import ios.uikit.enums.UIButtonType;
import ios.uikit.enums.UIControlEvents;
import ios.uikit.enums.UITableViewRowAnimation;
import ios.uikit.enums.UITableViewScrollPosition;

import com.intel.bit6.Bit6;

import static com.bit6.chatdemo.Constants.*;
import static ios.uikit.enums.UIAlertActionStyle.Cancel;
import static ios.uikit.enums.UIAlertActionStyle.Default;
import static ios.uikit.enums.UIAlertControllerStyle.ActionSheet;
import static ios.uikit.enums.UIAlertControllerStyle.Alert;
import static ios.uikit.enums.UIBarButtonItemStyle.Plain;
import static ios.uikit.enums.UIBarButtonSystemItem.Add;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("ChatsTableViewController")
@RegisterOnStartup
public class ChatsTableViewController extends UITableViewController {
	static {
		NatJ.register();
	}

	@Generated
	protected ChatsTableViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native ChatsTableViewController alloc();

    @Generated
	@Selector("init")
	public native ChatsTableViewController init();

	@Generated
	@Selector("initWithCoder:")
	public native ChatsTableViewController initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithNibName:bundle:")
	public native ChatsTableViewController initWithNibNameBundle(String nibNameOrNil, NSBundle nibBundleOrNil);

	@Generated
	@Selector("initWithStyle:")
	public native ChatsTableViewController initWithStyle(@NInt long style);

    @Selector("viewDidLoad")
    public void viewDidLoad() {

        navigationItem().setPrompt("Logged as " + Bit6.session().activeIdentity().uri());

        UIBarButtonItem callItem = UIBarButtonItem.alloc().initWithImageStyleTargetAction(UIImage.imageNamed("bit6ui_phone"), UIBarButtonItemStyle.Plain, this, new SEL("call"));
        navigationItem().setRightBarButtonItem(callItem);

        if (_conversation.typingAddress() != null) {
            typingBarButtonItem().setTitle(_conversation.address().uri() + "  is typing...");
        }
        else {
            typingBarButtonItem().setTitle("");
        }
    }

    private boolean scroll = false;

    @Selector("viewWillAppear:")
    public void viewWillAppear(boolean animated)
    {
        super.viewWillAppear(animated);

        navigationController().setToolbarHiddenAnimated(false, true);

        if (!scroll) {
            scroll = true;
            tableView().reloadData();
            tableView().setContentOffsetAnimated(new CGPoint(0,1000000),false);
        }
    }

    @Selector("dealloc")
    public void dealloc()
    {
        Bit6.setCurrentConversation(null);
        NSNotificationCenter.defaultCenter().removeObserver(this); Bit6.setCurrentConversation(null);
    }

    /**************** Table ****************/

    @Selector("tableView:numberOfRowsInSection:")
    @NInt
    public long tableViewNumberOfRowsInSection(UITableView tableView,@NInt long section){
        return getMessages().size();
    }

    @Selector("tableView:cellForRowAtIndexPath:")
    public UITableViewCell tableViewCellForRowAtIndexPath(UITableView tableView, NSIndexPath indexPath) {
        Bit6Message message = getMessages().get((int)indexPath.row());
        return tableView.dequeueReusableCellWithIdentifier(message.incoming() ? "textInCell" : "textOutCell");
    }

    @Selector("tableView:willDisplayCell:forRowAtIndexPath:")
    public void tableViewWillDisplayCellForRowAtIndexPath(UITableView tableView, UITableViewCell cell, NSIndexPath indexPath) {
        Bit6Message message = getMessages().get((int) indexPath.row());

        UILabel textLabel = (UILabel)cell.viewWithTag(1);
        textLabel.setText(ChatsTableViewController.textContentForMessage(message));

        UILabel detailTextLabel = (UILabel)cell.viewWithTag(2);
        if (detailTextLabel != null) {
            if (message.type() == Bit6MessageType.Call) {
                detailTextLabel.setText("");
            }
            else {
                switch ((int)message.status()) {
                    case (int)Bit6MessageStatus.New : detailTextLabel.setText(""); break;
                    case (int)Bit6MessageStatus.Sending : detailTextLabel.setText("Sending"); break;
                    case (int)Bit6MessageStatus.Sent : detailTextLabel.setText("Sent"); break;
                    case (int)Bit6MessageStatus.Failed : detailTextLabel.setText("Failed"); break;
                    case (int)Bit6MessageStatus.Delivered : detailTextLabel.setText("Delivered"); break;
                    case (int)Bit6MessageStatus.Read : detailTextLabel.setText("Read"); break;
                }
            }
        }
    }

    /**************** Calls ****************/

    @Selector("call")
    public void call() {
        UIAlertController alert = UIAlertController.alertControllerWithTitleMessagePreferredStyle(null, null, ActionSheet);
        alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Audio", Default, new UIAlertAction.Block_actionWithTitleStyleHandler() {
                    public void call_actionWithTitleStyleHandler(UIAlertAction var1) {
                        Bit6.startCallToStreams(_conversation.address(), Bit6CallStreams.Audio);
                    }
                })
        );
        alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Video", Default, new UIAlertAction.Block_actionWithTitleStyleHandler() {
                    public void call_actionWithTitleStyleHandler(UIAlertAction var1) {
                        Bit6.startCallToStreams(_conversation.address(),Bit6CallStreams.Audio|Bit6CallStreams.Video);
                    }})
        );
        alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Cancel", Cancel, null));
        navigationController().presentViewControllerAnimatedCompletion(alert, true, null);
    }

    /**************** Send Text ****************/

    @Selector("touchedComposeButton:")
    private void touchedComposeButton(UIBarButtonItem button) {

        if (!canChat()) {
            UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("You have left this group", null, UIAlertControllerStyle.Alert);
            errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
            navigationController().presentViewControllerAnimatedCompletion(errorAlert,true,null);
            return;
        }

        final UIAlertController alert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Type the message", null, Alert);
        alert.addTextFieldWithConfigurationHandler(null);
        alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Send", Default, new UIAlertAction.Block_actionWithTitleStyleHandler() {
            public void call_actionWithTitleStyleHandler(UIAlertAction var1) {
                UITextField input = alert.textFields().get(0);
                String text = input.text();

                if (text.length() > 0) {
                    Bit6OutgoingMessage message = Bit6OutgoingMessage.alloc().initWithDestination(_conversation.address());
                    message.setContent(text);
                    message.sendWithCompletionHandler(new Bit6OutgoingMessage.Block_sendWithCompletionHandler() {
                        public void call_sendWithCompletionHandler(NSDictionary<?, ?> response, NSError error) {
                            if (error != null) {
                                UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Error", error.localizedDescription(), UIAlertControllerStyle.Alert);
                                errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
                                navigationController().presentViewControllerAnimatedCompletion(errorAlert, true, null);
                            } else {
                                System.out.println("Message Sent");
                            }
                        }
                    });
                }
            }
        }));
        alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Cancel", UIAlertActionStyle.Cancel, null));
        navigationController().presentViewControllerAnimatedCompletion(alert, true, null);
    }

    /**************** Typing ****************/

    @Selector("typingBeginNotification:")
    public void typingBeginNotification(NSNotification notification)
    {
        Bit6Address fromAddress = (Bit6Address)notification.userInfo().get(Bit6FromKey);
        Bit6Address convervationAddress = (Bit6Address)notification.object();
        if (convervationAddress.isEqual(_conversation.address())) {
            typingBarButtonItem().setTitle(fromAddress.uri() + "  is typing...");
        }
    }

    @Selector("typingEndNotification:")
    public void typingEndNotification(NSNotification notification)
    {
        Bit6Address convervationAddress = (Bit6Address)notification.object();
        if (convervationAddress.isEqual(_conversation.address())) {
            typingBarButtonItem().setTitle("");
        }
    }

    /**************** Data Source ****************/

    @Selector("groupsChangedNotification:")
    private void groupsChangedNotification(NSNotification notification) {
        Bit6Group object = (Bit6Group)notification.userInfo().get(Bit6ObjectKey);
        String change = (String) notification.userInfo().get(Bit6ChangeKey);

        if (change.equals(Bit6UpdatedKey) && object.address().isEqual(_conversation.address())) {
            setTitle(ConversationsViewController.titleForConversation(_conversation));
        }
    }

    @Selector("messagesChangedNotification:")
    private void messagesChangedNotification(NSNotification notification) {

        Bit6Message object = (Bit6Message) notification.userInfo().get(Bit6ObjectKey);
        String change = (String) notification.userInfo().get(Bit6ChangeKey);

        if (change.equals(Bit6AddedKey)) {
            NSIndexPath indexPath = NSIndexPath.indexPathForRowInSection(getMessages().size(),0);
            getMessages().add(object);
            NSArray<NSIndexPath> indexes = (NSArray<NSIndexPath>)NSArray.arrayWithObject(indexPath);
            tableView().insertRowsAtIndexPathsWithRowAnimation(indexes, UITableViewRowAnimation.Automatic);
            scrollToBottomAnimated(true);
        }
        else if (change.equals(Bit6UpdatedKey)) {
            NSIndexPath indexPath = findMessageIndex(object);
            if (indexPath != null) {
                UITableViewCell cell = tableView().cellForRowAtIndexPath(indexPath);
                if (cell != null) {
                    tableViewWillDisplayCellForRowAtIndexPath(tableView(),cell,indexPath);
                }
            }
        }
        else if (change.equals(Bit6DeletedKey)) {
            NSIndexPath indexPath = findMessageIndex(object);
            if (indexPath != null) {
                getMessages().removeObjectAtIndex(indexPath.row());
                NSArray<NSIndexPath> indexes = (NSArray<NSIndexPath>)NSArray.arrayWithObject(indexPath);
                tableView().deleteRowsAtIndexPathsWithRowAnimation(indexes, UITableViewRowAnimation.Automatic);
            }
        }
    }

    private NSIndexPath findMessageIndex(Bit6Message msg) {
        for (int x=getMessages().size()-1 ; x>=0 ; x--){
            if (getMessages().get(x).isEqual(msg)) {
                return NSIndexPath.indexPathForRowInSection(x, 0);
            }
        }

        return null;
    }

    private Bit6Conversation _conversation;

    public void setConversation(Bit6Conversation conv) {
        _conversation = conv;
        Bit6.setCurrentConversation(conv);
        NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this, new SEL("messagesChangedNotification:"), Bit6MessagesChangedNotification, conv);
        NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this, new SEL("typingBeginNotification:"), Bit6TypingDidBeginRtNotification, null);
        NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this, new SEL("typingEndNotification:"), Bit6TypingDidEndRtNotification, null);

        if (conv.address().isGroup()) {
            NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this, new SEL("groupsChangedNotification:"), Bit6GroupsChangedNotification, null);
        }
    }

    //creating the local messages array
    private NSMutableArray<Bit6Message> _messages;
    private NSMutableArray<Bit6Message> getMessages() {
        if (_messages == null && _conversation != null) {
            _messages = (NSMutableArray<Bit6Message>) NSMutableArray.arrayWithArray(_conversation.messages());
        }
        return _messages;
    }

    /**************** ****************/

    private void scrollToBottomAnimated(boolean animated)
    {
        int size = getMessages().size();
        if (size>0) {
            long section = 0;
            long lastRow = size - 1;
            NSIndexPath scrollIndexPath = NSIndexPath.indexPathForRowInSection(lastRow, 0);
            tableView().scrollToRowAtIndexPathAtScrollPositionAnimated(scrollIndexPath, UITableViewScrollPosition.Bottom,animated);
        }
    }

    /**************** Helpers ****************/

    //if the user has left the group we disable the chats
    private boolean canChat() {
        if (_conversation.address().isGroup()) {
            Bit6Group group = Bit6Group.groupWithAddress(_conversation.address());
            return !group.hasLeft();
        }
        return true;
    }

    //we get a nice string to show for text messages and calls
    private static String textContentForMessage(Bit6Message message) {

        if (message.type() == Bit6MessageType.Call) {
            boolean showDuration = false;

            String status = "";

            switch ((int)message.callStatus()) {
                case (int)Bit6MessageCallStatus.Answer: status = "Answer"; showDuration = true; break;
                case (int)Bit6MessageCallStatus.Missed: status = "Missed"; break;
                case (int)Bit6MessageCallStatus.Failed: status = "Failed"; break;
                case (int)Bit6MessageCallStatus.NoAnswer: status = "No Answer"; break;
            }

            NSMutableArray channels = NSMutableArray.arrayWithCapacity(3);
            if (message.callHasChannel(Bit6MessageCallChannel.Audio)){
                channels.addObject("Audio");
            }
            if (message.callHasChannel(Bit6MessageCallChannel.Video)){
                channels.addObject("Video");
            }
            if (message.callHasChannel(Bit6MessageCallChannel.Data)){
                channels.addObject("Data");
            }

            String channel = channels.componentsJoinedByString(" + ");

            if (showDuration) {
                return String.format("%s Call - %ss",channel, message.callDuration().description());
            }
            else {
                return String.format("%s Call (%s)",channel, status);
            }

        }

        else if (message.type() == Bit6MessageType.Location) {
            return "Location";
        }

        else if (message.type() == Bit6MessageType.Attachments) {
            return "Attachment";
        }

        else {
            return message.content();
        }
    }

	@Generated
	@Selector("setTypingBarButtonItem:")
	public native void setTypingBarButtonItem_unsafe(UIBarButtonItem value);

	@Generated
	public void setTypingBarButtonItem(UIBarButtonItem value) {
		Object __old = typingBarButtonItem();
		if (value != null) {
			com.intel.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setTypingBarButtonItem_unsafe(value);
		if (__old != null) {
			com.intel.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}

	@Generated
	@Selector("typingBarButtonItem")
    @Property
	public native UIBarButtonItem typingBarButtonItem();

}