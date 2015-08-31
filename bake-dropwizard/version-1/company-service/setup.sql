
desc keyspaces;

select * from system.schema_keyspaces;

-- drop keyspace if exists bake_dropwizard;

create keyspace bake_dropwizard
with replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

use bake_dropwizard;

desc tables;
drop table if exists companys;
create table companys (
    id int,
    name text,
    inserted timestamp,
    updated timestamp,
    deleted timestamp,
    primary key (id)
);
desc table companys;

select * from companys;
insert into companys (id, name, inserted) values (1, 'Sonic Youth', '1970-01-01T00:00:00.000+0000');
select id, name, inserted, updated, deleted from companys;
