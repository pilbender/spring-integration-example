package org.raescott.springintegrationexample;

import org.springframework.stereotype.Service;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
@Service
public class MyJmsService {

	public static final String MY_QUEUE = "my.queue";

	public void myService(String name){
		System.out.println("################################");
		System.out.println("################################");
		System.out.println("################################");
		System.out.println("##  Hello " + name + "!!!" );
		System.out.println("################################");
		System.out.println("################################");
		System.out.println("################################");
	}
}
