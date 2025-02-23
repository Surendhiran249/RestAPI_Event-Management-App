package event_management.demo.controller;

import event_management.demo.entity.Vendor;
import event_management.demo.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping("/create")
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return vendorService.createVendor(vendor);
    }

    @PostMapping("/createbatch")
    public List<Vendor> saveVendors(@RequestBody List<Vendor> vendors) {
        return vendorService.saveVendors(vendors);
    }

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @GetMapping("/service/{serviceType}")
    public List<Vendor> getVendorsByServiceType(@PathVariable String serviceType) {
        return vendorService.getVendorsByServiceType(serviceType);
    }

    @PutMapping("/update/{id}")
    public Vendor updateVendor(@PathVariable Long id, @RequestBody Vendor vendor) {
        return vendorService.updateVendor(id, vendor);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVendor(@PathVariable Long id) {
        return vendorService.deleteVendor(id);
    }

    @GetMapping("/vendorspage")
    public Page<Vendor> getVendorsPaginated(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "5")int size) {
        Pageable pageable = PageRequest.of(page,size);
        return vendorService.getVendorsPaginated(pageable);
    }

    @GetMapping("/sorted")
    public List<Vendor> getVendorsSorted(@RequestParam String field, @RequestParam String direction) {
        return vendorService.getVendorsSorted(field, direction);
    }

    @GetMapping("/search")
    public List<Vendor> searchVendors(@RequestParam String keyword) {
        return vendorService.searchVendors(keyword);
    }
}
