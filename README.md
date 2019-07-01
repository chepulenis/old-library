Система Библиотека. Создайте Каталог, по которому можно искать по:
- Автору (одному из группы).
- Названию книги или её фрагменте.
- Одному из ключевых слов книги (атрибут книги).

Каталог книг заполняет Администратор, добавляя и изменяя/удаляя их.
Каждая книга должна иметь адрес (место на полке) или читателя. Читатель
чтобы взять книгу регистрируется, оставляя э-мейл и номер телефона. Книга
может быть взята у Администратора в библиотеке на время не более месяца,
только в случае если книга доступна в библиотеке. Администратор должен
иметь страницу где отражаются взятые книги и читатели, которые
пользуются книгой.


System requirements: 
Tomcat 9.0.21, MySQL Server, mysql-connector 8.0.16, JSP & JSTL libraries 


Preset queries:

CREATE DATABASE library;
USE library;
CREATE TABLE `books` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NULL,
    `author` VARCHAR(255) NULL,
    `description` LONGTEXT NULL,
    `genre` VARCHAR(255) NULL,
    `ISBN` VARCHAR(255) NULL,
    `address` VARCHAR(255) NULL,
    `takeDate` DATE NULL,
    `expirationDate` date  NULL,
    PRIMARY KEY(`id`));
CREATE TABLE `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL,
    `email` VARCHAR(255) NULL,
    `phoneNumber` INT NULL,
    `book_id` INT DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `user_book_id` FOREIGN KEY (`book_id`) REFERENCES books(`id`)
    );