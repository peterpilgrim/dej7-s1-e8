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

import java.util.Date;

/**
 * The type Stadium
 *
 * @author Peter Pilgrim
 */

public class Stadium {
    private String name;
    private String city;
    private int capacity;
    private String club;
    private String leagueStatus;
    private Date creationDate = new Date();


    public Stadium() {
    }

    public Stadium(String name, String city, int capacity, String club, String leagueStatus) {
        this(name,city,capacity,club,leagueStatus, new Date());
    }

    public Stadium(String name, String city, int capacity, String club, String leagueStatus, Date creationDate) {
        this.name = name;
        this.city = city;
        this.capacity = capacity;
        this.club = club;
        this.leagueStatus = leagueStatus;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getLeagueStatus() {
        return leagueStatus;
    }

    public void setLeagueStatus(String leagueStatus) {
        this.leagueStatus = leagueStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Stadium{");
        sb.append("name='").append(name).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", capacity=").append(capacity);
        sb.append(", club='").append(club).append('\'');
        sb.append(", leagueStatus='").append(leagueStatus).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append('}');
        return sb.toString();
    }
}
