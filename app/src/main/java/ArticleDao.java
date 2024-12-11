import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ArticleDao {

    @Insert
    void insert(ArticleEntity article);

    @Update
    void update(ArticleEntity article);

    @Delete
    void delete(ArticleEntity article);

    @Query("SELECT * FROM article_table")
    List<ArticleEntity> getAllArticles();

    @Query("DELETE FROM article_table")
    void deleteAllArticles();
}
