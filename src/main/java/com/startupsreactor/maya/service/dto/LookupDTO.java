package com.startupsreactor.maya.service.dto;

import com.startupsreactor.maya.domain.Lookup;

public class LookupDTO extends BaseDTO {

    private Long id;
    private String name;
    private String value;
    private String lang;
    private String description;
    private LookupDTO parent;

    public LookupDTO() {}

    public LookupDTO(Lookup lookup) {
        this.id = lookup.getId();
        this.name = lookup.getName();
        this.value = lookup.getValue();
        this.lang = lookup.getLang();
        this.description = lookup.getDescription();
        this.parent = lookup.getParent() != null ? new LookupDTO(lookup.getParent()) : null;
    }

    public Long getId() {
        return this.id;
    }

    public LookupDTO id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public LookupDTO name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return this.lang;
    }

    public LookupDTO lang(String lang) {
        this.setLang(lang);
        return this;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getValue() {
        return this.value;
    }

    public LookupDTO value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return this.description;
    }

    public LookupDTO description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LookupDTO getParent() {
        return this.parent;
    }

    public void setParent(LookupDTO lookup) {
        this.parent = lookup;
    }

    public LookupDTO parent(LookupDTO lookup) {
        this.setParent(lookup);
        return this;
    }
}
