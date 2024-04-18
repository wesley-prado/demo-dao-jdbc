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
	  sellerDao.update( Seller.newSeller(
			  13,
			  "Beatriz Avelino",
			  "anymail@gmail.com",
			  Date.from( sdt.parse( "03/02/2001" ).toInstant() ),
			  6500.0,
			  new Department().setId( 5 )
	  ) );
	} catch (ParseException e) {
	  e.printStackTrace();
	}
  }
}
