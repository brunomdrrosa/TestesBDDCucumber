package br.com.alura.leilao.acceptance.steps;

import org.junit.Assert;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LoginPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class LoginSteps {
	
	private Browser browser;
	private LoginPage loginPage;

	@Dado("o usuário inválido")
	public void o_usuario_inválido() {
		browser = new Browser();
		browser.seed();
		loginPage = browser.getLoginPage();
	}
	
	@Quando("tenta se logar")
	public void tenta_se_logar() {
		this.loginPage.realizaLoginComo("fulano", "xpto");
	}
	
	@Então("continua na página de login")
	public void continua_na_página_de_login() {
		Assert.assertTrue(this.loginPage.estaNaPaginaDeLoginComErro());
		browser.clean();
	}
}
