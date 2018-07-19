// Luokka int-tyyppisten muuttujien m‰‰ritt‰miseen
// Sis‰lt‰‰ nimen ja arvon

class Variable<String, Object>{
  protected String name;
  protected Integer value;
  
  // Konstruktori
  protected Variable(String name, int value){
    this.name = name;
    this.value = value;
  }
  
  // Havainnointimetodit
  protected int value(){
    return value; 
  }
  protected String name(){
    return name; 
  }
  
  // Asetusmetodi
  protected void setValue(int value){
    this.value = value;
  }  
}