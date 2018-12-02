Feature: As a user, I can view detailed information of an item

Scenario: user can see the detailed information of an item
    Given book "title" "author" "url" "isbn" "description" exists in the application
    And command "details" with id 1 is entered
    When user does nothing
    Then system will respond with 
            """
            ******************************************************
            (id: 1) Book: title by author
            ******************************************************
            Type:		Book
            Title:		title
            Author:		author
            URL:		url
            Description:	description
            ISBN:		isbn
            Read:		false
            ******************************************************
            """
    