package io.github.spengergasse.ViennaRuns;

import io.github.spengergasse.ViennaRuns.persistence.FeelingAfterRunRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class FeelingAfterRunRepositoryTest {


    @Autowired
    FeelingAfterRunRepository feelingAfterRunRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void ensureTransientPersistenceOfModel() throws Exception {

        // given
            /*Photographer photographer = new Photographer("unger@spengergasse.at");
            Photo photo1 = new Photo("photo1", 640, 480, LocalDate.now());
            photo1.setOrientation(Orientation.LANDSCAPE);
            photo1.setPhotographer(photographer);
            photo1.setRating(5);
            Photo photo2 = new Photo("photo2", 480, 640, LocalDate.now());
            photo2.setOrientation(Orientation.PORTRAIT);
            photo2.setPhotographer(photographer);
            Photo photo3 = new Photo("photo3", 480, 480, LocalDate.now());
            photo3.setOrientation(Orientation.SQUARE);
            photo3.setPhotographer(photographer);
            Album album = new Album("Album 2017/01");
            album.addPhotos(photo1, photo2, photo3);

            // when
            Album savedAlbum = albumRepository.save(album);

            // then
            assertThat(album).isNotNull();
            assertThat(album.getId()).isNotNull();
            assertThat(album.getVersion()).isNotNull();

            assertThat(album.getPhotos()).isNotNull().isNotEmpty().containsSequence(photo1, photo2, photo3);
            album.getPhotos().stream().forEach(this::assertPhoto);*/
    }

/*        private void assertPhoto(Photo photo) {
            assertThat(photo).isNotNull();
            assertThat(photo.getId()).isNotNull();
            assertThat(photo.getVersion()).isNotNull();
            assertThat(photo.getPhotographer()).isNotNull();
            assertThat(photo.getCreatedAt()).isNotNull();
        }*/
}
