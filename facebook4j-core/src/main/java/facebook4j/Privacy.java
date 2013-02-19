package facebook4j;

import java.util.List;


public interface Privacy {
    PrivacyType getValue();
    PrivacyType getFriends();
    List<String> getNetworks();
    List<String> getAllow();
    List<String> getDeny();
    List<String> getDescription();
}
