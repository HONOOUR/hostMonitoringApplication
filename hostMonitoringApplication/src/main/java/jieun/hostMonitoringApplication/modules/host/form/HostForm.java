package jieun.hostMonitoringApplication.modules.host.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Data
public class HostForm {

    @NotBlank
    @Length(max = 50)
    private String name;

    @NotBlank
    @Length(max = 50)
    private String address;

}
