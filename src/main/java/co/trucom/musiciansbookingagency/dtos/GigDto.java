package co.trucom.musiciansbookingagency.dtos;

public class GigDto {

	private Long gigId;
	private String location;
	private String dateStr;

	public GigDto() {
	}

	public GigDto(String city, String date) {
		location = city;
		dateStr = date;
	}

	public GigDto(Long i, String city, String date) {
		gigId = i;
		location = city;
		dateStr = date;
	}

	public Long getGigId() {
		return gigId;
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

	public void setLocation(String city) {
		location = city;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

}
