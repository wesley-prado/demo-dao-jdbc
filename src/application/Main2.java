package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

public class Main2 {
  public static void main(String[] args) {
	DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

	System.out.println( departmentDao.findAll() );
  }
}
