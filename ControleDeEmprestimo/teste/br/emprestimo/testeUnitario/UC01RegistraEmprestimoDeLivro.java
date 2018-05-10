package br.emprestimo.testeUnitario;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.emprestimo.modelo.Emprestimo;
import br.emprestimo.modelo.Livro;
import br.emprestimo.modelo.Usuario;
import br.emprestimo.servico.ServicoEmprestimo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class UC01RegistraEmprestimoDeLivro {
	static private Livro livro;
	static private Usuario usuario;
	static private ServicoEmprestimo servico;
	static private Emprestimo emprestimo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//cenario
		livro = new Livro();
		livro.setIsbn("121212");
		livro.setTitulo("Engenharia de Software");
		livro.setAutor("Pressman");
		usuario = new Usuario();
		usuario.setRa("11111");
		usuario.setNome("Jose da Silva");
		servico = new ServicoEmprestimo();
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Test
	public void CT01UC01FB_registrar_emprestimo_com_sucesso() {
		assertNotNull(servico.empresta(livro, usuario));
	}
	@Test(expected=RuntimeException.class)
	public void CT02UC01FB_registrar_emprestimo_com_dados_invalidos() {
		assertNotNull(servico.empresta(null, usuario));
	}
	@Test
	public void CT03UC01FB_registrar_emprestimo_com_dados_invalidos(){
		try{
			servico.empresta(null, usuario);
			fail ("deveria lançar uma exceção");
		}catch(RuntimeException e){
			assertEquals("Dados inválidos.", e.getMessage());
		}
	}
	@Test
	public void CT04UC01FB_registrar_emprestimo_com_sucesso_validacao_da_data() {
		//acao
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		String dataEsperada = new DateTime().plusDays(8).toString(fmt);
		emprestimo = servico.empresta(livro, usuario);
		String dataObtida = emprestimo.getDataDevolucao();
		//verificacao
	    assertTrue(dataEsperada.equals(dataObtida));
	}
	@Test
	public void CT05UC01FB_registrar_emprestimo_com_data_invalida() {
		assertFalse(emprestimo.validaData("30-03-2000"));
	}
	@Test(expected=RuntimeException.class)
	public void CT06UC01FB_registrar_emprestimo_com_livro_invalido_null() {
		emprestimo.setLivro(null);
	}
	
	@Test
	public void CT07QuandoUsuarioNuloErro() {
		//cenario
		Usuario usuario = null;
		Emprestimo umEmprestimo = new Emprestimo();
		//acao
		try {
			umEmprestimo.setUsuario(usuario);
			fail("Deveria falhar");
		}catch (Exception e) {
		//veriricação
			assertEquals("Usuário Invalido", e.getMessage());
		}
	}
	
	@Test
	public void CT08QuandoaDataDeEmprestimoErro() {
		//cenario
		String data = "30/02/2018";
		Emprestimo umEmprestimo = new Emprestimo();
		//acao
		boolean resultadoObtido = umEmprestimo.validaData(data);
		//veriricação
			assertFalse(resultadoObtido);
		}
	

	@Test
	public void CT09QuandoaDataDeEmprestimoErro() {
		// cenario
		String data = "09-05-2018";
		Emprestimo umEmprestimo = new Emprestimo();
		// acao
		boolean resultadoObtido = umEmprestimo.validaData(data);
		// veriricação
		assertFalse(resultadoObtido);
	}
	
	@Test
	public void CT010QuandoaDataDeEmprestimoErro() {
		// cenario
		String data = "09/05/2018";
		Emprestimo umEmprestimo = new Emprestimo();
		// acao
		boolean resultadoObtido = umEmprestimo.validaData(data);
		// veriricação
		assertFalse(resultadoObtido);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
