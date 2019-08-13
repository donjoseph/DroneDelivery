#Author: db.joseph1991@gmail.com

@tag
Feature: Verify that the program gives the most efficient schedule and NPS for 
valid orders in the input file

 @tag1
  Scenario Outline: Verify that the program gives the most efficient schedule 
  and NPS for valid orders of different order received time and different customer coordinates
  Given get the input file "<input_files>"
  And do the input validation
  When run the scheduler
  Then validate the output file of "<input_files>"
  
 Examples:
 |input_files|
 |input.txt|
 |input1.txt|
 |input3.txt|
 |input2.txt|