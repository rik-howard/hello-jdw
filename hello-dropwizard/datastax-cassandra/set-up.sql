
create keyspace datastax
with replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

use datastax;

desc tables;

drop table if exists imps;

create table imps (
    id int,
    forename text,
    surname text,
    primary key (id)
);

desc table imps;

select * from imps;
insert into imps (id, forename, surname) values (1, 'john', 'smith');
insert into imps (id, forename, surname) values (2, 'paul', 'smith');
insert into imps (id, forename, surname) values (3, 'john', 'jones');
select * from imps;
