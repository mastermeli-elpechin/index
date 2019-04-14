public enum StatusResponse {

    ERROR("error"),
    SUCCESS("success");

    private String statusResponse;

    StatusResponse(String statusResponse) {
        this.statusResponse = statusResponse;
    }

    public String getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(String statusResponse) {
        this.statusResponse = statusResponse;
    }
}
