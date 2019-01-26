package assignment2_dbt;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import models.*;
import repositories.*;

/**
 *
 * @author isami
 */
public class ShoeShopController {
    
    private Map<Integer, Customer> customerMap;
    private Map<Integer, ShoeModel> shoemodelMap;
    private Map<Integer, Shoe> shoeMap;
    private List<CartProduct> cartList;
    
    public ShoeShopController(){
        customerMap = CustomerRepo.getAllCustomersAsMap();
        shoemodelMap = ShoeModelRepo.getAllShoeModelsAsMap();
    }
    
    public void showAllCustomers(){
 
        for(Integer key : customerMap.keySet()){
            Customer c = customerMap.get(key);
            System.out.println(key + ": " + c.getFirstName() + " " + c.getLastName());
        }
    }
    
    public void showAllShoemodels(){
        
        for(Integer key : shoemodelMap.keySet()){
            ShoeModel sm = shoemodelMap.get(key);
            System.out.println(key + ": " + sm.getName() + " " 
                    + sm.getColors().stream().map(Color::getName).collect(Collectors.toList()));
        }
    }
    
    public void showSelectedShoeModelInfo(){
        
        shoeMap.keySet().forEach((key) -> {
            Shoe s = shoeMap.get(key);
            System.out.println(key + ": size[" + s.getSize()+ "] price[" + s.getPrice() + "]");
        });
    }
    
    public Customer chooseCustomer(){
        Customer customer = null;
        showAllCustomers();
        System.out.println("Choose a customer and input a number: ");
        while(customer == null){           
            int num = takeNumber();
            if(customerMap.containsKey(num)){
                customer = customerMap.get(num);
            } else {
                System.out.println("Invalid number. Try again.");
            }            
        }        
        return customer;
    }
    
    public ShoeModel chooseShoeModel(){
        ShoeModel shoemodel = null;
        int num;
        showAllShoemodels();
        System.out.println("Chose a shoemodel and input a number: ");
        while(shoemodel == null){           
            num = takeNumber();
            if(shoemodelMap.containsKey(num)){
                shoemodel = shoemodelMap.get(num);
            } else {
                System.out.println("Invalid number. Try again.");
            }
        }
        return shoemodel;
    }
    
    public Shoe chooseShoe(ShoeModel shoemodel){
        Shoe shoe = null;
        int num;        
        shoeMap = ShoeRepo.getShoesByShoeModelId(shoemodel.getId());
        System.out.println("Chose a size and input a number: ");
        showSelectedShoeModelInfo();
        while(shoe == null){            
            num = takeNumber();
            if(shoeMap.containsKey(num)){
                shoe = shoeMap.get(num);
            } else {
                System.out.println("Invalid number. Try again.");
            }
        }
        return shoe;
    }
    
    public void showSelectedItemInfo(ShoeModel shoemodel, Shoe shoe){
        System.out.println("Model: " + shoemodel.getName() + 
                shoemodel.getColors().stream().map(Color::getName).collect(Collectors.toList()) + 
                ", size: " + shoe.getSize() + ", price: " + shoe.getPrice());
    }
    
    public void showCartProductInfo(CartProduct product){
        Shoe shoe = product.getShoe();
        ShoeModel shoemodel = shoe.getShoemodel();
        System.out.println("Model: " + shoemodel.getName() + 
                shoemodel.getColors().stream().map(Color::getName).collect(Collectors.toList()) + 
                ", amount: " + product.getAmount() +", size: " + shoe.getSize() + 
                ", price: " + shoe.getPrice() + " : Total Price : " +
                shoe.getPrice()*product.getAmount());
    }
    
    public void showCartInfo(Customer customer){
        cartList = CartProductRepo.getCartProductsByCustomerId(customer.getId());
        cartList.stream().forEach(p -> showCartProductInfo(p));
    }
    
    public boolean askYesOrNo(){
        System.out.println("1.Yes\n2.No");
        while(true){
            int num = takeNumber();
            if(num == 1){
                return true;
            } else if(num == 2){
                return false;
            } else {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
    
    public String addtoCart(Customer customer, Shoe shoe){
        String msg = Repository.callAddToCart(customer.getId(), shoe.getId());
        return msg;
    }
    
    public Order order(Customer customer){
        Order order = CartProductRepo.createOrder(customer);       
        return order;
    }
    
    public void showOrderedItems(Order order){
        List<CartProduct> list = order.getProductList();
        list.stream().forEach(p -> showCartProductInfo(p));
    }
    
    public static int takeNumber(){
        Scanner sc = new Scanner(System.in);
        int input;       
        while(true) {
            if(sc.hasNextInt()) {
                input = sc.nextInt();
                break;
            }else {
                System.out.println("Input a number!");
                sc.next();
            }
        }
        return input;
    }
    
}
