package com.agung.test;

import com.agung.unit.test.dao.impl.ProductDao;
import com.agung.unit.test.entity.Product;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MockProdukDaoTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    public ProductDao productDao;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    @Test
    public void testFinById() throws SQLException {
        Product p = new Product();
        p.setId(1);
        p.setProductName("Dispenser");
        p.setPrice(new BigDecimal("300000"));

        when(productDao.findById(1)).thenReturn(p);

        Product product = productDao.findById(1);

        assertEquals(Integer.valueOf(1), product.getId());
        assertEquals("Dispenser",product.getProductName());

        verify(productDao).findById(1);
    }

    @Test
    public void testFindbyCode() throws SQLException {
        Product p = new Product();
        p.setProductCode("001");
        p.setId(1);
        p.setProductName("Speaker Active");
        p.setPrice(new BigDecimal("700000"));

        when(productDao.findByCode("001")).thenReturn(p);

        Product product = productDao.findByCode("001");

        assertEquals("001", product.getProductCode());
        assertNotEquals("003",product.getProductCode());
        assertNull(p.getCategory());

        verify(productDao).findByCode("001");
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(productDao);
    }

    @Test
    public void testSimpan() throws SQLException {
        Product p = new Product();
        p.setProductCode("001");
        p.setId(1);
        p.setProductName("Speaker Active");
        p.setPrice(new BigDecimal("700000"));

        productDao.save(p);

        verify(productDao).save(p);
        verify(productDao).save(productArgumentCaptor.capture());

        Product product = productArgumentCaptor.getValue();
        assertEquals(Integer.valueOf(1),product.getId());
        assertEquals("001", product.getProductCode());
        assertEquals("Speaker Active",product.getProductName());
    }
}
