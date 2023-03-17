insert into distance (distance_id, enabled,description, logo_url, name, length,gpxurl,descriptionURL,mapURL, receipt_of_itinerary)
values (
        1,
        true,
        '2021 elhozza Nektek a BükKiNyúlsz! legújabb túratávját, mely a 10 km-es Nyuszifül! A körtúra a Látogatóközponttól a Cigány-dûlõ felé indul Cserépfalu irányába, majd a Berezdaljai Pincesorra kanyarodva vezet el bennünket a Millenniumi-kilátóhoz - és a pazar cserépi panorámához! Innen a Kõ-völgy tanösvényen haladva érintjük a Berezd-tetõt és Mész-tetõt, további csodás kilátásokat és fotópontokat élvezve! Innen érintjük az ikonikus Ördögtorony kaptárkövét, majd visszatérünk a Látogatóközponthoz a megérdemelt pihenésért! A túraútvonal könnyû, családosan is teljesíthetõ nyomon vezet, tökéletes aktív program kezdõ természetjáróknak, vagy akár a terepfutással most ismerkedõknek is.',

        'assets/images/logo/logo10.png',
        'Nyuszifül',
        10.5,
        'https://bukkinyulsz.hu/static/bukkinyulsz/gpx/bukkinyulsz_10.gpx',
        'https://bukkinyulsz.hu/static/bukkinyulsz/img/bkn_terkep_13_2019.jpg',
        'https://bukkinyulsz.hu/static/bukkinyulsz/pdf/itiner_instant_13.pdf',
        'Autóval érkezõknek: Cserépfalut Bükkzsérc irányába elhagyva, kb. 1km-re jobbra, a Hór-völgy bejáratánál
Tömegközlekedéssel érkezõknek: Bükkzsérc felé tartó autóbuszok, „Hórvölgyi útelágazás” megállóhely');

insert into checkpoint (checkpoint_id, enabled, name) values (1,true, 'valami-1');
insert into checkpoint (checkpoint_id, enabled, name) values (2,true, 'valami-2');

insert into sub_section (sub_section_id, enabled, distance_id, position, sub_length, sub_elevation_gain, checkpoint_from_id, checkpoint_to_id)
 values (1, true, 1, 1, 20.1, 243, 1, 2);
insert into sub_section (sub_section_id, enabled, distance_id, position, sub_length, sub_elevation_gain, checkpoint_from_id, checkpoint_to_id)
values (2, true, 1, 2, 20.1, 243, 2, 1);

insert into sub_section_marking (sub_section_marking_id, enabled, sub_section_id, marking_id)
values (2,true,1,2)

