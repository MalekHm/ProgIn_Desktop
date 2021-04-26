/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.util.List;

/**
 *
 * @author Oumeyma
 * @param <A>
 */
public interface ICategoryActivity<A> {
    public void ajouter(A a);
    public void supprimer(A a);
    public void modifier(A a);
    public List<A> afficher();  
}
