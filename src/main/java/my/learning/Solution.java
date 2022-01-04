package my.learning;

/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 * We want to build a trip settlement app
 * Users can go on a trip together.
 * They pay for expenses individually
 * At the end of the trip, the system should settle all payments fairly (each user should have paid the same amount)
 * The system must generate payment advises like : User1 will pay XXX to userN, User 2 will pay yyy to userX etc.
 
 *
 */

class User {
	int id;
	String name;
}

class Trip {
	int id;
	String details;
	int[] users;
}

class Payment {
	int tripId;
	String userId;
	double amount;

	Payment(int t, double a, String u) {
		tripId = t;
		userId = u;
		amount = a;
	}

}

//todo need settlement object?

class Solution {
	public static void main(String[] args) {

		List<Payment> tripPayments = new ArrayList<Payment>();

		tripPayments.add(new Payment(1, 40, "A"));
		tripPayments.add(new Payment(1, 100, "B"));
		tripPayments.add(new Payment(1, 160, "C"));
		tripPayments.add(new Payment(1, 0, "D"));
		tripPayments.add(new Payment(1, 85, "E"));
		tripPayments.add(new Payment(1, 65, "F"));

		// int[] users = new int[] {11,22,33,44}; //trip.users;

		String[] users = new String[] { "A", "B", "C", "D", "E", "F" }; // trip.users;

		double tripTotal = 0.0;

		Map<String, Double> userSpends = new HashMap<String, Double>();

		for (Payment p : tripPayments) {
			// todo: have a check for tripid
			tripTotal += p.amount;

			double userPayment = 0;
			if (userSpends.containsKey(p.userId)) {
				userPayment = userSpends.get(p.userId);
			}
			userSpends.put(p.userId, userPayment + p.amount);

		}

		double avgAmount = tripTotal / users.length;

		// A= 40, B=100, C=160, D = 0
		// D will pay 75 to C -- 10
		// A will pay 10 to C
		// A will pay 25 to B

		Arrays.sort(users, new Comparator<String>() {
			public int compare(String a, String b) {
				Double aval = userSpends.getOrDefault(a, 0.0);
				Double bval = userSpends.getOrDefault(b, 0.0);

				return (aval > bval) ? 1 : -1;
			}
		});

		double iamountToPay = avgAmount - userSpends.getOrDefault(users[0], 0.0);

		double jamountToReceive = userSpends.getOrDefault(users[users.length - 1], 0.0) - avgAmount;

		for (int i = 0, j = users.length - 1; i < j;) {

			if (iamountToPay == 0) {
				iamountToPay = avgAmount - userSpends.getOrDefault(users[i], 0.0);
			}

			if (jamountToReceive == 0) {
				jamountToReceive = userSpends.getOrDefault(users[j], 0.0) - avgAmount;
			}

			if (iamountToPay > jamountToReceive) {
				iamountToPay = iamountToPay - jamountToReceive;
				System.out.println(users[i] + " will pay " + jamountToReceive + " to " + users[j]);
				jamountToReceive = 0;
				j--;
			} else if (iamountToPay < jamountToReceive) {
				jamountToReceive = jamountToReceive - iamountToPay;
				System.out.println(users[i] + " will pay " + iamountToPay + " to " + users[j]);
				iamountToPay = 0;
				i++;
			} else {
				
				
				if ( iamountToPay !=0) { // To avoid matching same amount
					System.out.println(users[i] + " will pay " + iamountToPay + " to " + users[j]);
				}
				
				i++;
				j--;
				
				iamountToPay = 0;
				jamountToReceive = 0;
			}

		}

	}
}
