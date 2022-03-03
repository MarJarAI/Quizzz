package commons;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long activityID;

    public String id; //id provided by activity-bank (i.e. "00-shower")

    public String image_path;
    public String title;
    public int consumption_in_wh;
    public String source;

    @SuppressWarnings("unused")
    public Activity() {

    }

    public Activity(String id, String image_path, String title, int consumption_in_wh, String source) {
        this.id = id;
        this.image_path = image_path;
        if(image_path == null) image_path = "";
        this.title = title;
        this.consumption_in_wh = consumption_in_wh;
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        return new EqualsBuilder()
                .append(activityID, activity.activityID)
                .append(consumption_in_wh, activity.consumption_in_wh)
                .append(id, activity.id)
                .append(image_path, activity.image_path)
                .append(title, activity.title)
                .append(source, activity.source)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(activityID)
                .append(id)
                .append(image_path)
                .append(title)
                .append(consumption_in_wh)
                .append(source)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("activityID", activityID)
                .append("id", id)
                .append("image_path", image_path)
                .append("title", title)
                .append("consumption_in_wh", consumption_in_wh)
                .append("source", source)
                .toString();
    }

    public long getId() {
        return activityID;
    }

}
