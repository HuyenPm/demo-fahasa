Feature: Order product action

  @OrderTest
  Scenario Outline: User logs in successfully and orders on Fahasa
    Given user is on Home Page
    And user logs in to system with username is "<username>" and password is "<password>"
    Then user logs in "<result>" and return message is "<mess>"
    When user clears cart
    When user searches product and adds the first item in list result to cart
      | product      | numberProduct | expectedTitle                                   |
      | truyen tranh | 3             | Kết quả tìm kiếm với: truyen tranh - FAHASA.COM |
      | tieu thuyet  | 1             | Kết quả tìm kiếm với: tieu thuyet - FAHASA.COM  |
      | nhà giả kim  | 2             | Kết quả tìm kiếm với: nhà giả kim - FAHASA.COM  |
    Then products in cart should be displayed
      | productName                                   | price    | quantity |
      | Tranh Truyện Màu Đồng Hành Với Phim Hoạt Hình | 67.500 đ | 3        |
      | Tiểu Thuyết Ngày Mới Và Hà Nội 36 Phố Phường  | 55.180 đ | 1        |
      | Nhà Giả Kim (Tái Bản 2017)                    | 55.200 đ | 2        |
    And user clicks to pay cart
    When user edits address of delivery with the following data
      | name       | phone      | country  | city   | district         | ward             | address |
      | Minh Huyen | 0333768855 | Việt Nam | Hà Nội | Quận Bắc Từ Liêm | Phường Cổ Nhuế 2 | ngõ 142 |
    And user confirms information of order with notes "<notes>" of delivery
    Examples:
      | username   | password   | result | mess | notes                                 |
      | 0333768855 | @a16021004 | passed |      | Vui lòng giao hàng vào giờ hành chính |

