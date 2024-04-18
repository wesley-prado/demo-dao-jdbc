package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		List<Seller> sellers = sellerDao.findAll();

		System.out.print( sellers );
	}
}
