package com.gogi.proj.orders.cj.model;

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
public class CjdeliveryDAOMybatis extends SqlSessionDaoSupport implements CjdeliveryDAO{

	@Resource(name="databaseProperties")
	private Properties dbProperties;
	
	private String namespace = "delivery.cj_delivery";

	@Override
	public List<OrdersVO> selectCjDeliveryTargetChecking(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectCjDeliveryTargetChecking", osVO);
	}

	@Override
	public int updateCjDeliveryTargetBeforeGrantInvoiceNum(List<OrdersVO> orderList) {
		// TODO Auto-generated method stub
		
		int result = 0;
		
		for(OrdersVO orVO : orderList) {
			result += getSqlSession().update(namespace+".updateCjDeliveryTargetBeforeGrantInvoiceNum", orVO);
		}
		
		return result;
	}

	@Override
	public int grantCjDeliveryInvoiceNumByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".grantCjDeliveryInvoiceNumByOrPk", orVO);
	}

	@Override
	public int grantCjDeliveryInvoiceNumBySerialSpecialNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".grantCjDeliveryInvoiceNumBySerialSpecialNumber", orVO);	
	}

	@Override
	public int selectDontGrantCjDelivOrderListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectDontGrantCjDelivOrderListInMonthCounting", osVO);
	}

	@Override
	public List<OrdersVOList> selectDontGrantCjDelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectDontGrantCjDelivOrderListInMonth", osVO);
	}

	@Override
	public List<OrdersVO> selectCjDeliveryExcelTarget(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectCjDeliveryExcelTarget", osVO);
	}

	@Override
	public int [] grantCjDeliveryInvoiceNumByBatchUpdate(List<OrdersVO> orderList) {
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
			+"or_delivery_company = 'CJ대한통운', "
			+"edt_fk = 5 "
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
	public List<OrdersVO> selectOrdersPkByOrSerialSpecialNumberForGrantCjInvoiceNum(String orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectOrdersPkByOrSerialSpecialNumberForGrantCjInvoiceNum", orSerialSpecialNumber);
	}
}
