package com.agung.test;

import com.agung.unit.test.dao.impl.ProductDao;
import com.agung.unit.test.dao.impl.ProductHistoryDao;
import com.agung.unit.test.entity.Category;
import com.agung.unit.test.entity.Product;
import com.agung.unit.test.entity.ProductHistory;
import com.agung.unit.test.service.ProductService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class MockProductServiceTest {


    ProductDao prodcutDao = mock(ProductDao.class);


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    public ProductDao productDao;

    @Mock
    public ProductHistoryDao productHistoryDao;

    @InjectMocks
    public ProductService productService;

    @Captor
    private ArgumentCaptor<ProductHistory> productHistoryArgumentCaptor;

    @Test
    public void testSaveProduct() throws SQLException {
        Product p = new Product();
        p.setId(1);
        p.setProductCode("PD-001");
        p.setProductName("Televisi LG");
        p.setCategory(new Category(1,"ct-001","elektronik"));
        p.setPrice(new BigDecimal("3000000"));

        productService.save(p);

        verify(productDao).save(p);
    }

    @Test
    public void tetsDeleteProduct() throws SQLException {
        Product p = new Product();
        p.setId(1);
        p.setProductCode("PD-001");
        p.setProductName("Televisi LG");
        p.setCategory(new Category(1,"ct-001","elektronik"));
        p.setPrice(new BigDecimal("3000000"));

        productService.delete(p);

        verify(productDao).delete(p.getId());
        //hati-hati dalam menggunakan any()
        //verify(productHistoryDao).save(any(ProductHistory.class));

        //lakukan capturing untuk mengecek inputan/parameter
        verify(productHistoryDao).save(productHistoryArgumentCaptor.capture());

        ProductHistory productHistory = productHistoryArgumentCaptor.getValue();

        assertEquals("PD-001", productHistory.getCode());
        assertEquals("Televisi LG", productHistory.getName());

    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(productDao,productHistoryDao);
    }
}
