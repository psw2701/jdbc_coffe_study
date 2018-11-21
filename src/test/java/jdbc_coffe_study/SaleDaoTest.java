package jdbc_coffe_study;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_coffe_study.dao.SaleDao;
import jdbc_coffe_study.dao.SaleDaoImpl;
import jdbc_coffe_study.dto.Sale;
import jdbc_coffe_study.jdbc.LogUtil;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SaleDaoTest {
	static SaleDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("START SaleDaoTest");
		dao = new SaleDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("END SaleDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	//select
	@Test
	public void test01selectSaleByAll() throws SQLException {
		LogUtil.prnLog("selectSaleByAll()");
		List<Sale> list = dao.selectSaleByAll();
		LogUtil.prnLog(list.toString());
		Assert.assertNotNull(list);
	}

	@Test
	public void test02selectSaleRankSale() {
		LogUtil.prnLog("selectSaleRank()");
		try {
			List<Sale> list = dao.selectSaleRank(true);
			LogUtil.prnLog(list.toString());
			Assert.assertNotNull(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //판매금액
	}
	
	@Test
	public void test03selectSaleRankMargin() {
		LogUtil.prnLog("selectSaleMargin()");
		try {
			List<Sale> list = dao.selectSaleRank(false);
			LogUtil.prnLog("size" + list.size());
			LogUtil.prnLog(list.toString());
			Assert.assertNotNull(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //판매금액
	}
}
