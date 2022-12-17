package image_p;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImageProcessing_Part2_2{
	
	String fname,lname,city;
	int year,purchase;
	
	ImageProcessing_Part2_2(String f, String l, String c, int y, int p){
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
	
	public static void main(String[] args) {
		
		List<ImageProcessing_Part2_2> cus = new ArrayList<ImageProcessing_Part2_2>();
		
		cus.add(new ImageProcessing_Part2_2("Adem","Özyavaþ","Ýstanbul",2010,15));
		cus.add(new ImageProcessing_Part2_2("Batuhan","Öznalçýn","Ýstanbul",2015,8));
		cus.add(new ImageProcessing_Part2_2("Utku","Avcý","Kýrklareli",2011,7));
		cus.add(new ImageProcessing_Part2_2("Ahmet","Ak","Trabzon",2012,5));
		cus.add(new ImageProcessing_Part2_2("Erkin","Arýcý","Ýzmit",2011,5));
		cus.add(new ImageProcessing_Part2_2("Doðukan","Güler","Malatya",2016,3));
		cus.add(new ImageProcessing_Part2_2("Enes","Tosun","Ankara",2017,8));
		cus.add(new ImageProcessing_Part2_2("Alper","Çimen","Çanakkale",2020,2));
		cus.add(new ImageProcessing_Part2_2("Burak","Kapacak","Bursa",2019,7));
		cus.add(new ImageProcessing_Part2_2("Mesut","Özil","Ýstanbul",2021,4));
		
		System.out.println("Predicate 1");
		cus.stream()
		.filter(is2011())
		.sorted(Comparator.comparingInt(ImageProcessing_Part2_2::getPurchase))
		.forEach(y -> System.out.print(y.getPurchase()+" "));
		
		System.out.println("\n\nPredicate 2");
		cus.stream()
		.filter(isUnique())
		.forEach(u -> System.out.print(u.getCity()+" "));
		
		System.out.println("\n\nPredicate 3");
		cus.stream()
		.filter(isCity())
		.sorted(Comparator.comparing(ImageProcessing_Part2_2::getFName))
		.forEach(c -> System.out.print(c.getFName()+" "));
		
		System.out.println("\n\nPredicate 4");
		cus.stream()
		.sorted(Comparator.comparing(ImageProcessing_Part2_2::getFName))
		.forEach(n -> System.out.print(n.getFName()+" "));
		
		System.out.println("\n\nPredicate 5");
		cus.stream()
		.filter(isAnkara())
		.forEach(c -> System.out.println(c.getFName()));
		
		System.out.println("\nPredicate 6");
		cus.stream()
		.filter(istP())
		.sorted(Comparator.comparing(ImageProcessing_Part2_2::getPurchase))
		.forEach(c -> System.out.println(c.getPurchase()));
		
		System.out.println("\nPredicate 7");
		ImageProcessing_Part2_2 fb;
		fb = cus.stream()
		.max(Comparator.comparing(ImageProcessing_Part2_2::getPurchase))
		.get();
		System.out.println(fb.getPurchase());
		
		System.out.println("\nPredicate 8");
		ImageProcessing_Part2_2 fbfb;
		fbfb = cus.stream()
			 .min(Comparator.comparing(ImageProcessing_Part2_2::getPurchase))
			 .get();
		System.out.println(fbfb.getPurchase());
		
		System.out.println("\nPredicate 9");
		cus.stream()
		.filter(isLess())
		.forEach(t -> System.out.println(t.getPurchase()));
		
	}
	
	public static Predicate<ImageProcessing_Part2_2> is2011(){
		return y -> y.getYear() == 2011;
	}
	
	public static Predicate<ImageProcessing_Part2_2> isUnique(){
		Set<String> unique = new HashSet<>();
		return u -> unique.add(u.getCity());
	}
	
	public static Predicate<ImageProcessing_Part2_2> isCity(){
		return c -> c.getCity().equals("Ýstanbul");
	}
	
	public static Predicate<ImageProcessing_Part2_2> isAnkara(){
		return n -> n.getCity().equals("Ankara");
	}
	
	public static Predicate<ImageProcessing_Part2_2> istP(){
		return c -> c.getCity().equals("Ýstanbul");
	}
	
	public static Predicate<ImageProcessing_Part2_2> isLess(){
		return t -> t.getPurchase() > 6;
	}
}