[IF 3110 Pengembangan Aplikasi Berbasis Web]


WS-Bank menggunakan Java 11 + JAX-WS yang dibundel dalam Apache Maven. Protokol yang digunakan adalah Simple Object Access Protocol (SOAP).
WS-Bank menggunakan wsbank.war yang akan ditempatkan pada folder {DIREKTORI_TOMCAT}/webapps/
Isi dari war dalah kelas-kelas java yang sudah dikompilasi dan diinterpretasi,
file sun-jaxws.xml dan web.xml yang masing-masing berfungsi sebagai deskriptor deployment.

File .java terdapat dalam src/main/java/com/webservice/*

Di dalam folder webservice, terdapat package-package sebagai berikut.
1. package com.webservice.controllers pada folder webservice/controllers. File *.java pada package ini mengimplementasi kelas-kelas interfaces.
2. package com.webservice.database pada folder webservice/database.  File Database.java dipakai sebagai koneksi utama java dan MySQL.
3. package com.webservice.interfaces pada folder webservice/interfaces. File *.java pada package ini merupakan endpoint pertama yang akan
dideteksi oleh SOAP
4. package com.webservice.models pada folder webservice/models. File *.java pada package ini merupakan pemetaan dari tabel-tabel MySQL.
5. package com.webservice.utils pada folder webservice/utils. Fungsi-fungsi pada Utils.java pada package ini membantu file *.java lain.


Basis data yang digunakan adalah MySQL dengan JDBC. JDBC menggunakan connection, result, dan statement yang sebaiknya diclose setelah digunakan.

Berikut adalah isi dari create_tables.sql

CREATE TABLE IF NOT EXISTS users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    account_number VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS virtual_accounts (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    virtual_account_number VARCHAR(255) NOT NULL,
    account_number VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS transactions (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    src_account_number VARCHAR(255) NOT NULL,
    dest_account_number VARCHAR(255) NOT NULL,
    dest_virtual_account VARCHAR(255),
    amount BIGINT NOT NULL,
    created_at TIMESTAMP,
    PRIMARY KEY (id)
);

Terdapat tiga tabel, yaitu users, virtual_accounts, dan transactions. Atribut dari setiap tabel dapat dilihat di create_tables.sql


Untuk menjalankan web service ini pada Linux, lakukan:
1. Install Apache Maven, Docker, dan Docker Compose.
2. Lalu ketik perintah "mvn package" pada direktori yang mempunyai pom.xml
3. Buat file .env dengan perintah "sudo cp ENV.EXAMPLE .env" pada direktori yang sama, lalu ketik isian yang sesuai.
3. Kemudian ketik "sudo docker-compose up -d --build wsbank-tomcat" pada direktori yang sama.
4. Ketik "sudo docker-compose stop".
5. Lalu ketik "sudo docker-compose up".
6. Ketik "sudo docker exec -it wsbank-database bash".
7. Lalu ketik "mysql -u <username pada .env> -p" dan masukkan password sesuai dengan .env
8. Buat database dengan perintah "CREATE DATABASE <nama database pada .env> ;".
9. Copy paste isi dari create_tables.sql dan tekan enter.
10. Pada .env terdapat TOMCAT_HOST_PORT. Buka http://localhost:< angka pada TOMCAT_HOST_PORT>/wsbank/login
11. Akan terlihat semua wsdl yang terdaftar.


[IF 3159 Dasar Pembangunan Perangkat Lunak]
 1. CI/CD: 13517115, 13517070
 2. Eksplorasi dan setup mesin deployment: 13517115, 13517070


