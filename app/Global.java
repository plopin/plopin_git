import play.GlobalSettings;
import play.api.mvc.EssentialFilter;
import play.filters.csrf.CSRFFilter;

/**
 * @author Pavel Lopin
 * Created to implement CSRF token functionality 
 */
public class Global extends GlobalSettings {
    @SuppressWarnings("unchecked")
	@Override
    public <T extends EssentialFilter> Class<T>[] filters() {
        return new Class[]{CSRFFilter.class};
    }
}