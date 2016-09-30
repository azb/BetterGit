/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bettergitprototype;

import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author Arthur
 */
public class BetterGitPrototype extends Application {

File localDir = new File("");
File cloudDir = new File("");
Label localDirLabel = new Label();
Label cloudDirLabel = new Label();
File[] localFileList = new File("").listFiles();
File[] cloudFileList = new File("").listFiles();

ArrayList<CheckBox> localFileCheckBox = new ArrayList<CheckBox>();
ArrayList<CheckBox> cloudFileCheckBox = new ArrayList<CheckBox>();

VBox localFileVBox = new VBox();
VBox cloudFileVBox = new VBox();

    @Override
    public void start(Stage primaryStage) {
        //localFileVBox.setPrefSize( Double.MAX_VALUE, Double.MAX_VALUE );
        //cloudFileVBox.setPrefSize( Double.MAX_VALUE, Double.MAX_VALUE );

        localDirLabel.setText("Choose a Local Directory");
        cloudDirLabel.setText("Choose a Cloud Directory");
        
        //Push Button
        Button pushBtn = new Button();
        pushBtn.setText("Push >>");
        pushBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Pulling selected files");
            }
        });

        //Pull Button
        Button pullBtn = new Button();
        pullBtn.setText("<< Pull");
        pullBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Pushing selected files");
            }
        });

        //Compare Button
        Button compareBtn = new Button();
        compareBtn.setText("Compare");
        compareBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Comparing selected files");
            }
        });

        VBox actionButtonsVBox = new VBox();
        //actionButtonsVBox.setPrefWidth(200);
        actionButtonsVBox.setMinWidth(100);
        //actionButtonsVBox.setPrefWidth(400);

        actionButtonsVBox.setAlignment(Pos.CENTER);
        actionButtonsVBox.getChildren().add(pushBtn);
        actionButtonsVBox.getChildren().add(pullBtn);
        actionButtonsVBox.getChildren().add(compareBtn);

        HBox fileListsHBox = new HBox();
        fileListsHBox.setMaxWidth(Double.MAX_VALUE);
        //Scrollpane for local files
        ScrollPane localFilePane = new ScrollPane();
        //localFilePane.setPrefWidth(300);
        localFilePane.setMaxWidth(Double.MAX_VALUE);
        localFilePane.setPrefWidth(800);
        //localFilePane.setFitToHeight(true);
        //localFilePane.setFitToWidth(true);
        //Scrollpane for cloud files
        ScrollPane cloudFilePane = new ScrollPane();
        //cloudFilePane.setPrefWidth(300);
        cloudFilePane.setMaxWidth(Double.MAX_VALUE);
        cloudFilePane.setPrefWidth(800);
        //cloudFilePane.setFitToHeight(true);
        //cloudFilePane.setFitToWidth(true);

        for (int i = 0; i < 100; i++) {
            CheckBox newBox = new CheckBox();
            newBox.setText("localfilename" + i + ".ext");
            localFileCheckBox.add(newBox); //.setText("filename.ext");
            localFileVBox.getChildren().add(newBox);

            CheckBox newBox2 = new CheckBox();
            
            newBox2.setText("cloudfilename" + i + ".ext");
            cloudFileCheckBox.add(newBox2); //.setText("filename.ext");
            cloudFileVBox.getChildren().add(newBox2);
        }
        localFileVBox.setPadding(new Insets(10, 10, 10, 10));
        cloudFileVBox.setPadding(new Insets(10, 10, 10, 10));
        
        localFilePane.setContent(localFileVBox);
        cloudFilePane.setContent(cloudFileVBox);
        /*
         Rectangle rect = new Rectangle(200, 200, Color.RED);
         ScrollPane s1 = new ScrollPane();
         s1.setPrefSize(120, 120);
         s1.setContent(rect);
         */
        fileListsHBox.getChildren().add(localFilePane);
        fileListsHBox.getChildren().add(actionButtonsVBox);
        fileListsHBox.getChildren().add(cloudFilePane);
        
        HBox repoSettings = new HBox();
        
        VBox localRepoSettings = new VBox();
        VBox cloudRepoSettings = new VBox();
        
        localRepoSettings.setPadding(new Insets(10, 10, 10, 10));
        cloudRepoSettings.setPadding(new Insets(10, 10, 10, 10));
        
        repoSettings.getChildren().add(localRepoSettings);
        repoSettings.getChildren().add(cloudRepoSettings);
        
        //Change Local Directory Button
        Button changeLocalDirBtn = new Button();
        changeLocalDirBtn.setText("Change local directory");
        changeLocalDirBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser dc = new DirectoryChooser();
                File selectedDirectory = dc.showDialog(primaryStage);
                
                if(selectedDirectory == null){
                    //do nothing
                    System.out.println("No local directory selected");
                }else{
                    localDir = selectedDirectory;
                    localDirLabel.setText(localDir.getPath());
                    localFileList = localDir.listFiles();
                    System.out.println("Changed local directory");
                    repopulateLocalFiles();
                }
            }
        });

        //Change Cloud Directory Button
        Button changeCloudDirBtn = new Button();
        changeCloudDirBtn.setText("Change cloud directory");
        changeCloudDirBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser dc = new DirectoryChooser();
                File selectedDirectory = dc.showDialog(primaryStage);
                
                if(selectedDirectory == null){
                    //do nothing
                }else{
                    cloudDir = selectedDirectory;
                    cloudDirLabel.setText(cloudDir.getPath());
                    cloudFileList = cloudDir.listFiles();
                    System.out.println("Changed cloud directory");
                    repopulateCloudFiles();
                }
            }
        });
        
        localRepoSettings.getChildren().add(localDirLabel);
        localRepoSettings.getChildren().add(changeLocalDirBtn);
        
        cloudRepoSettings.getChildren().add(cloudDirLabel);
        cloudRepoSettings.getChildren().add(changeCloudDirBtn);
        
        VBox fullView = new VBox();
        fullView.setMaxWidth(Double.MAX_VALUE);
        fullView.getChildren().add(repoSettings);
        fullView.getChildren().add(fileListsHBox);
        
        //GridPane master = new GridPane();
        //master.setPadding(new Insets(10, 10, 10, 10));
        
        //master.add(localDirLabel,1,1);
        //master.add(cloudDirLabel,2,1);
        
        Scene scene = new Scene(fullView, 800, 600);

        primaryStage.setTitle("BetterGit JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
public void repopulateLocalFiles()
{
localFileCheckBox.clear();
localFileVBox.getChildren().clear();
for(int i =0 ; i < localFileList.length; i++)
    {
    CheckBox newCheckBox = new CheckBox();
    newCheckBox.setText(localFileList[i].toString());
    localFileCheckBox.add(newCheckBox);            
    localFileVBox.getChildren().add(newCheckBox);
    }
}
public void repopulateCloudFiles()
{
cloudFileCheckBox.clear();
cloudFileVBox.getChildren().clear();
for(int i =0 ; i < cloudFileList.length; i++)
    {
    CheckBox newCheckBox = new CheckBox();
    newCheckBox.setText(cloudFileList[i].toString());
    cloudFileCheckBox.add(newCheckBox);
    cloudFileVBox.getChildren().add(newCheckBox);
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
