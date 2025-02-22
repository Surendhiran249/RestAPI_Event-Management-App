package event_management.demo.service;

import event_management.demo.entity.Vendor;
import event_management.demo.repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    @Autowired
    private VendorRepo vendorRepository;

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id).orElse(null);
    }

    public List<Vendor> getVendorsByServiceType(String serviceType) {
        return vendorRepository.findByServiceType(serviceType);
    }

    public Vendor updateVendor(Long id, Vendor updatedVendor) {
        if (vendorRepository.existsById(id)) {
            updatedVendor.setId(id);
            return vendorRepository.save(updatedVendor);
        }
        return null;
    }

    public String deleteVendor(Long id) {
        vendorRepository.deleteById(id);
        return "Vendor deleted successfully!";
    }

    public Page<Vendor> getVendorsPaginated(Pageable pageable) {
        return vendorRepository.findAll(pageable);
    }
}
