package ngo.best.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Ioana
 */

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.category",
                joinColumns = @JoinColumn(name = "category_id")),
        @AssociationOverride(name = "primaryKey.centroid",
                joinColumns = @JoinColumn(name = "centroid_id")) })
public class CentroidCategory {

    private CentroidCategoryId primaryKey = new CentroidCategoryId();

    private Double meanValue;

    @EmbeddedId
    @JsonIgnore
    public CentroidCategoryId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(CentroidCategoryId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Centroid getCentroid() {
        return getPrimaryKey().getCentroid();
    }

    public void setCentroid(Centroid centroid) {
        getPrimaryKey().setCentroid(centroid);
    }

    @Transient
    public Category getCategory() { return getPrimaryKey().getCategory(); }

    public void setCategory(Category category) {
        getPrimaryKey().setCategory(category);
    }

    public Double getMeanValue() {
        return meanValue;
    }

    public void setMeanValue(Double meanValue) {
        this.meanValue = meanValue;
    }
}
