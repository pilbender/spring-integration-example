package org.raescott.springintegrationexample;

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

/**
 * @author Richard Scott Smith <scott.smith@isostech.com>
 */
//@Service
public class SqsService {
	@SqsListener("assess-ci-POC")
	public void queueListener(Person person) {
		System.out.println("********************************************************************************");
		System.out.println(person.getFirstName() + " " + person.getLastName());
		System.out.println("********************************************************************************");
	}
}
