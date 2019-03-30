package com.devcomanda.easymeetup.controller.converters;

import com.devcomanda.easymeetup.model.entity.enums.Status;

import java.beans.PropertyEditorSupport;

public class StatusConverter extends PropertyEditorSupport {

    public void setAsText(final String text) throws IllegalArgumentException {
        setValue(Status.fromText(text));
    }

}
