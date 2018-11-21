package jdbc_coffe_study.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_coffe_study.dto.Product;



public interface ProductDao {
	List<Product> selectProductByAll() throws SQLException;
	
	Product selectProductByCode(Product product) throws SQLException;
}
