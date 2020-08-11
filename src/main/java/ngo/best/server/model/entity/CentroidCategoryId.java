package ngo.best.server.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author Ioana
 */

@Embeddable
public class CentroidCategoryId implements Serializable {

    private Centroid centroid;
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    public Centroid getCentroid() {
        return centroid;
    }

    public void setCentroid(Centroid centroid) {
        this.centroid = centroid;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
