import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;
    private ListView articlesListView;
    private ProgressBar progressBar;
    private ArrayAdapter<Article> adapter;
    private List<Article> articleList = new ArrayList<>();
    private Class<?> apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        articlesListView = findViewById(R.id.articlesListView);
        progressBar = findViewById(R.id.progressBar);

        apiService = RetrofitClient.getInstance().getClass();

        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                if (!query.isEmpty()) {
                    searchArticles(query);
                }
            }
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, articleList);
        articlesListView.setAdapter(adapter);
    }

    private void searchArticles(String query) {
        progressBar.setVisibility(View.VISIBLE);
        apiService.getDeclaredClasses().clone(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call call, ApiResponse.Response response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    articleList.clear();
                    articleList.addAll(response.body().getResponse().getResults());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to fetch articles", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
