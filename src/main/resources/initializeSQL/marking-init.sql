-- S�rga
insert into marking (marking_id, enabled, name, url) values (1, true,'S�rga -', 'assets/images/markings/yellow/-.png');
insert into marking (marking_id, enabled, name, url) values (2, true,'S�rga +', 'assets/images/markings/yellow/+.png');


insert into  tour_event (tour_event_id, enabled, active) values (1,true, true);
insert into distance (distance_id, enabled, length, name) values (1,true,10,'Nyuszif�l');
insert into distance (distance_id, enabled, length, name) values (2,true,13,'Ny�lfarknyi');
insert into distance (distance_id, enabled, length, name) values (3,true,26,'Ny�lon T�l');


insert into tour_event_distance (tour_event_distance_id, enabled,tour_event_id,distance_id) values (1,true,1,1);
insert into tour_event_distance (tour_event_distance_id, enabled,tour_event_id,distance_id) values (2,true,1,2);
insert into tour_event_distance (tour_event_distance_id, enabled,tour_event_id,distance_id) values (3,true,1,3);