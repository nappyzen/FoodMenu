package dao;


public class DatabaseHelperDao {

	private static DatabaseHelperDao dao = new DatabaseHelperDao();
	private static FoodDao foodDao = new FoodDao();
	private static ReportsDao reportsDao = new ReportsDao();
	private static NotesDao notesDao = new NotesDao();

	public static DatabaseHelperDao getControlDao() {
		return dao;
	}
	
	public FoodDao getFoodDao() {
		return foodDao;
	}

	public NotesDao getNotesDao() {
		return notesDao;
	}
	
	public ReportsDao getReportsDao() {
		return reportsDao;
	}
	
}
