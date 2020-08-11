package ngo.best.server.model.dto;

/**
 * @author Ioana
 */

public class EventDTO {

    private String name;
    private int firstYear;
    private String description;

    public EventDTO() {
    }

    public EventDTO(String name, int firstYear, String description) {
        this.name = name;
        this.firstYear = firstYear;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
