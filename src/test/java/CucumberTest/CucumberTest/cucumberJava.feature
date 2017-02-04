Feature: User Login

@e2e
Scenario: Registered User is able to login
	Given I have open the browser and navigate to the main page
	When I have entered my login and password
	Then I will successfully logged in