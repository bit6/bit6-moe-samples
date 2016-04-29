//
//  LoginViewController.h
//  ChatDemo
//
//  Created by Carlos Thurber on 03/08/16.
//  Copyright © 2016 intel. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LoginViewController : UIViewController

@property (weak, nonatomic) IBOutlet UITextField *usernameTextField;
@property (weak, nonatomic) IBOutlet UITextField *passwordTextField;
- (IBAction)touchedLoginBarButton:(UIBarButtonItem *)sender;
- (IBAction)touchedSignUpButton:(UIBarButtonItem *)sender;
- (void)viewDidLoad;

@end
