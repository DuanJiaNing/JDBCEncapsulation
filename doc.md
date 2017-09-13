#### 映射关系
见 mapping.xlsx 

#### 数据库表创建语句：

CREATE TABLE monitor (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,	 
	name TEXT NOT NULL,
	price FLOAT NOT NULL,
    description TEXT NOT NULL,
	monthly_sales INT NOT NULL);

CREATE TABLE monitor_detail (
    id INT NOT NULL,
    listing_time DATE NOT NULL,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    size INT NOT NULL,
    UNIQUE (id));


