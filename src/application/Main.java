package application;

import model.entities.Department;
import model.entities.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );

		try {
			System.out.println( new Seller(
					1,
					"Wesley Prado",
					"wesleyprado.dev@codemages.com",
					sdf.parse( "01/07/1995" ),
					10230.0,
					new Department( 1, "Software Engineering" )
			) );
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
