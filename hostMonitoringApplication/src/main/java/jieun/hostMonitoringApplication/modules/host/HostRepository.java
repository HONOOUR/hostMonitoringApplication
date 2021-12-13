package jieun.hostMonitoringApplication.modules.host;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface HostRepository extends JpaRepository<Host, Long> {

    List<Host> findAll();

    Host findByName(String name);

    Host findByAddress(String address);
}
