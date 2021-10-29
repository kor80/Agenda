package poo.agenda;

//WHAT?
public interface Agenda extends Iterable<Nominativo>{

  default int size(){
    int c=0;
    for ( Nominativo n : this) c++;
    return c;
  }

  default int svuota(){
    Iterator<Nominativo> it=iterator();
    while( it.hasNext() ){
      it.next();
      it.remove();
    }
  }

  void aggiungi( Nominativo n ); //Se gia presente lo considero un update

  default void rimuovi( Nominativo n ){
      Iterator<Nominativo> it=iterator();
      while( it.hasNext() ){
        Nominativo x=it.next();
        if( x.equals(n) ){ it.remove(); break; }
        if( x.compareTo(n)>0 ) return;
      }
  }

  default Nominativo cerca( Nominativo n ){ //n è fittizio
    for( Nominativo x : this ){
      if( x.equals(n) ) return x;
      if( x.compareTo(n)>0 ) return null;
    }
    return null;
  }

  default Nominativo cerca( String prefisso, String telefono ){
    for( Nominativo x : this )
      if( x.getPrefisso().equals(prefisso) && x.getTelefono().equals(telefono))
        return x;
    return null;
  }

  default void salva( String nomeFile ) throws IOException{
    PrintWriter pw=new PrintWriter(new FileWriter(nomeFile));
    for( Nominativo n : this ) pw.println(n);
    pw.close();
  }

  default void ripristina( String nomeFile ) throws IOException{
    BufferedReader br=new BufferedReader(new FileReader(nomeFile));
    boolean okLettura=true;
    Vector<Nominativo> backup=new ArrayVector<>();
    try{
      for(;;){
        String linea=br.readLine();
        if( linea==null ) break;
        StringTokenizer st=new StringTokenizer(linea, " -");
        String cog=st.nextToken();
        String nom=st.nextToken();
        String pre=st.nextToken();
        String tel=st.nextToken();
        backup.add(new Nominativo(cog,nom,pre,tel));
      }
    }catch( IOException e ){
      System.out.println("Errore ripristina. Nessuna lettura.");
      okLettura=false;
    }finally{
      br.close();
    }
    if( okLettura ){
      this.svuota();
      for( Nominativo n : backup )
        this.aggiungi(n);
    }
  }
}
