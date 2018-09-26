package griezma.api.ruckus;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

@Path("ruckus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RuckusResource {
	
	@Inject
	private RuckusService ruckus;
	
	public RuckusResource() {
		System.out.println("new RuckusResource");
	}
	
	@POST
	@Path("events")
	public void postEvents(String eventJson) {
		System.out.println("posted: " + eventJson);
		ruckus.addEvent(eventJson);
	}
	
	@GET
	@Path("ping")
	public String ping() {
		return "works";
	}
	
	@GET
	@Path("events")
	public StreamingOutput getEvents() {
		List<String> eventList = new ArrayList<>(ruckus.getEvents());
		ruckus.clearEvents();
		System.out.println("events: size=" + eventList.size());
		StreamingOutput so = new StreamingOutput() {
			@Override
			public void write(OutputStream os) throws IOException, WebApplicationException {
				Writer writer = new BufferedWriter(new OutputStreamWriter(os));
				List<String> events = new ArrayList<>(eventList);
				writer.write('[');
				final int last = eventList.size() - 1;
				int count = 0;
				for (String event : events) {
					writer.write(event);
					if (count < last) writer.write(',');
					++count;
				}
				writer.write(']');
				writer.flush();
			}
		};
		return so;
	}
}
