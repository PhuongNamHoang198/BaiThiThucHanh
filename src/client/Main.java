package client;

import controller.ProductManager;
import model.Product;
import storage.ReadWriteData;
import storage.ReadWriteDataInterface;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static ProductManager productManager = new ProductManager();
    static ReadWriteDataInterface readWrite = ReadWriteData.getInstance();

    public static void main(String[] args) {
        menuOfProduct();
    }

    public static void menuOfProduct() {
        try {
            do {

                System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM ----");
                System.out.println("Chọn chức năng theo số (để tiếp tục)");
                System.out.println("1. Xem danh sách");
                System.out.println("2. Thêm mới");
                System.out.println("3. Cập nhật");
                System.out.println("4. Xóa");
                System.out.println("5. Sắp xếp");
                System.out.println("6. Tìm sản phẩm có giá đắt nhất");
                System.out.println("7. Ghi file");
                System.out.println("8. Đọc file");
                System.out.println("9. Thoát");
                System.out.println("-----------------------------------------------");
                System.out.println("Mời bạn nhập lựa chọn: ");
                Scanner scanner = new Scanner(System.in);
                Scanner scanner1 = new Scanner(System.in);
                int num = scanner1.nextInt();
                switch (num) {
                    case 1:
                        productManager.display();
                        break;
                    case 2:
                        try {
                            System.out.print("Mời bạn nhập ID: ");
                            String ID = scanner.nextLine();
                            System.out.print("Mời bạn nhập tên: ");
                            String name = scanner.nextLine();
                            System.out.print("Mời bạn nhập giá: ");
                            double price = scanner1.nextDouble();
                            System.out.print("Mời bạn nhập quantity: ");
                            int quantity = scanner1.nextInt();
                            System.out.print("Mời bạn nhập mô tả: ");
                            String dsecription = scanner.nextLine();
                            Product product = new Product(ID, name, price, quantity, dsecription);
                            productManager.addProduct(product);
                        } catch (InputMismatchException e) {
                            System.out.println("Sai kiểu dữ liệu");
                            System.out.println("-------------------------------");
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("Mời bạn nhập ID");
                            String id = scanner.nextLine();
                            int check = productManager.checkID(id);
                            if (check == -1) {
                                System.out.println("Không có sản phẩm theo ID");
                            } else {
                                System.out.println("Mời bạn nhập ID mới");
                                String newID = scanner1.nextLine();
                                System.out.println("Mời bạn nhập tên mới");
                                String newName = scanner.nextLine();
                                System.out.println("Mời bạn nhập giá mới");
                                double newPrice = scanner1.nextDouble();
                                System.out.println("Mời bạn nhập số lượng mới");
                                int newQuantity = scanner.nextInt();
                                System.out.println("Mời bạn nhập mô tả mới");
                                String newDes = scanner1.nextLine();
                                Product product = new Product(newID, newName, newPrice, newQuantity, newDes);
                                productManager.editProduct(check, product);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Bạn đã nhập sai dữ liệu");
                            System.out.println("------------------------------");
                        }
                        break;
                    case 4:
                        try {
                            System.out.print("Mời bạn nhập ID: ");
                            String id = scanner.nextLine();
                            int check = productManager.checkID(id);
                            if (check == -1) {
                                System.out.println("Không có sản phẩm theo ID");
                                System.out.println("----------------------------------");
                            } else {
                                productManager.deleteProduct(check);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Bạn đã nhập sai dữ liệu");
                            System.out.println("------------------------------");
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("1. Sắp xếp");
                            System.out.println("2. Hiển thị danh sách đã sắp xếp");
                            System.out.println("3. Trở về menu chính");
                            int case5 = scanner.nextInt();
                            switch (case5){
                                case 1:
                                    productManager.sortByPriceLowtoHight();
                                    break;
                                case 2:
                                    productManager.display();
                                    break;
                                case 3:
                                    menuOfProduct();
                                    break;
                            }
                        } catch (InputMismatchException e){
                            System.out.println("Bạn đã nhập sai dữ liệu");
                            System.out.println("------------------------------");
                        }
                        break;
                    case 6:
                        productManager.sortByPriceLowtoHight();
                        List<Product> productsListInMain = productManager.productList;
                        for (int i = 0; i < productsListInMain.size(); i++) {
                            System.out.println(productsListInMain.get(productsListInMain.size()-1));
                        }
                        break;
                    case 7:
                        readWrite.writeData(productManager.productList);
                        break;
                    case 8:
                        productManager.productList = readWrite.readData();
                        break;
                    case 9:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Lựa chọn không tồn tại, mời bạn nhập lại !!!");
                        break;
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Bạn nhập sai dữ liệu");
            System.out.println("------------------------------");
            menuOfProduct();
        }
    }
}
