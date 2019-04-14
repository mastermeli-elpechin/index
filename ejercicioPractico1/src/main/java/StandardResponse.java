import com.google.gson.JsonElement;

public class StandardResponse {

    private String message;
    private StatusResponse statusResponse;
    private JsonElement data;

    public StandardResponse() {
    }

    public StandardResponse(StatusResponse statusResponse){
        this.statusResponse = statusResponse;
    }

    public StandardResponse(StatusResponse statusResponse, String message){
        this.statusResponse = statusResponse;
        this.message = message;
    }

    public StandardResponse (StatusResponse statusResponse, JsonElement data){
        this.statusResponse = statusResponse;
        this.data = data;
    }
}
