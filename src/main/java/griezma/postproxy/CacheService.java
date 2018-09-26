package griezma.postproxy;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

@Singleton
public class CacheService {
	private List<String> eventList = new ArrayList<>();
	
	public void addEvent(String event) {
		eventList.add(event);
	}
	
	public List<String> getEvents() {
		return eventList;
	}
	
	public void clearEvents() {
		eventList.clear();
	}
}
