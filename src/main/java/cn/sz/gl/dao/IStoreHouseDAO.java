package cn.sz.gl.dao;

public interface IStoreHouseDAO {

	public Integer findCountByBookid(Integer bookid);
	
	public void updateStorehouse(Integer bookid);
}
