create table category(
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
);

create table product(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_category INT NOT NULL,
    product_code VARCHAR(255),
    product_name VARCHAR(255),
    price DECIMAL(19.2),
    FOREIGN KEY (id_category) REFERENCES category(id) ON DELETE SET NULL
);

create table product_history(
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);
