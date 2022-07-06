package com.example.workflow.group3;

import java.io.Serializable;

public class Candidate implements Serializable {

    private String name;
    private boolean selected = false;

    public Candidate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
