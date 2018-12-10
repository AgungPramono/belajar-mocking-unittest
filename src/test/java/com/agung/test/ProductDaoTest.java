package com.agung.test;

import com.agung.unit.test.dao.DataRepository;
import com.agung.unit.test.dao.impl.ProductDao;
import com.agung.unit.test.entity.Category;
import com.agung.unit.test.entity.Product;
import com.agung.unit.test.helper.ConnectionHelper;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoTest extends BaseTest{

    private static DataRepository productDao;

    @BeforeClass
    public static void prepareAndResetData()throws Exception{
        createSkemaData();
        productDao = new ProductDao();
        System.out.println("----------------------< Mempersiapkan Data >--------------------");
        Connection con = ConnectionHelper.connect();
        IDataSet[] dataSets = new IDataSet[]{
            new FlatXmlDataSetBuilder().build(new File("src/test/resources/sample-data.xml"))
        };

        DatabaseOperation.CLEAN_INSERT.execute(
                new DatabaseConnection(con),
                new CompositeDataSet(dataSets)
        );
    }

    @Test
    public void testFindAll()throws SQLException {
        System.out.println("----------------------< Menjalankan Test cari semua produk >--------------------");
        List<Product> resutl = productDao.findAll();
        Assert.assertNotNull(resutl);
        Assert.assertEquals(6,resutl.size());
    }

    @Test
    public void testFindById() throws SQLException {
        System.out.println("----------------------< Menjalankan test cari produck by id >--------------------");
        Product p = (Product) productDao.findById(1);
        Assert.assertNotNull(p);
        Assert.assertEquals("P-001",p.getProductCode());
        Assert.assertNotEquals("Product001",p.getProductName());
    }

    @Test
    public void testFindByCode()throws SQLException{
        System.out.println("----------------------< Menjalankan test cari produk by code >--------------------");
        Product p = (Product) productDao.findByCode("P-001");
        Assert.assertNotNull(p);
        Assert.assertEquals("P-001",p.getProductCode());
        Assert.assertEquals("Produk-001",p.getProductName());
    }

    @Test
    public void testSaveProduct()throws SQLException{
        System.out.println("----------------------< Menjalankan test simpan produk >--------------------");
        Category c = new Category();
        c.setId(3);

        Product p = new Product();
        p.setProductCode("P-006");
        p.setProductName("Product-006");
        p.setPrice(new BigDecimal(100000));
        p.setCategory(c);

        productDao.save(p);

        Product p2 = (Product) productDao.findByCode("P-006");
        Assert.assertNotNull(p2);
    }

    @Test
    public void testDelete() throws SQLException {
        System.out.println("----------------------< Menjalankan test hapus produk >--------------------");
        productDao.delete(1);
        Product p = (Product) productDao.findById(1);
        Assert.assertNull(p);
    }

    @Test
    public void findProductCategory()throws SQLException{
        System.out.println("----------------------< Menjalankan test cari produk dan categorynya >--------------------");
        List<Product> result = productDao.findAllData();
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
        for (Product p:result) {
            Assert.assertNotNull(p.getCategory());
        }
    }


}
