package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Shop {
    public static void main(String[] args) throws FileNotFoundException, NullPointerException {


        try {
            TreeMap<Shopaholic, ArrayList<Products>> entries = new TreeMap<>();
            Scanner in = new Scanner(new File("./src/com/company/entries"));


            while (in.hasNext()) {
                Shopaholic user = new Shopaholic();
                Products product = new Products();

                user.setName(in.next());
                user.setSecondName(in.next());
                product.setProductName(in.next());
                product.setProductCost(in.next());


                    if (entries.containsKey(user)) {
                        entries.get(user).add(product);

                    } else{
                        ArrayList<Products> test = new ArrayList<>();
                        test.add(product);
                        entries.put(user,test);
                    }
            }


            for (Map.Entry<Shopaholic, ArrayList<Products>> entry : entries.entrySet()) {
                System.out.println(entry.getKey().getName() + " " + entry.getKey().getSecondName() + ": ");
                System.out.println(entry.getValue());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




