Feature: As a user I want to list items

    Scenario: user can list when there are no items added yet
        Given command "list" is entered
        When user does nothing
        Then system will respond with "No items added yet"
    
    Scenario: user can list one item
        Given command "list" is entered
        And item "Title" "Author" "url" exists in the application
        When user does nothing
        Then the item is listed with correct id "1", type "Book", title "Title" and author "Author"

    Scenario: user can list multiple items
        Given command "list" is entered
        And item "Book1" "Author1" "url" exists in the application
        And item "Book2" "Author1" "url" exists in the application
        And item "Book3" "Author2" "url" exists in the application
        And item "Book4" "Author3" "url2" exists in the application
        When user does nothing
#        Then system will respond with "(id: 1) Book: Book1 by Author1"
#        Then system will respond with "(id: 2) Book: Book2 by Author1"
#        Then system will respond with "(id: 3) Book: Book3 by Author2"
#        Then system will respond with "(id: 4) Book: Book4 by Author3"
        Then the item is listed with correct id "1", type "Book", title "Book1" and author "Author1"
        Then the item is listed with correct id "2", type "Book", title "Book2" and author "Author1"
        Then the item is listed with correct id "3", type "Book", title "Book3" and author "Author2"
        Then the item is listed with correct id "4", type "Book", title "Book4" and author "Author3"

    Scenario: user can list items sorted by title
        Given command "list by" is entered
        And command "title" is entered
        And book "The Cyberiad" "Stanislaw Lem" "www.url.com" "12345" "scifi" exists in the application
        And book "War with the newts" "Karel Capek" "www.url.com" "54321" "more scifi" exists in the application
        And book "The war of the worlds" "H. G. Wells" "www.url.com" "98765" "also scifi" exists in the application
        When user does nothing
        Then system will print items in order "1 3 2"
        #Then system will respond with print sequence "(id: 1) Book: The Cyberiad by Stanislaw Lem" "(id: 3) Book: The war of the worlds by H. G. Wells" "(id: 2) Book: War with the newts by Karel Capek"

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
        Then system will print items in order "Books: 1 4 3 Podcasts: 5 2 6"
        #Then system will respond with print sequence "(id: 1) Book: The Cyberiad by Stanislaw Lem" "(id: 4) Book: The war of the worlds by H. G. Wells" "(id: 3) Book: War with the newts by Karel Capek"
        #And system will respond with print sequence "(id: 5) Podcast: AnotherPodcast by Author2" "(id: 2) Podcast: OnePodcast by Author1" "(id: 6) Podcast: YetAnotherPodcast by Author3"

    Scenario: ui works correctly when listing
        Given item "Title" "Author" "url" exists in the application
        When items are listed
        And user does nothing
        Then the item is listed with correct id "1", type "Book", title "Title" and author "Author"