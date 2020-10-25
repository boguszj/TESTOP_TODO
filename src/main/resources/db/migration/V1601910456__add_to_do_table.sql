CREATE TABLE to_do (
                          to_do_id uuid primary key,
                          name varchar ( 127 ) not null,
                          description varchar ( 255 ),
                          to_do_status varchar ( 50 )
);