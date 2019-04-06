package kr.ac.hansung.cse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Product;

@Repository
public class ProductDao {

	   private JdbcTemplate jdbcTemplate;

	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	      jdbcTemplate = new JdbcTemplate(dataSource);
	   }
	   
	   public List<Product> getProducts(){
		   
		   String sqlStatement = "select * from product"; //record -> object
		   return jdbcTemplate.query(sqlStatement, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

				Product product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDiscription(rs.getString("discription"));
				
				return product;
			}
	   });
	 }

	   
/*  public Product getProduct(int id){
		   
		   
		   String sqlStatement = "select * from product where id=?";
			
		   return jdbcTemplate.queryForObject(sqlStatement,new Object[] {id},
				   new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

				Product product = new Product();
				
				product.getName();
				product.getDiscription();
				product.getManufacturer();
				product.getCategory();
				product.getPrice();
				
			
				product.setName(rs.getString("name"));
				product.setDiscription(rs.getString("discription"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getInt("price"));
		
				return product;
			}
	   });
  	}*/
	   
	   
	public boolean addProduct(Product product) {
		
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int uniInStock = product.getUnitInStock();
		String discription = product.getDiscription();
		
		String sqlStatement = "insert into product (name, category, price, manufacturer, unitInStock, discription) "
				+ "values (?, ?, ?, ?, ?, ?)";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {name, category, price, manufacturer, uniInStock, discription}) == 1);
		
	}

	public boolean deleteProduct(int id) {
		
		String sqlStatement = "delete from product where id =?";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {id}) ==1);
		
	}

	public Product getProductById(int id) {

	
		String sqlStatement = "select * from product where id=?";
		
		   return jdbcTemplate.queryForObject(sqlStatement,new Object[] {id},
				   new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

				Product product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDiscription(rs.getString("discription"));
				
				return product;
			}
	   });
		
	}

	public boolean updateProduct(Product product) {

		int id=product.getId();
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int uniInStock = product.getUnitInStock();
		String discription = product.getDiscription();
		
		String sqlStatement = "update product set name=?, category=?, price=?, manufacturer=?, unitInStock=?, discription=? "
				+ "where id=?";
			
		
		return (jdbcTemplate.update(sqlStatement, 
				new Object[] {name, category, price, manufacturer, uniInStock, discription, id}) == 1);
		
	}
}
