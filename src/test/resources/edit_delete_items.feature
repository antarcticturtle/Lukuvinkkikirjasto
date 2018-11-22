Feature: As a user I want to edit items

    Scenario: ui works correctly
        Given item "Title" "Author" "url" exists in the application
        When items are listed
        And user does nothing
        Then system will respond with "Welcome to the CS literature recommendation system!"
        And system will respond with 
            """
            quit = quit the application
            new = add a new item
            list = list items
            edit = edit item
            delete = delete item
            """
        And system will respond with "Book: Title by Author url"

    Scenario: user can edit a book title
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        When edit commands "0" "title" "New Title" are entered
        When items are listed
        And user does nothing
        And system will respond with "Book: New Title by Author url"

    Scenario: user can delete an item
        Given command "delete" is entered
        And item "Title" "Author" "url" exists in the application
        And command "0" is entered
        When items are listed
        And user does nothing
        And system will respond with "Select the id of the item you want to delete"
        And system will respond with "No items added yet"

    Scenario: user can't delete an item with wrong id
        Given command "delete" is entered
        And item "Title" "Author" "url" exists in the application
        And command "7" is entered
        When items are listed
        And user does nothing
        And system will respond with "Select the id of the item you want to delete"
        And system will respond with "Invalid id"

    Scenario: user can edit an book author
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        When edit commands "0" "author" "New Author" are entered
        When items are listed
        And user does nothing
        And system will respond with "Book: Title by New Author url"

    Scenario: user can edit an book description
        Given command "edit" is entered
        And book "Title" "Author" "url" "isbn" "description" exists in the application
        When edit commands "0" "description" "New Description" are entered
        When items are listed
        And user does nothing
        And system will respond with "Book: Title by Author url Isbn: isbn New Description"

    Scenario: user can edit an book title when multiple books exist
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        And item "Learn Python" "Developer" "www.google.com" exists in the application
        When edit commands "1" "url" "www.youtube.com" are entered
        When items are listed
        And user does nothing
        And system will respond with "Book: Title by Author url"
        And system will respond with "Book: Learn Python by Developer www.youtube.com"