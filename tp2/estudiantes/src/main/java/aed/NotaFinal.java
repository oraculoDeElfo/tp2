package aed;

public class NotaFinal implements Indexable<NotaFinal> {
    public double _nota;
    public int _id;

    //O(1)
    public NotaFinal(double nota, int id){
        _nota = nota;
        _id = id;
    }

    //O(1)
    @Override                                          
    public int compareTo(NotaFinal otra){
        if (otra._nota != this._nota){
            return Double.compare(this._nota, otra._nota);
        } 
        return (this._id - otra._id);
    }

    //O(1)
    @Override
    public boolean equals(Object o){
        if (!(o instanceof NotaFinal)){ 
            return false;
        } else {
            return this.equals((NotaFinal) o);
        }
    }

    //O(1)
    public boolean equals(NotaFinal otra){
        return (otra._id == this._id) && (this._nota == otra._nota);
    }

    //O(1)
    @Override
    public int obtenerId(){
        return this._id;
    }

    //O(1)
    public void actualizarNotar(double nota){
        _nota = nota;
    }
}