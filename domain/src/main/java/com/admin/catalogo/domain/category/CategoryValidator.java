package com.admin.catalogo.domain.category;

import com.admin.catalogo.domain.validation.Error;
import com.admin.catalogo.domain.validation.ValidationHandler;
import com.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    protected CategoryValidator(final Category aCategory, ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameContraints();
    }

    private void checkNameContraints() {
        final var name = this.category.getName();
        if (name == null){
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank() || name.isEmpty()){
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }
        final var length = name.trim().length();
        if ( length > 255 || length < 3){
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }


}
