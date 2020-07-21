package kr.or.ddit.join.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.or.ddit.common.CustomGenericException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.task.scheduler.XMLBaseTaskScheduler;
import kr.or.ddit.task.simple.XMLBaseSimpleTaskExecutor;
import kr.or.ddit.vo.MemberVO;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join/")
public class JoinController {
	@Autowired
	private IMemberService service;
	
	// Spring Task
	@Autowired
	private XMLBaseSimpleTaskExecutor taskExecutor;
    @Autowired
    private XMLBaseTaskScheduler scheduler;

    // Spring Batch
    @Autowired
    private Job simpleBatchJob;
    @Autowired
    private JobLauncher jobLauncher;
    
	@RequestMapping("loginForm")
	public String loginForm() throws Exception{
		return "join/loginForm";
	}
	
	@RequestMapping("loginCheck")
	public String loginCheck(String mem_id, String mem_pass,
				HttpSession session) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);
		
		MemberVO memberInfo = service.getMemberInfo(params);
		
		if(memberInfo != null){
			session.setAttribute("LOGIN_MEMBERINFO", memberInfo);
			
			// ########## 스프링 Task 시작 ##########
			// Spring Simple Task 실행
			this.taskExecutor.printMsg();
			// Spring Task Scheduler 실행
			this.scheduler.scheduling();
			// ########## 스프링 Task 끝 ##########
			
			return "redirect:/member/memberList.do";
		}else{
			// ########## 스프링 Batch 시작 ##########			
			// Spring Batch 실행
			// JobLauncher에의해 실행된 Job의 JobParameters는 해당 Jon을 식별하는 정보를 저장하게되며,
			// 동일한 잡의 실행시 동일한 JobParameters를 활용할수없음.
			// JobParametersBuilder에 다른 식별자 설정을 통해 생성된 JobParameter를 활용.
			JobParameters jobParameters = 
					  new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
			JobExecution jobExecution = this.jobLauncher.run(this.simpleBatchJob, jobParameters);
			System.out.println("Job 실행 결과 : " + jobExecution.getStatus());
			// ########## 스프링 Batch 끝 ##########
			
			String message="";
			try {
				message = URLEncoder.encode("회원이 아닙니다.", "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "redirect:/join/loginForm.do?message="+message;
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/join/loginForm.do";
	}
	
	@RequestMapping("errorExecute")
	public String errorExecute(){
		throw new CustomGenericException("error-code1234", "글로벌 에러");
	}
}
