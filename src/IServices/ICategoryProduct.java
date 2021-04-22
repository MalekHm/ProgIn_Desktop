/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import java.util.List;

/**
 *
 * @author hela1
 * @param <T>
 */

public interface ICategoryProduct <T> {
     public void add(T t);
    public void delete(T t);
    public void edit(T t);
    public List<T> show();
}
