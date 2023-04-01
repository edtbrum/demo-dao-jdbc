package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sdao = DaoFactory.createSellerDao();
		
		//Seller se = sdao.findById(2);
		//System.out.println(se);
		
		List<Seller> list = sdao.findByDepartment(new Department(2, null));
		for(Seller s : list) {
			System.out.println(s);
		}
	}

}
