package facebook4j;

/**
 * Facebook has introduced versioning for their Graph API
 * see (https://developers.facebook.com/docs/apps/versions)
 * 
 * This class sets all of the possible versions, for prefix into Facebook's Rest Graph API Edges
 * https://graph.facebook.com/{vX.X}/me/friends
 * 
 * By default no versioning is used and will follow Facebook's Graph API migration policy
 * 
 * 		"We refer to this as an unversioned call.
 *   	 An unversioned call will default to the oldest available version of the API."
 * 
 * To set a version, when constructing Facebook instance, set the Configuration.setGraphVersion()
 * with GraphVersion.{Version}
 * 
 * ConfigurationBuilder cb = new ConfigurationBuilder()
 *	                .setOAuthAppId(client.id)
 *	                .setOAuthAppSecret(client.secret)
 *	                .setGraphVersion(GraphVersion.V1_0)
 *	                .setOAuthPermissions(ALL_PERMISSIONS);
 * Facebook facebook = new FacebookFactory(cb.build()).getInstance(); 
 * 
 * 
 * @author willisju github
 */
public class Versioning {
	
	private String version = "";
	
	public enum GraphVersion {
    	V1_0("v1.0/"), V2_0("v2.0/"), V2_1("v2.1/"), V2_2("v2.2/"), UNVERSIONED("/");
    	
    	private String version = "";
    	
    	private GraphVersion(String value){
    		this.version = value;
    	}
    	
    	private String getVersion(){
    		return version;
    	}
    	
    };
    
    public String getVersion(){
    	return version;
    }

    public void setVersion(GraphVersion value){
    	version = value.getVersion();
    }
}
