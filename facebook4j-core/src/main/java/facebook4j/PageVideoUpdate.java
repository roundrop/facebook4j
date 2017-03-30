package facebook4j;

import facebook4j.internal.http.HttpParameter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @author Dimitry Kudryavtsev dk8996 at gmail.com
 */
public class PageVideoUpdate extends VideoUpdate {

	private static final long serialVersionUID = -7572933117144435735L;

	private Media source;
	private String title;
	private String description;
	private Boolean published;
	private Integer scheduledPublishTime;
	
    private FeedTargetingParameter feedTargeting;
    private TargetingParameter targeting;
    
	public PageVideoUpdate(Media source) {
		super(source);
		this.source = source;
	}

	public Media getSource() {
		return source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PageVideoUpdate title(String title) {
		setTitle(title);
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PageVideoUpdate description(String description) {
		setDescription(description);
		return this;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public PageVideoUpdate published(boolean published) {
		setPublished(published);
		return this;
	}

	public Integer getScheduledPublishTime() {
		return scheduledPublishTime;
	}

	public void setScheduledPublishTime(Integer scheduledPublishTime) {
		this.scheduledPublishTime = scheduledPublishTime;
	}

	public void setScheduledPublishTime(Date scheduledPublishTime) {
		long time = scheduledPublishTime.getTime() / 1000L;
		setScheduledPublishTime(Long.valueOf(time).intValue());
	}

	public PageVideoUpdate scheduledPublishTime(Integer scheduledPublishTime) {
		setScheduledPublishTime(scheduledPublishTime);
		return this;
	}

	public PageVideoUpdate scheduledPublishTime(Date scheduledPublishTime) {
		long time = scheduledPublishTime.getTime() / 1000L;
		return scheduledPublishTime(Long.valueOf(time).intValue());
	}
	
    public TargetingParameter getTargeting() {
        return targeting;
    }

    public void setTargeting(TargetingParameter targeting) {
        this.targeting = targeting;
    }

    public PageVideoUpdate targeting(TargetingParameter targetingParameter) {
        setTargeting(targetingParameter);
        return this;
    }

    public FeedTargetingParameter getFeedTargeting() {
        return feedTargeting;
    }

    public void setFeedTargeting(FeedTargetingParameter feedTargeting) {
        this.feedTargeting = feedTargeting;
    }

    public PageVideoUpdate feedTargeting(FeedTargetingParameter feedTargeting) {
        setFeedTargeting(feedTargeting);
        return this;
    }

	/* package */HttpParameter[] asHttpParameterArray() {
		List<HttpParameter> params = new ArrayList<HttpParameter>();
		params.add(source.asHttpParameter("source"));
		if (title != null) {
			params.add(new HttpParameter("title", title));
		}
		if (description != null) {
			params.add(new HttpParameter("description", description));
		}
		if (published != null) {
			params.add(new HttpParameter("published", published));
		}
		if (scheduledPublishTime != null) {
			params.add(new HttpParameter("scheduled_publish_time", scheduledPublishTime));
		}
        if (targeting != null) {
            params.add(new HttpParameter("targeting", targeting.asJSONString()));
        }
        if (feedTargeting != null) {
            params.add(new HttpParameter("feed_targeting", feedTargeting.asJSONString()));
        }
		return params.toArray(new HttpParameter[params.size()]);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PageVideoUpdate))
			return false;

		PageVideoUpdate that = (PageVideoUpdate) o;

		if (description != null ? !description.equals(that.description) : that.description != null)
			return false;
		if (published != null ? !published.equals(that.published) : that.published != null)
			return false;
		if (scheduledPublishTime != null ? !scheduledPublishTime.equals(that.scheduledPublishTime)
				: that.scheduledPublishTime != null)
			return false;
		if (source != null ? !source.equals(that.source) : that.source != null)
			return false;
		if (title != null ? !title.equals(that.title) : that.title != null)
			return false;
        if (feedTargeting != null ? !feedTargeting.equals(that.feedTargeting) : that.feedTargeting != null)
            return false;
        if (targeting != null ? !targeting.equals(that.targeting) : that.targeting != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = source != null ? source.hashCode() : 0;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (published != null ? published.hashCode() : 0);
		result = 31 * result + (scheduledPublishTime != null ? scheduledPublishTime.hashCode() : 0);
        result = 31 * result + (targeting != null ? targeting.hashCode() : 0);
        result = 31 * result + (feedTargeting != null ? feedTargeting.hashCode() : 0);
		return result;
	}

	@Override
    public String toString() {
        return "PageVideoUpdate{" +
                "source=" + source +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", published=" + published +
                ", scheduledPublishTime=" + scheduledPublishTime +
                ", feedTargeting=" + feedTargeting +
                ", published=" + published +
                '}';
    }
}
