CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE
    libros (
        id int (5) auto_increment primary key,
        nombre_libro varchar(50),
        editorial varchar(50),
        autor varchar(50),
        precio int
    );

INSERT INTO
    libros (nombre_libro, editorial, autor, precio) VALUE (
        "El principito",
        "Océano Historias gráficas",
        "Antoine de Saint-Exupéry",
        "450000"
    ),
    (
        "Harry Potter",
        "Bloomsbury Publishing Scholastic",
        "J. K. Rowling",
        "91900"
    ),
    (
        "Cien años de soledad",
        "Editorial Sudamericana",
        "Gabriel García Márquez",
        55000
    ),
    (
        "Don Quijote de la Mancha",
        "Alianza Editorial",
        "Miguel de Cervantes",
        62000
    ),
    (
        "1984",
        "Secker & Warburg",
        "George Orwell",
        47000
    ),
    (
        "El alquimista",
        "HarperCollins",
        "Paulo Coelho",
        39000
    ),
    (
        "Crónica de una muerte anunciada",
        "Editorial Oveja Negra",
        "Gabriel García Márquez",
        34000
    ),
    (
        "Orgullo y prejuicio",
        "Thomas Egerton",
        "Jane Austen",
        58000
    ),
    (
        "La sombra del viento",
        "Planeta",
        "Carlos Ruiz Zafón",
        51000
    ),
    (
        "El código Da Vinci",
        "Doubleday",
        "Dan Brown",
        60000
    );