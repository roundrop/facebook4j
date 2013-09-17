package facebook4j.internal.http;

import facebook4j.FacebookException;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

public class MockHttpResponseImpl extends HttpResponseImpl {
    private static final String DUMMY_URL = "https://fbcdn-photos-b-a.akamaihd.net/hphotos-ak-ash3/644169_573207722741517_740837405_a.jpg";

    MockHttpResponseImpl(String content) {
        super(content);
    }

    @Override
    public String getResponseHeader(String name) {
        if (name.equals("Location")) {
            try {
                JSONObject json = asJSONObject();
                JSONObject data = json.getJSONObject("data");
                return data.getString("url");
            } catch (JSONException e) {
                throw new AssertionError();
            } catch (FacebookException e) {
                return DUMMY_URL;
            }
        }
        return super.getResponseHeader(name);
    }

}
