import java.io.IOException;
import java.util.Collection;

public interface IAgencyService {

    public Collection<Agency> getAgencies (String site_id, String payment_method_id, String near_to, String limit, String offset, String criterio);
    public String readUrl(String urlString) throws IOException;
}
