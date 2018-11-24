Feature: As a user I want to add, edit, delete, modify and list items

    Scenario: user can list when there are no items added yet
        Given command "list" is entered
        When user does nothing
        Then system will respond with "No items added yet"
    
    Scenario: user can list one item
        Given command "list" is entered
        And item "Title" "Author" "url" exists in the application
        When user does nothing
        Then system will respond with "(id: -1) Book: Title by Author Url: url"

    Scenario: user can list multiple items
        Given command "list" is entered
        And item "Book1" "Author1" "url" exists in the application
        And item "Book2" "Author1" "url" exists in the application
        And item "Book3" "Author2" "url" exists in the application
        And item "Book4" "Author3" "url2" exists in the application
        When user does nothing
        Then system will respond with "(id: -1) Book: Book1 by Author1 Url: url"
        Then system will respond with "(id: -1) Book: Book2 by Author1 Url: url"
        Then system will respond with "(id: -1) Book: Book3 by Author2 Url: url"
        Then system will respond with "(id: -1) Book: Book4 by Author3 Url: url2"

    Scenario: user can add an item
        Given command "new" is entered
        And command "book" is entered
        When item "NewBook" "SomeAuthor" "url" "description" "isbn" is added
        And items are listed
        And user does nothing
        Then system will respond with "(id: -1) Book: NewBook by SomeAuthor Url: url Description: description Isbn: isbn"

    Scenario: user can add a video with all fields
        Given command "new" is entered
        And command "video" is entered
        When item "Frozen" "Disney" "www.disney.com" "Fun movie" is added
        And items are listed
        And user does nothing
        Then system will respond with "(id: -1) Video: Frozen by Disney Url: www.disney.com Description: Fun movie"

    Scenario: user can add a video with only title field
        Given command "new" is entered
        And command "video" is entered
        When item "Frozen" "" "" "" is added
        And items are listed
        And user does nothing
        Then system will respond with "(id: -1) Video: Frozen"

    Scenario: user can add an item
        Given command "new" is entered
        And command "book" is entered
        When item "NewBook" "SomeAuthor" "url" "" "" is added
        And items are listed
        And user does nothing
        Then system will respond with "(id: -1) Book: NewBook by SomeAuthor Url: url"

    Scenario: user can't add an item with no title
        Given command "new" is entered
        And command "book" is entered
        And command " " is entered
        # Trying to enter an empty title, should not work
        When item "Title" "SomeAuthor" "url" "" "" is added
        #And items are listed
        And user does nothing
        Then system will respond with "Please enter a valid title"
        #Then system will respond with "Book: NewBook by SomeAuthor Url: url"