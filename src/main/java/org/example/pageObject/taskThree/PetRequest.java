package org.example.pageObject.taskThree;

import java.util.List;

public class PetRequest {
    private long id;
    private PetCategory category;
    private String name;
    private List<String> photoUrls;
    private List<PetTags> tags;
    private String status;

    public PetRequest() {}
    public long getId() {
        return id;
    }
    public PetRequest setId(long id) {
        this.id = id;
        return this;
    }
    public PetCategory getCategory() {
        return category;
    }
    public PetRequest setPetCategory(PetCategory category) {
        this.category = category;
        return this;
    }
    public String getName() {
        return name;
    }
    public PetRequest setName(String name) {
        this.name = name;
        return this;
    }
    public List<String> getPhotoUrls() {
        return photoUrls;
    }
    public PetRequest setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }
    public List<PetTags> getTags() {
        return tags;
    }
    public PetRequest setTags(List<PetTags> tags) {
        this.tags = tags;
        return this;
    }
    public String getStatus() {
        return status;
    }
    public PetRequest setStatus(String status) {
        this.status = status;
        return this;
    }
}
