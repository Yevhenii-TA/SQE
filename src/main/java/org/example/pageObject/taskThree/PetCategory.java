package org.example.pageObject.taskThree;

public class PetCategory {
    private long id;
    private String name;
    public PetCategory() {}
    public long getId() {
        return id;
    }
    public PetCategory setId(long id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public PetCategory setName(String name) {
        this.name = name;
        return this;
    }
}
