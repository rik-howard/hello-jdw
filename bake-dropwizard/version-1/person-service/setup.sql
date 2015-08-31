
desc keyspaces;

select * from system.schema_keyspaces;

-- drop keyspace if exists bake_dropwizard;

create keyspace bake_dropwizard
with replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

use bake_dropwizard;

desc tables;
drop table if exists persons;
create table persons (
    id int,
    forename text,
    surname text,
    primary key (id)
);
desc table persons;

select * from persons;
insert into persons (id, forename, surname) values (1, 'Thurston', 'Moore');
insert into persons (id, forename, surname) values (2, 'Kim', 'Gordon');
insert into persons (id, forename, surname) values (3, 'Lee', 'Ronaldo');
insert into persons (id, forename, surname) values (4, 'Steve', 'Shelley');
select * from persons;

desc tables;
drop table if exists persons;
create table persons (
    id int,
    forename text,
    surname text,
    inserted timestamp,
    primary key (id)
);
desc table persons;

select * from persons;
insert into persons (id, forename, surname, inserted) values (1, 'Thurston', 'Moore', '1970-01-01T00:00:00.000+0000');
insert into persons (id, forename, surname, inserted) values (2, 'Kim', 'Gordon', '1970-01-01T00:00:00.000+0000');
insert into persons (id, forename, surname, inserted) values (3, 'Lee', 'Ronaldo', '1970-01-01T00:00:00.000+0000');
insert into persons (id, forename, surname, inserted) values (4, 'Steve', 'Shelley', '1970-01-01T00:00:00.000+0000');
select id, forename, surname, inserted from persons;

desc tables;
drop table if exists persons;
create table persons (
    id int,
    forename text,
    surname text,
    inserted timestamp,
    updated timestamp,
    deleted timestamp,
    primary key (id)
);
desc table persons;

select * from persons;
insert into persons (id, forename, surname, inserted) values (1, 'Thurston', 'Moore', '1970-01-01T00:00:00.000+0000');
insert into persons (id, forename, surname, inserted) values (2, 'Kim', 'Gordon', '1970-01-01T00:00:00.000+0000');
insert into persons (id, forename, surname, inserted) values (3, 'Lee', 'Ronaldo', '1970-01-01T00:00:00.000+0000');
insert into persons (id, forename, surname, inserted) values (4, 'Steve', 'Shelley', '1970-01-01T00:00:00.000+0000');
select id, forename, surname, inserted, updated, deleted from persons where id in (1, 2, 3, 4, 5, 6, 7, 8);

-- alter table persons
-- add inserted timestamp;
-- select * from persons;

-- update persons
-- set inserted = null
-- where id = 1;
-- select * from persons;
