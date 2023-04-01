package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {

		DepartmentDao sdao = DaoFactory.createDepartmentDao();
		
		List<Department> list = sdao.findAll();
		for(Department obj : list) {
			System.out.println(obj);
		}
		
	}

}
