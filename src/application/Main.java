package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
  private static final SimpleDateFormat sdt = new SimpleDateFormat( "dd/MM/yyyy" );

  public static void main(String[] args) {
	SellerDao sellerDao = DaoFactory.createSellerDao();
	List<Seller> sellers = sellerDao.findAll();

	System.out.print( sellers );

	try {
	  sellerDao.insert( Seller.newSeller(
			  13,
			  "Wrongful insert",
			  "wrongful_email@gmail.com",
			  Date.from( sdt.parse( "03/02/2001" ).toInstant() ),
			  6500.0,
			  new Department().setId( 5 )
	  ) );

	  Seller wrongfulSeller = sellerDao.findByEmail( "wrongful_email@gmail.com" );
	  System.out.println( wrongfulSeller );

	  sellerDao.deleteById( wrongfulSeller.getId() );
	} catch (ParseException e) {
	  e.printStackTrace();
	}
  }
}
