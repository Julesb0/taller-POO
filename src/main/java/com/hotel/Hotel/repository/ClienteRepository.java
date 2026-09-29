package com.hotel.Hotel.repository;

import com.hotel.Hotel.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByEmail(String email);

    @Query("""
            select distinct c from Cliente c
            left join fetch c.reservas r
            left join fetch r.habitacion
            where c.id = :id
            """)
    Optional<Cliente> findWithReservasById(@Param("id") UUID id);
}
