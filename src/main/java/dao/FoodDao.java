package dao;

import model.Food;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FoodDao extends Dao {

	public FoodDao() {
		super();
	}
	
	public List<Food> getAllFood() throws SQLException{
		List<Food> data = new ArrayList<>();
		String query = "select id, dish_name, price, delivery_mode, notes, category, ingredients from food_menu.food order by dish_name";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			Food food = new Food();
			food.setId(rs.getInt(1));
			food.setDishName(rs.getString(2));
			food.setPrice(rs.getString(3));
			food.setDeliveryMode(rs.getString(4));
			food.setNotes(rs.getString(5));
			food.setCategory(rs.getString(6));
			food.setIngredients(rs.getString(7));

			data.add(food);
		}
		return data;
	}
	
	public void insertFood(Food food) throws SQLException {

		String insert = "insert into food_menu.food(dish_name, price, delivery_mode, notes, category, ingredients) values(?,?,?,?,?,?)";
		stm = connector.prepareStatement(insert);

		stm.setString(1,food.getDishName());
		stm.setString(2,food.getPrice());
		stm.setString(3,food.getDeliveryMode());
		stm.setString(4,food.getNotes());
		stm.setString(5,food.getCategory());
		stm.setString(6,food.getIngredients());

		stm.executeUpdate();
		stm.close();
	}
	
	public void updateFood(Food food) throws SQLException {
		String update = "update food_menu.food set dish_name = ?, price = ?, delivery_mode = ?, notes = ?, category = ?, ingredients = ? where id = ?";
		stm = connector.prepareStatement(update);

		stm.setString(1,food.getDishName());
		stm.setString(2,food.getPrice());
		stm.setString(3,food.getDeliveryMode());
		stm.setString(4,food.getNotes());
		stm.setString(5,food.getCategory());
		stm.setString(6,food.getIngredients());

		stm.setInt(7,food.getId());
		
		stm.executeUpdate();
		stm.close();
	}
	
	public void deleteDish(int id) throws SQLException {
		String delete = "delete from food_menu.food where id = ?";
		stm = connector.prepareStatement(delete);

        stm.setInt(1, id);
        stm.execute();
	}
	
}
