package my.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 *
 */
public class CommonToAll 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!!" );
        
        //ConcurrentHashMap<Integer,String> m = new ConcurrentHashMap<Integer, String>();
        
        Car c1 = new Car("benz", 2011, 10.9);
        
        System.out.println(c1);
        
        Car c2 = new Car(c1);
        
        System.out.println(c2);
        
        if (c1 == c2)  System.out.println("==");
        if (c1.equals(c2)) System.out.println("equals");
        
        Map<Car, Integer> m = new HashMap<Car, Integer>();
        m.put(c1, 10);
        
        System.out.println(m.get(c1));
        System.out.println(m.get(c2));
        
    }
}

class Car {
	String name;
	int model;
	double mileage;
	
	Car(String n, int m, double mileage) {
		name = n;
		model = m;
		this.mileage = mileage;
	}
	public Car(Car c2) {
		name = c2.name;
		model = c2.model;
		mileage = c2.mileage;
	}
	
	@Override
	public String toString() {
		return name + ": " + model + ": " + mileage;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Car)) return false;
		
		Car c = (Car) o;
		
		if(c.name.equals(name) && c.model == model && Double.compare(c.mileage, mileage) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + model;
		result = 31 * result + name.hashCode();
		return result;
	}
}