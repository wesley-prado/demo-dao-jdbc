package model.dao;

import model.entities.Seller;

import java.util.List;

public interface SellerDao {
  void insert(Seller obj);

  void update(Seller obj);

  void deleteById(Integer id);

  Seller findById(Integer id);

  Seller findByEmail(String email);

  List<Seller> findAll();

  List<Seller> findByDepartmentId(Integer id);

}
