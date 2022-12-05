package ch.bbw.m151.jokesdb.datamodel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name = "jokes")
@Data
public class JokesEntity {
	@Id
	@GeneratedValue
	@Getter
	@Setter
	int id;

	@Column(nullable = false, length = 1000)
	@Getter
	@Setter
	String joke;

	private String category;
	private String type;
	private String setup;
	private String delivery;
	private Boolean safe;
	private String lang;

	@Getter
	@Setter
	Integer totalRatings;

	@Version
	private int version;

	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;
}