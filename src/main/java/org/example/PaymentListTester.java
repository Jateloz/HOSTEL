package org.example;


import java.util.Scanner;
/**
public class PaymentListTester(){
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice;
        int size;
        PaymentList list; //declare paymentList object to test

        //get size of list
        System.out.println("Size of list?");
        size = in.nextInt();
        list = new PaymentList(size);

        //menu
        do {
            //display options
            System.out.println();
            System.out.println("[1] ADD");
            System.out.println("[2] DISPLAY");
            System.out.println("[3] IS FULL");
            System.out.println("[4] GET PAYMENT");
            System.out.println("[5] GET TOTAL");
            System.out.println("[6] CALCULATE TOTAL PAID");
            System.out.println("[7] Quit");
            System.out.println();
            System.out.println("Enter a choice [1-7]: ");

            //get choice
            choice = in.nextInt();
            System.out.println();

            //process choice
            switch (choice) {
                case '1':
                    //option1(list);
                    break;
                case '2':
                    option2(list);
                    break;
                case '3':
                    option3(list);
                    break;
                case '4':
                    option4(list);
                    break;
                case '5':
                    option5(list);
                    break;
                case '6':
                    option6(list);
                    break;
                case '7':
                    System.out.println("Testing complete");
                    break;
                default:
                    System.out.println("[1-7] only");
            }
        }
        while (choice != '7');
    }

    //ADD
    static void option1(Payment listIn) {
        Scanner in = new Scanner(System.in);
        //prompt for payment details
        System.out.println("Enter Month: ");
        String month = in.next();
        System.out.println("Enter Amount: ");
        double amount = in.nextDouble();

        //create new payment object from input
        Payment p = new Payment(month,amount);

        //attempt to add payment to list
       // boolean ok = listIn.addPayment(p);  //value of false sent back if unable to add
        //if (!ok) {  //check if the item was added

            System.out.println("ERROR: list is full");
        }
    //}

    //DISPLAY
    static void option2(PaymentList listIn) {
        System.out.println("ITEMS ENTERED");
        System.out.println(listIn);  //calls toString method of paymentList
    }

    //IS FULL
    static void option3(PaymentList listIn) {
        if (listIn.isFull()) {
            System.out.println("List is full");

        } else {
            System.out.println("List is NOT full");

        }
    }

    //GET PAYMENT
    static void option4(PaymentList listIn) {
        Scanner in = new Scanner(System.in);
        //prompt for and receive payment number
        System.out.println("Enter payment number to receive: ");
        int num = in.nextInt();
        //retrieve payment object from list
        Payment p = listIn.getPayment(num); //returns null if invalid position
        if (p != null) {  //check payment retrieved
            System.out.println(p);

        } else {

            System.out.println("INVALID PAYMENT NUMBER");
        }
    }
    //GET TOTAL
    static void option5(PaymentList listIn){
        System.out.println("TOTAL NUMBER OF PAYMENTS ENTERED: ");
        System.out.println(listIn.getTotal());
    }

    //GET TOTAL PAID
    static void option6(PaymentList listIn){
        System.out.println("TOTAL OF PAYMENTS MADE SO FAR: ");
        System.out.println(listIn.calculateTotalPaid());
    }
}
 */
