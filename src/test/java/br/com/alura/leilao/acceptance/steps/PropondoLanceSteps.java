package br.com.alura.leilao.acceptance.steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class PropondoLanceSteps {

	private Lance lance;
	private Leilao leilao;
	private ArrayList<Lance> lista;
	
	@Before
	public void setup() {
		this.lista = new ArrayList<Lance>();
		leilao = new Leilao("Tablet XPTO");
	}
	
	@After
	public void tearDown() {
	}
	
	@Dado("um lance válido")
	public void dado_um_lance_valido() {
		Usuario usuario = new Usuario("fulano");
		lance = new Lance(usuario , BigDecimal.TEN);
	}

	@Quando("propõe ao leilão")
	public void quando_propoe_o_lance() {
		leilao.propoe(lance);
	}
	
	@Então("o lance é aceito")
	public void entao_o_lance_e_aceito() {
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
	}
	
	@Dado("um lance de {double} reais do usuário {string}")
	public void um_lance_de_reais_do_usuario_fulano(Double valor, String nomeUsuario) {
		Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
		lista.add(lance);
	}

	@Quando("propõe vários lances ao leilão")
	public void propoe_varios_lances_ao_leilao() {
		this.lista.forEach(lance -> leilao.propoe(lance));
	}
	
	@Então("os lances são aceitos")
	public void os_lances_sao_aceitos() {
		Assert.assertEquals(this.lista.size(), leilao.getLances().size());
		Assert.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
		Assert.assertEquals(this.lista.get(1).getValor(), leilao.getLances().get(1).getValor());
	}
	
	@Dado("um lance inválido de {double} reais e do usuário {string}")
	public void um_lance_inválido_de_reais(Double valor, String nomeUsuario) {
		this.lance = new Lance(new BigDecimal(valor));
	}

	@Então("o lance não é aceito")
	public void o_lance_nao_e_aceito() {
		Assert.assertEquals(0, leilao.getLances().size());
	}
	
	@Dado("dois lances")
	public void dois_lances(DataTable dataTable) {
	    List<Map<String, String>> valores = dataTable.asMaps();
	    for (Map<String, String> mapa : valores) {
	    	String valor = mapa.get("valor");
	    	String nome = mapa.get("nomeUsuario");
	    	
	    	Lance lance = new Lance(new Usuario(nome), new BigDecimal(valor));
	    	lista.add(lance);
	    }
	    
	}
	
	@Então("o segundo lance não é aceito")
	public void o_segundo_lance_não_é_aceito() {
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
	}
	
}
