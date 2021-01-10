package ru.itmo.wp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Tag {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Tag.Name name;

    public Tag() {
    }

    public Tag(@NotNull Tag.Name name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tag.Name getName() {
        return name;
    }

    public void setName(Tag.Name name) {
        this.name = name;
    }

    public enum Name {
        TAGRED,
        TAGYELLOW
    }
}
