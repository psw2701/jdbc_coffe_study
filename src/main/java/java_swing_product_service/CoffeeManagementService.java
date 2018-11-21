package java_swing_product_service;

import java.sql.SQLException;

import jdbc_coffe_study.dao.ProductDao;
import jdbc_coffe_study.dao.ProductDaoImpl;
import jdbc_coffe_study.dao.SaleDao;
import jdbc_coffe_study.dao.SaleDaoImpl;
import jdbc_coffe_study.dto.Product;
import jdbc_coffe_study.dto.Sale;

public class CoffeeManagementService {

	private SaleDao saleDao;
	private ProductDao pdtDao;

	public CoffeeManagementService() {
		saleDao = new SaleDaoImpl();
		pdtDao = new ProductDaoImpl();
	}

	public int registerSale(Sale sale) throws SQLException {
		return saleDao.insertSale(sale);
	};

	public Product searchProduct(Product product) throws SQLException {
		return pdtDao.selectProductByCode(product);
	}

}
