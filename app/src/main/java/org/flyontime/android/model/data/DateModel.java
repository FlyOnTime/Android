package org.flyontime.android.model.data;

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
}
