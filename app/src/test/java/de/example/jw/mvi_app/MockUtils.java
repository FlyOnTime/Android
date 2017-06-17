package de.example.jw.mvi_app;

import java.util.ArrayList;
import java.util.List;

import de.example.jw.mvi_app.model.data.Programs;
import de.example.jw.mvi_app.model.data.SchoolsModel;

/**
 * Created by JW on 05.05.2017.
 */

public class MockUtils {

    static List<SchoolsModel> generateFakeList() {
        List<SchoolsModel> items = new ArrayList<>();

        items.add(fakeSchool());

        return items;
    }

    static SchoolsModel fakeSchool() {
        List<String> programs = new ArrayList<>();
        programs.add("Be smart - don't Start");
        return new SchoolsModel("Heilwig Gymnasium", "1", "Wilhelm-Metzger-Straße", "Gymnasium", 1, "+49 40 428 86-8910", "+49 40 428 86-890", "http://www.heilwig.hamburg.de", "Heilwig-Gymnasium@bsb.hamburg.de", null, "HH", new Programs(programs), true, 9.998888, 53.604958);
    }

    static SchoolsModel fakeSchool(String name) {
        List<String> programs = new ArrayList<>();
        return new SchoolsModel(name, "1", "Wilhelm-Metzger-Straße", "Gymnasium", 1, "+49 40 428 86-8910", "+49 40 428 86-890", "http://www.heilwig.hamburg.de", "Heilwig-Gymnasium@bsb.hamburg.de", null, "HH", new Programs(programs), true, 9.998888, 53.604958);
    }

}
