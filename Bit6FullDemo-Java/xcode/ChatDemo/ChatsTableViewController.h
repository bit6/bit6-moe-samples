//
//  ChatsTableViewController.h
//  ChatDemo
//
//  Created by Carlos Thurber on 03/17/16.
//  Copyright © 2016 intel. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ChatsTableViewController : UITableViewController

@property (weak, nonatomic) IBOutlet UIBarButtonItem *typingBarButtonItem;
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section;
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath;
- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath;
- (IBAction)touchedComposeButton:(UIBarButtonItem*)sender;
- (void)viewDidLoad;
- (void)viewWillAppear:(BOOL)animated;
- (void)viewWillDisappear:(BOOL)animated;
- (void)dealloc;

@end
