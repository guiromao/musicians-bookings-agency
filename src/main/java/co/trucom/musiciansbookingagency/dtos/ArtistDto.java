package co.trucom.musiciansbookingagency.dtos;

import java.util.ArrayList;
import java.util.List;

import co.trucom.musiciansbookingagency.models.Gig;

public class ArtistDto {

	private String name;
	private List<Gig> gigs;

	public ArtistDto(String string) {
		name = string;
		gigs = new ArrayList<>();
	}

	public ArtistDto(String string, List<Gig> listGigs) {
		name = string;
		gigs = listGigs;
	}

	public String getName() {
		return name;
	}
	
	public List<Gig> getGigs() {
		return gigs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGigs(List<Gig> gigs) {
		this.gigs = gigs;
	}

	

}
