
package org.flyontime.android.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class Programs {

    @SerializedName("programs")
    @Expose
    private List<String> programs = null;

    public Programs(List<String> programs) {
        this.programs = programs;
    }

    public List<String> getPrograms() {
        return programs;
    }

    public void setPrograms(List<String> programs) {
        this.programs = programs;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(programs).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Programs)) {
            return false;
        }
        Programs rhs = ((Programs) other);
        return new EqualsBuilder().append(programs, rhs.programs).isEquals();
    }

}
