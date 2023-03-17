insert into distance (distance_id, enabled,description, logo_url, name, length,gpxurl,descriptionURL,mapURL, receipt_of_itinerary)
values (
        1,
        true,
        '2021 elhozza Nektek a B�kKiNy�lsz! leg�jabb t�rat�vj�t, mely a 10 km-es Nyuszif�l! A k�rt�ra a L�togat�k�zpontt�l a Cig�ny-d�l� fel� indul Cser�pfalu ir�ny�ba, majd a Berezdaljai Pincesorra kanyarodva vezet el benn�nket a Millenniumi-kil�t�hoz - �s a pazar cser�pi panor�m�hoz! Innen a K�-v�lgy tan�sv�nyen haladva �rintj�k a Berezd-tet�t �s M�sz-tet�t, tov�bbi csod�s kil�t�sokat �s fot�pontokat �lvezve! Innen �rintj�k az ikonikus �rd�gtorony kapt�rk�v�t, majd visszat�r�nk a L�togat�k�zponthoz a meg�rdemelt pihen�s�rt! A t�ra�tvonal k�nny�, csal�dosan is teljes�thet� nyomon vezet, t�k�letes akt�v program kezd� term�szetj�r�knak, vagy ak�r a terepfut�ssal most ismerked�knek is.',

        'assets/images/logo/logo10.png',
        'Nyuszif�l',
        10.5,
        'https://bukkinyulsz.hu/static/bukkinyulsz/gpx/bukkinyulsz_10.gpx',
        'https://bukkinyulsz.hu/static/bukkinyulsz/img/bkn_terkep_13_2019.jpg',
        'https://bukkinyulsz.hu/static/bukkinyulsz/pdf/itiner_instant_13.pdf',
        'Aut�val �rkez�knek: Cser�pfalut B�kkzs�rc ir�ny�ba elhagyva, kb. 1km-re jobbra, a H�r-v�lgy bej�rat�n�l
T�megk�zleked�ssel �rkez�knek: B�kkzs�rc fel� tart� aut�buszok, �H�rv�lgyi �tel�gaz�s� meg�ll�hely');

insert into checkpoint (checkpoint_id, enabled, name) values (1,true, 'valami-1');
insert into checkpoint (checkpoint_id, enabled, name) values (2,true, 'valami-2');

insert into sub_section (sub_section_id, enabled, distance_id, position, sub_length, sub_elevation_gain, checkpoint_from_id, checkpoint_to_id)
 values (1, true, 1, 1, 20.1, 243, 1, 2);
insert into sub_section (sub_section_id, enabled, distance_id, position, sub_length, sub_elevation_gain, checkpoint_from_id, checkpoint_to_id)
values (2, true, 1, 2, 20.1, 243, 2, 1);

insert into sub_section_marking (sub_section_marking_id, enabled, sub_section_id, marking_id)
values (2,true,1,2)

