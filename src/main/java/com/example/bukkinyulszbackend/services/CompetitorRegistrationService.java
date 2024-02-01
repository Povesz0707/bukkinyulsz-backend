package com.example.bukkinyulszbackend.services;

import com.example.bukkinyulszbackend.exception.BusinessException;
import com.example.bukkinyulszbackend.model.BillingDetail;
import com.example.bukkinyulszbackend.model.CompetitorRegistration;
import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.model.TourEvent;
import com.example.bukkinyulszbackend.model.payload.CompetitorRegistrationSimpleRequest;
import com.example.bukkinyulszbackend.repository.CompetitorRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CompetitorRegistrationService implements BaseServiceInterface<CompetitorRegistration>{
    private CompetitorRegistrationRepository competitorRegistrationRepository;
    private BillingDetailService billingDetailService;

    private DistanceService distanceService;
    private TourEventService tourEventService;

    @Autowired
    private void setTourEventService(TourEventService tourEventService) {
        this.tourEventService = tourEventService;
    }

    @Autowired
    private void setDistanceService(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @Autowired
    private void setBillingDetailService(BillingDetailService billingDetailService) {
        this.billingDetailService = billingDetailService;
    }

    @Autowired
    private void setCompetitorRegistrationRepository(CompetitorRegistrationRepository competitorRegistrationRepository) {
        this.competitorRegistrationRepository = competitorRegistrationRepository;
    }

    @Transactional(rollbackFor = BusinessException.class)
    public List<CompetitorRegistration> competitorRegistration(CompetitorRegistrationSimpleRequest registrationSimpleRequest){
        List<CompetitorRegistration> list = new ArrayList<>();
        final String registrationUUID = UUID.randomUUID().toString();

        if(registrationSimpleRequest.getCompetitorRegistrations() != null){
            for(CompetitorRegistration registration : registrationSimpleRequest.getCompetitorRegistrations()){
                final TourEvent selectedTourEvent = this.tourEventService.getById(registration.getTourEvent().getId());
                Distance selectedDistance = distanceService.getById(registration.getDistance().getId());
                registration.setDistance(selectedDistance);
                registration.setTourEvent(selectedTourEvent);
                registration.setRegistrationUUID(registrationUUID);
                CompetitorRegistration firstRegistration = this.competitorRegistrationRepository.save(registration);
                firstRegistration.setBarcode(generateBarcode(firstRegistration));
                CompetitorRegistration finalRegistration = this.competitorRegistrationRepository.save(firstRegistration);
                list.add(finalRegistration);
            }
        }
        if(registrationSimpleRequest.getBillingDetail() != null){
            System.out.println(registrationSimpleRequest.getBillingDetail());
            BillingDetail incomingBillingDetail = registrationSimpleRequest.getBillingDetail();;
            incomingBillingDetail.setRegistrationUUID(registrationUUID);
            final BillingDetail savedBillingDetail = this.billingDetailService.add(incomingBillingDetail);
        }
        return list;
    }

    private String generateBarcode(CompetitorRegistration registration){
        String distance = String.format("%.0f", registration.getDistance().getLength());;
        String result = (registration.getTourEvent().getDateOfEvent().getYear() + 1900)+"/"+distance+"/"+registration.getId();
        if(registration.getCatering() != null || registration.getShirt() != null){
            result += "-";
            if(registration.getCatering() != null){
                result += "E";
            }
            if(registration.getShirt() != null){
                result += "P";
            }
        }
        return result;
    }

    @Override
    public Boolean delete(long id) throws BusinessException {
        return null;
    }

    @Override
    public CompetitorRegistration add(CompetitorRegistration data) throws BusinessException {
        return null;
    }

    @Override
    public List<CompetitorRegistration> list() throws BusinessException {
        return null;
    }

    @Override
    public CompetitorRegistration getById(long id) throws BusinessException {
        return null;
    }

    @Override
    public CompetitorRegistration edit(CompetitorRegistration data) throws BusinessException {
        return null;
    }
}
