package com.agung.unit.test.dao.impl;

import com.agung.unit.test.dao.ProductHistoryRepository;
import com.agung.unit.test.entity.ProductHistory;
import com.agung.unit.test.helper.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductHistoryDao implements ProductHistoryRepository {

    public static final String SQL_SAVE_PROD_HISTORY = "insert into product_history (id,code,name) values (?,?)";

    public void save(ProductHistory productHistory){
        Connection con = ConnectionHelper.connect();
        try {
            PreparedStatement ps = con.prepareStatement(SQL_SAVE_PROD_HISTORY);
            ps.setInt(1,productHistory.getId());
            ps.setString(2,productHistory.getCode());
            ps.setString(3,productHistory.getName());
            ps.executeUpdate();
            ConnectionHelper.disconnect(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
