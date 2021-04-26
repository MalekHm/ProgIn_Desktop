/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entity.Blog;
import java.io.File;
import java.util.List;

/**
 *
 * @author Oumeyma
 * @param <B>
 */
public interface IBlog<B> {
    public void ajouter(B b);
    public void supprimer(B b);
    public void modifier(B b);
    public List<B> afficher();
     public void saveFile(File f,String name);
      public List<Blog> list();
}
