package com.raven43.simplestore.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Item {

    @Value("${simplestore.upload.path}")
    private static String FILE_SRC;

    @Id
    @GeneratedValue
    private Long id;

    @Length(max = 32)
    private String name;

    @Length(max = 4096)
    private String description;

    @Length(max = 128)
    private String imgName;

    @Length(max = 32)
    private String category;

    //@CollectionTable(name = "item_tags", joinColumns = @JoinColumn(name = "item_id"))
    private String[] tags;

    private Long price;

    @OneToOne(targetEntity = Topic.class, cascade = CascadeType.ALL)
    private Topic topic;



    public Item() {
        topic = new Topic();
    }

    public Item(
            @Length(max = 32) String name,
            @Length(max = 4096) String description,
            @Length(max = 128) @Nullable String imgName,
            @Length(max = 32) String category,
            String[] tags,
            Long price
    ) {
        this.name = name;
        this.description = description;
        this.imgName = imgName == null ? FILE_SRC + "default.png" : imgName;
        this.category = category;
        this.tags = tags;
        this.price = price;
        topic = new Topic();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }


    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Long getPrice() {
        return price;
    }
    public String getPriceS() {
        return price.toString();
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgName='" + imgName + '\'' +
                ", category='" + category + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", price=" + price +
                '}';
    }
}
