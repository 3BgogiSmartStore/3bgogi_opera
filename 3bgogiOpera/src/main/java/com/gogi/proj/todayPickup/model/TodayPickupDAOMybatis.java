package com.gogi.proj.todayPickup.model;

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
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class TodayPickupDAOMybatis extends SqlSessionDaoSupport implements TodayPickupDAO{

	@Resource(name="databaseProperties")
	private Properties dbProperties;
	
	private String namespace = "delivery.today_pickup";
	
	@Override
	public int[] updateTodayPickupInvoiceNumber(List<OrdersVO> orderList) {
		// TODO Auto-generated method stub
		
		String driverClass = dbProperties.getProperty("Globals.DriverClassName");
		String dbUrl = dbProperties.getProperty("Globals.Url");
		String dbUserName = dbProperties.getProperty("Globals.UserName");
		String dbPass = dbProperties.getProperty("Globals.Password");
		
		int [] result = null;
		
		int [] suc = new int[3];
		
		Connection con = null;
        PreparedStatement pstmt = null ;
        
        String batchSql = ""
        		+"UPDATE orders "
        		+"SET or_delivery_invoice_number = ?, "
        		+"or_delivery_company = '오늘의픽업', "
        		+"regi_no = ? "
        		+"WHERE or_serial_special_number = ? ";
		
        try {
        	
        	Class.forName(driverClass);
			con = DriverManager.getConnection(dbUrl, dbUserName,dbPass);
			
			pstmt = con.prepareStatement(batchSql);
			con.setAutoCommit(false);
			
			int listSize = orderList.size();
			
			for( int i = 0; i < listSize; i++) {
				
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
			
			result = pstmt.executeBatch() ;
            con.commit() ;
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
	public int grantTodayPickupInvoiceNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		
		return getSqlSession().update(namespace+".grantTodayPickupInvoiceNumber", orVO);
	}

	@Override
	public List<OrdersVO> selectTodayPickupTargetChecking(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectTodayPickupTargetChecking", osVO);
	}

	@Override
	public int selectDontGrantTodayPickupDelivOrderListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectDontGrantTodayPickupDelivOrderListInMonthCounting", osVO);
	}

	@Override
	public List<OrdersVO> selectDontGrantTodayPickupDelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectDontGrantTodayPickupDelivOrderListInMonth", osVO);
	}

	@Override
	public OrdersVO todayPickupDeliveryInvoiceNumberReprinting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".todayPickupDeliveryInvoiceNumberReprinting", osVO);
	}

	@Override
	public int updateTodayPickupDeliveryTargetBeforeGrantInvoiceNum(List<OrdersVO> orderList) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		for(OrdersVO orVO : orderList) {
			
			result += getSqlSession().update(namespace+".updateTodayPickupDeliveryTargetBeforeGrantInvoiceNum", orVO);
		}
		
		return result;
	}

}
