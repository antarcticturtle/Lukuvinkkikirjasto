Feature: As a user I want to add, edit, delete, modify and list items

    Scenario: user can list when there are no items added yet
        Given command "list" is entered
        When user does nothing
        Then system will respond with "No items added yet"

    Scenario: user can list one item
        Given command "list" is entered
        And item "Title" "Author" "url" exists in the application
        When user does nothing
        Then system will respond with "Book: Title by Author url"

    Scenario: user can list multiple items
        Given command "list" is entered
        And item "Book1" "Author1" "url" exists in the application
        And item "Book2" "Author1" "url" exists in the application
        And item "Book3" "Author2" "url" exists in the application
        And item "Book4" "Author3" "url2" exists in the application
        When user does nothing
        Then system will respond with "Book: Book1 by Author1 url"
        Then system will respond with "Book: Book2 by Author1 url"
        Then system will respond with "Book: Book3 by Author2 url"
        Then system will respond with "Book: Book4 by Author3 url2"

    Scenario: user can add an item
        Given command "new" is entered
        And command "book" is entered
        When item "NewBook" "SomeAuthor" "url" is added
        And items are listed
        And user does nothing
        Then system will respond with "Book: NewBook by SomeAuthor url"

    Scenario: user can add an item with additional information
        Given command "new" is entered
        And command "book" is entered
        When item "NewBook" "SomeAuthor" "ThisUrl" with additional information "isbn" is added
        And items are listed
        And user does nothing
        Then system will respond with "Book: NewBook by SomeAuthor ThisUrl Isbn: isbn"