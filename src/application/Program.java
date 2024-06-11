package application;

import entities.Employers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employers> list = new ArrayList<>();

        System.out.println("Quantos funcionarios serão registrados?");
        int n = sc.nextInt();

        for(int i =0; i <n; i++){
            System.out.println("Funcionario #" + (i+1) + ":");

            System.out.print("Id:");
            Integer id = sc.nextInt();

            //VALIDAR SE O ID EXISTE!
            while(hasId(list, id)){
                System.out.println("ID já cadastrado, tente novamente");
                id = sc.nextInt();
            }
            sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Salary:");
            Double salary = sc.nextDouble();

            Employers emp = new Employers(name, id,salary);
            list.add(emp);
        }

        System.out.println();
        System.out.print("Entre com o empregado que terá o salário incrementado:");
        int idSalary = sc.nextInt();




        //Integer pos = retornaPosicao(list, idSalary);
        Employers pos = list.stream().filter( x -> x.getId() == idSalary).findFirst().orElse(null);
        if(pos == null){
            System.out.println("Não encontrou! Esse ID não existe.");
        }
        else{
            System.out.println("Entre com a porcentagem:");
            double porcentagem = sc.nextDouble();
            pos.increasySalary(porcentagem);
        }

        System.out.println();
        System.out.println("Lista de Funcionarios");
        for(Employers e: list){
            System.out.println(e);
        }

        sc.close();
    }

    public static Integer retornaPosicao(List<Employers> list, int id){

        for(int i =0; i < list.size(); i++){
            if(list.get(i).getId() == id){
                return i;
            }
        }
        return null;
    }

    public static boolean hasId(List<Employers> list, int id){
        Employers p = list.stream().filter( x -> x.getId() == id).findFirst().orElse(null);
        return p !=null;
    }
}
