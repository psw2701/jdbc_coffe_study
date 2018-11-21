package jdbc_coffe_study.dao;


import java.sql.SQLException;
import java.util.List;

import jdbc_coffe_study.dto.Sale;

public interface SaleDao {
	List<Sale> selectSaleByAll() throws SQLException;

	int insertSale(Sale sale) throws SQLException;
	
	List<Sale> selectSaleRank(boolean isSale) throws SQLException;
}
