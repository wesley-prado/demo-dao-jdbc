package model.dao;

import database.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DaoFactory {
  @Contract(value = " -> new")
  public static @NotNull SellerDao createSellerDao() {
	return new SellerDaoJDBC( DB.getConnection() );
  }

  @Contract(value = " -> new")
  public static @NotNull DepartmentDao createDepartmentDao() {
	return new DepartmentDaoJDBC( DB.getConnection() );
  }
}
