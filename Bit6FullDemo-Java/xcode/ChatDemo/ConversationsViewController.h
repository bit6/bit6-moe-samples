//
//  ConversationListTableViewController.h
//  ChatDemo
//
//  Created by Carlos Thurber on 03/13/16.
//  Copyright © 2016 intel. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ConversationsViewController : UIViewController <UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section;
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath;
- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath;
- (IBAction)touchedAddButton:(UIBarButtonItem*)sender;
- (IBAction)touchedLogoutBarButton:(id)sender;
- (void)viewDidLoad;
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender;
- (void)dealloc;

@end
