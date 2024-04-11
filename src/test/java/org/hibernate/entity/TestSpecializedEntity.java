package org.hibernate.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class TestSpecializedEntity extends TestBaseEntity {
    public String anotherAttribute;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    public List<TestDetailEntity> details = new ArrayList<>();
}
