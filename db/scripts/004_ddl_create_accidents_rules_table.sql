create table accidents_rules(
    id serial primary key,
    accident_id int not null references accidents(id),
    rule_id int not null references rules(id)
);

ALTER TABLE accidents ADD COLUMN rule_id int reference rules(id);
