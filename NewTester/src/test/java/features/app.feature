Feature: display hello world
  Scenario:  app is started
    Given The app has no exception
    When the app executes
    Then display "hello world"