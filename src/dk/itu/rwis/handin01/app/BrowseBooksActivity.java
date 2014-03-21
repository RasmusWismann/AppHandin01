package dk.itu.rwis.handin01.app;

import dk.itu.rwis.handin01.db.BookDAO;
import itu.rwis.handin01.AddBookFragment;
import itu.rwis.handin01.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.database.Cursor;

public class BrowseBooksActivity extends ListActivity {

	private BookDAO bookAdapter;
	private SimpleCursorAdapter cursorAdapter;
	
	private Button newBook;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_books);
		
		bookAdapter = new BookDAO(this);
		bookAdapter.open();
		Cursor cursor = bookAdapter.getAllBooks();
		
		String[] fromColumns = { "Title", "Author" };
		int[] toViews = { android.R.id.text1, android.R.id.text2 };
		
		cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, fromColumns, toViews, 0);
		setListAdapter(cursorAdapter);
		
		
		
		newBook = (Button) findViewById(R.id.new_book);
		
		newBook.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
				AddBookFragment fragment = new AddBookFragment();
				fragmentTransaction.add(R.id.add_book_fragment, fragment);
				fragmentTransaction.commit();
			}
		});
	}
	

}
