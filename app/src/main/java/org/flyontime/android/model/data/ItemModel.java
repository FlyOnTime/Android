package org.flyontime.android.model.data;

/**
 * Created by jossi on 17.06.2017.
 */

public class ItemModel extends DashboardModelInterface {
    private String cardTitle;
    private String cardContent;
    private String timelinestopContent;
    private boolean isActive;

    public ItemModel(String cardTitle, String cardContent, String timelinestopContent, boolean isActive) {
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
        this.timelinestopContent = timelinestopContent;
        this.isActive = isActive;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardContent() {
        return cardContent;
    }

    public String getTimelinestopContent() {
        return timelinestopContent;
    }

    public boolean isActive() {
        return isActive;
    }
}
