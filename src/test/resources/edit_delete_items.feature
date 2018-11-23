Feature: As a user I want to edit items

    Scenario: ui works correctly when listing
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
        And system will respond with "Book: Title by Author Url: url"

    Scenario: ui works correctly when adding video
        Given command "new" is entered
        And command "video" is entered
        When item "Frozen" "" "" "" is added
        And items are listed
        And user does nothing
        Then system will respond with "Welcome to the CS literature recommendation system!"
        And system will respond with "Title: "
        And system will respond with "Author (leave empty to skip): "
        And system will respond with "Url (leave empty to skip): "
        And system will respond with "Description (leave empty to skip): "
        And system will respond with 
            """
            quit = quit the application
            new = add a new item
            list = list items
            edit = edit item
            delete = delete item
            """
        And system will respond with "Video: Frozen"

    Scenario: user can edit a book title
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        When edit commands "0" "title" "New Title" are entered
        When items are listed
        And user does nothing
        And system will respond with "Book: New Title by Author Url: url"

    Scenario: user can't enter a string as a letter to select item to edit
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        And command "x" is entered
        When edit commands "0" "title" "New Title" are entered
        When items are listed
        And user does nothing
        And system will respond with "Please enter a valid id"
        And system will respond with "Book: New Title by Author Url: url"

    Scenario: user can't enter an id that doesn't exist when selecting an item to edit
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        And command "5" is entered
        When edit commands "0" "title" "New Title" are entered
        When items are listed
        And user does nothing
        And system will respond with "Please enter a valid id"
        And system will respond with "Book: New Title by Author Url: url"

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
        And system will respond with "Book: Title by New Author Url: url"

    Scenario: user can't edit a book field that doesn't exist
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        And command "0" is entered
        When edit commands "doesn't exist" "New Author" "author" are entered
        When items are listed
        And user does nothing
        And system will respond with "Invalid field. Please try again"
        And system will respond with "Book: Title by New Author Url: url"

    Scenario: user can edit an book description
        Given command "edit" is entered
        And book "Title" "Author" "url" "isbn" "description" exists in the application
        When edit commands "0" "description" "New Description" are entered
        When items are listed
        And user does nothing
        And system will respond with "Book: Title by Author Url: url Description: New Description Isbn: isbn"

    Scenario: user can edit an book title when multiple books exist
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        And item "Learn Python" "Developer" "www.google.com" exists in the application
        When edit commands "1" "url" "www.youtube.com" are entered
        When items are listed
        And user does nothing
        And system will respond with "Book: Title by Author Url: url"
        And system will respond with "Book: Learn Python by Developer Url: www.youtube.com"