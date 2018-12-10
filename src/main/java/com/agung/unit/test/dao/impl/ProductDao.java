package com.agung.unit.test.dao.impl;

import com.agung.unit.test.dao.DataRepository;
import com.agung.unit.test.entity.Category;
import com.agung.unit.test.entity.Product;
import com.agung.unit.test.helper.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDao implements DataRepository<Product> {

    private static final String SQL_INSERT = "insert into product (id_category,product_code,product_name,price) values (?,?,?,?)";
    private static final String SQL_FIND_BY_CODE = "select * from product where product_code=?";
    private static final String SQL_SELECT_ALL = "select * from product";
    private static final String SQL_DELETE = "delete from product where id=?";
    private static final String SQL_FIND_BY_ID = "select * from product where id=?";
    private static final String SQL_FIND_PRODUCT_CATEGORY = "select p.*,c.* from product p join category c on p.id_category=c.id";

    public void save(Product p)throws SQLException {
        Connection con = ConnectionHelper.connect();
        PreparedStatement ps = con.prepareStatement(SQL_INSERT);
        ps.setInt(1,p.getCategory().getId());
        ps.setString(2,p.getProductCode());
        ps.setString(3,p.getProductName());
        ps.setBigDecimal(4,p.getPrice());
        ps.executeUpdate();
        ConnectionHelper.disconnect(con);
    }

    @Override
    public Product findById(Integer id) throws SQLException {
        Connection con = ConnectionHelper.connect();
        PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            Product p = ProductMapper(rs);
            return p;
        }
        return null;
    }


    public Product findByCode(String code)throws SQLException{
        Connection con = ConnectionHelper.connect();
        PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_CODE);
        ps.setString(1,code);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Product p = ProductMapper(rs);
            return p;
        }
        ConnectionHelper.disconnect(con);
        return null;
    }

    public List<Product> findAll()throws SQLException{
        List<Product> productList = new LinkedList<>();

        Connection con = ConnectionHelper.connect();
        PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Product p = ProductMapper(rs);
            productList.add(p);
        }
        ConnectionHelper.disconnect(con);
        return productList;
    }

    @Override
    public List<Product> findAllData() throws SQLException{
        List<Product> productList = new LinkedList<>();

        Connection con = ConnectionHelper.connect();
        PreparedStatement ps = con.prepareStatement(SQL_FIND_PRODUCT_CATEGORY);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Product p = ProductCatgoryMapper(rs);
            productList.add(p);
        }
        ConnectionHelper.disconnect(con);
        return productList;
    }

    public void delete(Integer id)throws SQLException{
        Connection con = ConnectionHelper.connect();
        PreparedStatement ps = con.prepareStatement(SQL_DELETE);
        ps.setInt(1,id);
        ps.executeUpdate();
        ConnectionHelper.disconnect(con);
    }

    private Product ProductMapper(ResultSet rs) throws SQLException {
        Product p = new Product();

        p.setId(rs.getInt("id"));
        p.setProductCode(rs.getString("product_code"));
        p.setProductName(rs.getString("product_name"));
        p.setPrice(rs.getBigDecimal("price"));
        return p;
    }

    private Product ProductCatgoryMapper(ResultSet rs) throws SQLException {
        Product p = new Product();
        Category c = new Category();

        c.setId(rs.getInt("id_category"));
        c.setCode(rs.getString("code"));
        c.setName(rs.getString("name"));

        p.setId(rs.getInt("id"));
        p.setProductCode(rs.getString("product_code"));
        p.setProductName(rs.getString("product_name"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setCategory(c);

        return p;
    }

}
