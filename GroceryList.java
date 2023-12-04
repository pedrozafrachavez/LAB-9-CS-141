import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Product{
    //Attributes
    String productName;
    double price;
    //Methods
    public String getProduct(){ return this.productName; }
    public double getPrice(){ return this.price; }
    //No arg constructor
    Product(){
        productName = "";
        price = 0.0;
    }
    //2 arg
    Product(String productName, double price){
        this.productName = productName;
        this.price = price;
    }
    public String toString(){
        String a = String.format("%-15s $%.2f", productName, price);
        return a;
    }
}
public class GroceryList{
    public static void addProduct(ArrayList<Product> groceryItems){
        Scanner in = new Scanner(System.in);
    
        System.out.print("Enter an item: ");
        String itemName = in.next();
        //Checks that the item is not greater than 40 char
        char[] arr = (itemName.toCharArray());
        while (arr.length > 40){
            System.out.println("ENTER A NAME LESS 40 CHARACTERS:");
            itemName = in.next();
            arr = (itemName.toCharArray());
        }
        System.out.print("Enter a price: ");
        double itemPrice = in.nextDouble();
        System.out.println("Enter an index:");
        int index = in.nextInt();
        while (index > groceryItems.size() || index < groceryItems.size()){
            System.out.println("ERROR OUT OF RANGE, INDEX RANGE AVAIABLE: " +
             groceryItems.size());
            System.out.print("ENTER A NEW IN RANGE INDEX: ");
            index = in.nextInt();
            
        }
        boolean productExists = false;
        //Check if a product wanting to be added exists alr..
        //Reiterates across each product name to check
        for (int i = 0; i < groceryItems.size(); i++) {
            if (itemName.equalsIgnoreCase(groceryItems.get(i).getProduct())) {
                System.out.println("THE PRODUCT YOU ENTERED ALREADY EXISTS. TRY AGAIN");
                productExists = true;
                addProduct(groceryItems);
                break; //Exit the loop since we found a match..
            }
        }
        //Adds to the grocery list after a match is not found..
        if (!productExists) {
            Product newProduct = new Product(itemName, itemPrice);
            groceryItems.add(index, newProduct);
            System.out.println("Product added successfully");
        }
    }
    public static void displayItems(ArrayList<Product> groceryItems){
        System.out.println("Product        Price");
        for (int i = 0; i < groceryItems.size(); i++){
            System.out.println(groceryItems.get(i).toString());
        } 
    }
    public static void removeItem(ArrayList<Product> groceryItems){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the index you would like to remove: ");
        String index = in.next();
        if (index.toLowerCase().equals("exit")){
            System.out.println("YOU QUIT");
        } else { 
            try {
                groceryItems.remove(Integer.parseInt(index));
                System.out.println("ITEM REMOVED SUCCESSFULLY");
            } catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("TRY AGAIN OR ENTER 'EXIT' TO QUIT");
                removeItem(groceryItems);                
            }
        }
    }
    public static void productTotal(ArrayList<Product> groceryItems){
        double totalPrice = 0.0;
        for (int i = 0; i < groceryItems.size(); i++){
            //Increments by the price of each product..
            totalPrice += groceryItems.get(i).getPrice();
        } 
        System.out.println("The Total Price: $" + totalPrice);
    } 
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ArrayList<Product> groceryItems = new ArrayList<>();
        int userChoice = 0;
        while (userChoice != 5){
            System.out.println("\n------Grocery List Menu------");
            //Conventions..
            System.out.print("<1> Add to the list at any given index \n<2>");
            System.out.print(" Remove from list at any given index \n<3> Display ");
            System.out.print("grocery list \n<4> Show current total of all the products \n<5> Quit\n");
            System.out.print("Enter a number to choose: ");
            userChoice = in.nextInt();
            //Checks if user entered the correct range
            if (userChoice > 5 || userChoice < 1 ){
                System.out.println("ENTERED WRONG DATA RANGE TRY AGAIN");
                main(args);
            } else{
                // Append elements
                if (userChoice == 1){
                    addProduct(groceryItems);
                } else if (userChoice == 2){
                    //Deletes at given index
                    removeItem(groceryItems);
                } else if (userChoice == 3){
                    //Displays grocery list
                    displayItems(groceryItems);
                } else if (userChoice == 4){
                    // show the current total of all the Products in the ArrayList
                    productTotal(groceryItems);
                } else if (userChoice == 5){
                    System.out.println("Grocery List has been exited");
                    break;
                } else {
                    System.out.println("ERROR: Your input was out of range");
                }
            }
        }
    }
}
