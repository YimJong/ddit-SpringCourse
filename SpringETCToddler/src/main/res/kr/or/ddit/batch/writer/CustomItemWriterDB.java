package kr.or.ddit.batch.writer;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.or.ddit.batch.report.Report;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CustomItemWriterDB implements ItemWriter<Report> {
	@Autowired
	private SqlMapClient client;
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public void write(List<? extends Report> lprodList) throws SQLException{
		this.client.startBatch();
		for(Report lprodInfo : lprodList){
			this.client.insert("lprod.insertLprodInfo", lprodInfo);
		}
		this.client.executeBatch();
	}
}
