package com.devcomanda.easymeetup.model.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@ToString
@Getter
public enum Status {
    NEW("NEW"),
    IN_PROGRESS("IN_PROGRESS"),
    CLOSED("CLOSED"),
    DONE("DONE");

    private String text;

    Status(String text) {
        this.text = text;
    }

    @JsonCreator
    public static Status fromText(String text){
        for(Status s : Status.values()){
            if(s.getText().equals(text)){
                return s;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type: " + text + "You can choose from: " + Arrays.toString(Status.values())
        );
    }

}
