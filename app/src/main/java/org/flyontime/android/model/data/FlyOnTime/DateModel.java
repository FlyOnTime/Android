package org.flyontime.android.model.data.FlyOnTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jossi on 18.06.2017.
 */

public class DateModel extends DashboardModelInterface {

    private Date date;

    public DateModel(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getShortenedDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM hh:mm");
        return simpleDateFormat.format(date);
    }
}
