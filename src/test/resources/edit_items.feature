Feature: As a user I want to edit items

    Scenario: user can edit a book title
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        When edit commands "1" "New Title" "" "" "" "" are entered
        When items are listed
        And user does nothing
        And system will respond with "(id: 1) Book: New Title by Author"

    Scenario: user can't enter a string as a letter to select item to edit
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        And command "x" is entered
        When edit commands "1" "New Title" "" "" "" "" are entered
        When items are listed
        And user does nothing
        And system will respond with "Please enter a number"
        And system will respond with "(id: 1) Book: New Title by Author"

    Scenario: user can't enter an id that doesn't exist when selecting an item to edit
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        And command "5" is entered
        When edit commands "0" "New Title" "" "" "" "" are entered
        When items are listed
        And user does nothing
        And system will respond with "Please enter a valid id"
        And system will respond with "(id: 1) Book: Title by Author"

    Scenario: user can edit an book author
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        When edit commands "1" "" "New Author" "" "" "" are entered
        When items are listed
        And user does nothing
        And system will respond with "(id: 1) Book: Title by New Author"

    Scenario: user can edit an book description
        Given command "edit" is entered
        And book "Title" "Author" "url" "isbn" "description" exists in the application
        When edit commands "1" "" "" "" "New Description" "" are entered
        When command "details" with id 1 is entered
        And user does nothing
        Then system will respond with
        """
        (id: 1) Book: Title by Author
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Type:          Book
        Title:         Title
        Author:        Author
        URL:           url
        Description:   New Description
        Read:          false
        ISBN:          isbn
        """

    Scenario: user can edit an book title when multiple books exist
        Given command "edit" is entered
        And item "Title" "Author" "url" exists in the application
        And item "Learn Python" "Developer" "www.google.com" exists in the application
        When edit commands "2" "" "" "www.youtube.com" "" "" are entered
        When items are listed
        And user does nothing
        And system will respond with "(id: 1) Book: Title by Author"
        And system will respond with "(id: 2) Book: Learn Python by Developer"

    Scenario: user can edit a book isbn
        Given command "edit" is entered
        And book "Title" "Author" "url" "isbn" "description" exists in the application
        When edit commands "1" "" "" "" "" "New ISBN" are entered
        When command "details" with id 1 is entered
        And user does nothing
        Then system will respond with
        """
        (id: 1) Book: Title by Author
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Type:          Book
        Title:         Title
        Author:        Author
        URL:           url
        Description:   description
        Read:          false
        ISBN:          New ISBN
        """

    Scenario: user can't edit the isbn field of an item that is not a book
        Given command "edit" is entered
        And podcast "Title" "Author" "url" "description" exists in the application
        When edit commands "1" "" "" "" "" are entered
        And user does nothing
        Then system will not ask for isbn

    Scenario: user can't enter a title that is over 50 characters when editing a book
        Given command "edit" is entered
        And book "Title" "Author" "url" "isbn" "description" exists in the application
        When edit commands "1" "title" with too long "51" characters and valid value "new title" are entered
        And user does nothing
        Then system will respond with "Please enter a valid title (max. 50 characters)"

    Scenario: user can't enter an author that is over 50 characters when editing a book
        Given command "edit" is entered
        And book "Title" "Author" "url" "isbn" "description" exists in the application
        When edit commands "1" "author" with too long "51" characters and valid value "new author" are entered
        And user does nothing
        Then system will respond with "Please enter a valid author (max. 50 characters)"

    Scenario: user can't enter a url that is over 500 characters when editing a book
        Given command "edit" is entered
        And book "Title" "Author" "url" "isbn" "description" exists in the application
        When edit commands "1" "url" with too long "501" characters and valid value "new url" are entered
        And user does nothing
        Then system will respond with "Please enter a valid url (max. 500 characters)"

    Scenario: user can't enter a description that is over 500 characters when editing a book
        Given command "edit" is entered
        And book "Title" "Author" "url" "isbn" "description" exists in the application
        When edit commands "1" "description" with too long "501" characters and valid value "new description" are entered
        And user does nothing
        Then system will respond with "Please enter a valid description (max. 500 characters)"

    Scenario: user can't enter an isbn that is over 20 characters when editing a book
        Given command "edit" is entered
        And book "Title" "Author" "url" "isbn" "description" exists in the application
        When edit commands "1" "isbn" with too long "21" characters and valid value "new description" are entered
        And user does nothing
        Then system will respond with "Please enter a valid isbn (max. 20 characters)"