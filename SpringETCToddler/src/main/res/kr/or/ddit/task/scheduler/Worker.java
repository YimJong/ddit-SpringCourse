package kr.or.ddit.task.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Worker implements Runnable {

	@Override
	public void run() {
		System.out.println("working : " + new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date()));
	}

}
