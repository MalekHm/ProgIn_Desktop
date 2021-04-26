/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventure;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ousse
 */
public class CategoryEventController implements Initializable {

    @FXML
    private TextField TF_IDCatEvent;
    @FXML
    private TextField TF_IDCatNameEvent;
    @FXML
    private TextField TF_IDCatDescEvent;
    @FXML
    private TableView<?> tv_Cat;
    @FXML
    private TableColumn<?, ?> Col_idCat;
    @FXML
    private TableColumn<?, ?> Col_NameCat;
    @FXML
    private TableColumn<?, ?> Col_DescCat;
    @FXML
    private Button bt_Ajout;
    @FXML
    private Button bt_Modifier;
    @FXML
    private Button bt_Supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
