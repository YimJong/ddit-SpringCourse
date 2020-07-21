package kr.or.ddit.batch.processor;

import kr.or.ddit.batch.report.Report;

import org.springframework.batch.item.ItemProcessor;

public class CustomProcessor implements ItemProcessor<Report, Report> {

	@Override
	public Report process(Report item) throws Exception {
		System.out.println("CustomProcessor process() 호출 및 Report 반환 " + item);
		return item;
	}
}
