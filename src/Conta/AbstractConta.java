package Conta;

import Cliente.Cliente;

import java.util.Random;

public abstract class AbstractConta {
 private int codigoAgencia;
 private int numeroConta;
 private int senha;
 protected double saldo;
 private Cliente cliente;

  private Random random = new Random();
 public AbstractConta(Cliente cliente, int codigoAgencia, int senha ) {

  this.cliente = cliente;
  this.codigoAgencia = codigoAgencia;
  this.senha = senha;
  this.saldo = 0;
 this.numeroConta =  random.nextInt(1000);

 }

 public int getCodigoAgencia() {
  return codigoAgencia;
 }

 public int getSenha() {
  return senha;
 }

 protected String getCpf(){

  return cliente.getCpf();
 }

 @Override
 public String toString() {
  return "Nome: " + cliente.getNome() + " CPF: " + cliente.getCpf() + " Codigo agencia: "
        + this.codigoAgencia + "Numero Conta: " + this.numeroConta + " Saldo: " + this.saldo ;
 }




}


