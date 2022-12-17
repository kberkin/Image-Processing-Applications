package image_p;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Customer{
	private String fname,lname,city;
	private int year,purchase;
	
	Customer(String f, String l, String c, int y, int p){
		this.fname = f;
		this.lname = l;
		this.city = c;
		this.year = y;
		this.purchase = p;
	}
	
	public String getFName() {
		return fname;
	}
	public String getLName() {
		return lname;
	}
	public String getCity() {
		return city;
	}
	public int getYear() {
		return year;
	}
	public int getPurchase() {
		return purchase;
	}
}

public class ImageProcessing_Part2 {

	
	public static void main(String[] args) {
		
		List<Customer> cus = new ArrayList<Customer>();
		cus.add(new Customer("Adem","Özyavaþ","Ýstanbul",2010,15));
		cus.add(new Customer("Batuhan","Öznalçýn","Ýstanbul",2015,8));
		cus.add(new Customer("Utku","Avcý","Kýrklareli",2011,7));
		cus.add(new Customer("Ahmet","Ak","Trabzon",2012,5));
		cus.add(new Customer("Erkin","Arýcý","Ýzmit",2011,5));
		cus.add(new Customer("Doðukan","Güler","Malatya",2016,3));
		cus.add(new Customer("Enes","Tosun","Ankara",2017,8));
		cus.add(new Customer("Alper","Çimen","Çanakkale",2020,2));
		cus.add(new Customer("Burak","Kapacak","Bursa",2019,7));
		cus.add(new Customer("Mesut","Özil","Ýstanbul",2021,4));
		
		System.out.println("Lambda 1");
		Stream<Customer> cus1 = 
						cus.stream()
						.filter(y -> y.getYear() == 2011)
						.sorted(Comparator.comparingInt(Customer::getPurchase));
		cus1.forEach(y -> System.out.print(y.getPurchase()+" "));
		
		System.out.println("\n\nLambda 2");
		Set<String> unique = new HashSet<>();
		List<Customer> cus2 =
						cus.stream()
						.filter(c -> unique.add(c.getCity()))
						.collect(Collectors.toList());
		unique.forEach(c -> System.out.print(c.toString()+" "));
		
		System.out.println("\n\nLambda 3");
		Stream<Customer> cus3 = 
						cus.stream()
						.filter(c -> c.getCity().equals("Ýstanbul"))
						.sorted(Comparator.comparing(Customer::getFName));
		cus3.forEach(c -> System.out.println(c.getFName()));
		
		System.out.println("\n\nLambda 4");
		Stream<Customer> cus4 =
						cus.stream()
						.sorted(Comparator.comparing(Customer::getFName));
		cus4.forEach(n -> System.out.print(n.getFName()+" "));
		
		System.out.println("\n\nLambda 5");
		Stream<Customer> cus5 =
						cus.stream()
						.filter(c -> c.getCity().equals("Ankara"));
		cus5.forEach(c -> System.out.println(c.getFName()));
		
		System.out.println("\nLambda 6");
		Stream<Customer> cus6 = 
						(Stream<Customer>) cus.stream()
						.filter(c -> c.getCity().equals("Ýstanbul"))
						.sorted(Comparator.comparing(Customer::getPurchase));
		cus6.forEach(c -> System.out.println(c.getPurchase()));
		
		System.out.println("\nLambda 7");
		Customer cus7;
			cus7 = cus.stream()
				 .max(Comparator.comparing(Customer::getPurchase))
				 .get();
		System.out.println(cus7.getPurchase());
		
		
		System.out.println("\nLambda 8");			
		Customer cus8;
		cus8 = cus.stream()
			 .min(Comparator.comparing(Customer::getPurchase))
			 .get();
		System.out.println(cus8.getPurchase());
		
		
		System.out.println("\nLambda 9");
		Stream<Customer> cus9 = 
						cus.stream()
						.filter(t -> t.getPurchase() > 6);
		cus9.forEach(t -> System.out.println(t.getPurchase()));
						
	}
}