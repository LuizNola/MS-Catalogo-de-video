package com.admin.catalogo.domain.category;

import com.admin.catalogo.domain.AggregateRoot;
import com.admin.catalogo.domain.Identifier;
import com.admin.catalogo.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.UUID;

public class Category extends AggregateRoot<CategoryId> {


    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            final CategoryId id,
            final String name,
            final String description,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Category newCategory(final String aName, final String aDescription, final boolean aActive) {

        final var id = CategoryId.unique();
        final var now = Instant.now();
        final var deletedAt = aActive ? null : now;
        return new Category(id, aName, aDescription, aActive, now, now, deletedAt);
    }


    public Category deactivate(){
        if(getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.active = false;
        this.updatedAt = Instant.now();

        return  this;
    }

    public Category activate(){
        if(getDeletedAt() != null) {
            this.deletedAt = null;
        }
        this.active = true;
        this.updatedAt = Instant.now();

        return  this;
    }

    public Category update(final String aName, final String aDescription, final boolean isActive){
        this.name = aName;
        this.description = aDescription;
        if(isActive){
            activate();
        }else{
            deactivate();
        }
        this.updatedAt = Instant.now();

        return this;
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public CategoryId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }


}
