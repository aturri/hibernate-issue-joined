package org.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TestDetailEntity {

    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne(optional = false)
    public TestSpecializedEntity parent;

    public String anAttribute;

    public TestDetailEntity() {
    }

    public TestDetailEntity(final TestSpecializedEntity parent, final String anAttribute) {
        this.parent = parent;
        this.parent.details.add(this);
        this.anAttribute = anAttribute;
    }
}
