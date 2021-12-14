package jieun.hostMonitoringApplication.modules.host;

import jieun.hostMonitoringApplication.modules.host.form.HostForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HostController {
    private final HostRepository hostRepository;
    private final HostService hostService;
    private final ModelMapper modelMapper;

    @GetMapping("/hosts")
    public String viewHosts(Model model) {
        List<Host> hosts = hostRepository.findAll();
        model.addAttribute("hosts", hosts);
        return "host/view";
    }

    @GetMapping("/new-host")
    public String viewNewHostForm(Model model) {
        model.addAttribute("hostForm", new HostForm());
        return "host/form";
    }

    @PostMapping("/new-host")
    public String registerHost(Model model, @Valid HostForm hostForm, Errors errors) {
        if (errors.hasErrors()) {
            return "host/form";
        }

        if (!hostService.canRegisterHost()) {
            model.addAttribute("message", "호스트 개수가 100를 초과했습니다.");
            return "host/form";

        }

        hostService.registerNewHost(modelMapper.map(hostForm, Host.class));
        return "redirect:/hosts";
    }

    @GetMapping("/host/{name}")
    public String updateHost(Model model, @PathVariable String name) {
        Host host = hostService.getHost(name);
        model.addAttribute(modelMapper.map(host, HostForm.class));
        return "host/host";
    }

    @PostMapping("/update/host/{name}")
    public String updateHost(Model model,@Valid HostForm hostForm, Errors errors, @PathVariable String name) {
        if (errors.hasErrors()) {
            return "host/host";
        }

        Host host = hostService.getHost(name);
        if (hostService.updateHost(host, modelMapper.map(hostForm, Host.class)) == null) {
            model.addAttribute("message", "이미 등록된 호스트 입니다.");
            return "host/host";
        }

        return "redirect:/hosts";
    }

    @PostMapping("/delete/host/{name}")
    public String deleteHost(@PathVariable String name) {
        hostService.deleteHost(name);
        return "redirect:/hosts";
    }

}
