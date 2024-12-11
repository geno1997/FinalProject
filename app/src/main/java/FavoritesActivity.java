import static android.os.Build.VERSION_CODES.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    private ListView favoritesListView;
    private ArrayAdapter<ArticleEntity> adapter;
    private List<ArticleEntity> favoriteArticles = new ArrayList<>();
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoritesListView = findViewById(R.id.favoritesListView);
        database = AppDatabase.getDatabase(this);

        // Fetch favorites from the database in a background thread
        new Thread(() -> {
            favoriteArticles.addAll(database.articleDao().getAllArticles());
            runOnUiThread(() -> {
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, favoriteArticles);
                favoritesListView.setAdapter(adapter);
            });
        }).start();

        favoritesListView.setOnItemClickListener((parent, view, position, id) -> {
            // Handle item click (e.g., open article details)
            ArticleEntity selectedArticle = favoriteArticles.get(position);
            Intent intent = new Intent(FavoritesActivity.this, ArticleDetailActivity.class);
            intent.putExtra("article", (CharSequence) selectedArticle); // Pass article object to ArticleDetailActivity
            startActivity(intent);
        });

        favoritesListView.setOnItemLongClickListener((parent, view, position, id) -> {
            // Handle long click (delete the article)
            ArticleEntity articleToDelete = favoriteArticles.get(position);
            new Thread(() -> {
                database.articleDao().delete(articleToDelete); // Delete from database
                favoriteArticles.remove(position);
                runOnUiThread(() -> {
                    adapter.notifyDataSetChanged();
                    Toast.makeText(FavoritesActivity.this, "Article deleted", Toast.LENGTH_SHORT).show();
                });
            }).start();
            return true;
        });
    }
}
