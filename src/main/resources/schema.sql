create table if not exists candy(
   id bigint not null,
   name nvarchar(255),
   price decimal(12,4),
   primary key (id)
);

create table if not exists candy_delivery(
    candy_id bigint not null,
    delivery_id bigint not null
--     This could cause some conflicts with the default hibernate ddl-auto behavior
--      if hibernate attempts to delete the delivery table after adding the foreign key relationship
    foreign key (candy_id) references candy(id),
    foreign key (delivery_id) references delivery(id) on delete cascade
);