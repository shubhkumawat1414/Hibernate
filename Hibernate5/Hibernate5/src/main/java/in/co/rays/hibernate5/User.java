package in.co.rays.hibernate5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

    @NamedQuery(name = "allUser", query = "fromUser") /* used to apply Named Query  */
    @Cache(region = "personCache", usage = CacheConcurrencyStrategy.READ_ONLY) /* used to apply second level cache */ 
	@Entity
	@Table(name="USER")
	public class User {
		
		@Id
		@GeneratedValue(generator = "increment")
		@GenericGenerator(name="increment",strategy = "increment")
		@Column(name = "ID")
		private int id;
		
		@Column(name= "FIRST_NAME")
		private String fname;
		
		@Column(name= "LAST_NAME")
		private String lname;
		
		@Column(name= "USER_NAME")
		private String userName;
		
		@Column(name= "PWD")
		private String pwd;
		
		public User() {
			// TODO Auto-generated constructor stub
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
}
