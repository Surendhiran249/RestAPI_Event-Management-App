package event_management.demo.repository;

import event_management.demo.entity.Vendor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepo extends JpaRepository<Vendor, Long> {
    List<Vendor> findByServiceType(String serviceType);
    Page<Vendor> findAll(Pageable pageable);
}
