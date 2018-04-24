package br.com.igbeni;

import br.com.igbeni.repository.ProductRepositoryFactory;
import br.com.igbeni.tax.TaxPolicyFactory;

public class Main {

    public static void main(String[] args) {
        SalesTaxes salesTaxes = new SalesTaxes(TaxPolicyFactory.create(), ProductRepositoryFactory.create());

        //Input 1
        System.out.println("--- Input 1 ---");
        System.out.println("1 book at 12.49");
        System.out.println("1 music CD at 14.99");
        System.out.println("1 chocolate bar at 0.85");

        salesTaxes.addNewItem("1 book at 12.49");
        salesTaxes.addNewItem("1 music CD at 14.99");
        salesTaxes.addNewItem("1 chocolate bar at 0.85");

        System.out.println();
        System.out.println("--- Output 1 ---");
        System.out.println(salesTaxes.completedSale());
        System.out.println();

        //Input 2
        System.out.println("--- Input 2 ---");
        System.out.println("1 imported box of chocolates at 10.00");
        System.out.println("1 imported bottle of perfume at 47.50");

        salesTaxes.addNewItem("1 imported box of chocolates at 10.00");
        salesTaxes.addNewItem("1 imported bottle of perfume at 47.50");

        System.out.println();
        System.out.println("--- Output 2 ---");
        System.out.println(salesTaxes.completedSale());
        System.out.println();

        //Input 3
        System.out.println("--- Input 3 ---");
        System.out.println("1 imported bottle of perfume at 27.99");
        System.out.println("1 bottle of perfume at 18.99");
        System.out.println("1 packet of headache pills at 9.75");
        System.out.println("1 box of imported chocolates at 11.25");

        salesTaxes.addNewItem("1 imported bottle of perfume at 27.99");
        salesTaxes.addNewItem("1 bottle of perfume at 18.99");
        salesTaxes.addNewItem("1 packet of headache pills at 9.75");
        salesTaxes.addNewItem("1 box of imported chocolates at 11.25");

        System.out.println();
        System.out.println("--- Output 3 ---");
        System.out.println(salesTaxes.completedSale());
    }
}
