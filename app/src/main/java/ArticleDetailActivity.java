import static android.os.Build.VERSION_CODES.R;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ArticleDetailActivity extends AppCompatActivity {
    private TextView articleTitle, articleSection, articleUrl;
    private Button saveToFavoritesButton;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        articleTitle = findViewById(R.id.articleTitle);
        articleSection = findViewById(R.id.articleSection);
        articleUrl = findViewById(R.id.articleUrl);
        saveToFavoritesButton = findViewById(R.id.saveToFavoritesButton);

        // Get article data from the intent
        Article article = (Article) getIntent().getSerializableExtra("article");
        articleTitle.setText(article.getTitle());
        articleSection.setText(article.getSection());
        articleUrl.setText(article.getWebUrl());

        database = AppDatabase.getDatabase(this);

        saveToFavoritesButton.setOnClickListener(v -> {
            // Create a new ArticleEntity and save it to the database
            ArticleEntity articleEntity = new ArticleEntity(article.getTitle(), article.getSection(), article.getWebUrl());
            new Thread(() -> {
                database.articleDao().insert(articleEntity); // Save to database
                runOnUiThread(() -> {
                    Toast.makeText(this, "Saved to favorites", Toast.LENGTH_SHORT).show();
                });
            }).start();
        });
    }
}
