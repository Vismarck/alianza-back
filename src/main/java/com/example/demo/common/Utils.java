package com.example.demo.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.demo.model.ClientsModel;

public class Utils {
	
	public static Date dateConvert(String dat) {
		Date dateCon = new Date();
		if (!dat.trim().isEmpty()) {
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
			try {
				dateCon = formatoDeFecha.parse(dat);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dateCon;
	}
	
	public static List<ClientsModel> listAllClients(){
		
		ClientsModel cliente1 = new ClientsModel();
		cliente1.setSharedKey("jgutierrez");
		cliente1.setName("Juliana Gutierrez");
		cliente1.setPhone("3216876543");
		cliente1.setEmail("jgutierrez@gmail.com");
		cliente1.setStartDate(Utils.dateConvert("20/05/2019"));
		cliente1.setEndDate(Utils.dateConvert("20/06/2020"));
		cliente1.setDateAdded(Utils.dateConvert("20/05/2019"));
		
		ClientsModel cliente2 = new ClientsModel();
		cliente2.setSharedKey("mmartinez");
		cliente2.setName("Manuel Martinez");
		cliente2.setPhone("3216876543");
		cliente2.setEmail("mmartinez@gmail.com");
		cliente2.setStartDate(Utils.dateConvert("20/05/2019"));
		cliente2.setEndDate(Utils.dateConvert("20/06/2020"));
		cliente2.setDateAdded(Utils.dateConvert("20/05/2019"));
		
		ClientsModel cliente3 = new ClientsModel();
		cliente3.setSharedKey("aruiz");
		cliente3.setName("Ana Ruiz");
		cliente3.setPhone("3216876543");
		cliente3.setEmail("aruizz@gmail.com");
		cliente3.setStartDate(Utils.dateConvert("20/05/2019"));
		cliente3.setEndDate(Utils.dateConvert("20/06/2020"));
		cliente3.setDateAdded(Utils.dateConvert("20/05/2019"));
		
		ClientsModel cliente4 = new ClientsModel();
		cliente4.setSharedKey("ogarcia");
		cliente4.setName("Oscar Garcia");
		cliente4.setPhone("3216876543");
		cliente4.setEmail("ogarcia@gmail.com");
		cliente4.setStartDate(Utils.dateConvert("20/05/2019"));
		cliente4.setEndDate(Utils.dateConvert("20/06/2020"));
		cliente4.setDateAdded(Utils.dateConvert("20/05/2019"));
		
		ClientsModel cliente5 = new ClientsModel();
		cliente5.setSharedKey("tramos");
		cliente5.setName("Tatiana Ramos");
		cliente5.setPhone("3216876543");
		cliente5.setEmail("tramos@gmail.com");
		cliente5.setStartDate(Utils.dateConvert("20/05/2019"));
		cliente5.setEndDate(Utils.dateConvert("20/06/2020"));
		cliente5.setDateAdded(Utils.dateConvert("20/05/2019"));
		
		ClientsModel cliente6 = new ClientsModel();
		cliente6.setSharedKey("caiza");
		cliente6.setName("Carlos Ariza");
		cliente6.setPhone("3216876543");
		cliente6.setEmail("cariza@gmail.com");
		cliente6.setStartDate(Utils.dateConvert("20/05/2019"));
		cliente6.setEndDate(Utils.dateConvert("20/06/2020"));
		cliente6.setDateAdded(Utils.dateConvert("20/05/2019"));
		
		ClientsModel cliente7 = new ClientsModel();
		cliente7.setSharedKey("rvillaneda");
		cliente7.setName("Rodrigo Villaneda");
		cliente7.setPhone("3216876543");
		cliente7.setEmail("rvillaneda@gmail.com");
		cliente7.setStartDate(Utils.dateConvert("20/05/2019"));
		cliente7.setEndDate(Utils.dateConvert("20/06/2020"));
		cliente7.setDateAdded(Utils.dateConvert("20/05/2019"));
		
		ClientsModel cliente8 = new ClientsModel();
		cliente8.setSharedKey("mfonseca");
		cliente8.setName("Mauricio Foncesa");
		cliente8.setPhone("3216876543");
		cliente8.setEmail("mfoncesa@gmail.com");
		cliente8.setStartDate(Utils.dateConvert("20/05/2019"));
		cliente8.setEndDate(Utils.dateConvert("20/06/2020"));
		cliente8.setDateAdded(Utils.dateConvert("20/05/2019"));
		
		return Arrays.asList(cliente1,cliente2,cliente3,cliente4,cliente5,cliente6,cliente7,cliente8);
	}

}
