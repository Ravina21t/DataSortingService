package com.sort;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/")
public class SortService { 
	@POST
	@Path("/")
	@Produces("text/csv")
	@Consumes(MediaType.APPLICATION_JSON)
	public String service(String data) throws Exception {
		//Parse and sort the input JSON
		ParseAndSortUtil obj = new ParseAndSortUtil();   	
		return obj.parse(data); 
	}          
}