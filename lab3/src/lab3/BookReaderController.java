package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import textproc.*;

public class BookReaderController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();

		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();

		// bok som ska läsas in, lagras i arraylist (om den behöver användas
		// igen, istället för att skapa en ny scanner).
		List<String> holgersson = new ArrayList<String>();
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		while (s.hasNext()) {
			holgersson.add(s.next().toLowerCase());
		}
		s.close();
		// undantagsord
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			stopwords.add(scan.next().toLowerCase());
		}
		scan.close();

		GeneralWordCounter gwc = new GeneralWordCounter(stopwords);
		holgersson.forEach(word -> gwc.process(word));

		ObservableList<Map.Entry<String, Integer>> words = FXCollections.observableArrayList(gwc.getWords());
		ListView<Map.Entry<String, Integer>> listView = new ListView<Map.Entry<String, Integer>>(words);
		root.setCenter(listView);

		// Hbox for bottom-buttons
		HBox hbox = new HBox();
		ToggleGroup group = new ToggleGroup();
		RadioButton rbtn1 = new RadioButton("Alphabetic");
		RadioButton rbtn2 = new RadioButton("Frequency");
		rbtn1.setToggleGroup(group);
		rbtn2.setToggleGroup(group);
		// Button btn1 = new Button("Alphabetic");
		// Button btn2 = new Button("Frequency");
		hbox.getChildren().addAll(rbtn1, rbtn2);
		root.setBottom(hbox);
		rbtn1.setOnAction(click -> words.sort(new WordCountComparator2())); // numbers
																			// last
		rbtn2.setOnAction(click -> words.sort(new WordCountComparator()));

		// Search functin
		Button btn3 = new Button("Find");
		TextField tx1 = new TextField();
		hbox.getChildren().add(tx1);
		HBox.setHgrow(tx1, Priority.ALWAYS);
		tx1.setMaxWidth(Double.MAX_VALUE);
		hbox.getChildren().add(btn3);

		root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER) {
				if (scene.focusOwnerProperty().get() instanceof Button) {
					Button focusedbtn = (Button) scene.focusOwnerProperty().get();
					focusedbtn.fire();
					ev.consume();
				}
				if (scene.focusOwnerProperty().get().equals(tx1)) {
					btn3.fire();
					ev.consume();
				}
			}
		});
		btn3.setOnAction(click -> {
			for (int i = 0; i < words.size(); i++) {
				if (words.get(i).getKey().equals(tx1.getText().trim().toLowerCase())) {
					listView.scrollTo(words.get(i));
					listView.getSelectionModel().select(words.get(i));
					break;
				} else if (i + 1 == words.size()) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setTitle("Information");
					alert.setContentText("Your search for the word -" + tx1.getText().trim()
							+ "- did not match any words in the list");
					alert.showAndWait();
				}

			}
		});
		// FileChooser
		Button btn4 = new Button("New file");
		hbox.getChildren().add(btn4);
		FileChooser fileChooser = new FileChooser();

		btn4.setOnAction(click -> {
			File file = fileChooser.showOpenDialog(primaryStage);
			if (file.getName().toLowerCase().endsWith(".txt") && file.canRead()) {
				try {
					GeneralWordCounter gwcnew = new GeneralWordCounter(stopwords);
					Scanner s1 = new Scanner(file);
					s1.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
					while (s1.hasNext()) {
						gwcnew.process(s1.next().toLowerCase());
					}
					s1.close();
					words.clear();
					words.addAll(FXCollections.observableArrayList(gwcnew.getWords()));
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setTitle("Error");
				alert.setContentText("The file selected must be a readable .txt file");
				alert.showAndWait();
			}

		});

	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}