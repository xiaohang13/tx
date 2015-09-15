package com.nfmedia.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nfmedia.tx.mapper.BookShopMapper;
import com.nfmeida.tx.service.BookShopService;

/**
 * BookShopService实现类 
 * @author rabbit
 *
 */

@Service(value="bookShopService")
public class BookShopServiceImpl implements BookShopService{

	@Autowired
	private BookShopMapper bookShopMapper;
	
	@Override
	public void purchase(String username, String isbn) throws Exception {
		// 1. 获取书籍价格
		int price = bookShopMapper.findBookPriceByIsbn(isbn);
		
		// 2. 更新书籍库存
		bookShopMapper.updateBookStock(isbn);
		
		// 3. 更新用户账户余额
		bookShopMapper.updateUserBalance(username, price);
		
	}

}
