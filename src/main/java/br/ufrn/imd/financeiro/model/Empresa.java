package br.ufrn.imd.financeiro.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Empresa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_EMPRESA")
	@SequenceGenerator(name="SEQ_EMPRESA", sequenceName="id_seq_empresa", allocationSize=1)
	@Column(name="id_empresa")
	private Integer id;
	
	private String cnpj;
	
	private String nomeFantasia;
	
	@OneToMany(mappedBy="empresa", fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private List<Conta> contas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public List<Conta> getContas() {
		return contas;
	}
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
	
}
