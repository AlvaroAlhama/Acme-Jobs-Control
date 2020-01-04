
package acme.entities.jobs;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class XxxxApplication extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	private String				answer;

	private String				xxxx;

	@Pattern(regexp = "((^(?=[a-zA-Z0-9\\p{Punct}\\\\¿¡´¨]{10,}$)(?=(?:[^a-zA-Z]*[a-zA-Z]){3})(?=(?:[^0-9]*[0-9]){3})(?=(?:[^\\p{Punct}\\\\¿¡´¨]*[\\p{Punct}\\\\¿¡´¨]){3}).*)|^$)")
	private String				password;

}
