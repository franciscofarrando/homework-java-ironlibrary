create database ironlibrary;
drop database ironlibrary;
use ironlibrary;

select * from books;

INSERT INTO books (isbn, title, category, quantity, author_id) VALUES ('978-0-13-468599-1', 'Effective Java', 'Programming', 5, 1);
INSERT INTO books (isbn, title, category, quantity, author_id) VALUES ('978-0-596-52068-7', 'Java Concurrency in Practice', 'Programming', 3, 1);
INSERT INTO books (isbn, title, category, quantity, author_id) VALUES ('978-1-491-94722-0', 'Clean Code', 'Programming', 4, 2);
INSERT INTO books (isbn, title, category, quantity, author_id) VALUES ('978-0-13-235088-4', 'Design Patterns', 'Software Design', 2, 2);
INSERT INTO books (isbn, title, category, quantity, author_id) VALUES ('978-0-321-35668-0', 'Refactoring', 'Programming', 6, 3);
INSERT INTO books (isbn, title, category, quantity, author_id) VALUES ('978-1-59327-584-6', 'Automate the Boring Stuff with Python', 'Automation', 7, 2);
INSERT INTO books (isbn, title, category, quantity, author_id) VALUES ('978-1-59327-599-0', 'The Linux Command Line', 'Operating Systems', 5, 3);
INSERT INTO books (isbn, title, category, quantity, author_id) VALUES ('978-0-262-03384-8', 'Introduction to Algorithms', 'Algorithms', 3, 3);
INSERT INTO books (isbn, title, category, quantity, author_id) VALUES ('978-0-07-161586-0', 'Data Structures and Algorithms in Java', 'Algorithms', 4, 3);
INSERT INTO books (isbn, title, category, quantity, author_id) VALUES ('978-1-4919-1889-0', 'Head First Java', 'Programming', 6, 1);

INSERT INTO author (author_id, name, email) VALUES
(1, 'Gabriel García Márquez', 'gabriel@example.com'),
(2, 'Isabel Allende', 'isabel@example.com'),
(3, 'Jorge Luis Borges', 'jorge@example.com');
