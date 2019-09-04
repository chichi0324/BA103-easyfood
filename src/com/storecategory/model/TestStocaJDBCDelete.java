package com.storecategory.model;

public class TestStocaJDBCDelete {

	public static void main(String[] args) {
		
		StocaJDBCDAO dao = new StocaJDBCDAO();
		dao.delete("STOCA_0007");
		System.out.println("success");

	}

}
