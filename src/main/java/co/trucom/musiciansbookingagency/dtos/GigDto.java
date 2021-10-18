package co.trucom.musiciansbookingagency.dtos;

public class GigDto {

	private Long gigId;
	private Long artistId;
	private String location;
	private String dateStr;
	
	public GigDto(Long artId, String city, String date) {
		artistId = artId;
		location = city;
		dateStr = date;
	}

	public GigDto(Long i, Long artId, String city, String date) {
		gigId = i;
		artistId = artId;
		location = city;
		dateStr = date;
	}

	public Long getGigId() {
		return gigId;
	}

	public Long getArtistId() {
		return artistId;
	}

	public String getLocation() {
		return location;
	}

	public String getDateStr() {
		return dateStr;
	}
	
	public void setGigId(Long id) {
		this.gigId = id;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	public void setLocation(String city) {
		location = city;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

}
