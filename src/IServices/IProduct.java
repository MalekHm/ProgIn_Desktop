/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import java.io.File;
import java.util.List;

/**
 *
 * @author hela1
 * @param <T>
 */
public interface IProduct <T> {
    public void add(T t);
    public void delete(T t);
    public void edit(T t);
    public List<T> show();
    public List<T> searchByName(String Name); 
    public void count();
    public List<T> list();
    public void saveFile(File f,String name);
}
