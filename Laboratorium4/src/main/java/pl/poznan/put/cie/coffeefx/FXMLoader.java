package pl.poznan.put.cie.coffeefx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FXMLoader {
    private Pane view;

    public Pane getPage(String fileName){
        try{
            URL fileURL = Launcher.class.getResource("/"+ fileName + ".fxml");
            if(fileURL == null)
            {
                throw new java.io.FileNotFoundException("FXML file cant be found");
            }
            view = new FXMLLoader().load(fileURL);
        }catch(Exception e)
        {
            System.out.println("No page " + fileName + " please check FXMLoader");
        }
        return view;
    }
}
