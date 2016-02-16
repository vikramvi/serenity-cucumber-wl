Feature: Wunderlist log-in page should not allow users to login with incorrect info
         It should show relevant info about invalid login info to user
         
Scenario: User tries to log-in with wrong password
  Given User is on log-in page
  When  enters correct email id
  And   enters wrong password
  And   clicks on Sign In button
  Then  User is shown error message For Providing Wrong Login Info
 
Scenario: User tries to log-in with wrong email id
  Given User is on log-in page
  When  enters wrong email id
  And   enters correct password
  And   clicks on Sign In button
  Then  User is shown error message For Providing Wrong Login Info
  
         