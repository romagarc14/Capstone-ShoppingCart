package com.company.shoppingcart.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Float price;
    private Boolean isImported;
    private String category;
    private String imageUrl;
    private Integer quantity;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getImported() {
        return isImported;
    }

    public void setImported(Boolean imported) {
        isImported = imported;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(getId(), item.getId()) &&
                Objects.equals(getName(), item.getName()) &&
                Objects.equals(getPrice(), item.getPrice()) &&
                Objects.equals(getImported(), item.getImported()) &&
                Objects.equals(getCategory(), item.getCategory()) &&
                Objects.equals(getImageUrl(), item.getImageUrl()) &&
                Objects.equals(getQuantity(), item.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getImported(), getCategory(), getImageUrl(), getQuantity());
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + id +
                ", itemName='" + name + '\'' +
                ", itemPrice='" + price + '\'' +
                ", itemImported'" + isImported + '\'' +
                ", itemCategory'" + category + '\'' +
                ", itemImageUrl'" + imageUrl + '\'' +
                ", itemQuantity'" + quantity +
                '}';
    }
}




