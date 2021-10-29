package poo.agenda;
import poo.util.*;

public class AgendaVector extends AgendaAstratta{

  private Vector<Nominativo> tabella;

  public AgendaVector(){
    this(100);
  }

  public AgendaVector( int n ){
    if( n<=0 ) throw new IllegalArgumentException();
    tabella=new ArrayVector<>(n);
  }

  //provvisorio
  private static int ricercaBinaria( Vector<Nominativo> v, Nominativo n ){
    //v è oridnato
    int inf=0, sup=v.size()-1;
    while( inf<=sup ){
      int med=(inf+sup)/2;
      Nominativo x=v.get(med);
      if( x.equals(n) ) return med;
      if( x.compareTo(n)>0 ) sup=med-1;
      else inf=med+1;
    }
    return -1;
  }

  @Override
  public int size(){ return tabella.size(); }

  @Override
  public void svuota(){ tabella.clear(); }

  @Override
  public void aggiungi( Nominativo n ){
    int i=ricercaBinaria(tabella,n);
    if( i>=0 ) { tabella.set(i,n); return; }
    i=0;
    while( i<tabella.size() ){
      Nominativo x=tabella.get(i);
      if( x.compareTo(n)>0 ) break;
      i++;
    }
    tabella.add(i,n);
  }

  @Override
  public void rimuovi( Nominativo n ){
    int i=ricercaBinaria(tabella,n);
    if( i<0 ) return;
    tabella.remove(i);
  }

  @Override
  public Nominativo cerca( Nominativo n ){
    int i=ricercaBinaria(tabella,n);
    if( i<0 ) return null;
    return tabella.get(i);
  }

  @Override
  public java.util.Iterator<Nominativo> iterator(){
    return tabella.iterator();
  }
}
