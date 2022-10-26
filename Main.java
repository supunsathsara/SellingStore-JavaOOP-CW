/*
 * D G S S SATHSARA
 * GADSE221F-017
 * 2022/10/26
 */

class Customer {
    protected String firstName;
    protected String surName;
    protected int quantityOfP = 0;
    protected int quantityOfQ = 0;
    protected Item itemP;
    protected Item itemQ;

    public Customer(String firstName, String surName, int quantityOfP, int quantityOfQ, Item itemP, Item itemQ) {
        this.firstName = firstName;
        this.surName = surName;
        this.quantityOfP = quantityOfP;
        this.quantityOfQ = quantityOfQ;
        this.itemP = itemP;
        this.itemQ = itemQ;
    }

    // if the customer only bought one item, the other item will be null
    public Customer(String firstName, String surName, int quantityOfP, Item itemP) {
        this.firstName = firstName;
        this.surName = surName;
        this.quantityOfP = quantityOfP;
        this.itemP = itemP;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public int getQuantityOfP() {
        return quantityOfP;
    }

    public int getQuantityOfQ() {
        return quantityOfQ;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setQuantityOfP(int quantityOfP) {
        this.quantityOfP = quantityOfP;
    }

    public void setQuantityOfQ(int quantityOfQ) {
        this.quantityOfQ = quantityOfQ;
    }

    public float findBillAmount() {
        return quantityOfQ == 0 ? (quantityOfP * itemP.unitPrice)
                : (quantityOfP * itemP.unitPrice) + (quantityOfQ * itemQ.unitPrice);
        // return (quantityOfP * itemP.unitPrice) + (quantityOfQ * itemQ.unitPrice);
    }
}

class LocalCustomer extends Customer {

    protected float discount = 0.0f;

    public LocalCustomer(String firstName, String surName, int quantityOfP, int quantityOfQ, Item itemP, Item itemQ,
            float discount) {
        super(firstName, surName, quantityOfP, quantityOfQ, itemP, itemQ);
        this.discount = discount;

    }

    // if the customer only bought one item
    public LocalCustomer(String firstName, String surName, int quantityOfP, Item itemP,
            float discount) {
        super(firstName, surName, quantityOfP, itemP);
        this.discount = discount;

    }

    @Override
    public float findBillAmount() {
        return super.findBillAmount() - (super.findBillAmount() * discount);
    }
}

class ForeignCustomer extends Customer {

    protected float tax = 0.0f;

    public ForeignCustomer(String firstName, String surName, int quantityOfP, int quantityOfQ, Item itemP, Item itemQ,
            float tax) {
        super(firstName, surName, quantityOfP, quantityOfQ, itemP, itemQ);
        this.tax = tax;
    }

    // if the customer only bought one item
    public ForeignCustomer(String firstName, String surName, int quantityOfP, Item itemP, float tax) {
        super(firstName, surName, quantityOfP, itemP);
        this.tax = tax;
    }

    @Override
    public float findBillAmount() {
        return super.findBillAmount() + (super.findBillAmount() * tax);
    }
}

class Item {
    protected float unitPrice;

    public Item() {
        unitPrice = 0.0f;
    }

    public Item(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

}

public class Main {
    public static void main(String[] args) {
        Item P = new Item();
        P.setUnitPrice(75.0f); // using setters

        // LocalCustomer
        LocalCustomer A = new LocalCustomer("Henry", "Kent", 5, P, 0.5f);
        // System.out.println("Local Customer Bill: " + A.findBillAmount());
        System.out.printf("Local Customer\n Name: %s\n SurName: %s\n Bill Amount: %.2f", A.getFirstName(),
                A.getSurName(),
                A.findBillAmount());

        System.out.println("\n");

        Item Q = new Item(80.0f); // using constructor

        // ForeignCustomer
        ForeignCustomer B = new ForeignCustomer("Bruce", "Wayne", 2, Q, 0.1f);

        System.out.printf("Foreign Customer\n Name: %s\n SurName: %s\n Bill Amount: %.2f", B.getFirstName(),
                B.getSurName(),
                B.findBillAmount());

        /*
         * Sample Output:
         * Local Customer
         * Name: Henry
         * SurName: Kent
         * Bill Amount: 187.50
         * 
         * Foreign Customer
         * Name: Bruce
         * SurName: Wayne
         * Bill Amount: 176.00
         */

    }
}