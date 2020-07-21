package kr.or.ddit.task.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class XMLBaseTaskScheduler {
	@Autowired
	@Qualifier("xmlBaseTaskScheduler")
	private ThreadPoolTaskScheduler scheduler;
	// Black Box 패턴 스레드 구현체
	@Autowired
	private Worker worker;
	private long servicePoolTime = 1000L;
	
	public void scheduling(){
		// 1초 간격
//		this.scheduler.scheduleWithFixedDelay(worker, servicePoolTime);
		// 최초 1초 뒤 실행
//		this.scheduler.scheduleWithFixedDelay(worker, servicePoolTime);
		this.scheduler.schedule(worker, new CronTrigger("*/2 * * * * ?"));
	}
}
