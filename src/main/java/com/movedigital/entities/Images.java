package com.movedigital.entities;


import javax.persistence.*;

//@Entity
public class Images {

  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "images_Sequence")
  @SequenceGenerator(name = "images_Sequence", sequenceName = "seq_images", initialValue = 1, allocationSize = 1)
  private long idimage;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "iditem", nullable = false)
  private Item iditem;

  private String path;


  public long getIdimage() {
    return idimage;
  }

  public void setIdimage(long idimage) {
    this.idimage = idimage;
  }


  public Item getIditem() {
    return iditem;
  }

  public void setIditem(Item iditem) {
    this.iditem = iditem;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

}
