package com.nfmedia.tx.dao;

/**
 * 书籍管理DAO
 * @author rabbit
 *
 */
public interface BookShopDao {

	// 根据书号获取书的单价
	public abstract int findBookPriceByIsbn(String isbn) throws Exception;

	// 更新书的库存，使书号对应的库存-1
	public abstract void updateBookStock(String isbn) throws Exception;
	
	// 更新用户的账户余额：使username的balance - price
	void updateUserBalance(String username, int price) throws Exception;
}
