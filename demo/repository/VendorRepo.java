package event_management.demo.repository;

import event_management.demo.entity.Vendor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepo extends JpaRepository<Vendor, Long> {
    List<Vendor> findByServiceType(String serviceType);
    Page<Vendor> findAll(Pageable pageable);
    @Query("SELECT v FROM Vendor v WHERE LOWER(v.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
       "OR LOWER(v.serviceType) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
       "OR LOWER(v.contactPerson) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Vendor> searchVendors(@Param("keyword") String keyword);
}
