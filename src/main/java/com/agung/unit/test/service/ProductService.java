package com.agung.unit.test.service;

import com.agung.unit.test.dao.impl.ProductDao;
import com.agung.unit.test.dao.impl.ProductHistoryDao;
import com.agung.unit.test.entity.Product;
import com.agung.unit.test.entity.ProductHistory;

import java.sql.SQLException;

public class ProductService {

    private ProductDao productDao;

    private ProductHistoryDao productHistoryDao;

    public void save(Product product){
        try {
            productDao.save(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Product product){
        try {
            productDao.delete(product.getId());

            ProductHistory productHistory = new ProductHistory();
            productHistory.setCode(product.getProductCode());
            productHistory.setName(product.getProductName());

            productHistoryDao.save(productHistory);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
