package by.teachmeskills.eshop.repositories;

import by.teachmeskills.eshop.entities.Category;

public interface CategoryRepository extends BaseRepository<Category>{
    Category findById(int id) throws Exception;
}