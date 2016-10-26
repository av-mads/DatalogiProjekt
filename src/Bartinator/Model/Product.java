package Bartinator.Model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column
    private String name, category;
    @Column
    private double price;
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> descriptions = new HashMap<>();
    @ManyToOne
    private Category cat;

    public Category getCat() {
        return cat;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String key, String value){
        if(descriptions.containsKey(key)){
            descriptions.replace(key, value);
        } else {
            descriptions.put(key, value);
        }
    }

    public Map<String, String> getDescriptions() {
        return descriptions;
    }
/*
    public void actionHandler() throws IOException {
        Stage stage = new Stage();
        System.out.println(id);
        Parent root1;
        root1 = FXMLLoader.load(loginController.class.getResource("../View/btnCreateMenu.fxml"));
        stage.setScene(new Scene(root1, 500, 500));
        stage.initModality(Modality.APPLICATION_MODAL);
        btnCreateController.productID = getId();
        stage.show();
    }*/

    @Override
    public String toString() {
        return "Product{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", descriptions=" + descriptions +
                '}';
    }
}
