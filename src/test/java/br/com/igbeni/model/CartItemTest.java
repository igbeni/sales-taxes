package br.com.igbeni.model;

import br.com.igbeni.repository.ProductRepository;
import br.com.igbeni.repository.ProductRepositoryFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class CartItemTest {

    private ProductRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = ProductRepositoryFactory.create();
    }

    @Test
    public void noWordItem() throws Exception {
        CartItem item = CartItem.fromString("", repository);

        assertThat(item).isNull();
    }

    @Test
    public void oneWordItem() throws Exception {
        CartItem item = CartItem.fromString("15 book at 12.49", repository);

        assertThat(item).isNotNull();
        assertThat(item.getQuantity()).isEqualTo(15);
        assertThat(item.getProductName()).isEqualTo("book");
        assertThat(item.getProductPrice()).isEqualTo(new BigDecimal("12.49"));
        assertThat(item.getType()).isEqualTo(ProductType.BOOKS);
    }

    @Test
    public void oneWordimportedItem() throws Exception {
        CartItem item = CartItem.fromString("1 imported book at 12.49", repository);

        assertThat(item).isNotNull();
        assertThat(item.getQuantity()).isEqualTo(1);
        assertThat(item.getProductName()).isEqualTo("book");
        assertThat(item.getProductPrice()).isEqualTo(new BigDecimal("12.49"));
        assertThat(item.getType()).isEqualTo(ProductType.BOOKS);
        assertThat(item.isImported()).isEqualTo(true);
    }

    @Test
    public void multipleWordItem() throws Exception {
        CartItem item = CartItem.fromString("1051 imported bottle of perfume at 47.50", repository);

        assertThat(item).isNotNull();
        assertThat(item.getQuantity()).isEqualTo(1051);
        assertThat(item.getProductName()).isEqualTo("bottle of perfume");
        assertThat(item.getProductPrice()).isEqualTo(new BigDecimal("47.50"));
        assertThat(item.getType()).isEqualTo(ProductType.OTHER);
        assertThat(item.isImported()).isEqualTo(true);
    }
}