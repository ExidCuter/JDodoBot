package xyz.the_dodo.database.types;

import xyz.the_dodo.database.types.common.Identificator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_prefix")
public class Prefix extends Identificator {
	private String m_prefix;

	@ManyToOne
	@JoinColumn(name = "server_id")
	private Server m_server;
}
