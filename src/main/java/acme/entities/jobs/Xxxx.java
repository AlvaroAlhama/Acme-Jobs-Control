
package acme.entities.jobs;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Xxxx extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@Length(max = 20)
	private String				pieceOfText;

	@URL
	private String				linkInfo;

}
