package com.bit6.chatdemo;


import com.intel.bit6.Bit6;
import com.intel.bit6.Bit6Address;
import com.intel.bit6.Bit6CallController;
import com.intel.bit6.Bit6CallViewController;
import com.intel.bit6.Bit6Group;
import com.intel.bit6.Bit6GroupMember;
import com.intel.bit6.Bit6OutgoingMessage;
import com.intel.bit6.Bit6Session;
import com.intel.bit6.enums.Bit6CallStreams;
import com.intel.bit6.enums.Bit6MessageFileType;
import com.intel.bit6.enums.Bit6MessageType;
import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Generated;
import com.intel.moe.natj.general.ann.NInt;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.general.ann.Runtime;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Selector;

import ios.NSObject;
import ios.foundation.NSBundle;
import ios.foundation.NSCoder;
import ios.foundation.NSDictionary;
import ios.foundation.NSError;
import ios.foundation.NSIndexPath;
import ios.foundation.NSMutableString;
import ios.foundation.NSNotification;
import ios.foundation.NSNotificationCenter;
import ios.foundation.enums.NSComparisonResult;
import ios.foundation.struct.NSRange;
import ios.uikit.UIAlertAction;
import ios.uikit.UIAlertController;
import ios.uikit.UIBarButtonItem;
import ios.uikit.UITableView;
import ios.uikit.UITableViewCell;
import ios.uikit.UITableViewController;
import ios.uikit.UITextField;
import ios.uikit.enums.UIAlertActionStyle;
import ios.uikit.enums.UIAlertControllerStyle;
import ios.uikit.enums.UIBarButtonItemStyle;
import ios.uikit.enums.UIBarButtonSystemItem;
import ios.foundation.NSMutableArray;
import ios.foundation.NSArray;
import ios.foundation.NSNumber;
import ios.uikit.UILabel;
import ios.foundation.NSString;
import ios.uikit.enums.UITableViewCellEditingStyle;
import ios.uikit.enums.UITableViewRowAnimation;

import com.intel.moe.natj.objc.SEL;

import com.intel.bit6.Bit6Conversation;
import com.intel.bit6.Bit6Message;

import static ios.uikit.enums.UIBarButtonItemStyle.*;
import static ios.uikit.enums.UIBarButtonSystemItem.*;
import static ios.uikit.enums.UIAlertControllerStyle.*;
import static ios.uikit.enums.UIAlertActionStyle.*;

import static com.bit6.chatdemo.Constants.*;
import static com.intel.bit6.enums.Bit6MessageType.*;
import com.intel.moe.natj.general.ann.Mapped;
import com.intel.moe.natj.objc.map.ObjCObjectMapper;
import ios.uikit.UIStoryboardSegue;
import com.intel.moe.natj.general.ann.ByValue;
import com.intel.moe.natj.general.ann.NFloat;
import com.intel.moe.natj.general.ann.UncertainArgument;
import com.intel.moe.natj.objc.ann.IsOptional;
import ios.coregraphics.struct.CGPoint;
import ios.uikit.UIFocusAnimationCoordinator;
import ios.uikit.UIScrollView;
import ios.uikit.UITableViewFocusUpdateContext;
import ios.uikit.UITableViewRowAction;
import ios.uikit.UIView;
import ios.uikit.UIViewController;
import ios.uikit.protocol.UITableViewDataSource;
import ios.uikit.protocol.UITableViewDelegate;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("ConversationsViewController")
@RegisterOnStartup
public class ConversationsViewController extends UIViewController implements UITableViewDataSource, UITableViewDelegate {
	static {
		NatJ.register();
	}

	@Generated
	protected ConversationsViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native ConversationsViewController alloc();

	@Generated
	@Selector("init")
	public native ConversationsViewController init();

	@Generated
	@Selector("initWithCoder:")
	public native ConversationsViewController initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithNibName:bundle:")
	public native ConversationsViewController initWithNibNameBundle(String nibNameOrNil, NSBundle nibBundleOrNil);

	@Selector("dealloc")
    public void dealloc()
    {
        NSNotificationCenter.defaultCenter().removeObserver(this);
    }

    @Selector("viewDidLoad")
    public void viewDidLoad()
    {
        navigationItem().setBackBarButtonItem(UIBarButtonItem.alloc().initWithTitleStyleTargetAction("Back", Plain, null, null));

        navigationItem().setPrompt("Logged as " + Bit6.session().activeIdentity().uri());
    }

    @Selector("viewWillAppear:")
    public void viewWillAppear(boolean animated)
    {
        super.viewWillAppear(animated);

        navigationController().setToolbarHiddenAnimated(true, true);
    }

    /**************** Actions ****************/

    @Selector("touchedAddButton:")
    private void touchedAddButton(UIBarButtonItem sender)
    {
        final UIAlertController alert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Type the destination username, or type several usernames separated by comma to create a group conversation", null, Alert);
        alert.addTextFieldWithConfigurationHandler(null);
        alert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", Default, new UIAlertAction.Block_actionWithTitleStyleHandler() {
            public void call_actionWithTitleStyleHandler(UIAlertAction var1) {

                UITextField input = alert.textFields().get(0);
                String text = input.text();

                if (text.length() > 0) {
                    String[] destinations = text.split(",");

                    //Creating Single Conversation
                    if (destinations.length == 1) {
                        Bit6Address address = Bit6Address.addressWithUsername(destinations[0]);
                        if (address != null) {
                            Bit6Conversation conv = Bit6Conversation.conversationWithAddress(address);
                            Bit6.addConversation(conv);
                        } else {
                            UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Invalid username", null, UIAlertControllerStyle.Alert);
                            errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
                            navigationController().presentViewControllerAnimatedCompletion(errorAlert, true, null);
                        }
                    }

                    //Creating Group Conversation
                    else {

                        final NSMutableArray<Bit6Address> addresses = (NSMutableArray<Bit6Address>) NSMutableArray.arrayWithCapacity(destinations.length);
                        final NSMutableArray<String> roles = (NSMutableArray<String>) NSMutableArray.arrayWithCapacity(destinations.length);

                        for (String destination : destinations) {
                            Bit6Address address = Bit6Address.addressWithUsername(destination);
                            if (address != null) {
                                addresses.add(address);
                                roles.add(Bit6GroupMemberRole_User);
                            }
                        }

                        Bit6Group.createGroupWithMetadataCompletion(null, new Bit6Group.Block_createGroupWithMetadataCompletion() {
                            public void call_createGroupWithMetadataCompletion(Bit6Group group, NSError error) {
                                if (error == null) {

                                    //inviting users to the group
                                    group.inviteGroupMembersWithAddressesRolesCompletion(addresses, roles, new Bit6Group.Block_inviteGroupMembersWithAddressesRolesCompletion() {
                                        public void call_inviteGroupMembersWithAddressesRolesCompletion(NSArray<? extends Bit6GroupMember> members, NSError error2) {
                                            if (error2 != null) {
                                                UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Failed to invite users to the group", null, Alert);
                                                errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
                                                navigationController().presentViewControllerAnimatedCompletion(errorAlert, true, null);
                                            }
                                        }
                                    });
                                } else {
                                    UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("Failed to create the Group", null, Alert);
                                    errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
                                    navigationController().presentViewControllerAnimatedCompletion(errorAlert, true, null);
                                }
                            }
                        });

                    }
                }

            }
        }));
        alert.addAction(UIAlertAction.actionWithTitleStyleHandler("Cancel", UIAlertActionStyle.Cancel, null));
        navigationController().presentViewControllerAnimatedCompletion(alert, true, null);

    }

    @Selector("touchedLogoutBarButton:")
    private void touchedLogoutBarButton(UIBarButtonItem sender)
    {
        Bit6.session().logoutWithCompletionHandler(new Bit6Session.Block_logoutWithCompletionHandler() {
            public void call_logoutWithCompletionHandler(NSDictionary<?, ?> response, NSError error) {
                if (error != null) {
                    UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("ERROR", error.localizedDescription(), Alert);
                    errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
                    navigationController().presentViewControllerAnimatedCompletion(errorAlert, true, null);
                } else {
                    navigationController().popViewControllerAnimated(true);
                }
            }
        });
    }

    /**************** Table ****************/

	@Selector("tableView:cellForRowAtIndexPath:")
	public UITableViewCell tableViewCellForRowAtIndexPath(UITableView tableView, NSIndexPath indexPath){
        UITableViewCell cell = tableView.dequeueReusableCellWithIdentifier("conversationCell");
        return cell;
    }

	@Selector("tableView:numberOfRowsInSection:")
	@NInt
	public long tableViewNumberOfRowsInSection(UITableView tableView, @NInt long section) {
        return getConversations().count();
    }

    @Selector("tableView:willDisplayCell:forRowAtIndexPath:")
    public void tableViewWillDisplayCellForRowAtIndexPath(UITableView tableView, UITableViewCell cell, NSIndexPath indexPath){
        Bit6Conversation conversation = getConversations().get((int) indexPath.row());

        UILabel textLabel = (UILabel)cell.viewWithTag(1);
        UILabel detailTextLabel = (UILabel)cell.viewWithTag(2);

        Bit6Message lastMessage = conversation.messages().lastObject();
        NSNumber badge = conversation.badge();
        detailTextLabel.setText(ConversationsViewController.contentStringForMessage(lastMessage));


        String title = ConversationsViewController.titleForConversation(conversation);
        if (conversation.address().isGroup()) {
            Bit6Group group = Bit6Group.groupWithConversation(conversation);
            if (group.hasLeft()) {
                detailTextLabel.setText("You have left this group");
            }
        }

        textLabel.setText(title + (badge.intValue()!=0 ? (" (" + badge.description() + ")") : "") );
    }

    @Selector("tableView:commitEditingStyle:forRowAtIndexPath:")
    public void tableViewCommitEditingStyleForRowAtIndexPath(UITableView uiTableView, @NInt long editingStyle, NSIndexPath nsIndexPath) {

        if (editingStyle == UITableViewCellEditingStyle.Delete) {
            Bit6Conversation conversation = getConversations().get((int)nsIndexPath.row());
            Bit6Group group = Bit6Group.groupWithConversation(conversation);
            if (group != null && !group.hasLeft()) {
                group.leaveGroupWithCompletion( new Bit6Group.Block_leaveGroupWithCompletion() {
                    public void call_leaveGroupWithCompletion(NSError error) {
                        if (error != null) {
                            UIAlertController errorAlert = UIAlertController.alertControllerWithTitleMessagePreferredStyle("ERROR", error.localizedDescription(), Alert);
                            errorAlert.addAction(UIAlertAction.actionWithTitleStyleHandler("OK", UIAlertActionStyle.Cancel, null));
                            navigationController().presentViewControllerAnimatedCompletion(errorAlert,true,null);
                        }
                    }
                });
                return;
            }

            Bit6.deleteConversationCompletion(conversation,null);;
        }

    }

    @Selector("tableView:titleForDeleteConfirmationButtonForRowAtIndexPath:")
    public String tableViewTitleForDeleteConfirmationButtonForRowAtIndexPath(UITableView uiTableView, NSIndexPath nsIndexPath) {

        Bit6Conversation conversation = getConversations().get((int)nsIndexPath.row());
        Bit6Group group = Bit6Group.groupWithConversation(conversation);
        if (group != null) {
            //for group conversations we show "Leave" if the user is still part of the group, and "Delete" if not
            return group.hasLeft()?"Delete":"Leave";
        }
        else {
            return "Delete";
        }

    }

    /**************** Navigation ****************/

    //entering to a conversation
    @Selector("prepareForSegue:sender:")
    public void prepareForSegueSender(UIStoryboardSegue segue, @Mapped(ObjCObjectMapper.class) Object sender){
        if (segue.identifier().equals("showChats")) {
            ChatsTableViewController mltvc = (ChatsTableViewController)segue.destinationViewController();
            NSIndexPath indexPath = tableView().indexPathForCell((UITableViewCell)sender);
            Bit6Conversation conversation = getConversations().get((int) indexPath.row());
            mltvc.setConversation(conversation);
            mltvc.setTitle(ConversationsViewController.titleForConversation(conversation));
            tableView().deselectRowAtIndexPathAnimated(indexPath,true);
        }
    }

    /**************** Data Source ****************/

    @Selector("groupsChangedNotification:")
    private void groupsChangedNotification(NSNotification notification) {
        Bit6Group object = (Bit6Group)notification.userInfo().get(Bit6ObjectKey);
        String change = (String)notification.userInfo().get(Bit6ChangeKey);

        if (change.equals(Bit6UpdatedKey)) {
            NSIndexPath indexPath = findConversationIndex(Bit6Conversation.conversationWithAddress(object.address()));
            NSArray<NSIndexPath> indexes = (NSArray<NSIndexPath>)NSArray.arrayWithObject(indexPath);
            tableView().reloadRowsAtIndexPathsWithRowAnimation(indexes, UITableViewRowAnimation.Automatic);
        }
    }

    @Selector("conversationsChangedNotification:")
    private void conversationsChangedNotification(NSNotification notification) {
        Bit6Conversation object = (Bit6Conversation)notification.userInfo().get(Bit6ObjectKey);
        String change = (String)notification.userInfo().get(Bit6ChangeKey);

        //a new conversation has been added
        if (change.equals(Bit6AddedKey)) {
            NSIndexPath indexPath = NSIndexPath.indexPathForRowInSection(0,0);
            getConversations().insertObjectAtIndex(object, 0);
            NSArray<NSIndexPath> indexes = (NSArray<NSIndexPath>)NSArray.arrayWithObject(indexPath);
            tableView().insertRowsAtIndexPathsWithRowAnimation(indexes, UITableViewRowAnimation.Automatic);
        }
        //a conversation has changed
        else if (change.equals(Bit6UpdatedKey)) {
            NSIndexPath indexPath = findConversationIndex(object);
            if (indexPath != null) {
                if (indexPath.row() != 0) {
                    Bit6Conversation firstConversation = getConversations().get(0);
                    Bit6Conversation modifiedConversation = getConversations().get((int)indexPath.row());
                    if (firstConversation.compare(modifiedConversation) == NSComparisonResult.Descending) {
                        getConversations().removeObjectAtIndex(indexPath.row());
                        getConversations().insertObjectAtIndex(object,0);
                        tableView().moveRowAtIndexPathToIndexPath(indexPath, NSIndexPath.indexPathForRowInSection(0, 0));
                    }
                    else {
                        NSArray<NSIndexPath> indexes = (NSArray<NSIndexPath>)NSArray.arrayWithObject(indexPath);
                        tableView().reloadRowsAtIndexPathsWithRowAnimation(indexes,UITableViewRowAnimation.Automatic);
                    }
                }
                else {
                    NSArray<NSIndexPath> indexes = (NSArray<NSIndexPath>)NSArray.arrayWithObject(indexPath);
                    tableView().reloadRowsAtIndexPathsWithRowAnimation(indexes,UITableViewRowAnimation.Automatic);
                }
            }
        }
        //a conversation has been deleted
        else if (change.equals(Bit6DeletedKey)) {
            NSIndexPath indexPath = findConversationIndex(object);
            if (indexPath != null) {
                getConversations().removeObjectAtIndex(indexPath.row());
                NSArray<NSIndexPath> indexes = (NSArray<NSIndexPath>)NSArray.arrayWithObject(indexPath);
                tableView().deleteRowsAtIndexPathsWithRowAnimation(indexes,UITableViewRowAnimation.Automatic);
            }
        }

    }

    private NSIndexPath findConversationIndex(Bit6Conversation conv) {
        for (int x=getConversations().size()-1 ; x>=0 ; x--){
            if (getConversations().get(x).isEqual(conv)) {
                return NSIndexPath.indexPathForRowInSection(x, 0);
            }
        }

        return null;
    }

    //create the local conversations array
    private NSMutableArray<Bit6Conversation> _conversations;
	private NSMutableArray<Bit6Conversation> getConversations() {
        if (_conversations == null) {
            try {
                Class.forName(Bit6Conversation.class.getName());
                Class.forName(Bit6Message.class.getName());
                Class.forName(Bit6Group.class.getName());
                Class.forName(Bit6GroupMember.class.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this,new SEL("conversationsChangedNotification:"),Bit6ConversationsChangedNotification,null);
            NSNotificationCenter.defaultCenter().addObserverSelectorNameObject(this, new SEL("groupsChangedNotification:"), Bit6GroupsChangedNotification, null);
            NSArray convs = Bit6.conversations();
            _conversations = NSMutableArray.arrayWithArray(convs);
        }
        return _conversations;
    }

    /**************** Helpers ****************/

    public static String contentStringForMessage(Bit6Message message) {
        if (message == null) {
            return null;
        }
        else if (message.type() == Bit6MessageType.Text){
            return message.content();
        }
        else if (message.type() == Bit6MessageType.Call){
            return "Call";
        }
        else if (message.type() == Bit6MessageType.Location) {
            return "Location";
        }
        else {
            if (message.attachFileType() == Bit6MessageFileType.Audio) {
                return "Recording";
            }
            else if (message.attachFileType() == Bit6MessageFileType.Video) {
                return "Video";
            }
            else {
                return "Photo";
            }
        }
    }

    public static String titleForConversation(Bit6Conversation conversation) {
        if (conversation.address().isGroup()) {
            Bit6Group group = Bit6Group.groupWithConversation(conversation);

            NSDictionary<? extends String, ?> metadata = group.metadata();
            if (metadata != null) {
                String title = (String) metadata.get("title");
                if (title != null && title.length() > 0) {
                    return title;
                }
            }

            NSArray members = group.members();

            if (members != null) {
                NSArray<Bit6Address> array = (NSArray<Bit6Address>)members.valueForKey("address");
                NSMutableArray<Bit6Address> membersAddresses = (NSMutableArray<Bit6Address>)NSMutableArray.arrayWithArray(array);
                membersAddresses.removeObject(Bit6.session().activeIdentity());

                if (membersAddresses.count() > 1) {
                    NSMutableString string = NSMutableString.stringWithCapacity(60);
                    for (Bit6Address address : membersAddresses) {
                        string.appendFormat("%@, ",address.uri());
                    }
                    string.deleteCharactersInRange(new NSRange(string.length()-2,2));
                    return string.description();
                }
            }

            return "Group";


        }

        return conversation.address().uri();
    }

	@Generated
	@Selector("setTableView:")
	public native void setTableView_unsafe(UITableView value);

	@Generated
	public void setTableView(UITableView value) {
		Object __old = tableView();
		if (value != null) {
			com.intel.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setTableView_unsafe(value);
		if (__old != null) {
			com.intel.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}

	@Generated
	@Selector("tableView")
	public native UITableView tableView();

}