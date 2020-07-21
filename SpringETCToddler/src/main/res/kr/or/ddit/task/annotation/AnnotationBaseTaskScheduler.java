package kr.or.ddit.task.annotation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBaseTaskScheduler {

	// 1초 간격
	@Scheduled(fixedDelay=1000)
	public void timeCheck(){
		System.out.println("AnnotationBaseTaskScheduler fixedDelay=1000");
	}
	// 최초 2초 이후, 5초이내 동작.
	@Scheduled(fixedRate=5000)
	public void dateCheck(){	
		System.out.println("AnnotationBaseTaskScheduler fixedRate=5000");
	}
	@Scheduled(cron="*/5 * * * * MON-FRI")
	public void monthCheck(){
		System.out.println("AnnotationBaseTaskScheduler */5 * * * * MON-FRI");
	}
}
