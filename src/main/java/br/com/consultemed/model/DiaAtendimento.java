//package br.com.consultemed.model;
//
//import java.io.Serializable;
//
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import br.com.consultemed.enums.Dias;
//
//@Entity
//@Table(name = "dia_atendimento_medico")
//public class DiaAtendimento implements Serializable {
//	
//	private static final long serialVersionUID = 1L;
//	
//	private Long id;
//	private Medico medico;
//	private Dias dia;
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	
//	@ManyToOne
//	public Medico getMedico() {
//		return medico;
//	}
//	public void setMedico(Medico medico) {
//		this.medico = medico;
//	}
//	
//	@ManyToOne
//	@Enumerated(EnumType.STRING)
//	public Dias getDia() {
//		return dia;
//	}
//	public void setDia(Dias dia) {
//		this.dia = dia;
//	}
//	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		DiaAtendimento other = (DiaAtendimento) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}	
//	
//}
