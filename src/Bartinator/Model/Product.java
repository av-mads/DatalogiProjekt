package Bartinator.Model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private double price;
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> descriptions = new HashMap<>();
    @ManyToOne
    private Category category;

    public Product(){};

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Map<String, String> getDescriptions() {
        return descriptions;
    }
    public void setDescriptions(Map<String, String> descriptions) {
        this.descriptions = descriptions;
    }
    public void setDescription(String key, String value){
        if(descriptions.containsKey(key)){
            descriptions.replace(key, value);
        } else {
            descriptions.put(key, value);
        }
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (id != product.id) return false;
		return name.equals(product.name);
	}

	@Override
    public String toString() {
        return "Product{" +
                "ID=" + getId() +
                ", name='" + getName() + '\'' +
                ", category='" + getCategory() + '\'' +
                ", price=" + getPrice() +
                ", descriptions=" + getDescriptions() +
                '}';
    }
}
