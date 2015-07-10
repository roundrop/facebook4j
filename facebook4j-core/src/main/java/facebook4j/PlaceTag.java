package facebook4j;

import java.util.Date;

public interface PlaceTag extends FacebookResponse {
    String getId();
    Date getCreatedTime();
    Place getPlaceTag();
    
}
