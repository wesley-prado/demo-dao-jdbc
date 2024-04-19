package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Main2 {
  public static void main(String[] args) {
	DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

	Department dep = new Department().setId( 6 ).setName( "Management" );
	departmentDao.update( dep );

	System.out.println( dep );
  }
}
