package it.laterale.cloud.dtos;

import java.io.Serializable;

/**
 * The type Team dto.
 */
public class TeamDto implements Serializable {

    private String name;
    private int members;
    private String primaryColor;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets members.
     *
     * @return the members
     */
    public int getMembers() {
        return members;
    }

    /**
     * Sets members.
     *
     * @param members the members
     */
    public void setMembers(int members) {
        this.members = members;
    }

    /**
     * Gets primary color.
     *
     * @return the primary color
     */
    public String getPrimaryColor() {
        return primaryColor;
    }

    /**
     * Sets primary color.
     *
     * @param primaryColor the primary color
     */
    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }
}
