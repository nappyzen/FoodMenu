package dao;

import model.Food;

import java.sql.SQLException;

public class NotesDao extends Dao {

    public NotesDao() {
        super();
    }

    public void updateNote(Food food) throws SQLException {
        String update = "update food_menu.food set notes = ? where id = ?";
        stm = connector.prepareStatement(update);

        stm.setString(1, food.getNotes());
        stm.setInt(2, food.getId());

        stm.executeUpdate();
        stm.close();

    }
}
