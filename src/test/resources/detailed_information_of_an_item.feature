Feature: As a user, I can view detailed information of an item

Scenario: user can see the detailed information of a book
    Given book "title" "author" "url" "isbn" "description" exists in the application
    And command "details" with id 1 is entered
    When user does nothing
    Then system will respond with 
        """
        ******************************************************
        (id: 1) Book: title by author
        ******************************************************
        Type:          Book
        Title:         title
        Author:        author
        URL:           url
        Description:   description
        Read:          false
        ISBN:          isbn
        ******************************************************

        """

Scenario: user can see the detailed information of a video
    Given video "title" "author" "url" "description" exists in the application
    And command "details" with id 1 is entered
    When user does nothing
    Then system will respond with 
        """
        ******************************************************
        (id: 1) Video: title by author
        ******************************************************
        Type:          Video
        Title:         title
        Author:        author
        URL:           url
        Description:   description
        Read:          false
        ******************************************************

        """
