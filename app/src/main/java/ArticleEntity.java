import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_table")
public class ArticleEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String section;
    private String webUrl;

    // Constructor
    public ArticleEntity(String title, String section, String webUrl) {
        this.title = title;
        this.section = section;
        this.webUrl = webUrl;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
