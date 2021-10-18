package co.trucom.musiciansbookingagency.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "gigs")
public class Gig implements Serializable {

	private static final long serialVersionUID = -471367515104856194L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gig_id")
	private Long gigId;

	@Column(name = "location")
	private String location;

	@Column(name = "date")
	private Date date;

	@ManyToOne(optional = false)
	@JoinColumn(name = "artist_id")
	@JsonIgnore
	private Artist artist;

	public Gig() {
	}

	public Gig(Date date, String location, Artist artist) {
		this.date = date;
		this.location = location;
		this.artist = artist;
	}

	@Override
	public String toString() {
		return "{ GigID=" + gigId + ", Location=" + location + ", Band=" + artist.getName() + " }";
	}
	
	public Long getGigId() {
		return gigId;
	}

	public String getLocation() {
		return location;
	}

	public Date getDate() {
		return date;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setGigId(Long gigId) {
		this.gigId = gigId;
	}

	public void setLocation(String city) {
		location = city;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}
