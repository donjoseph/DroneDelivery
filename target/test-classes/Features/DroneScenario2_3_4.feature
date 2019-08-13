#Author: db.joseph1991@gmail.com

@scenario2
Feature: Verify the working time of the drone is between 6 am and 10 pm

 
 @Scenario2_testCase1
  Scenario: Verify that the program gives the most efficient schedule and NPS for 
  valid orders and delivery should be starting at 6 am only.
  Given get the input array with data
  |WM001 N11W5 05:11:50|
  |WM002 S3E2 05:11:55|
  |WM003 N7E50 05:31:50|
  |WM004 N11E5 06:11:50|
  And set the output file to "Scenario2_testCase1"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario2_testCase1"
  
  
  @Scenario2_testCase2
  Scenario: Verify that the program gives the most efficient schedule and NPS for 
  valid orders which are received before 6 am and after the permitted order receive 
  start time(receive start time is an assumption for better NPS)
  Given get the input array with data
  |WM001 N11W5 05:11:50|
  |WM002 S3E2 05:11:55|
  |WM003 N7E50 05:31:50|
  |WM004 N11E5 06:11:50|
  And set the output file to "Scenario2_testCase2"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario2_testCase2"
  
  
  @Scenario2_testCase3
  Scenario: Verify that the program gives the most efficient schedule and NPS for 
  valid orders and calculated order delivery time and return time of the drone for 
  all the orders should be before 10 pm.
  Given get the input array with data
  |WM001 N11W5 05:11:50|
  |WM002 S3E2 05:11:55|
  |WM003 N7E50 05:31:50|
  |WM004 N11E5 06:11:50|
  And set the output file to "Scenario2_testCase3"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario2_testCase3"
  
  @Scenario2_testCase4
  Scenario: Verify that the program gives the most efficient schedule and NPS for 
  orders in which orders placed after 10 pm should not be processed.
  Given get the input array with data
  |WM001 N11W5 05:11:50|
  |WM002 S3E2 05:11:55|
  |WM003 N7E50 05:31:50|
  |WM004 N11E5 22:11:50|
  And set the output file to "Scenario2_testCase4"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario2_testCase4"
  
  @Scenario2_testCase5
  Scenario: Verify that the program gives the most efficient schedule and NPS for 
  orders in which orders having calculated order delivery time or return time 
  beyond 10 pm should not be processed.
  Given get the input array with data
  |WM001 N11W5 05:11:50|
  |WM002 S3E2 05:11:55|
  |WM003 N7E50 05:31:50|
  |WM004 N50E10 21:11:50|
  And set the output file to "Scenario2_testCase5"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario2_testCase5"
  
  
  @Scenario2_testCase6
  Scenario: Verify that the program gives the most efficient schedule and NPS for
  valid orders in which drone return time of one of the order is exactly 10 pm.
  Given get the input array with data
  |WM001 N11W5 05:11:50|
  |WM002 S3E2 05:11:55|
  |WM003 N7E50 05:31:50|
  |WM004 N4E3 21:50:00|
  And set the output file to "Scenario2_testCase6"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario2_testCase6"