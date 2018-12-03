Feature: As a user I want to add items

    Scenario: user can add an item
        Given command "new" is entered
        And command "book" is entered
        When item "NewBook" "SomeAuthor" "url" "description" "isbn" is added
        And items are listed
        And user does nothing
        Then system will respond with "(id: 1) Book: NewBook by SomeAuthor"

    Scenario: user can add a video with all fields
        Given command "new" is entered
        And command "video" is entered
        When item "Frozen" "Disney" "www.disney.com" "Fun movie" is added
        And items are listed
        And user does nothing
        Then system will respond with "(id: 1) Video: Frozen by Disney"

    Scenario: user can add a video with only title field
        Given command "new" is entered
        And command "video" is entered
        When item "Frozen" "" "" "" is added
        And items are listed
        And user does nothing
        Then system will respond with "(id: 1) Video: Frozen"

    Scenario: user can add an item without description and isbn
        Given command "new" is entered
        And command "book" is entered
        When item "NewBook" "SomeAuthor" "url" "" "" is added
        And items are listed
        And user does nothing
        Then system will respond with "(id: 1) Book: NewBook by SomeAuthor"

    Scenario: user can't add an item with no title
        Given command "new" is entered
        And command "book" is entered
        And command " " is entered
        When item "Title" "SomeAuthor" "url" "" "" is added
        And user does nothing
        Then system will respond with "Please enter a valid title"

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
        And system will respond with "(id: 1) Video: Frozen"