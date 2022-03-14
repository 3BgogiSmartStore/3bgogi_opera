package com.gogi.proj.freshsolutions.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class FreshSolutionsDAOMybatis extends SqlSessionDaoSupport implements FreshSolutionsDAO{

	@Resource(name="databaseProperties")
	private Properties dbProperties;
	
	private String namespace = "delivery.fresh_solutions_delivery";

	@Override
	public int selectDontGrantFreshSolutionsDelivOrderListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectDontGrantFreshSolutionsDelivOrderListInMonthCounting", osVO);
	}

	@Override
	public List<OrdersVOList> selectDontGrantFreshSolutionsDelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectDontGrantFreshSolutionsDelivOrderListInMonth", osVO);
	}

	@Override
	public List<OrdersVO> selectFreshSolutionsDeliveryTargetChecking() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectFreshSolutionsDeliveryTargetChecking");
	}

	@Override
	public int updateFreshSolutionsDeliveryTargetBeforeGrantInvoiceNum(List<OrdersVO> orderList) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		for(OrdersVO orVO : orderList) {
			
			result += getSqlSession().update(namespace+".updateFreshSolutionsDeliveryTargetBeforeGrantInvoiceNum", orVO);
		}
		
		return result;
	}

	@Override
	public int[] grantFreshSolutionsDeliveryInvoiceNumByBatchUpdate(List<OrdersVO> orderList) {
		// TODO Auto-generated method stub
		String driverClass = dbProperties.getProperty("Globals.DriverClassName");
		String dbUrl = dbProperties.getProperty("Globals.Url");
		String dbUserName = dbProperties.getProperty("Globals.UserName");
		String dbPass = dbProperties.getProperty("Globals.Password");
		
		int []result = null;
		int [] suc = new int [2];
		
		Connection con = null;
        PreparedStatement pstmt = null ;
        
        String batchSql = ""
        
        +"UPDATE "
			+"orders "
		+"SET "
			+"or_delivery_invoice_number = ?, "
			+"regi_no = ?, "
			+"edt_fk = 3 "
		+"WHERE "
			+"or_serial_special_number = ? "
			+"AND or_delivery_invoice_number = '입력전' "
				+"AND or_sending_deadline >= DATE_FORMAT(DATE_ADD(NOW() , INTERVAL -21 DAY), '%Y-%m-%d') ";

        try {
			Class.forName(driverClass);
			
			con = DriverManager.getConnection(dbUrl, dbUserName,dbPass);
			
			pstmt = con.prepareStatement(batchSql);
			con.setAutoCommit(false);
			
			int invoiceSize = orderList.size();
			
			for( int i = 0; i < invoiceSize; i++) {
				pstmt.setString(1, orderList.get(i).getOrDeliveryInvoiceNumber());
				pstmt.setString(2, orderList.get(i).getOrDeliveryInvoiceNumber());
				pstmt.setString(3, orderList.get(i).getOrSerialSpecialNumber());
				
				pstmt.addBatch();
				pstmt.clearParameters();
				
				if(i % 3000 == 0) {
					
					result = pstmt.executeBatch();
					
					
					pstmt.clearBatch();
					
					con.commit();
					
					for(int j = 0; j < result.length; j++) {
						if(result[j] == 0) {
							suc[1]++;
						}else {
							
							suc[0]++;
						}
					}
					
				}
			}
			
			result = pstmt.executeBatch();
			
            con.commit() ;
            pstmt.clearParameters();
            con.setAutoCommit(true);
            
            for(int j = 0; j < result.length; j++) {
				if(result[j] == 0) {
					suc[1]++;
				}else {
					
					suc[0]++;
				}
			}
            
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            
            try {
                con.rollback() ;
                con.setAutoCommit(true);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
		}finally{
            if (pstmt != null) try {pstmt.close();pstmt = null;} catch(SQLException ex){}
            
            if (con != null) try {con.close();con = null;} catch(SQLException ex){}
            
        }
		
		return suc;
	}

	@Override
	public int grantFreshSolutionsDeliveryInvoiceNumBySerialSpecialNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".grantFreshSolutionsDeliveryInvoiceNumBySerialSpecialNumber", orVO);
	}

	@Override
	public List<OrdersVO> selectFreshSolutionsDeliveryExcelTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectFreshSolutionsDeliveryExcelTarget", osVO);
	}

	@Override
	public List<OrdersVO> selectOrdersPkByOrSerialSpecialNumberForGrantFreshSolutionsInvoiceNum(
			String orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectOrdersPkByOrSerialSpecialNumberForGrantFreshSolutionsInvoiceNum", orSerialSpecialNumber);
	}
	
}
