package com.epam.threadingdemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThreadingdemoApplication {

	public static void main(String[] args) {
		
		// System.out.println("Main Thread Started");

		// // This is a user thread
		// // if the user thread dies, the main thread will continue to run
		// // but if the main thread dies, all user threads will also die
		// // also, all the daemon threads will die if there are no user threads running

		// Thread threadOne = new ThreadOne("Zephyr");

		// // Making threadOne a daemon thread
		// threadOne.setDaemon(true);
		// threadOne.start();
		// System.out.println("Main Thread Ended");

        Thread t1 = new CookingTask("Pasta");
        Thread t2 = new CookingTask("Salad");
        Thread t3 = new CookingTask("Dessert");
        Thread t4 = new CookingTask("Rice");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
	}

}
