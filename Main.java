import java.text.DecimalFormat;
import java.util.*;

public class Main {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    public static class Funcionario {
        String nome;
        float matricula;
        double salario = 2000.00;
        int faltas;

        public Funcionario(String nome, float matricula, int faltas) {
            this.nome = nome;
            this.matricula = matricula;
            this.faltas = faltas;
        }

        public static double calcularSalarioFinal(double salario, int faltas) {
            double salariofinal;
            salariofinal = salario - (salario / 30) * faltas;
            return salariofinal;
        }

        public static void exibirDados(String nome, float matricula, double salario, int faltas, int caso, double salariofinal) {
            switch (caso) {
                case 1:
                    System.out.println("Nome: " + nome);
                    break;
                case 2:
                    System.out.println("Matrícula: " + matricula);
                    break;
                case 3:
                    System.out.println("Salário Base: " + salario);
                    break;
                case 4:
                    System.out.println("Faltas: " + faltas);
                    break;
                case 5:
                    System.out.println("Salário Final: " + df.format(salariofinal));
                    break;
                default:
                    System.out.println("Caso inválido");
                    break;
            }
        }
    }

    public static class FuncionarioComissao extends Funcionario {
        float valorVendas;
        double percentual;

        public FuncionarioComissao(String nome, float matricula, int faltas, float valorVendas, double percentual) {
            super(nome, matricula, faltas);
            this.valorVendas = valorVendas;
            this.percentual = percentual;
        }

        public static double calcularComissao(float valorVendas, double percentual) {
            double extraPercentual;
            extraPercentual = valorVendas * (percentual / 100);
            return extraPercentual;
        }

        public static double calcularSalarioComisionado(double extraPercentual, int faltas) {
            double salario = 2000.00;
            double salariofinal = calcularSalarioFinal(salario, faltas);
            salariofinal += extraPercentual;
            return salariofinal;
        }
    }

    public static class FuncionarioProducao extends Funcionario {
        int quantidadeDePecas;
        double valorPorPecas;

        public FuncionarioProducao(String nome, float matricula, int faltas, int quantidadeDePecas, double valorPorPecas) {
            super(nome, matricula, faltas);
            this.quantidadeDePecas = quantidadeDePecas;
            this.valorPorPecas = valorPorPecas;
        }

        public static double calcularProdutividade(double valorPorPecas, int quantidadeDePecas) {
            double extraProdutividade;
            extraProdutividade = valorPorPecas * quantidadeDePecas;
            return extraProdutividade;
        }

        public static double calcularSalarioProducao(double extraProdutividade, double salario, int faltas) {
            double salariofinal = calcularSalarioFinal(salario, faltas);
            salariofinal += extraProdutividade;
            return salariofinal;
        }
    }

public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int tipo = 1;
    while(tipo!=4){
        System.out.println("Escolha o tipo de funcionário para cadastrar:");
        System.out.println("1 Funcionário Comum");
        System.out.println("2 Funcionário com Comissão");
        System.out.println("3 Funcionário de Produção");
        System.out.println("4 Parar");
        System.out.print("Opção: ");
        tipo = leitor.nextInt();
        leitor.nextLine();
        if(tipo ==4){
            break;
        }
        System.out.print("Digite o nome: ");
        String nome = leitor.nextLine();
        System.out.print("Digite a matrícula: ");
        float matricula = leitor.nextFloat();
        System.out.print("Digite o número de faltas: ");
        int faltas = leitor.nextInt();

        switch (tipo) {
            case 1:
                Funcionario f = new Funcionario(nome, matricula, faltas);
                double salarioFinalFunc = Funcionario.calcularSalarioFinal(f.salario, f.faltas);
                
                System.out.println("\nDados do Funcionário Comum");
                Funcionario.exibirDados(f.nome, f.matricula, f.salario, f.faltas, 5, salarioFinalFunc);
                break;

            case 2:
                System.out.print("Digite o valor das vendas: ");
                float valorVendas = leitor.nextFloat();
                System.out.print("Digite o percentual de comissão (%): ");
                double percentual = leitor.nextDouble();

                FuncionarioComissao fc = new FuncionarioComissao(nome, matricula, faltas, valorVendas, percentual);
                double comissao = FuncionarioComissao.calcularComissao(fc.valorVendas, fc.percentual);
                double salarioFinalComissao = FuncionarioComissao.calcularSalarioComisionado(comissao, fc.faltas);

                System.out.println("\nDados do Funcionário com Comissão");
                System.out.println("Nome: " + fc.nome);
                System.out.println("Matrícula: " + fc.matricula);
                System.out.println("Salário Final (com comissão): " + salarioFinalComissao);
                break;

            case 3:
                System.out.print("Digite a quantidade de peças produzidas: ");
                int quantidadeDePecas = leitor.nextInt();
                System.out.print("Digite o valor por peça: ");
                double valorPorPecas = leitor.nextDouble();

                FuncionarioProducao fp = new FuncionarioProducao(nome, matricula, faltas, quantidadeDePecas, valorPorPecas);
                double produtividade = FuncionarioProducao.calcularProdutividade(fp.valorPorPecas, fp.quantidadeDePecas);
                double salarioFinalProd = FuncionarioProducao.calcularSalarioProducao(produtividade, fp.salario, fp.faltas);

                System.out.println("\nDados do Funcionário de Produção");
                System.out.println("Nome: " + fp.nome);
                System.out.println("Matrícula: " + fp.matricula);
                System.out.println("Salário Final (com produção): " + salarioFinalProd);
                break;

            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
        leitor.close();
    }
}