package com.admin.catalogo.domain.validation.handler;

import com.admin.catalogo.domain.exceptions.DomainException;
import com.admin.catalogo.domain.validation.Error;
import com.admin.catalogo.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification  implements ValidationHandler {

    private List<Error> errors;

    private Notification(final List<Error> errors){
        this.errors = errors;
    }

    public static Notification create(){
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Error anError ){
        return new Notification(new ArrayList<>()).append(anError);
    }

    public static Notification create(final Throwable t ){
        return create(new Error(t.getMessage()));
    }


    @Override
    public Notification append(Error anError) {

        this.errors.add(anError);
        return this;
    }

    @Override
    public Notification append(final ValidationHandler anHandler) {
        this.errors.addAll(anHandler.getErrors());
        return this;
    }

    @Override
    public Notification validate(Validation anValidation) {

        try {
            anValidation.validate();
        }catch (final DomainException ex){
            this.errors.addAll(ex.getErrors());
        }catch (final Throwable t){
            this.errors.add(new Error(t.getMessage()));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }
}
