package facebook4j.internal.json;

import static facebook4j.internal.util.z_F4JInternalParseUtil.getRawString;
import facebook4j.City;
import facebook4j.FacebookException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Robson Cassol - robsoncassol at gmail.com
 * @since Facebook4J 2.2.1
 */

public class CityJSONImpl implements City, java.io.Serializable{

	private static final long serialVersionUID = -3125589330475980520L;
	private String id;
	private String name;

	public CityJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        id = getRawString("id", json);
        name = getRawString("name", json);
    }


	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
