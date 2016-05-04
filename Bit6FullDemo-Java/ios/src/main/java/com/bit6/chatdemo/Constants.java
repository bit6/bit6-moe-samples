package com.bit6.chatdemo;

import com.intel.inde.moe.natj.objc.SEL;

import ios.foundation.NSNotificationCenter;

/**
 * Created by carlosthurberb on 03/16/16.
 */
public interface Constants {

    public static final String Bit6GroupsChangedNotification = "Bit6GroupsChangedNotification";
    public static final String Bit6ConversationsChangedNotification = "Bit6ConversationsChangedNotification";
    public static final String Bit6MessagesChangedNotification = "Bit6MessagesChangedNotification";
    public static final String Bit6IncomingCallNotification = "Bit6IncomingCallNotification";
    public static final String Bit6CallAddedNotification = "Bit6CallAddedNotification";
    public static final String Bit6CallPermissionsMissingNotification = "Bit6CallPermissionsMissingNotification";
    public static final String Bit6CallMissedNotification = "Bit6CallMissedNotification";
    public static final String Bit6TypingDidBeginRtNotification = "Bit6TypingDidBeginRtNotification";
    public static final String Bit6TypingDidEndRtNotification = "Bit6TypingDidEndRtNotification";



    public static final String Bit6ObjectKey = "object";
    public static final String Bit6ChangeKey = "change";
    public static final String Bit6AddedKey = "added";
    public static final String Bit6UpdatedKey = "updated";
    public static final String Bit6DeletedKey = "deleted";
    public static final String Bit6FromKey = "from";

    public static final String Bit6ErrorKey = "error";


    public static final String Bit6GroupMemberRole_User = "user";

}
