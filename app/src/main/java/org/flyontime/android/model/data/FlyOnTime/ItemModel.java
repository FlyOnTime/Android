package org.flyontime.android.model.data.FlyOnTime;

import org.flyontime.android.ui.adapter.CardType;

/**
 * Created by jossi on 17.06.2017.
 */

public class ItemModel extends DashboardModelInterface {
    private String cardTitle;
    private String cardContent;
    private String timelinestopContent;
    private boolean isActive;
    private CardType cardType;

    private int numOfBags;
    private int maxWeightPerBag;

    private int baggageDropQueuingTime;
    private int baggageDropPredAccuracy;
    private String baggageDropOpen;
    private String baggageDropClose;

    private int securityWaitingTime;
    private int securityPredAccuracy;

    public ItemModel(String cardTitle, String timelinestopContent, boolean isActive, CardType cardType, int numOfBags, int maxWeightPerBag, int baggageDropQueuingTime, int baggageDropPredAccuracy, String baggageDropOpen, String baggageDropClose, int securityWaitingTime, int securityPredAccuracy) {
        this.cardTitle = cardTitle;
        this.timelinestopContent = timelinestopContent;
        this.isActive = isActive;
        this.cardType = cardType;
        this.numOfBags = numOfBags;
        this.maxWeightPerBag = maxWeightPerBag;
        this.baggageDropQueuingTime = baggageDropQueuingTime;
        this.baggageDropPredAccuracy = baggageDropPredAccuracy;
        this.baggageDropOpen = baggageDropOpen;
        this.baggageDropClose = baggageDropClose;
        this.securityWaitingTime = securityWaitingTime;
        this.securityPredAccuracy = securityPredAccuracy;
        this.cardContent = getCardContent();
    }

    public ItemModel(String cardTitle, String timelinestopContent, boolean isActive, CardType cardType, int baggageDropQueuingTime, int baggageDropPredAccuracy, String baggageDropOpen, String baggageDropClose) {
        this.cardTitle = cardTitle;
        this.timelinestopContent = timelinestopContent;
        this.isActive = isActive;
        this.cardType = cardType;
        this.baggageDropQueuingTime = baggageDropQueuingTime;
        this.baggageDropPredAccuracy = baggageDropPredAccuracy;
        this.baggageDropOpen = baggageDropOpen;
        this.baggageDropClose = baggageDropClose;
        this.cardContent = getCardContent();
    }

    public ItemModel(String cardTitle, String timelinestopContent, boolean isActive, CardType cardType, int securityWaitingTime, int securityPredAccuracy) {
        this.cardTitle = cardTitle;
        this.timelinestopContent = timelinestopContent;
        this.isActive = isActive;
        this.cardType = cardType;
        this.securityWaitingTime = securityWaitingTime;
        this.securityPredAccuracy = securityPredAccuracy;
        this.cardContent = getCardContent();
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardContent() {
        String content = "";
        try {
            switch (cardType) {
                case CHECKIN:
                    content = "check more luggage, buy seats and select preferred services";
                    break;
                case PREPARE:
                    content = "checked luggage: " + String.valueOf(this.numOfBags) + " bags (max. " + String.valueOf(this.maxWeightPerBag) + "kg/bag)";
                    break;
                case COMMUTE:
                    content = "Commute goes here :)";
                    break;
                case BAGGAGEDROP:
                    content = "estimated queuing time: " + String.valueOf(baggageDropQueuingTime) + " mins (" + String.valueOf(baggageDropPredAccuracy) + " % accuracy) \n open " + baggageDropOpen + " - " + baggageDropClose;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public String getTimelinestopContent() {
        return timelinestopContent;
    }

    public boolean isActive() {
        return isActive;
    }

    public CardType getCardType() {
        return cardType;
    }

    public int getNumOfBags() {
        return numOfBags;
    }

    public int getMaxWeightPerBag() {
        return maxWeightPerBag;
    }

    public int getBaggageDropQueuingTime() {
        return baggageDropQueuingTime;
    }

    public int getBaggageDropPredAccuracy() {
        return baggageDropPredAccuracy;
    }

    public String getBaggageDropOpen() {
        return baggageDropOpen;
    }

    public String getBaggageDropClose() {
        return baggageDropClose;
    }

    public int getSecurityWaitingTime() {
        return securityWaitingTime;
    }

    public int getSecurityPredAccuracy() {
        return securityPredAccuracy;
    }
}
