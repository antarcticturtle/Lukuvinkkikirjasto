#COMMENTED BECAUSE THE FINAL OUTPUT IS STILL CHANGING

#Feature: As a user I want to add, edit, delete, modify and list items
#
#    Scenario: user can list when there are no items added yet
#        Given command "list" is entered
#        When user does nothing
#        Then system will respond with "No items added yet"
#
#    Scenario: user can list one item
#        Given command "list" is entered
#        And item "Title" "Author" exists in the application
#        When user does nothing
#        Then system will respond with "Title by Author"
#
#    Scenario: user can list multiple items
#        Given command "list" is entered
#        And item "Book1" "Author1" exists in the application
#        And item "Book2" "Author1" exists in the application
#        And item "Book3" "Author2" exists in the application
#        And item "Book4" "Author3" exists in the application
#        When user does nothing
#        Then system will respond with "Book1 by Author1"
#        Then system will respond with "Book2 by Author1"
#        Then system will respond with "Book3 by Author2"
#        Then system will respond with "Book4 by Author3"
#
#    Scenario: user can add an item
#        Given command "new" is entered
#        When item "NewBook" "SomeAuthor" is added
#        And items are listed
#        And user does nothing
#        Then system will respond with "NewBook by SomeAuthor"
#
#    Scenario: user can add an item with additional information
#        Given command "new" is entered
#        When item "NewBook" "SomeAuthor" with additional information "url" "isbn" "tag" is added
#        And items are listed
#        And user does nothing
#        Then system will respond with "NewBook by SomeAuthor"    