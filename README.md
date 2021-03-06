# InstaAutomation
###### an instagram automation tool written in Java.

InstaAutomation adalah perangkat lunak berbasis Java yang dikembangkan menggunakan teknologi Java 8 untuk melakukan automasi akun Instagram. InstaAutomation adalah perangkat lunak yang dijalankan menggunakan komputer Desktop dan dapat berjalan di sistem operasi apapun (Linux, Windows, dsb) selama pada sistem operasi tersebut terpasang Java Runtime Environment.
# Komponen-komponen Utama
  - Java 8
  - Selenium
  - Google Chrome

# Fitur baru !
#### Modul Like
  - Like foto berdasarkan hashtag sebanyak apapun termasuk 8 postingan paling populer
  - interval waktu untuk like foto yang bisa dikustomisasi se unik mungkin
  - otomatis mematikan komputer (now only supports linux) pada proses like foto
  - Headless browser
### Modul Follow  
- Follow akun berdasaran hashtag dengan jumlah akun yang ditentukan dalam sekali operasional.
- Follow akun berdasarkan hashtag termasuk menyukai salah satu foto (bot akan melakukan 'like' dulu kemudian memfollow akun tersebut)
- Follow akun pengikut (follower) dari akun target yang ditentukan dengan jumlah akun yang akan difollow harus ditentukan atau software akan mendeteksi otomatis jumlah pengikut akun target dan memfollow semuanya (tidak disarankan seperti ini).
- Deteksi akun bot agar tidak di follow (experimental feature).
- Filter untuk follow / tidak follow akun private (dari follower akun target)
- Filter untuk follow / tidak follow akun dengan jumlah follower tertentu (dari akun target)
- Pembuatan analisa laporan scraping pengikut akun target dalam format .csv (experimental)

### Software Requirement
InstaAutomation membutuhkan : 
1. Java 8 (JRE), untuk proses instalasi Java 8, silahkan merujuk ke link berikut https://www.java.com/en/download/help/download_options.xml
2. Google Chrome versi 72 (how to check : Help -> About Google Chrome) it's a must btw.

### How To Use!!!
1. Unduh file InstaAutomation.zip pada link berikut ( https://github.com/omandotkom/InstaAutomation/blob/master/target/bin-public/InstaAutomation.zip ).
2. Ekstrak file InstaAutomation.zip pada folder yang Anda tentukan.
3. Ekstrak chromedriver sesuai dengan sistem operasi yang digunakan, Windows 64 bit tetap dapat menggunakan chromedriver 32 bit.
4. Jalankan InstaAutomation.jar dengan klik kanan -> Open atau ketik perintah
```sh
$ java -jar InstaAutomation-1.0-SNAPSHOT-jar-with-dependencies.jar
```
