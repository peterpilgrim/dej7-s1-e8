/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2017 by Peter Pilgrim, Milton Keynes, P.E.A.T UK LTD
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Creative Commons 3.0
 * Non Commercial Non Derivation Share-alike License
 * https://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 * Developers:
 * Peter Pilgrim -- design, development and implementation
 *               -- Blog: http://www.xenonique.co.uk/blog/
 *               -- Twitter: @peter_pilgrim
 *
 * Contributors:
 *
 *******************************************************************************/
package uk.co.xenonique.training.stadia;

import javax.enterprise.context.ApplicationScoped;
import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * The type StadiumResources
 *
 * @author Peter Pilgrim
 */
@Path("stadium")
@ApplicationScoped
public class StadiumResources {

    private List<Stadium> stadiumList ;


    public StadiumResources() {
        final List<Stadium> startList = Arrays.asList(
                new Stadium("Old Trafford", "Manchester", 75653, "Manchester United", "Premier League"),
                new Stadium("Anfield", "Liverpool", 55097, "Liverpool", "Premier League"),
                new Stadium("Etihad Stadium", "Manchester", 55097, "Manchester City", "Premier League"),
                new Stadium("Wembley Stadium", "London", 90000, "England national football team", "National Stadium"),
                new Stadium("Stamford Bridge", "London", 41798, "Chelsea", "Premier League"),
                new Stadium("Emirates Stadium ", "London", 60260, "Arsenal", "Premier League"),
                new Stadium("St James' Park", "Newcastle upon Tyne", 52338, "Newcastle United", "Premier League"),
                new Stadium("Villa Park", "Birmingham", 42660, "Aston Villa", "Championship")
        );

        stadiumList = new ArrayList<>(startList);
    }

    @Path("data")
    @GET
    @Produces(APPLICATION_JSON)
    public String stadiumList() throws IOException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Stadium stadium : stadiumList) {
            JsonObjectBuilder objectBuilder = convertStadiumToJsonObject(stadium);
            arrayBuilder.add(objectBuilder.build());
        }
        
        JsonArray jsonArray = arrayBuilder.build();
        return  writeJsonStructureToString(jsonArray);
    }

    @Path("sort-by-capacity")
    @GET
    @Produces(APPLICATION_JSON)
    public String stadiumListSortedByCapacity() throws IOException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        List<Stadium> sortedList = stadiumList.stream().sorted(
                (s1, s2) -> Integer.compare(s2.getCapacity(), s1.getCapacity())).collect(Collectors.toList());

        for (Stadium stadium : sortedList) {
            JsonObjectBuilder objectBuilder = convertStadiumToJsonObject(stadium);
            arrayBuilder.add(objectBuilder.build());
        }


        JsonArray jsonArray = arrayBuilder.build();
        return  writeJsonStructureToString(jsonArray);
    }

    @Path("data/{ClubName}")
    @GET
    @Produces(APPLICATION_JSON)
    public Response findByClub(@PathParam("ClubName") String clubName) throws IOException {

        Optional<Stadium> result = stadiumList.stream().filter(s1 -> s1.getClub().equalsIgnoreCase(clubName)).findFirst();

        if ( result.isPresent()) {

            JsonObjectBuilder objectBuilder = convertStadiumToJsonObject(result.get());

            String jsonString = writeJsonStructureToString(objectBuilder.build());
            return Response.ok(jsonString, APPLICATION_JSON).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).entity("Club ["+clubName+"] not found in stadia.").build();
        }
    }

    @Path("data")
    @POST
    @Consumes(APPLICATION_JSON)
    public Response addStadium(JsonObject json) throws IOException {

        String stadiumName = json.getString("name");

        Optional<Stadium> result = stadiumList.stream().filter( s1 -> s1.getName().equalsIgnoreCase(stadiumName)).findFirst();

        if ( result.isPresent()) {
            return Response.status(Response.Status.FOUND).entity("Already exists stadiumName: ["+stadiumName+"]").build();
        }
        else {
            Stadium stadium = new Stadium();
            stadium.setName(json.getString("name"));
            stadium.setCapacity(json.getInt("capacity"));
            stadium.setCity(json.getString("city"));
            stadium.setClub(json.getString("club"));
            stadium.setLeagueStatus(json.getString("leagueStatus"));
            
            stadiumList.add(stadium);

            return Response.ok().build();
        }
    }

    @Path("data/{clubName}")
    @PUT
    @Consumes(APPLICATION_JSON)
    public Response updateStadium(@PathParam("clubName") String clubName,  JsonObject json) throws IOException {
        Optional<Stadium> result = stadiumList.stream().filter( s1 -> s1.getClub().equalsIgnoreCase(clubName)).findFirst();

        if ( result.isPresent()) {
            Stadium stadium = result.get();
            stadium.setCapacity(json.getInt("capacity"));
            stadium.setCity(json.getString("city"));
            stadium.setLeagueStatus(json.getString("leagueStatus"));
            stadium.setName(json.getString("name"));
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity ["+clubName+"] was not found and no removal was performed").build();
        }
    }

    @Path("data/{clubName}")
    @DELETE
    public Response removeStadium(@PathParam("clubName") String clubName) throws IOException {
        Optional<Stadium> result = stadiumList.stream().filter( s1 -> s1.getClub().equalsIgnoreCase(clubName)).findFirst();
        if ( result.isPresent()) {
            stadiumList.remove(result.get());
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity ["+clubName+"] was not found and no removal was performed").build();
        }
    }

    public static String writeJsonStructureToString(JsonStructure jsonValue) throws IOException {
        Map<String, Boolean> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(config);

        String jsonString;
        try (Writer writer = new StringWriter()) {
            writerFactory.createWriter(writer).write(jsonValue);
            jsonString = writer.toString();
        }
        return jsonString;
    }

    public static JsonObjectBuilder convertStadiumToJsonObject(Stadium stadium) {
        return Json.createObjectBuilder()
                .add("name", stadium.getName())
                .add("city", stadium.getCity())
                .add("capacity", stadium.getCapacity())
                .add("club", stadium.getClub())
                .add("leagueStatus", stadium.getLeagueStatus())
                .add("creationDate", new SimpleDateFormat("dd-MM-yyyy")
                        .format(stadium.getCreationDate()));
    }
}































/*

        Old Trafford 	Manchester 	75,653 [3] 	Manchester United 	Premier League

        Emirates Stadium 	London 	60,260 [4] 	Arsenal 	Premier League

        Villa Park 	Birmingham 	42,660 [10] 	Aston Villa 	Championship

        Stamford Bridge 	London 	41,798 [11] 	Chelsea 	Premier League

        St James' Park 	Newcastle upon Tyne 	52,338 [8] 	Newcastle United 	Premier League

        Wembley Stadium 	London 	90,000 [2] 	England national football team 	National Stadium 	N/A

        Etihad Stadium 	Manchester 	55,097 [6] 	Manchester City 	Premier League 	4 	Commercially known as the Etihad S

        Anfield 	Liverpool 	54,074 [7] 	Liverpool 	Premier League

 */
