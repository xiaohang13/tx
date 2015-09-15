package com.nfmedia.tx.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 书籍管理DAO
 * @author rabbit
 *
 */
public interface BookShopMapper {

	// 根据书号获取书的单价
	public abstract int findBookPriceByIsbn(String isbn) throws Exception;

	// 更新书的库存，使书号对应的库存-1
	public abstract void updateBookStock(String isbn) throws Exception;
	
	// 更新用户的账户余额：使username的balance - price
	void updateUserBalance(@Param("username")String username, @Param("price")int price) throws Exception;
	
	// 获取某一本书籍的库存数量
	public abstract int findBookStockCountByIsbn(String isbn) throws Exception;
	
	// 获取账户的余额信息
	public abstract int findAccountBalanceByName(String username) throws Exception;
}
