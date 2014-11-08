package net.codesree.common;

import java.io.Serializable;

public class Author implements Serializable {

  private int id=-1;
  private String name=null;
  
  public Author() {}
  public Author(int _id,String _name) {
    setId(_id);
    setName(_name);
  }
  
  public void setId(int _id) { this.id=_id; }
  public int getId() { return this.id; }
  
  public void setName(String _name) { this.name=_name; }
  public String getName() { return this.name; }

  public String toString() { return "Author[id:"+getId()+",name:"+getName()+"]"; }
}
