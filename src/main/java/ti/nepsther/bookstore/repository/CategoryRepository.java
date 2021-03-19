package ti.nepsther.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ti.nepsther.bookstore.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
