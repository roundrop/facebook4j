package facebook4j.internal.http;

import facebook4j.FacebookException;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

public class MockHttpResponseImpl extends HttpResponseImpl {
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
                throw new AssertionError();
            }
        }
        return super.getResponseHeader(name);
    }
}
