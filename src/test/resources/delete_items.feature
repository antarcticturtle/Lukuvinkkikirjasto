Feature: As a user I want to delete items

    Scenario: user can delete an item
        Given command "delete" is entered
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