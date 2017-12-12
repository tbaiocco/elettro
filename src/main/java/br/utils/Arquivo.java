package br.utils;

import java.io.*;

/************
 * Arquivo - versão 25/05/2007
 * 
 * abrirEscrita() --> boolean
 * abrirLeitura() --> boolean
 * lerLinha() --> string
 * escreverLinha( <string> )
 * fecharArquivo()
 */
public class Arquivo {

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String nomeArquivo;
    private char status;

    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        status = 'C'; // Closed, Read, Write
    }

    public boolean abrirLeitura() {
        boolean sucesso = true;
        FileReader fileReader = null;
        if (status != 'C') {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" já está aberto");
            sucesso = false;
        } else {
            try {
                fileReader = new FileReader(nomeArquivo);
            } catch (FileNotFoundException e) {
                System.err.println("-- Arquivo \"" + nomeArquivo + "\" não encontrado");
                sucesso = false;
                //e.printStackTrace();
            }
            if (sucesso) {
                status = 'R';
                bufferedReader = new BufferedReader(fileReader);
            }
        }
        return (sucesso);
    }

    public boolean abrirEscrita() {
        boolean sucesso = true;
        FileWriter fileWriter = null;
        if (status != 'C') {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" já está aberto");
            sucesso = false;
        } else {
            try {
                fileWriter = new FileWriter(nomeArquivo, false);

            } catch (IOException e) {
                System.err.println("-- Erro de escrita no arquivo \"" + nomeArquivo + "\"");
                sucesso = false;
                //e.printStackTrace();
            }
            if (sucesso) {
                status = 'W';
                bufferedWriter = new BufferedWriter(fileWriter);
            }
        }
        return (sucesso);
    }

    public String lerLinha() {
        String linha = null;
        if (status == 'R') {
            try {
                linha = bufferedReader.readLine();
            } catch (IOException e) {
                System.err.println("-- Erro de leitura no arquivo \"" + nomeArquivo + "\"");
                //e.printStackTrace();
            }
        } else {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" não está pronto para leitura");
        }
        return (linha);
    }

    public String ler() {
        String linha = "";
        if (status == 'R') {
            try {
                String aux;
                while (bufferedReader.ready()) {
                    aux = bufferedReader.readLine();
                    if(!aux.equals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?> ")){
                        linha += aux+"\n";
                    }
                }

            } catch (IOException e) {
                System.err.println("-- Erro de leitura no arquivo \"" + nomeArquivo + "\"");
                //e.printStackTrace();
                }
        } else {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" não está pronto para leitura");
        }
        return (linha);
    }

    public void escreverLinha(String linha) {
        if (status == 'W') {
            try {
                bufferedWriter.write(linha);
                //bufferedWriter.newLine();
            } catch (IOException e) {
                System.err.println("Erro de escrita no arquivo \"" + nomeArquivo + "\"");
                //e.printStackTrace();
            }
        } else {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" não está pronto para escrita");
        }
    }

    

    public void fecharArquivo() {
        if (status == 'R') // estava aberto para leitura
        {
            try {
                bufferedReader.close();
                status = 'C';
            } catch (IOException e) {
                System.err.println("Erro ao fechar o arquivo \"" + nomeArquivo + "\"");
                //e.printStackTrace();
            }
        }
        if (status == 'W') // estava aberto para gravação
        {
            try {
                bufferedWriter.close();
                status = 'C';
            } catch (IOException e) {
                System.err.println("Erro ao fechar o arquivo \"" + nomeArquivo + "\"");
                //e.printStackTrace();
            }
        }
    }
    
    public void escreverLinhaSemNovaLinha(String linha) {
        if (status == 'W') {
            try {
                bufferedWriter.write(linha);
            } catch (IOException e) {
                System.err.println("Erro de escrita no arquivo \"" + nomeArquivo + "\"");
                //e.printStackTrace();
            }
        } else {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" não está pronto para escrita");
        }
    }

    public boolean excluir() {
        boolean sucesso = false;
        File f = new File(nomeArquivo);
        if(f.exists()){
            if(f.delete()){
                sucesso = true;
            }else{
                System.out.println("erro ao excluir!!! "+ f.getAbsolutePath());
            }
        }
        return (sucesso);
    }

    public boolean validateDir(String path){
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            } else return false;
        } else {
            file.mkdir();
            return true;
        }
    }
}
