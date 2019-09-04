package com.tools;

public class update_DB_pic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UploadPicture up =new UploadPicture();
		String path="storeIMG/";

		for(int i=1;i<=9;i++){
			up.updatePicture("STR_000"+i, path+"STR_000"+i+".jpg");
		}
		
		for(int i=10;i<=36;i++){
			up.updatePicture("STR_00"+i, path+"STR_00"+i+".jpg");
		}

		up.updatePicture("STR_0030", path+"STR_0030.jpg");

	}

}
