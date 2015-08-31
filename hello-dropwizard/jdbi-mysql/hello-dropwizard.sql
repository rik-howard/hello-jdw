
-- mysql -h localhost -u root -p
-- r00t

-- revoke all on hello_dropwizard.* from rik@localhost;
-- drop user rik@localhost;
-- create user rik@localhost identified by 'r1k';
grant all on hello_dropwizard.* to rik@localhost;

quit;

-- mysql -h localhost -u rik -p
-- r1k

show databases;

create database if not exists hello_dropwizard;

use hello_dropwizard;

show tables;

create table if not exists users
(
    id integer (3) unsigned zerofill not null auto_increment,
    name varchar (16) not null,
    primary key (id),
    unique (name)
);

select *
from users;

insert  into users (name)
values ('rik');

-- the postman requests rely on rik getting an id that equals 1
