package kr.or.ddit.batch.mapper;

import java.text.SimpleDateFormat;

import kr.or.ddit.batch.report.Report;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ReportFieldSetMapper implements FieldSetMapper<Report> {
	@Override
	public Report mapFieldSet(FieldSet fieldSet) throws BindException {
		System.out.println("ReportFieldSetMapper mapFieldSet() 호출 및 Report 작성 반환");
		Report report = new Report();
		report.setLprod_id(fieldSet.readString(0));
		report.setLprod_gu(fieldSet.readString(1));
		report.setLprod_nm(fieldSet.readString(2));
		
		return report;
	}
	
}
