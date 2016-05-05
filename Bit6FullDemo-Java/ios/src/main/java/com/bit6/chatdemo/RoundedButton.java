package com.bit6.chatdemo;


import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.ByValue;
import com.intel.moe.natj.general.ann.Generated;
import com.intel.moe.natj.general.ann.NInt;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.general.ann.Runtime;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Selector;
import ios.coregraphics.struct.CGRect;
import ios.foundation.NSCoder;
import ios.uikit.UIButton;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("RoundedButton")
@RegisterOnStartup
public class RoundedButton extends UIButton {
	static {
		NatJ.register();
	}

	@Generated
	protected RoundedButton(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native RoundedButton alloc();

	@Generated
	@Selector("buttonWithType:")
	public static native RoundedButton buttonWithType(@NInt long buttonType);

	@Generated
	@Selector("init")
	public native RoundedButton init();

	@Generated
	@Selector("initWithCoder:")
	public native RoundedButton initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithFrame:")
	public native RoundedButton initWithFrame(@ByValue CGRect frame);

	@Selector("layoutSubviews")
	public void layoutSubviews() {
		setClipsToBounds(true);
		layer().setCornerRadius(10);

        super.layoutSubviews();
	}
}