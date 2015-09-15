package com.nfmedia.tx.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nfmedia.tx.exception.BookStockException;
import com.nfmedia.tx.exception.UserAccountException;
import com.nfmedia.tx.mapper.BookShopMapper;
import com.nfmeida.tx.service.BookShopService;

/**
 * testcase
 * 
 * @author rabbit
 *
 */
public class BookShopMapperTest {

	private ApplicationContext applicationContext;
	private BookShopMapper bookShopMapper;
	private BookShopService bookShopService;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:application-spring.xml");
		
		// 这里直接通过getBean获取，是因为spring与mybatis整合后，通过mapper代理的方式配置时，直接获取首字母小写类名的对应类
		bookShopMapper = (BookShopMapper) applicationContext.getBean("bookShopMapper");
		
		// 这里是通过注解的方式，@Service对实现类BookShopServiceImpl进行获取
		bookShopService = (BookShopService) applicationContext.getBean("bookShopService");
	}

	@Test
	public void testFindBookPriceByIsbn() throws Exception {

		int price = bookShopMapper.findBookPriceByIsbn("1001");
		System.out.println("the book price is " + price);
	}

	@Test
	public void testUpdateBookStock() throws Exception {
		// 检查书籍库存是否足够
		int number = bookShopMapper.findBookStockCountByIsbn("1002");
		if (number == 0) {
			throw new BookStockException("库存不足！！！");
		}

		bookShopMapper.updateBookStock("1002");
		System.out.println("stock number -1 ");
	}

	@Test
	public void testUpdateUserBalance() throws Exception {

		// 检查账户余额是否充足
		int bookPrice = bookShopMapper.findBookPriceByIsbn("1001");
		int balance = bookShopMapper.findAccountBalanceByName("AA");

		System.out.println("balance " + balance);

		if (balance - bookPrice < 0) {
			throw new UserAccountException("余额不足。。。");
		}

		bookShopMapper.updateUserBalance("AA", 130);
		System.out.println("余额扣除成功。。。。");
	}

	@Test
	public void testBookShopService() throws Exception {
		bookShopService.purchase("AA", "1001");
	}
}
