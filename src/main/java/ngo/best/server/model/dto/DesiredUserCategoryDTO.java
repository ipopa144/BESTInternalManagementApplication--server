package ngo.best.server.model.dto;

/**
 * @author Ioana
 */

public class DesiredUserCategoryDTO {

    String categoryName;
    Double grade;

    public DesiredUserCategoryDTO() {
    }

    public DesiredUserCategoryDTO(String categoryName, Double grade) {
        this.categoryName = categoryName;
        this.grade = grade;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

}
