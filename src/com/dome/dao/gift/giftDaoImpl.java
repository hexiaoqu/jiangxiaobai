package com.dome.dao.gift;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dome.dao.BaseDaoImpl;
import com.dome.entity.User;
import com.dome.entity.UserGift;

public class giftDaoImpl extends BaseDaoImpl implements giftDao{

	public giftDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	public Object tableToClass(ResultSet rs) throws Exception {
			UserGift ug = new UserGift();
			ug.setLid(rs.getInt("lid"));
			ug.setLname(rs.getString("lname"));		
			ug.setLprice(rs.getDouble("lprice"));
			ug.setLread(rs.getString("lread"));
			ug.setLtime(rs.getTimestamp("ltime"));
			ug.setHot(rs.getInt("hot"));
			ug.setLintegral(rs.getInt("lintegral"));
			ug.setLbaiduread(rs.getString("lbaiduread"));
		return ug;
	}
	public Object aaa(ResultSet rs) throws Exception {
		UserGift ug = new UserGift();
		ug.setLid(rs.getInt("lid"));
	return ug;
}
	

	@Override
	public void saveGift(UserGift ug) {
		// TODO Auto-generated method stub
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); df.format(new Date())
		//System.out.println(df.format(new Date()));
		String sql = "INSERT INTO `gift_add`(lprice,lintegral,lread,lbaiduread,ltime,lname,hot) values(?,?,?,?,?,?,?)";
		Object[] params = new Object[] {ug.getLprice(),ug.getLintegral(),ug.getLread(),ug.getLbaiduread(),new Date(),ug.getLname(),ug.getHot()};
		this.excuteUpdate(sql, params);	
	}

	@Override
	public void updateGift(UserGift ug) {
		// TODO Auto-generated method stub
		String sql = "update `gift_add` set delete=?  where lid=?";		
		Object[] params = new Object[] {ug.getClass()};
		this.excuteUpdate(sql, params);	
	}

	@Override
	public UserGift findAllGift(Integer id) {
		String sql = "select * from `gift_add` where lid=? and delete = 1";		
		ResultSet rs = null;
		UserGift ug = null;
		try {		
			Object[] params = new Object[] {id};
			rs = this.executeQuery(sql, params);
			while(rs.next()) {
				ug = (UserGift) tableToClass(rs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.closeResource(rs);
			this.closeResource();
		}
			return ug;
	}

	@Override
	public void deleteupdateGift(Integer id) {
		String sql = "update `gift_add` set delete=0  where lid=?";		
		Object[] params = new Object[] {id};
		this.excuteUpdate(sql, params);	
	}

	@Override
	public UserGift getGiftId(String name) {
		UserGift uug=null;
		String sql = "select lid from `gift_add` where lname=?";		
		ResultSet rs = null;		
		try {		
			Object[] params = new Object[] {name};
			rs = this.executeQuery(sql, params);
			while(rs.next()) {
				uug=(UserGift)aaa(rs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.closeResource(rs);
			this.closeResource();
		}
			return uug;
	}

	
	@Override
	public Map<String, Object> queryGiftPage(Integer limit, Integer pageSize) {
		Map<String,Object> map = new HashMap<>();
		List<UserGift> list = new ArrayList<>();
		String sql = "SELECT lid,lprice,lintegral,lread,lbaiduread,ltime,hot,lname FROM gift_add order by lid asc  LIMIT ?,?";
		String sql2 = "SELECT COUNT(1) as count FROM gift_add";
		ResultSet rs = null;		
		ResultSet rs2 = null;
		Integer count = 0;
		UserGift ug = new UserGift();
		try {		
			Object[] params = new Object[] {limit,pageSize};
			Object[] params2 = new Object[] {};			
			rs = this.executeQuery(sql, params);
			rs2 = this.executeQuery(sql2, params2);		
			while(rs.next()) {
				ug = (UserGift) tableToClass(rs);
				list.add(ug);
			}
			while(rs2.next()) {				
				count = rs2.getInt("count");
			}
			map.put("list", list);
			map.put("conut",count.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.closeResource(rs);
			this.closeResource();
		}
		return map;
	}

	@Override
	public void deleteGift(Integer id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM `gift_add`  where lid=?";		
		Object[] params = new Object[] {id};
		this.excuteUpdate(sql, params);	
	}

	@Override
	public Map<String, Object> likeGift(String lname, Integer hot) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		List<UserGift> list = new ArrayList<>();
		StringBuffer sb = new  StringBuffer("SELECT lid,lprice,lintegral,lread,lbaiduread,ltime,lname,hot FROM gift_add WHERE 1=1");
		StringBuffer sb2 = new StringBuffer("SELECT count(1) as count FROM gift_add WHERE 1=1");
		if(hot!=null) {
			sb.append(" AND hot ="+hot);
			sb2.append(" AND hot ="+hot);
		}
		if(lname != null||" ".equals(lname)) {
			sb.append(" AND lname LIKE \"%"+lname+"%\"");
			sb2.append(" AND lname LIKE \"%"+lname+"%\"");
		}
		sb.append(" order by lid ASC");		
		sb2.append(" order by lid ASC");		
		String sql = sb.toString();
		String sql2 = sb2.toString();
		ResultSet rs = null;		
		ResultSet rs2 = null;
		Integer count = 0;
		UserGift ug = new UserGift();
		try {		
			Object[] params = new Object[] {};
			Object[] params2 = new Object[] {};			
			rs = this.executeQuery(sql, params);
			rs2 = this.executeQuery(sql2, params2);		
			while(rs.next()) {
				ug = (UserGift) tableToClass(rs);
				list.add(ug);
			}
			while(rs2.next()) {				
				count = rs2.getInt("count");
			}
			map.put("list", list);
			map.put("conut",count.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.closeResource(rs);
			this.closeResource();
		}
		return map;
	}

	

}
