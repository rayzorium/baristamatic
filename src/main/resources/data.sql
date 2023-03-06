insert into drink (name)
values  ('Coffee'),
        ('Decaf Coffee'),
        ('Caffe Latte'),
        ('Caffe Americano'),
        ('Caffe Mocha'),
        ('Cappuccino');

insert into ingredient (name,cent_cost)
values  ('Coffee',75),
        ('Decaf Coffee',75),
        ('Sugar',25),
        ('Cream',25),
        ('Steamed Milk',35),
        ('Foamed Milk',35),
        ('Espresso',110),
        ('Cocoa',90),
        ('Whipped Cream',100);

insert into drink_ingredient (drink_id,ingredient_name,required_ingredient_amount)
values  (1,'Coffee',3),
        (1,'Sugar',1),
        (1,'Cream',1),
        (2,'Decaf Coffee',3),
        (2,'Sugar',1),
        (2,'Cream',1),
        (3,'Espresso',2),
        (3,'Steamed Milk',1),
        (4,'Espresso',3),
        (5,'Espresso',1),
        (5,'Cocoa',1),
        (5,'Steamed Milk',1),
        (5,'Whipped Cream',1),
        (6,'Espresso',2),
        (6,'Steamed Milk',1),
        (6,'Foamed Milk',1);