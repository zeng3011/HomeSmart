package object.homesmart.gogo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "BOOKS.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String TABLE_NAME = "books_table";
	
	public static final String BOOK_ID = "id";
	public static final String BOOK_NAME = "book_name";
	public static final String BOOK_AUTHOR = "book_author";
	private static SQLiteDatabase db = null ;
	 
	 /**  
     * (Context context, String name, CursorFactory factory,int version)  
     * @param context 上下文对象  
     * @param name 数据库名称 secb.db  
     * @param factory  游标工厂  
     * @param version 数据库版本  
     */ 
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	//创建表结构
	private static final String CREATE_STUDENT_TABLE = "create table "
			+ TABLE_NAME + " (" 
			+ BOOK_ID + " integer primary key autoincrement, " 
			+ BOOK_NAME + " text not null, " 
			+ BOOK_AUTHOR + " text);";

	/**数据库第一次被使用时创建数据库  
     * @param db 操作数据库的  
     */
	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建表
		db.execSQL(CREATE_STUDENT_TABLE);
	}
	
	/**数据库版本发生改变时才会被调用,数据库在升级时才会被调用;  
     * @param db 操作数据库  
     * @param oldVersion 旧版本  
     * @param newVersion 新版本  
     */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}
	 
	public Cursor select() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}
	
	/**
	 *增加操作
	 * insert(String table,String nullColumnHack,ContentValues values)
	 * 参数1  表名称，
  	 * 参数2  空列的默认值
  	 * 参数3 ContentValues类型的一个封装了列名称和列值的Map；
	 */
	public long insert(String bookname,String author){
		db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(BOOK_NAME, bookname);
		values.put(BOOK_AUTHOR, author);
		return db.insert(TABLE_NAME, null, values);
	}
	
	/**
	 * 删除操作
	 * delete(String table,String whereClause,String[] whereArgs)方法
	 * 参数1  表名称 
	 * 参数2  删除条件
	 * 参数3  删除条件值数组
	 */
	public boolean delete(int id){
		db = this.getWritableDatabase();
		String where = BOOK_ID + " = '"+id+"'";
		return db.delete(TABLE_NAME, where, null) > 0;
	}
	
	/**
	 * 修改操作
	 * update(String table,ContentValues values,String whereClause, String[] whereArgs)方法
	 * 参数1  表名称
	 * 参数2  跟行列ContentValues类型的键值对Key-Value
	 * 参数3  更新条件（where字句）
	 * 参数4  更新条件数组
	 */
	public boolean update(int id,String bookname, String author) {
		db = this.getWritableDatabase();
		String where = BOOK_ID + " = '"+ id +"'";
		ContentValues args = new ContentValues();
		args.put(BOOK_NAME, bookname);
		args.put(BOOK_AUTHOR, author);
		return db.update(TABLE_NAME, args, where, null) > 0;
	}
	
	/**
	 * 查詢
	 * 参数1:table：表名。相当于select语句from关键字后面的部分。如果是多表联合查询，可以用逗号将两个表名分开。 
	 * 参数2:columns：要查询出来的列名。相当于select语句select关键字后面的部分。 
	 * 参数3:selection：查询条件子句，相当于select语句where关键字后面的部分，在条件子句允许使用占位符“?” 
	 * 参数4:selectionArgs：对应于selection语句中占位符的值，值在数组中的位置与占位符在语句中的位置必须一致，否则就会有异常。 
	 * 参数5:groupBy：相当于select语句group by关键字后面的部分 
	 * 参数6:having：相当于select语句having关键字后面的部分 
	 * 参数7:orderBy：相当于select语句order by关键字后面的部分，如：personid desc, age asc; 
	 * 参数8:limit：指定偏移量和获取的记录数，相当于select语句limit关键字后面的部分。 
	 */
	public Cursor query(int id) {
		db = this.getWritableDatabase();
		String where = BOOK_ID + " = '"+ id +"'";
		return db.query(TABLE_NAME, new String[] {BOOK_NAME, BOOK_AUTHOR}, where, null, null, null, null);
	}
}
