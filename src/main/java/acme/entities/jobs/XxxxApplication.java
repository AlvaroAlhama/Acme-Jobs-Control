
package acme.entities.jobs;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class XxxxApplication extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				answer;

	private String				xxxx;

	@Length(min = 12)
	@Pattern(regexp = "^(?=(?:[^\\p{Digit}]*[\\p{Digit}]){3,})(?=(?:[^\\p{Alpha}]*[\\p{Alpha}]){3,})(?=(?:[^\\p{Punct}\\\\¿¡´¨]*[\\p{Punct}\\\\¿¡´¨]){3,}).*")
	private String				password;

}
