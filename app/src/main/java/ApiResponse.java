import java.util.List;

public class ApiResponse {
    private Response response;

    public class Response {
        private List<Article> results;

        public List<Article> getResults() {
            return results;
        }

        public boolean isSuccessful() {
        }

        public ApiResponse body() {
            return null;
        }
    }

    public Response getResponse() {
        return response;
    }
}
