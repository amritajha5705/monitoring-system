package monitoring_system.controller;

import monitoring_system.entity.Metric;
import monitoring_system.repository.MetricRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {

    private final MetricRepository repository;

    public MetricController(MetricRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Metric addMetric(@RequestBody Metric metric) {
        return repository.save(metric);
    }

    @GetMapping
    public List<Metric> getAllMetrics() {
        return repository.findAll();
    }
    @DeleteMapping("/{id}")
    public void deleteMetric(@PathVariable Long id) {
        repository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Metric updateMetric(@PathVariable Long id, @RequestBody Metric updatedMetric) {

        Metric existingMetric = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Metric not found"));

        existingMetric.setSystemName(updatedMetric.getSystemName());
        existingMetric.setCpuUsage(updatedMetric.getCpuUsage());
        existingMetric.setMemoryUsage(updatedMetric.getMemoryUsage());
        existingMetric.setDiskUsage(updatedMetric.getDiskUsage());
        existingMetric.setTimestamp(updatedMetric.getTimestamp());

        return repository.save(existingMetric);
    }
}