package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Main2 {
  public static void main(String[] args) {
	DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

	Department dep = new Department().setName( "Cleaning" );
	departmentDao.insert( dep );

	System.out.println( dep );
  }
}
