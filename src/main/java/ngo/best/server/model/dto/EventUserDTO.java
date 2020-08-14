package ngo.best.server.model.dto;

/**
 * @author Ioana
 */

public class EventUserDTO {

    private Long id;
    private String name;
    private int firstYear;
    private String description;

    public EventUserDTO() {
    }

    public EventUserDTO(Long id, String name, int firstYear, String description) {
        this.id = id;
        this.name = name;
        this.firstYear = firstYear;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
