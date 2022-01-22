/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package p7_notepad;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class P7_notepad extends Application {

    MenuBar bar = new MenuBar();
    Menu file = new Menu("File");
    Menu edit = new Menu("Edit");
    Menu help = new Menu("Help");
    MenuItem file_new = new MenuItem("new");
    MenuItem file_open = new MenuItem("open");
    MenuItem file_save = new MenuItem("save");
    MenuItem file_exit = new MenuItem("exit");
    MenuItem edit_undo = new MenuItem("Undo");
    MenuItem edit_cut = new MenuItem("Cut");
    MenuItem edit_copy = new MenuItem("Copy");
    MenuItem edit_paste = new MenuItem("Paste");
    MenuItem edit_delete = new MenuItem("Delete");
    MenuItem edit_selectAll = new MenuItem("Select All");
    MenuItem help_about = new MenuItem("About Notepad");
    SeparatorMenuItem sep1 = new SeparatorMenuItem();
    SeparatorMenuItem sep2 = new SeparatorMenuItem();
    SeparatorMenuItem sep3 = new SeparatorMenuItem();
    TextArea txt = new TextArea();
    @Override
    public void init() {
        file_new.setAccelerator(KeyCombination.keyCombination("Ctrl+l"));
        file_open.setAccelerator(KeyCombination.keyCombination("Ctrl+o"));
        file_save.setAccelerator(KeyCombination.keyCombination("Ctrl+b"));
        file_exit.setAccelerator(KeyCombination.keyCombination("Ctrl+t"));
        edit_undo.setAccelerator(KeyCombination.keyCombination("Ctrl+u"));
        edit_cut.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        edit_copy.setAccelerator(KeyCombination.keyCombination("Ctrl+e"));
        edit_paste.setAccelerator(KeyCombination.keyCombination("Ctrl+r"));
        edit_delete.setAccelerator(KeyCombination.keyCombination("Ctrl+h"));
        file.getItems().addAll(file_new, file_open, file_save, file_exit);
        file.getItems().add(3,sep1);
        edit.getItems().addAll(edit_undo,edit_cut,edit_copy,edit_paste,edit_delete,edit_selectAll);
        edit.getItems().add(1,sep2);
        edit.getItems().add(6,sep3);
        help.getItems().addAll(help_about);
        bar.getMenus().addAll(file, edit, help);
       
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //_________________________________________________________________ FILE ______________________________________________________________
        //////////////////////new
        file_new.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Open New File");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to clear screen ?");
        alert.showAndWait();
        txt.clear();
        }});
        //////////////////////open
        file_open.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
          try{
//          File opened_file = new File("C:\\Users\\Admin\\Desktop\\hello.txt");
          FileChooser fc = new FileChooser();
          FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("txt files", "*.txt");
          File opened_file = fc.showOpenDialog(null);
          Scanner scan = new Scanner(opened_file);
          while (scan.hasNext()) {
               txt.appendText(scan.nextLine() + '\n');
            }
            scan.close();
          }catch(Exception e){
           System.out.println(e.getMessage());}
        }});
        //////////////////////save
        file_save.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
          FileChooser fc = new FileChooser();
          FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("txt files", "*.txt");
          File saved_file = fc.showSaveDialog(null);
          try{
          FileWriter fw = new FileWriter(saved_file);
          fw.write(txt.getText());
          fw.close();
          } catch(Exception e){
              System.out.println(e.getMessage());}
        }});
        //////////////////////exit
        file_exit.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        Platform.exit();
        }});
        //_________________________________________________________________ EDIT ______________________________________________________________
        ////////////////////undo
        edit_undo.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        txt.deletePreviousChar();
        }});
        ////////////////////cut
        edit_cut.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        txt.cut();
        }});
        ////////////////////copy
        edit_copy.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        txt.copy();
        }});
        ////////////////////paste
        edit_paste.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        txt.paste();
        }});
        ////////////////////delete
        edit_delete.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        txt.deleteText(txt.getSelection());
        }});
        ////////////////////select all
        edit_selectAll.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        txt.selectAll();
        }});
        //_________________________________________________________________ HELP ______________________________________________________________
        //////////////////////open
        help_about.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Eman Hesham IOT");
        alert.showAndWait();
        }});
        //______________________________________________________________________________________________________________________
        BorderPane pane = new BorderPane();
        pane.setTop(bar);
        pane.setCenter(txt);
        Scene scene = new Scene(pane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Notepad");
        primaryStage.show();
    }

    public static void main(String[] args) {
        // TODO code application logic here
     Application.launch(args);
    }

}
