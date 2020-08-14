package ngo.best.server.model.dto;

/**
 * @author Ioana
 */

public class NotificationCategoryDTO {

    private Double grade;
    private String category;

    public NotificationCategoryDTO() {
    }

    public NotificationCategoryDTO(Double grade, String category) {
        this.grade = grade;
        this.category = category;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
