package ngo.best.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Ioana
 */

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.user",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "primaryKey.category",
                joinColumns = @JoinColumn(name = "category_id")) })
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "primaryKey")
public class UserCategory {

    private UserCategoryId primaryKey = new UserCategoryId();

    private Double grade;

    @EmbeddedId
    @JsonIgnore
    public UserCategoryId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(UserCategoryId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public User getUser() {
        return getPrimaryKey().getUser();
    }

    public void setUser(User user) {
        getPrimaryKey().setUser(user);
    }

    @Transient
    public Category getCategory() { return getPrimaryKey().getCategory(); }

    public void setCategory(Category category) {
        getPrimaryKey().setCategory(category);
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}