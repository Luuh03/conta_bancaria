package conta_bancaria.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import conta_bancaria.model.Conta;
import conta_bancaria.repository.ContaRepository;
import conta_bancaria.util.Cores;

public class ContaController implements ContaRepository {

	// Criar collection arraylist
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	
	// Variável para controlar os números das contas
	int numero = 0;
	
	@Override
	public void procurarPorNumero(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);
		
		if(conta.isPresent())
			conta.get().visualizar();
		else
			System.out.printf("\nA conta %d não foi encontrada!", numero);
	}

	@Override
	public void listarTodas() {
		for(var conta : listaContas) {
			conta.visualizar();
		}
		
	}
	
	@Override
	public void listarPorTitular(String titular) {
		
		List<Conta> listaTitulares = listaContas.stream()
				.filter(c -> c.getTitular().toUpperCase().contains(titular.toUpperCase()))
				.collect(Collectors.toList());
		
		if(listaTitulares.isEmpty())
			System.out.printf("\nNenhuma Conta foi encontrada com base no critério: %s", titular);
		
		for(var conta : listaTitulares)
			conta.visualizar();
		
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println(Cores.TEXT_GREEN + "A conta foi criada com sucesso!" + Cores.TEXT_RESET);
	}

	@Override
	public void atualizar(Conta conta) {
		Optional<Conta> buscaConta = buscarNaCollection(conta.getNumero());
		
		if(buscaConta.isPresent()) {
			listaContas.set(listaContas.indexOf(buscaConta.get()), conta);
			System.out.printf("\nA conta %d foi atualizada com sucesso!", conta.getNumero());
		}
		else
			System.out.printf("\nA conta %d não foi encontrada!", conta.getNumero());
		
	}

	@Override
	public void deletar(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);
		
		if(conta.isPresent()) {
			if(listaContas.remove(conta.get()) == true)
				System.out.printf("\nA conta %d foi excluída com sucesso!", numero);
		}
		else
			System.out.printf("\nA conta %d não foi encontrada!", numero);
		
	}

	@Override
	public void sacar(int numero, float valor) {
		Optional<Conta> conta = buscarNaCollection(numero);
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		
		if(conta.isPresent()) {
			if(conta.get().sacar(valor) == true)
				System.out.printf("\nO saque no valor de %s na conta número %d foi feito com sucesso!", nfMoeda.format(valor), numero);
		}
		else
			System.out.printf("\nA conta %d não foi encontrada!", numero);
		
	}

	@Override
	public void depositar(int numero, float valor) {
		Optional<Conta> conta = buscarNaCollection(numero);
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		
		if(conta.isPresent()) {
			conta.get().depositar(valor);
			System.out.printf("\nO depósito no valor de %s na conta número %d foi feito com sucesso!", nfMoeda.format(valor), numero);
		}
		else
			System.out.printf("\nA conta %d não foi encontrada!", numero);
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		Optional<Conta> contaOrigem = buscarNaCollection(numeroOrigem);
		Optional<Conta> contaDestino = buscarNaCollection(numeroDestino);
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		
		if(contaOrigem.isPresent() && contaDestino.isPresent()) {
			if(contaOrigem.get().sacar(valor) == true) {
				contaDestino.get().depositar(valor);
				System.out.printf("\nA transferência no valor de %s da conta número %d para a conta número %d foi feita com sucesso!", nfMoeda.format(valor), numeroOrigem, numeroDestino);
			}
		}
		else
			System.out.printf("\nUma das contas não foi encontrada!");
		
	}
	
	// Métodos Auxiliares
	public int gerarNumero() {
		return ++numero;
	}
	
	public Optional<Conta> buscarNaCollection(int numero) {
		for(var conta : listaContas) {
			if(conta.getNumero() == numero)
				return Optional.of(conta);
		}
		
		return Optional.empty();
	}


}
