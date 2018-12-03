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

    Scenario: user can list items sorted by title
        Given command "list by" is entered
        And command "title" is entered
        And book "The Cyberiad" "Stanislaw Lem" "www.url.com" "12345" "scifi" exists in the application
        And book "War with the newts" "Karel Capek" "www.url.com" "54321" "more scifi" exists in the application
        And book "The war of the worlds" "H. G. Wells" "www.url.com" "98765" "also scifi" exists in the application
        When user does nothing
        Then system will respond with print sequence "(id: 1) Book: The Cyberiad by Stanislaw Lem Url: www.url.com Description: scifi Isbn: 12345" "(id: 3) Book: The war of the worlds by H. G. Wells Url: www.url.com Description: also scifi Isbn: 98765" "(id: 2) Book: War with the newts by Karel Capek Url: www.url.com Description: more scifi Isbn: 54321"

    Scenario: user can list items of each category sorted by title
        Given command "list by" is entered
        And command "type and title" is entered
        And book "The Cyberiad" "Stanislaw Lem" "www.url.com" "12345" "scifi" exists in the application
        And podcast "OnePodcast" "Author1" "www.url.com" "description" exists in the application
        And book "War with the newts" "Karel Capek" "www.url.com" "54321" "more scifi" exists in the application
        And book "The war of the worlds" "H. G. Wells" "www.url.com" "98765" "also scifi" exists in the application
        And podcast "AnotherPodcast" "Author2" "www.url.com" "description" exists in the application
        And podcast "YetAnotherPodcast" "Author3" "www.url.com" "description" exists in the application
        When user does nothing
        Then system will respond with print sequence "(id: 1) Book: The Cyberiad by Stanislaw Lem Url: www.url.com Description: scifi Isbn: 12345" "(id: 4) Book: The war of the worlds by H. G. Wells Url: www.url.com Description: also scifi Isbn: 98765" "(id: 3) Book: War with the newts by Karel Capek Url: www.url.com Description: more scifi Isbn: 54321"
        And system will respond with print sequence "(id: 5) Podcast: AnotherPodcast by Author2 Url: www.url.com Description: description" "(id: 2) Podcast: OnePodcast by Author1 Url: www.url.com Description: description" "(id: 6) Podcast: YetAnotherPodcast by Author3 Url: www.url.com Description: description"