package eu.grayroot.anarchycore.sql;

import eu.grayroot.anarchycore.AnarchyCore;
import eu.grayroot.anarchycore.object.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportData {
	
	private Connection connection;
	
	public ReportData(Connection connection) {
		this.connection = AnarchyCore.getInstance().getConnection();
	}

	public void createReport(Report report){
		try {
			PreparedStatement rs = connection.prepareStatement("INSERT INTO `reports`(`content`, `server`, `author_name`) VALUES (?,?,?)");
			rs.setString(1, report.getContent());
			rs.setString(2, report.getServer());
			rs.setString(3, report.getAuthorName());
			rs.executeUpdate();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
