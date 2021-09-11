Feature: Acceptance Tests for the Simple Calulator API 
Description: The purpose of these tests is to cover the acceptance tests scenario for the Simple Calculator API
	
	Scenario: Calculator is able to perform addition
		When I want to add two numbers
		Then Two numbers are added
		
	Scenario: Calculator is able to perform subtraction
		When I want to subtract two numbers
		Then Two numbers are subtracted
		
	Scenario: Calculator is able to perform multiplication
		When I want to multiply two numbers
		Then Two numbers are multiplied
		
	Scenario: Calculator is able to perform division
		When I want to add dvide numbers
		Then Two numbers are divided