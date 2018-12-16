package userRegistration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dataAccess.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RegisterUserController {

	List<String> imagesPathsList = new ArrayList<String>();

	@FXML
	private Label nameLbl;
	@FXML
	private TextField nameTxt;
	@FXML
	private Label emailLbl;
	@FXML
	private TextField emailTxt;
	@FXML
	private Label imgLbl;
	@FXML
	private TextArea imgTextArea;
	@FXML
	private Button selectImgBtn;
	@FXML
	private Button addUserBtn;
	@FXML
	private Label nameErrorLbl;
	@FXML
	private Label emailErrorLbl;
	@FXML
	private Label imgErrorLbl;
	@FXML
	private Label lbl;

	@FXML
	void chooseFiles(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("JPG Images", "*.jpg"));
		fc.getExtensionFilters().add(new ExtensionFilter("JPEG Images", "*.jpeg"));
		fc.getExtensionFilters().add(new ExtensionFilter("PNG Images", "*.png"));
		fc.getExtensionFilters().add(new ExtensionFilter("PGM Images", "*.pgm"));
		List<File> f = fc.showOpenMultipleDialog(null);
		for (File file : f) {
			System.out.println(file.getAbsolutePath());
			imagesPathsList.add(file.getAbsolutePath());
			imgTextArea.appendText("- " + file.getAbsolutePath() + "\n");
		}
	}

	@FXML
	void addUser(ActionEvent event) {
		nameErrorLbl.setText("");
		emailErrorLbl.setText("");
		imgErrorLbl.setText("");
		if (validName() && validEmail() && validImg()) {
			for (int i = 0; i < imagesPathsList.size(); i++) {
				new Driver().insertUser(nameTxt.getText(), emailTxt.getText(), imagesPathsList.get(i));
			}
			imagesPathsList.removeAll(imagesPathsList);

		} else {
			setLabels();
		}
	}

	boolean validName() {
		if (nameTxt.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	boolean validEmail() {
		if (emailTxt.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	boolean validImg() {
		if (imgTextArea.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	void setLabels() {
		if (!validName())
			nameErrorLbl.setText("Please insert name");
		if (!validEmail())
			emailErrorLbl.setText("Please insert email");
		if (!validImg())
			imgErrorLbl.setText("Please select image(s)");
	}


}
