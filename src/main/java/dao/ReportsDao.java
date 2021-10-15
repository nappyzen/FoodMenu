package dao;


import javafx.scene.chart.PieChart.Data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportsDao extends Dao {

	public ReportsDao() {
		super();
	}

	public List<Data> getAllFoodBasedOnDelivery() throws SQLException {
		List<Data> data = new ArrayList<>();
		String query = "select count(*), delivery_mode FROM food_menu.food group by delivery_mode;";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query);

		while(rs.next()) {
			data.add(new Data(rs.getString(2), rs.getDouble(1)));
		}
		return data;
	}


}
