#Author: db.joseph1991@gmail.com

@scenario1
Feature: Verify that the program gives the most efficient schedule and NPS for 
valid orders in the input array


 @Scenario1_testCase1
  Scenario: Verify that the program gives the most efficient schedule and NPS for
  valid orders of different order received time and different customer coordinates
  Given get the input array with data
  |WM001 N11W5 05:11:50|
  |WM002 S3E2 05:11:55|
  |WM003 N7E5 05:31:50|
  |WM004 N11E5 06:11:50|
  And set the output file to "Scenario1_testCase1"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario1_testCase1"

  @Scenario1_testCase2
  Scenario: Verify that the program gives the most efficient schedule and NPS for
  valid orders of same order received time and different customer coordinates
  Given get the input array with data
  |WM001 N11W5 06:11:50|
  |WM002 S3E2 06:11:50|
  |WM003 N7E14 06:11:50|
  |WM004 N12E5 06:11:50|
  And set the output file to "Scenario1_testCase2"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario1_testCase2"

  @Scenario1_testCase3
  Scenario: Verify that the program gives the most efficient schedule and NPS for
  valid orders of same order received time and same customer coordinates
  Given get the input array with data
  |WM001 N2W5 06:11:50|
  |WM002 S2E5 06:11:50|
  |WM003 N2E5 06:11:50|
  |WM004 N5E2 06:11:50|
  And set the output file to "Scenario1_testCase3"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario1_testCase3"


  @Scenario1_testCase4
  Scenario: Verify that the program gives the most efficient schedule and NPS for
  valid orders in which there is a huge time gap between each orders
  Given get the input array with data
  |WM001 N2W5 05:11:50|
  |WM002 S2E5 07:11:50|
  |WM003 N2E5 09:11:50|
  |WM004 N5E2 12:11:50|
  And set the output file to "Scenario1_testCase4"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario1_testCase4"

  @Scenario1_testCase5
  Scenario: Verify that the program gives the most efficient schedule and NPS for
  valid orders in which there is at least one order which will be a neutral customer
  and the rest promoters.
  Given get the input array with data
  |WM001 N1W5 05:11:50|
  |WM002 S50E30 05:12:50|
  |WM003 N2E12 08:11:50|
  |WM004 N15E24 10:11:50|
  And set the output file to "Scenario1_testCase5"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario1_testCase5"

  @Scenario1_testCase6
  Scenario: Verify that the program gives the most efficient schedule and NPS for
  valid orders in which there is at least one order which will be a detractor customer
  and the rest promoters.
  Given get the input array with data
  |WM005 N12E14 05:08:50|
  |WM004 N50E20 06:08:50|
  |WM001 N90W180 06:09:50|
  |WM002 S2E4 16:12:50|
  And set the output file to "Scenario1_testCase6"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario1_testCase6"

  @Scenario1_testCase7
  Scenario: Verify that the program gives the most efficient schedule and NPS for
  valid orders in which there is at least one order each which will be a detractor customer
  and a neutral customer and the rest promoters.
  Given get the input array with data
  |WM001 N1W5 06:11:50|
  |WM002 S50E110 07:11:50|
  |WM003 N200E50 07:12:50|
  |WM004 N14E12 19:11:50|
  And set the output file to "Scenario1_testCase7"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario1_testCase7"
  
  @Scenario1_testCase8
  Scenario: Verify that the program gives the most efficient schedule and NPS for 
  valid orders in which there is all orders are detractors – worst case(NPS=-100)
  Given get the input array with data
  |WM001 N150W300 06:10:50|
  |WM002 S96E24 06:12:50|
  |WM003 N75E57 06:13:50|
  |WM004 N35E56 06:14:50|
  And set the output file to "Scenario1_testCase8"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario1_testCase8"
  
  @Scenario1_testCase9
  Scenario: Verify that the program gives the most efficient schedule and NPS for 
  valid orders in which all orders are neutral (NPS=0)
  Given get the input array with data
  |WM001 N90W10 06:10:50|
  |WM002 S10E90 09:12:50|
  |WM003 N90E10 12:13:50|
  |WM004 N10E90 15:11:50|
  And set the output file to "Scenario1_testCase9"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario1_testCase9"
  
  @Scenario1_testCase10
  Scenario: Verify that the program gives the most efficient schedule and NPS for 
  valid orders in which all orders are promoters(NPS=100)
  Given get the input array with data
  |WM001 N10W11 06:03:50|
  |WM002 S2E4 06:12:50|
  |WM003 N2E3 06:13:50|
  |WM004 N4E2 09:11:50|
  And set the output file to "Scenario1_testCase10"
  And do the input validation
  When run the scheduler
  Then validate the output file of "Scenario1_testCase10"