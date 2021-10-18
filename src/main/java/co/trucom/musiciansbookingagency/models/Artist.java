package co.trucom.musiciansbookingagency.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "artist")
	private Set<Gig> gigs;

	public Artist() {
	}

	public Artist(String name) {
		this.name = name;
		this.gigs = new HashSet<>();
	}

	public Artist(String name, Set<Gig> gigs) {
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

	public Set<Gig> getGigs() {
		return gigs;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGigs(Set<Gig> gigs) {
		this.gigs = gigs;
	}

}
