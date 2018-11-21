package jdbc_coffe_study.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_coffe_study.dto.Product;
import jdbc_coffe_study.dto.Sale;
import jdbc_coffe_study.dto.SaleDetail;
import jdbc_coffe_study.jdbc.ConnectionProvider;
import jdbc_coffe_study.jdbc.LogUtil;

public class SaleDaoImpl implements SaleDao {

	@Override
	public List<Sale> selectSaleByAll() throws SQLException {
		LogUtil.prnLog("selectSaleByAll()");

		List<Sale> list = new ArrayList<>();
		String sql = "select no, code, price, saleCnt, marginRate from sale";
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getSale(rs));
			}
		}
		return list;
	}

	private Sale getSale(ResultSet rs) throws SQLException {
		int no = rs.getInt("no");
		Product code = new Product(rs.getString("code"));
		int price = rs.getInt("price");
		int saleCnt = rs.getInt("saleCnt");
		int marginRate = rs.getInt("marginRate");

		return new Sale(no, code, price, saleCnt, marginRate);
	}

	@Override
	public int insertSale(Sale sale) throws SQLException {
		LogUtil.prnLog("insertSale");

		String sql = "insert into sale values(?, ?, ?, ?, ?)";
		int res = 0;

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, sale.getNo());
			pstmt.setString(2, sale.getProduct().getCode());
			pstmt.setInt(3, sale.getPrice());
			pstmt.setInt(4, sale.getSaleCnt());
			pstmt.setInt(5, sale.getMarginRate());

			LogUtil.prnLog(pstmt);

			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public List<Sale> selectSaleRank(boolean isSale) throws SQLException {
		LogUtil.prnLog("selectSaleRank");
		List<Sale> list = new ArrayList<>();
		String sql = "{call rank_product(?)}";
		try (Connection conn = ConnectionProvider.getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
			cs.setBoolean(1, isSale);
			LogUtil.prnLog(cs.toString());
			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					list.add(getSaleDetail(rs));
				}
			}
		}
		LogUtil.prnLog("selectSaleRank" + list.size());
		return list;
	}

	private Sale getSaleDetail(ResultSet rs) throws SQLException {
		int no = rs.getInt("no");
		Product product = new Product(rs.getString("code"), rs.getString("name"));
		int price = rs.getInt("price");
		int saleCnt = rs.getInt("saleCnt");
		int marginRate = rs.getInt("marginRate");
		int addTax = rs.getInt("addTax");
		int salePrice = rs.getInt("salePrice");
		int supplyValue = rs.getInt("supplyValue");
		int marginPrice = rs.getInt("marginPrice");
		int rank = rs.getInt("rank");

		SaleDetail detail = new SaleDetail(supplyValue, addTax, salePrice, marginPrice, rank);
		Sale sale = new Sale(no, product, marginPrice, saleCnt, marginRate, detail);
		LogUtil.prnLog(sale.toString());
		return sale;

	}

}
