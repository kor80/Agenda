package poo.agenda;

public class Nominativo implements Comparable<Nominativo>{
  private string cognome, nome, prefisso, telefono;

  public Nominativo( String nome, String Cognome, String prefisso, String telefono ){
    this.cognome=cognome;
    this.nome=nome;
    this.prefisso=prefisso;
    this.telefono=telefono;
  }

  public String getCognome(){ return cognome; }
  public String getNome(){ return nome; }
  public String getPrefisso(){ return prefisso; }
  public String getTelefono(){ return telefono; }

  public int compareTo( Nominativo n ){
    if( this.cognome.compareTo(n.cognome)<0 ||
        this.cognome.equals(n.cognome) && this.nome.compareTo(n.nome)<0 ) return -1;
    if( this.cognome.equals(n.cognome) && this.nome.equals(n.nome) ) return 0;
    return -1;
  }

  public boolean equals( Object o ){
    if( !(o instanceof Nominativo) ) return false;
    if( o==this ) return true;
    Nominativo n=(Nominativo)o;
    return this.cognome.equals(n.cognome) && this.nome.equals(n.nome);
  }

  public int hashCode(){
    final int M=17;
    int h=cognome.hashCode(); //di Class
    h=h*M+nome.hashCode();
    return h;
  }

  public string toString(){
    return cognome+" "+nome+" "+prefisso+"-"+telefono;
  }

  
}
