package repositories;

import entidades.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
}
