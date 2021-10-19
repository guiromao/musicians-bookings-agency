package co.trucom.musiciansbookingagency.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "artists")
public class Artist implements Serializable {

	private static final long serialVersionUID = 7913332929091634386L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artist_id")
	private Long artistId;

	@Column(name = "artist_name")
	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "artist", cascade = CascadeType.ALL)
	@JsonManagedReference(value="gigs")
	private List<Gig> gigs;

	public Artist() {
	}

	public Artist(String name) {
		this.name = name;
		this.gigs = new ArrayList<>();
	}

	public Artist(String name, List<Gig> gigs) {
		this.name = name;
		this.gigs = gigs;
	}

	public void printGigs() {
		for(Gig gig: gigs) {
			System.out.println(gig);
		}
	}

	public void addGig(Gig gig) {
		gigs.add(gig);
	}

	public Long getArtistId() {
		return artistId;
	}

	public String getName() {
		return name;
	}

	public List<Gig> getGigs() {
		return gigs;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGigs(List<Gig> gigs) {
		this.gigs = gigs;
	}

}
