Feature: Login action

  @LoginTest
  Scenario Outline: Login with case success (passed) and case fail (failed)
    Given user is on Home Page
    When user logs in to system with username is "<username>" and password is "<password>"
    Then user logs in "<result>" and return message is "<mess>"
    Examples:
      | username   | password    | result | mess                                   |
      | 0333768855 | @a160210043 | failed | Số điện thoại/Email hoặc Mật khẩu sai! |
      | 0333768855 | @a16021004a | passed |                                        |
      | 0333768855 | @a16021004  | passed |                                        |
