package com.example.bukkinyulszbackend.controller;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.model.TourEvent;
import com.example.bukkinyulszbackend.model.TourEventDistance;
import com.example.bukkinyulszbackend.services.DistanceService;
import com.example.bukkinyulszbackend.services.TourEventDistanceService;
import com.example.bukkinyulszbackend.services.TourEventService;
import com.example.bukkinyulszbackend.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(AppConstant.URI_API + "/" + AppConstant.URI_API_INIT)
public class InitializerController {
    SimpleDateFormat formatterYMD=new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatterYMDHM=new SimpleDateFormat("yyyy-dd-MM HH:mm");
    SimpleDateFormat formatterHM=new SimpleDateFormat("HH:mm");
    @Autowired
    private TourEventService tourEventService;
    @Autowired
    private DistanceService distanceService;

    @Autowired
    private TourEventDistanceService tourEventDistanceService;

    private TourEvent tourEvent;
    private List<Distance> distanceList = new ArrayList<>();
    private List<TourEventDistance> tourEventDistanceList = new ArrayList<>();


    @GetMapping("/initialize")
    public ResponseEntity<String> init() throws BusinessException {
        initTour();
        initDistances();
        initTourEventDistance();
        return ResponseEntity.ok("INIT DONE");
    }

    private void initTourEventDistance() throws BusinessException {
        for(Distance d : this.distanceList){
            TourEventDistance tourEventDistance = new TourEventDistance();
            tourEventDistance.setTourEvent(this.tourEvent);
            tourEventDistance.setDistance(d);
            tourEventDistanceList.add(this.tourEventDistanceService.add(tourEventDistance));
        }


    }

    private void initTour()  throws BusinessException{
        TourEvent tourEvent1 = new TourEvent();
        tourEvent1.setDateOfEvent(getDate("2023-10-10"));
        tourEvent1.setName("Bükkinyulsz");
        tourEvent1.setPlaceOfEvent("Cserépfalu");
        tourEvent1.setActive(true);
        tourEvent1.setApplicationFrom(getDateTime("2023-03-20 00:00"));
        tourEvent1.setApplicationTo(getDateTime("2023-06-20 00:00"));
        this.tourEvent = this.tourEventService.add(tourEvent1);
    }

    private void initDistances() throws BusinessException{
        Distance distance = new Distance();
        distance.setName("Nyuszifül");
        distance.setLength(10F);
        distance.setStartPlace("Suba-lyuk Múzeum és Látogatóközpont");
        distance.setFinishPlace("Suba-lyuk Múzeum és Látogatóközpont");
        distance.setStartTimeFrom(getDateTime("2023-10-10 09:00"));
        distance.setStartTimeTo(getDateTime("2023-10-10 11:00"));
        distance.setTimeLimit(getHourMin("05:00"));
        distance.setPrice(2000);
        distance.setMaxNumberOfCompetitor(1000);
        distance.setDescription("2021 elhozza Nektek a BükKiNyúlsz! legújabb túratávját, mely a 10 km-es Nyuszifül! A körtúra a Látogatóközponttól a Cigány-dűlő felé indul Cserépfalu irányába, majd a Berezdaljai Pincesorra kanyarodva vezet el bennünket a Millenniumi-kilátóhoz - és a pazar cserépi panorámához! Innen a Kő-völgy tanösvényen haladva érintjük a Berezd-tetőt és Mész-tetőt, további csodás kilátásokat és fotópontokat élvezve! Innen érintjük az ikonikus Ördögtorony kaptárkövét, majd visszatérünk a Látogatóközponthoz a megérdemelt pihenésért!\n" +
                "A túraútvonal könnyű, családosan is teljesíthető nyomon vezet, tökéletes aktív program kezdő természetjáróknak, vagy akár a terepfutással most ismerkedőknek is.");
        distance.setReceiptOfItinerary("Autóval érkezőknek: Cserépfalut Bükkzsérc irányába elhagyva, kb. 1km-re jobbra, a Hór-völgy bejáratánál\n" +
                "Tömegközlekedéssel érkezőknek: Bükkzsérc felé tartó autóbuszok, „Hórvölgyi útelágazás” megállóhely");
        distance.setReceiptOfItineraryFrom(getDateTime("2023-10-09 09:00"));
        distance.setReceiptOfItineraryTo(getDateTime("2023-10-09 15:00"));
        distance.setTimekeepingType("Ellenőrzőpontokon bélyegzés, számítógépes rajt és célidő rögzítés, ezzel párhuzamosan opcionálisan GeoGo-s teljesítés (nevezéskor szükséges előrejelezni)");
        distance.setServices("Frissítés útközben:\n" +
                "\n" +
                "Mész-tető: étel és ital frissítés\n" +
                "A célban (megegyezik a rajt helyszínével):\n" +
                "\n" +
                "frissítő\n" +
                "melegétel (opcionális)\n" +
                "oklevél, kitűző");
        distanceList.add(this.distanceService.add(distance));
    }

    private Date getDate(String s)  {
        try{
            return formatterYMD.parse(s);
        }catch (Exception ex){
            return null;
        }
    }
    private Date getDateTime(String s)  {
        try{
            return formatterYMDHM.parse(s);
        }catch (Exception ex){
            return null;
        }
    }    private Date getHourMin(String s)  {
        try{
            return formatterHM.parse(s);
        }catch (Exception ex){
            return null;
        }
    }
}
