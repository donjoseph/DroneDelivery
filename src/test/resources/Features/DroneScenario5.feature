#Author: db.joseph1991@gmail.com

@scenario5
Feature: Verify error handling by the program (Non-functional)

 
 @Scenario5_testCase1
  Scenario: Verify that orders having invalid format are skipped and 
  logged separately with error message and program should continue processing the 
  remaining orders. These invalid orders should not be considered as an order for NPS calculation.
  Given get the input array with data
  |001 N11W5 05:11:50|
  |WM002 S3E2 05:11:55|
  |WM002 S3E2 05:11:5555|
  And set the output file to "Scenario5_testCase1"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario5_testCase1"
  
  
  @Scenario5_testCase2
  Scenario: Verify that orders beyond 10 pm and before permitted order place start time 
  are skipped and logged separately with error message and program should continue processing 
  the remaining orders. These invalid orders should not be considered as an order for NPS calculation.
  Given get the input array with data
  |WM001 N11W5 04:11:50|
  |WM002 S3E2 05:11:55|
  |WM003 N7E50 05:31:50|
  |WM004 N11E5 22:11:50|
  And set the output file to "Scenario5_testCase2"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario5_testCase2"
  
  
#  @Scenario5_testCase3
#  Scenario: Verify that orders having duplicate order id are skipped and logged separately
#  with error message and program should continue processing the remaining orders.
#  These invalid orders should not be considered as an order for NPS calculation.
#  Given get the input array with data
#  |WM001 N11W5 05:11:50|
#  |WM002 S3E2 05:11:55|
#  |WM003 N7E50 05:31:50|
#  |WM004 N11E5 06:11:50|
#  And set the output file to "Scenario5_testCase3"
#  And do the input validation
#  When run the scheduler
#  Then validate the output file of "Scenario5_testCase3"
#
#  @Scenario5_testCase4
#  Scenario: Verify that orders beyond city limits (assumption for better NPS) are skipped
#  and logged separately with error message and program should continue processing the
#  remaining orders. These invalid orders should not be considered as an order for NPS calculation.
#  Given get the input array with data
#  |WM001 N11W5 05:11:50|
#  |WM002 S3E2 05:11:55|
#  |WM003 N7E50 05:31:50|
#  |WM004 N11E5 06:11:50|
#  And set the output file to "Scenario5_testCase4"
#  And do the input validation
#  When run the scheduler
#  Then validate the output file of "Scenario5_testCase4"
#