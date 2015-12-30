package object.homesmart.gogo.db;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homesmart.R;

public class SQLiteDemo extends Activity implements OnItemClickListener{
	private DatabaseHelper databaseHelper;
	private Cursor mCursor;
	private EditText BookName;
	private EditText BookAuthor;
	private ListView BooksList;
	 
	private int BOOK_ID = 0;
	protected final static int MENU_ADD = Menu.FIRST;
	protected final static int MENU_DELETE = Menu.FIRST + 1;
	protected final static int MENU_UPDATE = Menu.FIRST + 2;
	protected final static int MENU_QUERY = Menu.FIRST + 3;
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqldemo);
		setUpViews();
	}
	 
	public void setUpViews(){
		databaseHelper = new DatabaseHelper(this);
		mCursor = databaseHelper.select();
		BookName = (EditText)findViewById(R.id.bookname);
		BookAuthor = (EditText)findViewById(R.id.author);
		BooksList = (ListView)findViewById(R.id.bookslist);
		BooksList.setAdapter(new BooksListAdapter(this, mCursor));
		BooksList.setOnItemClickListener(this);
	}
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, MENU_ADD, 0, "ADD");
		menu.add(Menu.NONE, MENU_DELETE, 0, "DELETE");
		menu.add(Menu.NONE, MENU_UPDATE, 0, "UPDATE");
		menu.add(Menu.NONE,MENU_QUERY,0,"QUERY");
		return true;
	}
	 
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		switch (item.getItemId()){
			case MENU_ADD:
				add();
//				databaseHelper.insert("215", "sadasd");
//				mCursor.requery();
//				BooksList.invalidateViews();
				break;
			
			case MENU_DELETE:
				delete();
//				databaseHelper.delete(24);
//				mCursor.requery();
//				BooksList.invalidateViews();
				break;
				
			case MENU_UPDATE:
				update();
//				databaseHelper.update(13, "4154", "dsadasx");
//				mCursor.requery();
//				BooksList.invalidateViews();
				break;
				
			case MENU_QUERY:
				query();
//				databaseHelper.query(8);
//				mCursor.requery();
//				BooksList.invalidateViews();
//				Log.i("BOOK", BookName.getText().toString());
				break;
		}
		return true;
	}

	public void add(){
		String bookname = BookName.getText().toString();
		String author = BookAuthor.getText().toString();
		//书名和作者都不能为空，或者退出
		if (bookname.equals("") || author.equals("")){
			return;
		}
		databaseHelper.insert(bookname, author);
		mCursor.requery();
		BooksList.invalidateViews();
		BookName.setText("");
		BookAuthor.setText("");
		Toast.makeText(this, "Add Successed!", Toast.LENGTH_SHORT).show();
	}
	 
	public void delete(){
		if (BOOK_ID == 0) {
			return;
		}
		databaseHelper.delete(BOOK_ID);
		mCursor.requery();
		BooksList.invalidateViews();
		Toast.makeText(this, "Delete Successed!", Toast.LENGTH_SHORT).show();
	}
	 
	public void update(){
		String bookname = BookName.getText().toString();
		String author = BookAuthor.getText().toString();
		//书名和作者都不能为空，或者退出
		if (bookname.equals("") || author.equals("")){
			return;
		}
		databaseHelper.update(BOOK_ID, bookname, author);
		mCursor.requery();
		BooksList.invalidateViews();
		Toast.makeText(this, "Update Successed!", Toast.LENGTH_SHORT).show();
	}
	 
	private void query() {
		databaseHelper.query(mCursor.getPosition());
		mCursor.requery();
		BooksList.invalidateViews();
		Toast.makeText(this, "query Successed!", Toast.LENGTH_SHORT).show();
	}

	 
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		mCursor.moveToPosition(position);
		BOOK_ID = mCursor.getInt(0);
		BookName.setText(mCursor.getString(1));
		BookAuthor.setText(mCursor.getString(2));
		Log.i("BOOK", "BOOK_ID="+BOOK_ID);
	}
	 
	public class BooksListAdapter extends BaseAdapter{
		private Context mContext;
		private Cursor mCursor;
		public BooksListAdapter(Context context,Cursor cursor) {
			mContext = context;
			mCursor = cursor;
		}
		
		@Override
		public int getCount() {
			return mCursor.getCount();
		}
		
		@Override
		public Object getItem(int position) {
			return null;
		}
		
		@Override
		public long getItemId(int position) {
			return 0;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView mTextView = new TextView(mContext);
			mCursor.moveToPosition(position);
			mTextView.setText(mCursor.getString(1) + "___"+ mCursor.getString(2));
			return mTextView;
		}
	}
}
