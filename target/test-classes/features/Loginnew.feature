Feature: Login to Atlas Portal application 

Scenario: login to application (positive)

Given goto login page
And Enter Username
And Enter Password 
When Click login button 
Then check login success