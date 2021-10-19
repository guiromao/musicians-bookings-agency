package co.trucom.musiciansbookingagency.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.trucom.musiciansbookingagency.models.Gig;

public interface GigRepository extends JpaRepository<Gig, Long> {

	@Query("SELECT g FROM Gig g WHERE artist_id = ?1")
	List<Gig> findByArtistId(Long id);

	@Query("SELECT g FROM Gig g WHERE artist_id = ?1 AND location = ?2")
	List<Gig> findByArtistAndCity(Long id, String city);

}
