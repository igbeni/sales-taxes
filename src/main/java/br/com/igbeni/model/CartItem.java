package br.com.igbeni.model;

import br.com.igbeni.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartItem {
    public static final String PATTERN = "^(\\d+)\\s([a-zA-Z\\s]*)\\sat\\s(\\d+\\.\\d{2}?)";

    private final String productName;
    private final BigDecimal productPrice;
    private final int quantity;
    private boolean imported;
    private ProductType type;

    public CartItem(String productName, BigDecimal productPrice, int quantity, boolean imported, ProductType type) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.imported = imported;
        this.type = type;
    }

    public static CartItem fromString(String s, ProductRepository repository) {
        Matcher matcher = Pattern.compile(PATTERN).matcher(s);

        if (matcher.matches()) {
            int quantity = Integer.parseInt(matcher.group(1));
            String productName = matcher.group(2);
            BigDecimal productPrice = new BigDecimal(matcher.group(3));

            boolean isImported = productName.contains("imported");
            if (isImported) {
                productName = productName.replace("imported", "")
                        .replaceAll("\\s{2,}", " ")
                        .trim();
            }

            ProductType productType = repository.ofProduct(productName);

            return new CartItem(productName, productPrice, quantity, isImported, productType);
        }

        return null;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isImported() {
        return imported;
    }

    public ProductType getType() {
        return type;
    }
}
