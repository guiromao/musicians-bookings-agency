package co.trucom.musiciansbookingagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MusiciansBookingAgencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusiciansBookingAgencyApplication.class, args);
	}

}
