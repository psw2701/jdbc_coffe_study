package java_swing_product_service;

import java.sql.SQLException;
import java.util.List;

import jdbc_coffe_study.dao.SaleDao;
import jdbc_coffe_study.dao.SaleDaoImpl;
import jdbc_coffe_study.dto.Sale;

public class OutputService {
	private SaleDao saleDao;

	public OutputService() {
		saleDao = new SaleDaoImpl();
	}
	
	public List<Sale> outputOrder(boolean isSale) throws SQLException {
		return saleDao.selectSaleRank(isSale);
	}
}
