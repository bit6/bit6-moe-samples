//
//  BXUCallViewController.h
//  Bit6
//
//  Created by Carlos Thurber on 01/05/15.
//  Copyright (c) 2015 Bit6. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MyCallViewController : Bit6CallViewController

@property (weak, nonatomic) IBOutlet UIView *overlayView;
@property (weak, nonatomic) IBOutlet UIView *controlsView;

@property (weak, nonatomic) IBOutlet UILabel *usernameLabel;
@property (weak, nonatomic) IBOutlet UILabel *timerLabel;

@property (weak, nonatomic) IBOutlet CircleButton *muteAudioButton;
@property (weak, nonatomic) IBOutlet CircleButton *bluetoothButton;
@property (weak, nonatomic) IBOutlet CircleButton *speakerButton;
@property (weak, nonatomic) IBOutlet CircleButton *cameraButton;

@end