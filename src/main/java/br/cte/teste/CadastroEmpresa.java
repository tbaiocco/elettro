package br.cte.teste;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import br.cte.base.EmpresaDb;
import br.cte.model.Empresa;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author DerliRiffel
 */
public class CadastroEmpresa {

    public static void main(String[] args) {
        Empresa empresa = new Empresa();
        empresa.setCnpj("00963519000760");
        empresa.setIe("0963492675");
        empresa.setRazaosocial("EXCCEL FREIGHT TRANSPORTES INTERNACIONAIS LTDA");
        empresa.setTipoCertificado(1);
        empresa.setCertificado("exccel-poa.pfx");

        String senha = "exccel";
        BasicTextEncryptor textEncryptorNew = new BasicTextEncryptor();
        String senhaMestra = "liquid***###+++";
        textEncryptorNew.setPassword("A" + senhaMestra);
        empresa.setSenha(textEncryptorNew.encrypt(senha));

        empresa.setcUf(43);
        empresa.setMun("PORTO ALEGRE");
        empresa.setcMun(431490);
        empresa.setUf("RS");

        empresa.setAmbiente("1"); //1 - Prod | 2 - homo

        empresa.setDbHost("//127.0.0.1:5432");
	empresa.setDbPort(5432);
        empresa.setDbURL("jdbc:postgresql://127.0.0.1:5432/exccel");
        empresa.setDbDriver("org.postgresql.Driver");
        empresa.setDbUser("postgres");
        empresa.setDbPass("");

        EmpresaDb eDb = new EmpresaDb();
        if (eDb.salvaEmpresa(empresa)) {
            System.out.println("Empresa " + empresa.getRazaosocial() + ", foi salva com sucesso.");
            System.out.println("\n\n");

            Empresa e = eDb.getEmpresa(empresa.getCnpj());
            System.out.println("Empresa salva no xml: " + e.getRazaosocial() + " " + e.getCnpj());
        } else {
            System.out.println("Empresa " + empresa.getRazaosocial() + ", não pode ser salva");
        }
    }
}
