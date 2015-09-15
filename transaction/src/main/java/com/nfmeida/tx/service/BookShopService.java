package com.nfmeida.tx.service;

/**
 * 书籍操作interface
 * @author rabbit
 *
 */

public interface BookShopService {
	
	public abstract void purchase(String username, String isbn) throws Exception;
	
}
