package org.example.pageObject.taskThree;

public class PetTags {
    private long id;
    private String name;
    public PetTags() {}
    public long getId() {
        return id;
    }
    public PetTags setId(long id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public PetTags setName(String name) {
        this.name = name;
        return this;
    }
}
