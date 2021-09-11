Feature: Acceptance Tests for the Simple Calulator API 
Description: The purpose of these tests is to cover the acceptance tests scenario for the Simple Calculator API
	
	Scenario: Calculator is able to perform addition
		When I want to add 10 and 15
		Then Addition of 10 and 15 should be 25
		
	Scenario: Calculator is able to perform subtraction
		When I want to subtract 15 and 10
		Then Subtraction of 15 and 10 should be 5
		
	Scenario: Calculator is able to perform multiplication
		When I want to multiply 15 and 10
		Then Multiplication of 15 and 10 should be 150
		
	Scenario: Calculator is able to perform division
		When I want to divide 20 and 10
		Then Division of 20 and 10 should be 2
		
	Scenario: Calculator is not able to perform division by zero
		When I want to divide 20 and 0
		Then Division of 20 and 0 should fail