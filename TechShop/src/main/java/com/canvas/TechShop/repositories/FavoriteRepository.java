package com.canvas.TechShop.repositories;

import com.canvas.TechShop.auth.User;
import com.canvas.TechShop.models.Favorite;
import jakarta.transaction.Transactional;
import org.eclipse.angus.mail.imap.protocol.UIDSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@Transactional
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    Optional<Favorite> findByUser(User user);
}
