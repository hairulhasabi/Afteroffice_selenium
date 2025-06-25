Feature: Lakukan checkout barang dari swaglab

Background: User login dengan valid credentials
    Given User masuk ke halaman login
    When User menginput email "standard_user" dan password "secret_sauce"
    Then User redirect ke halaman home

     Scenario: Pembeli melakukan checkout barang
        When Pembeli menambahkan barang ke keranjang
        Then verifikasi produk pada halaman keranjang Sauce Labs Fleece Jacket
        When Pembeli memilih checkout barang
        And Pembeli mengisi data diri
        And Pembeli melakukan overview
        And Pembeli melakukan submit order dan menampilkan pesan konfirmasi Thank you for your order!
