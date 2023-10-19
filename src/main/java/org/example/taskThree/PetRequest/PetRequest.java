package org.example.taskThree.PetRequest;

import java.util.List;

public class PetRequest {
    public static final String CREATE_PET_ENDPOINT = "/pet";
    private long id;
    private PetCategory category;
    private String name;
    private List<String> photoUrls;
    private List<PetTags> tags;
    private String status;

    public PetRequest() {}
    public PetRequest(long id, PetCategory category, String name, List<String> photoUrls, List<PetTags> tags, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public PetCategory getCategory() {
        return category;
    }
    public void setPetCategory(PetCategory category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getPhotoUrls() {
        return photoUrls;
    }
    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }
    public List<PetTags> getTags() {
        return tags;
    }
    public void setTags(List<PetTags> tags) {
        this.tags = tags;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
