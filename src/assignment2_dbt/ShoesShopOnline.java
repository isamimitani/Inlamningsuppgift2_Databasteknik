package assignment2_dbt;

import models.*;

/**
 *
 * @author isami
 */
public class ShoesShopOnline {

    
    public static void main(String[] args) {
        ShoesShopOnline shop = new ShoesShopOnline();               
    }
    
    public ShoesShopOnline(){
        
        ShoeShopController controller = new ShoeShopController();
        System.out.println("Welcome to our shop! Which customer are you?");
        Customer customer = controller.chooseCustomer();
        System.out.println("Hello " + customer.getFirstName() + "!");
        System.out.println();
        
        while(true){
            System.out.println("Which product do you want to add to cart?");
            ShoeModel shoemodel = controller.chooseShoeModel();
            Shoe shoe = controller.chooseShoe(shoemodel);
            System.out.println("You chose:");
            controller.showSelectedItemInfo(shoemodel, shoe);
            System.out.println("Would you like to add your item to cart?");
            if(controller.askYesOrNo()){
                String msg = controller.addtoCart(customer, shoe);
                if(msg != null){
                     System.out.println(msg);
                     continue;
                } 
            } else {
                continue;
            }
            
            System.out.println("You have following items in your cart.");
            controller.showCartInfo(customer);
            
            System.out.println("Would you like to order the items in your cart?");
            if(controller.askYesOrNo()){
                Order order = controller.order(customer);
                System.out.println("You have ordered following items.");
                controller.showOrderedItems(order);
                System.out.println("Thank you for your purchase!");
                System.exit(0);
            } else {
                continue;
            }           
        }
    }

}
