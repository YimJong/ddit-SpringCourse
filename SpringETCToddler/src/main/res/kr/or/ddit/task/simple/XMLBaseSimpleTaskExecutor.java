package kr.or.ddit.task.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class XMLBaseSimpleTaskExecutor {
	@Autowired
	@Qualifier("xmlBaseTaskExecutor")
	private ThreadPoolTaskExecutor executor;
	
	public void printMsg(){
		for(int i=0; i<10; i++){
			this.executor.execute(new MsgPrtTask("전송메세지(" + i + ")"));
		}
	}
	
	private class MsgPrtTask implements Runnable{
		private String message;
		
		public MsgPrtTask(String message){
			this.message = message;
		}
		
		@Override
		public void run() {
			System.out.println("취득 메세지 : " + this.message);
		}
	}
	
	
}

