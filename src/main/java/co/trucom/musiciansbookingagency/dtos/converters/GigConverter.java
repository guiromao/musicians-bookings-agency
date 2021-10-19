package co.trucom.musiciansbookingagency.dtos.converters;

import java.util.ArrayList;
import java.util.List;

import co.trucom.musiciansbookingagency.dtos.GigDto;
import co.trucom.musiciansbookingagency.models.Gig;
import co.trucom.musiciansbookingagency.utils.Utils;

public class GigConverter {

	public static Gig dtoToGig(GigDto dto) {
		Gig gig = new Gig();
		gig.setDate(Utils.stringToDate(dto.getDateStr()));
		gig.setLocation(dto.getLocation());

		return gig;
	}

	public static GigDto gigToDto(Gig gig) {
		return new GigDto(gig.getGigId(),
				gig.getLocation(),
				Utils.dateToString(gig.getDate()));
	}

	public static List<GigDto> listToDto(List<Gig> list) {
		List<GigDto> result = new ArrayList<>();

		for(Gig gig: list) {
			result.add(gigToDto(gig));
		}

		return result;
	}

}
