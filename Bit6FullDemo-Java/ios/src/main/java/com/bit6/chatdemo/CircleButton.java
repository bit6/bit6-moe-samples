package com.bit6.chatdemo;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.NInt;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.RegisterOnStartup;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.ObjCClassName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.coregraphics.struct.CGRect;
import ios.foundation.NSCoder;
import ios.uikit.UIButton;
import ios.uikit.UIColor;

@Generated
@Runtime(ObjCRuntime.class)
@ObjCClassName("CircleButton")
@RegisterOnStartup
public class CircleButton extends UIButton {
	static {
		NatJ.register();
	}

	@Generated
	protected CircleButton(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native CircleButton alloc();

	@Generated
	@Selector("buttonWithType:")
	public static native CircleButton buttonWithType(@NInt long buttonType);

	@Generated
	@Selector("init")
	public native CircleButton init();

	@Generated
	@Selector("initWithCoder:")
	public native CircleButton initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithFrame:")
	public native CircleButton initWithFrame(@ByValue CGRect frame);

	@Selector("layoutSubviews")
	public void layoutSubviews() {
		setClipsToBounds(true);
		layer().setCornerRadius(frame().size().width()/2.0);

		super.layoutSubviews();
	}

    @Selector("setEnabled:")
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        refreshColor();
    }

    @Selector("setEnabled:")
    public void setHighlighted(boolean enabled) {
        super.setHighlighted(enabled);
        refreshColor();
    }

    public void refreshColor() {
        if (on()) {
            setBackgroundColor( UIColor.colorWithRedGreenBlueAlpha(216/255.0,236/255.0,255/255.0,1.0) );
        }
        else if (isHighlighted()) {
            setBackgroundColor( UIColor.colorWithRedGreenBlueAlpha(216/255.0,236/255.0,255/255.0,1.0) );
        }
        else if (! isEnabled()) {
            setBackgroundColor(UIColor.grayColor());
        }
        else {
            setBackgroundColor( UIColor.whiteColor() );
        }
    }

    private boolean on;

    @Selector("on")
    public boolean on(){
        return on;
    }

    @Selector("setOn:")
    public void setOn(boolean value) {
        on = value;
        refreshColor();
    }
}