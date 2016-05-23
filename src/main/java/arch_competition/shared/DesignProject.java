package arch_competition.shared;

import java.io.Serializable;
import java.util.Date;

/**
 */
public class DesignProject implements Serializable {
    private String name;
    private String description;
    private String picture;
    private String votes;
    private Date creationDate;
    private String id;

    @Override
    public String toString() {

        return "NAME "+ name + "  DESCRIPTION  "+ description+ " ID " +id + " Creation Date" + creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
