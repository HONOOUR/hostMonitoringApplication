package jieun.hostMonitoringApplication.modules.host;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HostService {
    private final HostRepository hostRepository;

    public boolean canRegisterHost() {
        long count = hostRepository.count();
        if (count < 100) {
            return true;
        } else {
            return false;
        }
    }

    public void registerNewHost(Host host) {
        boolean isAlive = isHostAlive(host.getName());
        host.setAlive(isAlive);
        host.setCreatedAt(LocalDateTime.now());
        if (isAlive) {
            host.setLastAliveAt(LocalDateTime.now());
        }
        host.setUpdatedAt(LocalDateTime.now());
        hostRepository.save(host);
    }

    public boolean isHostAlive(String name) {
        boolean isAlive = false;
        try {
            if (InetAddress.getByName(name).isReachable(5000)){
                isAlive = true;
            }
        } catch (UnknownHostException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }
        return isAlive;
    }

    public Host getHost(String name) {
        return hostRepository.findByName(name);
    }

    public Host updateHost(Host host, Host newHost) {
        if (isHostRegistered(host, newHost)) {
            return null;
        }
        host.setAddress(newHost.getAddress());
        host.setName(newHost.getName());
        boolean isAlive = isHostAlive(newHost.getName());
        host.setAlive(isAlive);
        if (isAlive) {
            host.setLastAliveAt(LocalDateTime.now());
        }
        host.setUpdatedAt(LocalDateTime.now());
        hostRepository.save(host);
        return host;
    }

    private boolean isHostRegistered(Host oldHost, Host newHost) {
        boolean isRegistered = false;
        Host host = hostRepository.findByName(newHost.getName());
        if (host != null && host != oldHost) {
            isRegistered = true;
        }

        host = hostRepository.findByAddress(host.getAddress());
        if (host != null && host != oldHost) {
            isRegistered = true;
        }
        return isRegistered;
    }

    public void deleteHost(String name) {
        Host host = getHost(name);
        hostRepository.delete(host);
    }
}
