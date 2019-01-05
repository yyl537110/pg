package com.slj.pg.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Dict {
    private Integer id;

    @NotBlank
    private String type;

    @NotBlank
    private String text;

    @NotNull
    private Integer value;

    @NotNull
    private Integer seq;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}