package com.example.mtb.dto;

public record SeatResponse(String seatId,String seatname) implements  Comparable<SeatResponse>{
    @Override
    public int compareTo(SeatResponse o) {

        return this.seatname.compareToIgnoreCase(o.seatname);
    }
}
