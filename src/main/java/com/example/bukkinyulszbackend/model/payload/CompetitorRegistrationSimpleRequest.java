package com.example.bukkinyulszbackend.model.payload;

import com.example.bukkinyulszbackend.model.BillingDetail;
import com.example.bukkinyulszbackend.model.CompetitorRegistration;
import com.example.bukkinyulszbackend.model.TourEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CompetitorRegistrationSimpleRequest {
    private List<CompetitorRegistration> competitorRegistrations;
    private BillingDetail billingDetail;
}
