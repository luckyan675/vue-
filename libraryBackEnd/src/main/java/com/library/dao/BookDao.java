package com.library.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.library.entity.Book;
import com.library.entity.Classification;

@Repository
public class BookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String ADD_BOOK = "INSERT INTO book VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String DELETE_BOOK = "delete from book where id = ?  ";
	private final static String UPDATE_BOOK = "update book set name= ? ,author= ? ,publish= ? ,ISBN= ? ,introduction= ? ,language= ? ,price= ? ,pubdate= ? ,class_id= ? ,pressmark= ? ,state= ?, img_src=?  where book_id= ? ;";
	private final static String SELECT_ALL_BOOKS = "SELECT B.*,C.id AS c_id ,C.class_index AS c_index , C.class_name AS c_name FROM book B left join class  C on B.class_id = C.id";
	private final static String SELECT_BOOK_BY_NAME_OR_ABSTRACT = "SELECT B.*,C.id AS c_id ,C.class_index AS c_index , C.class_name AS c_name FROM book B left join class  C on B.class_id = C.id WHERE B.name like ? OR B.author like ? OR B.publish like ? OR B.introduction like ? OR B.language like ?";
	private final static String SELECT_BOOK_BY_ID = "SELECT B.*,C.id AS c_id ,C.class_index AS c_index , C.class_name AS c_name FROM book B left join class  C on B.class_id = C.id  WHERE B.id = ? ";
	private final static String SELECT_BOOK_BY_CLASSIFICATION_ID = "SELECT B.*,C.id AS c_id ,C.class_index AS c_index , C.class_name AS c_name FROM book B left join class  C on B.class_id = C.id  WHERE class_id = ?";
	
	/**
	 * 新增图书
	 * 
	 * @param book
	 * @return
	 */
	public int createBook(Book book) {
		String id = UUID.randomUUID().toString().replaceAll("-", "");

		String name = book.getName();
		String author = book.getAuthor();
		String publish = book.getPublish();
		String isbn = book.getIsbn();
		String introduction = book.getIntroduction();
		String language = book.getLanguage();
		BigDecimal price = book.getPrice();
		Date pubdate = book.getPubdate();
		String classId = book.getClassId();
		int pressmark = book.getPressmark();
		int state = book.getState(); 
		String imgSrc = book.getImgSrc();

		return jdbcTemplate.update(ADD_BOOK, new Object[] { id, name, author, publish, isbn, introduction, language,
				price, pubdate, classId, pressmark, state, imgSrc });
	}

	/**
	 * 删除图书
	 * 
	 * @param bookId
	 * @return
	 */
	public int deleteBook(String bookId) {
		return jdbcTemplate.update(DELETE_BOOK, bookId);
	}

	/**
	 * 更新图书
	 * 
	 * @param book
	 * @return
	 */
	public int updateBook(Book book) {
		String bookId = book.getId();
		String name = book.getName();
		String author = book.getAuthor();
		String publish = book.getPublish();
		String isbn = book.getIsbn();
		String introduction = book.getIntroduction();
		String language = book.getLanguage();
		BigDecimal price = book.getPrice();
		Date pubdate = book.getPubdate();
		String classId = book.getClassId();
		int pressmark = book.getPressmark();
		int state = book.getState();
		String imgSrc = book.getImgSrc();
		return jdbcTemplate.update(UPDATE_BOOK, new Object[] { name, author, publish, isbn, introduction, language,
				price, pubdate, classId, pressmark, state,imgSrc, bookId });
	}

	/**
	 * id获取图书
	 * 
	 * @param id
	 * @return
	 */
	public Book getBookById(String id) {
		Book book = new Book();
		jdbcTemplate.query(SELECT_BOOK_BY_ID, new Object[] { id }, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					Book book = new Book();
					Classification classification = new Classification();
					book.setId(resultSet.getString("id"));
					book.setPrice(resultSet.getBigDecimal("price"));
					book.setState(resultSet.getInt("state"));
					book.setPublish(resultSet.getString("publish"));
					book.setPubdate(resultSet.getDate("pubdate"));
					book.setName(resultSet.getString("name"));
					book.setIsbn(resultSet.getString("isbn"));
					book.setClassId(resultSet.getString("class_id"));
					book.setAuthor(resultSet.getString("author"));
					book.setIntroduction(resultSet.getString("introduction"));
					book.setPressmark(resultSet.getInt("pressmark"));
					book.setLanguage(resultSet.getString("language"));
					book.setImgSrc(resultSet.getString("img_src"));
					classification.setId(resultSet.getString("c_id"));
					classification.setClassName(resultSet.getString("c_name"));
					classification.setIndex(resultSet.getString("c_index"));
					book.setClassification(classification);
				}
			}
		});
		return book;
	}

	/**
	 * 关键字获取图书列表
	 * 
	 * @param keywords
	 * @return
	 */
	public ArrayList<Book> getBookByKeyWords(String keywords) {
		ArrayList<Book> books = new ArrayList<Book>();
		jdbcTemplate.query(SELECT_BOOK_BY_NAME_OR_ABSTRACT, new Object[] { "%" + keywords + "%", "%" + keywords + "%",
				"%" + keywords + "%", "%" + keywords + "%", "%" + keywords + "%" }, new RowCallbackHandler() {
					public void processRow(ResultSet resultSet) throws SQLException {
						resultSet.beforeFirst();
						while (resultSet.next()) {
							Book book = new Book();
							Classification classification = new Classification();
							book.setId(resultSet.getString("id"));
							book.setPrice(resultSet.getBigDecimal("price"));
							book.setState(resultSet.getInt("state"));
							book.setPublish(resultSet.getString("publish"));
							book.setPubdate(resultSet.getDate("pubdate"));
							book.setName(resultSet.getString("name"));
							book.setIsbn(resultSet.getString("isbn"));
							book.setClassId(resultSet.getString("class_id"));
							book.setAuthor(resultSet.getString("author"));
							book.setIntroduction(resultSet.getString("introduction"));
							book.setPressmark(resultSet.getInt("pressmark"));
							book.setLanguage(resultSet.getString("language"));
							book.setImgSrc(resultSet.getString("img_src"));
							classification.setId(resultSet.getString("c_id"));
							classification.setClassName(resultSet.getString("c_name"));
							classification.setIndex(resultSet.getString("c_index"));
							book.setClassification(classification);
							books.add(book);
						}

					}
				});
		return books;
	}
	
	/**
	 * 按照分类id获取图书列表
	 * 
	 * @param classId
	 * @return
	 */
	public ArrayList<Book> getBookListByClassId(String classId) {
		ArrayList<Book> books = new ArrayList<Book>();
		jdbcTemplate.query(SELECT_BOOK_BY_CLASSIFICATION_ID, new Object[] { classId }, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					Book book = new Book();
					Classification classification = new Classification();
					book.setId(resultSet.getString("id"));
					book.setPrice(resultSet.getBigDecimal("price"));
					book.setState(resultSet.getInt("state"));
					book.setPublish(resultSet.getString("publish"));
					book.setPubdate(resultSet.getDate("pubdate"));
					book.setName(resultSet.getString("name"));
					book.setIsbn(resultSet.getString("isbn"));
					book.setClassId(resultSet.getString("class_id"));
					book.setAuthor(resultSet.getString("author"));
					book.setIntroduction(resultSet.getString("introduction"));
					book.setPressmark(resultSet.getInt("pressmark"));
					book.setLanguage(resultSet.getString("language"));
					book.setImgSrc(resultSet.getString("img_src"));
					classification.setId(resultSet.getString("c_id"));
					classification.setClassName(resultSet.getString("c_name"));
					classification.setIndex(resultSet.getString("c_index"));
					book.setClassification(classification);
					books.add(book);
				}
				
			}
		});
		return books;
	}

	/**
	 * 获取全部图书列表
	 * 
	 * @return
	 */
	public ArrayList<Book> getBookList() {
		final ArrayList<Book> books = new ArrayList<Book>();

		jdbcTemplate.query(SELECT_ALL_BOOKS, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					Book book = new Book();
					Classification classification = new Classification();
					book.setId(resultSet.getString("id"));
					book.setPrice(resultSet.getBigDecimal("price"));
					book.setState(resultSet.getInt("state"));
					book.setPublish(resultSet.getString("publish"));
					book.setPubdate(resultSet.getDate("pubdate"));
					book.setName(resultSet.getString("name"));
					book.setIsbn(resultSet.getString("isbn"));
					book.setClassId(resultSet.getString("class_id"));
					book.setAuthor(resultSet.getString("author"));
					book.setIntroduction(resultSet.getString("introduction"));
					book.setPressmark(resultSet.getInt("pressmark"));
					book.setLanguage(resultSet.getString("language"));
					book.setImgSrc(resultSet.getString("img_src"));
					classification.setId(resultSet.getString("c_id"));
					classification.setClassName(resultSet.getString("c_name"));
					classification.setIndex(resultSet.getString("c_index"));
					book.setClassification(classification);
					books.add(book);
				}
			}
		});
		return books;

	}

}
