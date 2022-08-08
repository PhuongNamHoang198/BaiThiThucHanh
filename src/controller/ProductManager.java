package controller;

import model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductManager {
    public List<Product> productList = new ArrayList<>();
//    private ReadWriteData readWriteData = ReadWriteData.getInstance();

    public void display(){
        for (Product x : productList
             ) {
            System.out.println(x.toString());
        }
    }
    public void addProduct(Product product) {
        productList.add(product);
    }

    public void editProduct(int id, Product product) {
        productList.set(id,product);
    }

    public void deleteProduct(int id_product) {
        productList.remove(id_product);
    }

    public void deleteAll() {
        productList.removeAll(productList);
    }

    public int checkID(String id){
        int check = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (id.equals(productList.get(i).getID())){
                check = i;
            }
        }
        return check;
    }

    public void sortByPriceLowtoHight(){
        productList.sort(Comparator.comparingDouble(Product::getPrice));
    }
}
