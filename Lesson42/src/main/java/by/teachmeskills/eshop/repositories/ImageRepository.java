package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Image;

import java.util.List;

public interface ImageRepository extends BaseRepository<Image> {
    List<Image> getAllSecondaryImages(int id);
}
