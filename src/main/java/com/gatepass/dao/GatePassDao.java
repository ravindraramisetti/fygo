package com.gatepass.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.gatepass.bo.GatePassBO;
import com.gatepass.bo.SearchGatePassBO;


@Repository
public class GatePassDao {
  private final String GATEPASS_INSERT="insert into gatepass(visitor_nm,mobile_no,email_address,purpose,expected_entry_time,whom_to_meet,flat_no,block_no,status) values(?,?,?,?,?,?,?,?,?)";	
  private final String GET_GATEPASS_BY_GATEPASS_NO="select gatepass_no,visitor_nm,mobile_no,email_address,purpose,expected_entry_time,whom_to_meet,flat_no,block_no,status from gatepass where gatepass_no=?";
  private final static String GET_GATEPASS_BY_SEARCH="select gatepass_no,visitor_nm,mobile_no,email_address,purpose,expected_entry_time,whom_to_meet,flat_no,block_no,status from gatepass where ";
  private static boolean whereCaluse=false;
  @Autowired	
  private JdbcTemplate jdbcTemplate; 
  
  public int creteGatePass(final GatePassBO gatePassBO) {
	  int gatePassNo=0;
	  KeyHolder kh=null;
	  kh=new GeneratedKeyHolder();
	  gatePassNo=jdbcTemplate.update(new PreparedStatementCreator() {
		
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			PreparedStatement pstmt=null;
			pstmt=con.prepareStatement(GATEPASS_INSERT);
			pstmt.setString(1,gatePassBO.getVisitorName());
			pstmt.setString(2,gatePassBO.getMobileNo());
			pstmt.setString(3,gatePassBO.getEmailAddress());
			pstmt.setString(4,gatePassBO.getPurpose());
			pstmt.setDate(5,new Date(gatePassBO.getExpectedEntryTime().getTime()));
			pstmt.setString(6,gatePassBO.getWhomToMeet());
			pstmt.setInt(7,gatePassBO.getFlatNo());
			pstmt.setInt(8,gatePassBO.getBlockNo());
			pstmt.setString(9,gatePassBO.getStatus());
			return pstmt;
		}
	},kh);
	  
	  gatePassNo=kh.getKey().intValue();
	  return gatePassNo;
  }
  
  public GatePassBO getGatePassByGatePassNo(int gatePassNo) {
	 return jdbcTemplate.queryForObject(GET_GATEPASS_BY_GATEPASS_NO,new GatePassRowMapper(),new Object[] {gatePassNo});
  }
  private final class GatePassRowMapper implements RowMapper<GatePassBO> {
    @Override
	public GatePassBO mapRow(ResultSet rs, int rowNum) throws SQLException {
		GatePassBO gatePassBO=new GatePassBO(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10));
		return gatePassBO;
	}
	  
  }
  
  public List<GatePassBO> getGatePassBySearch(SearchGatePassBO sgbo) {
	  final String sql=creteSearchQuery(GET_GATEPASS_BY_SEARCH,sgbo);
	 
	  return jdbcTemplate.query(new PreparedStatementCreator() {
		
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			PreparedStatement pstmt=null;
			pstmt=con.prepareStatement(sql);
			return pstmt;
		}
	}, new GatePassRowMapper());
	  
  }
  
  private static String creteSearchQuery(String sql,SearchGatePassBO sgbo) {
	  String csql=sql;
	  PreparedStatement pstmt=null;
	  if(sgbo.getFlatNo()>0) {
		  csql+=checkWhereCaluse(sql," "+"flat_no=?","flat_no=?");
		  System.out.println(csql);
	  }
	  if(sgbo.getBlockNo()>0) {
		  csql+=checkWhereCaluse(sql," "+"block_no=?"," "+"and"+" "+"block_no=?");
		  System.out.println(csql);
	  }
	  /*if(sgbo.getFromDate().getTime()>0 && sgbo.getToDate().getTime() >0) {
		  csql=checkWhereCaluse(sql,"and"+" "+"expected_entry_time=?"," "+"expected_entry_time=?");
	  }*/
	  if(sgbo.getStatus()!=null && sgbo.getStatus().trim().length()>0) {
		  csql+=checkWhereCaluse(sql," "+"status=?"," "+"and"+" "+"status=?");
	  }
	 
	return csql;
	 
  }
  private static String checkWhereCaluse(String sql,String ifWhere,String ifNotWhere) {
	 return (sql.endsWith("where"))? ifWhere:ifNotWhere;
  }
  
  public static void main(String []args) {
	  SearchGatePassBO sgbo=new SearchGatePassBO();
	  sgbo.setBlockNo(1);
	  sgbo.setFlatNo(89);
	  sgbo.setStatus("entry");
	  String sq=creteSearchQuery(GET_GATEPASS_BY_SEARCH,sgbo);
	  System.out.println(sq);
  }
}

