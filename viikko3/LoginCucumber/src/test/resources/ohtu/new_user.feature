Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When  username "uusi" and password "salasana12" are entered
        Then  system will respond with "new user registered"
    
    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        And   user "vanha" with password "vanha1234" is created
        When  username "vanha" and password "salasana12" are entered
        Then  system will respond with "username is already taken"

    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When  username "uu" and password "salasana12" are entered
        Then  system will respond with "username must contain at least 3 characters"

    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When  username "uusi" and password "sana12" are entered
        Then  system will respond with "password must contain at least 8 characters"

    Scenario: creation fails with valid username and password long enough but consisting of only letters
        Given command new is selected
        When  username "uusi" and password "salasana" are entered
        Then  system will respond with "password must not consist of letters only"

    Scenario: can login with successfully generated account
        Given user "eero" with password "salainen1" is created
        And   command login is selected
        When  username "eero" and password "salainen1" are entered
        Then  system will respond with "logged in"
