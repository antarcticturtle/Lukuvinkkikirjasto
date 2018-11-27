Feature: As a user I want to list items

    Scenario: user can list when there are no items added yet
        Given command "list" is entered
        When user does nothing
        Then system will respond with "No items added yet"
    
    Scenario: user can list one item
        Given command "list" is entered
        And item "Title" "Author" "url" exists in the application
        When user does nothing
        Then system will respond with "(id: 1) Book: Title by Author Url: url"

    Scenario: user can list multiple items
        Given command "list" is entered
        And item "Book1" "Author1" "url" exists in the application
        And item "Book2" "Author1" "url" exists in the application
        And item "Book3" "Author2" "url" exists in the application
        And item "Book4" "Author3" "url2" exists in the application
        When user does nothing
        Then system will respond with "(id: 1) Book: Book1 by Author1 Url: url"
        Then system will respond with "(id: 2) Book: Book2 by Author1 Url: url"
        Then system will respond with "(id: 3) Book: Book3 by Author2 Url: url"
        Then system will respond with "(id: 4) Book: Book4 by Author3 Url: url2"