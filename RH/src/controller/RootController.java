package controller;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ItemVO;
import model.MemberVO;
import model.ProductVO;

public class RootController implements Initializable {

	@FXML
	private TableView<MemberVO> tableView = new TableView<>();
	@FXML
	private TableView<ItemVO> ItemTable = new TableView<>();
	@FXML
	private TableView<ProductVO> OrderTable = new TableView<>();
	@FXML
	private TableView<ProductVO> productTable = new TableView<>();
	@FXML
	private Button btnJoin;// ȸ������

	@FXML
	private ToggleGroup tgSearchMember;// ��ȭ��ȣ,�̸� ��۱׷�
	@FXML
	private RadioButton rbM_hp;// ȸ����ȣ
	@FXML
	private RadioButton rbM_neme;// �̸�

	@FXML
	private TextField txtSearch;// ã��ĭ
	@FXML
	private Button btnSearch;// ã���ư

	// @FXML
	// private TextField txtM_no;// ȸ����ȣ
	@FXML
	private TextField txtM_name;// ȸ���̸�
	@FXML
	private ToggleGroup tgM_gender;// ȸ������
	@FXML
	private RadioButton rbM_male;// ����
	@FXML
	private RadioButton rbM_female;// ����
	@FXML
	private TextField txtM_hp;// ȸ�� ��ȭ��ȣ
	@FXML
	private TextField txtM_visitMotive;// ���Ե���
	@FXML
	private DatePicker dpM_birthday;// �������
	@FXML
	private TextField txtM_recomm;// ��õ��
	@FXML
	private Button btnSave;// �����ư
	@FXML
	private Button btnInit;// �ʱ�ȭ ��ư
	@FXML
	private Button btnExit;// �ݱ��ư
	@FXML
	private Button btnEdit;// ȸ������ ����
	@FXML
	private Button btnDelete;// ����
	@FXML
	private Button btnTotal;// ��üȸ������ ��ȸ

	// �޴�����
	@FXML
	private Button btnCut;// ĿƮ
	@FXML
	private Button btnPerm;// ��
	@FXML
	private Button btnColer;// ����
	@FXML
	private Button btnClinic;// Ŭ����
	@FXML
	private Button btnDry;// �����
	@FXML
	private Button btnEtc;// ��Ÿ
	@FXML
	private Button btnMenuAdd;// �޴��߰�
	@FXML
	private Button btnMenuDelete;// �޴�����
	@FXML
	private Button btnItemAdd;// �޴��߰���ư
	@FXML
	private Button btnAll;// ��ü�޴�

	@FXML
	private Button btnItemDelete;// ����
	@FXML
	private TextField txtI_name;// �޴��̸�
	@FXML
	private String txtI_price;// �޴�����
	@FXML
	private ComboBox<String> cbI_category;// ī�װ�

	@FXML
	private Button btnPay;// ���� ��ư
	@FXML
	private Button btnMemberOk;// Ȯ�ι�ư
	@FXML
	private Button btnMenuOk;// Ȯ�ι�ư

	@FXML
	private Button btnOrderDelete;// �׸����

	@FXML
	private TextField txtName;// ���� ��������

	@FXML
	private TextField txtSum;// ����

	@FXML
	private TextField txtVisits;// ����湮�ڼ�
	/*
	 * @FXML private Label lblOrderName;
	 * 
	 * @FXML private TextField lblOrderGender;
	 * 
	 * @FXML private TextField lblOrderMenu;
	 * 
	 * @FXML private TextField lblOrderPrice;
	 */

	// �� ������ ���� ���ֻ�
	MemberVO Member = new MemberVO();
	ItemVO Item = new ItemVO();
	ProductVO product = new ProductVO();

	ObservableList<MemberVO> data = FXCollections.observableArrayList();
	ObservableList<ItemVO> dataI = FXCollections.observableArrayList();
	ObservableList<ProductVO> dataP = FXCollections.observableArrayList();
	ObservableList<ProductVO> dataO = FXCollections.observableArrayList();

	int memberSelectedIndex;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// ���̺� Į�������
		tableView.setEditable(false);// ǥ ���� �Ұ���->��â����� �����ϰ� �ϱ�����
		// Member
		TableColumn colM_no = new TableColumn("ȸ����ȣ");// ���̺� Į��
		colM_no.setMinWidth(50);
		colM_no.setCellValueFactory(new PropertyValueFactory<>("M_no"));

		TableColumn colM_name = new TableColumn("�̸�");
		colM_name.setMinWidth(200);
		colM_name.setCellValueFactory(new PropertyValueFactory<>("M_name"));

		TableColumn colM_gender = new TableColumn("����");
		colM_gender.setMinWidth(50);
		colM_gender.setCellValueFactory(new PropertyValueFactory<>("M_gender"));

		TableColumn colM_hp = new TableColumn("��ȭ��ȣ");
		colM_hp.setMinWidth(200);
		colM_hp.setCellValueFactory(new PropertyValueFactory<>("M_hp"));

		tableView.setItems(data);
		tableView.getColumns().addAll(colM_no, colM_name, colM_gender, colM_hp);

		// Item

		TableColumn colI_name = new TableColumn("�޴���");
		colI_name.setMinWidth(200);
		colI_name.setCellValueFactory(new PropertyValueFactory<>("I_name"));
		TableColumn colI_price = new TableColumn("����");
		colI_price.setMinWidth(200);
		colI_price.setCellValueFactory(new PropertyValueFactory<>("I_price"));

		ItemTable.setItems(dataI);
		ItemTable.getColumns().addAll(colI_name, colI_price);

		// product
		TableColumn colProP_no = new TableColumn("�ֹ���ȣ");

		colProP_no.setMinWidth(100);
		colProP_no.setCellValueFactory(new PropertyValueFactory<>("P_no"));

		TableColumn colProM_name = new TableColumn("ȸ���̸�");
		colProM_name.setMinWidth(100);
		colProM_name.setCellValueFactory(new PropertyValueFactory<>("M_name"));

		TableColumn colProM_gender = new TableColumn("����");
		colProM_gender.setMinWidth(50);
		colProM_gender.setCellValueFactory(new PropertyValueFactory<>("M_gender"));

		TableColumn colProI_name = new TableColumn("�޴���");
		colProI_name.setMinWidth(480);
		colProI_name.setCellValueFactory(new PropertyValueFactory<>("I_name"));

		TableColumn colProP_date = new TableColumn("�ֹ���¥");
		colProP_date.setMinWidth(150);
		colProP_date.setCellValueFactory(new PropertyValueFactory<>("P_date"));

		TableColumn colProI_price = new TableColumn("����");
		colProI_price.setMinWidth(150);
		colProI_price.setCellValueFactory(new PropertyValueFactory<>("I_price"));

		productTable.setItems(dataP);
		productTable.getColumns().addAll(colProP_no, colProM_name, colProM_gender, colProI_name, colProP_date,
				colProI_price);

		// Order
		TableColumn colOrderI_name = new TableColumn("�޴���");
		colOrderI_name.setMinWidth(200);
		colOrderI_name.setCellValueFactory(new PropertyValueFactory<>("I_name"));
		TableColumn colOrderI_price1 = new TableColumn("����");
		colOrderI_price1.setMinWidth(270);
		colOrderI_price1.setCellValueFactory(new PropertyValueFactory<>("I_price"));

		OrderTable.setItems(dataO);
		OrderTable.getColumns().addAll(colOrderI_name, colOrderI_price1);

		// ���̺� �並 Ŭ���ϱ� ������ ���� ��Ȱ��ȭ
		btnEdit.setDisable(true);
		tableView.setOnMouseClicked(event -> {
			if (event.getClickCount() != 2) {

				btnEdit.setDisable(false);
			}
		});
		tableView.setOnMouseClicked(event -> {
			if (event.getClickCount() != 2) {
				String mName;
				mName = tableView.getSelectionModel().getSelectedItem().getM_name();
				txtName.setText(mName);
			}
		});

		btnDry.setOnAction(event -> handlerBtnDryAction(event));
		btnEtc.setOnAction(event -> handlerBtnEtcAction(event));
		btnClinic.setOnAction(event -> handlerBtnClinicAction(event));
		btnColer.setOnAction(event -> handlerBtnColerAction(event));
		btnPerm.setOnAction(event -> handlerBtnPermAction(event));
		btnCut.setOnAction(event -> handlerBtnCutAction(event));

		btnPay.setOnMouseClicked(event -> handlerBtnPayAction(event));
		btnOrderDelete.setOnMouseClicked(event -> handlerBtnOrderDeleteAction(event));
		btnMenuOk.setOnMouseClicked(event -> handlerBtnMenuOkAction(event));

		btnItemDelete.setOnMouseClicked(event -> handlerBtnItemDeleteAction(event));
		btnAll.setOnAction(event -> {
			totalListItem();
		});// ��ü�޴���ư
		btnItemAdd.setOnAction(event -> handlerBtnItemAddAction(event));

		btnEdit.setOnMouseClicked(event -> handlerBtnEditAction(event));// ����
		btnJoin.setOnAction(event -> handlerBtnJoinAction(event));// ����
		btnSearch.setOnAction(event -> handlerBtnSearchAction(event));// �˻�
		btnTotal.setOnAction(event -> {
			try {
				data.removeAll(data);
				// ȸ�� ��ü ����
				totalList();
				btnEdit.setDisable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}

		});// ȸ�� ��ü����Ʈ

		// ȸ�� ��ü ����
		totalList();
		totalListItem();
		productTotalList();
		
		totalInfo(); // �湮�ڼ� �ʱ�ȭ

	}

	// ��Ÿ��ư
	public void handlerBtnEtcAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getEtc();
		int rowCount = list.size();// �� ������ ���� �� ��ŭ ����� ���Ѵ�

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// �ε����� ������ 0���� 1�� �����ϸ� ,������ �����͸� list�� �ִ´�
		}
	}

	// ����̹�ư
	public void handlerBtnDryAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getDry();
		int rowCount = list.size();// �� ������ ���� �� ��ŭ ����� ���Ѵ�

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// �ε����� ������ 0���� 1�� �����ϸ� ,������ �����͸� list�� �ִ´�
		}
	}

	// Ŭ���й�ư
	public void handlerBtnClinicAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getClinic();
		int rowCount = list.size();// �� ������ ���� �� ��ŭ ����� ���Ѵ�

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// �ε����� ������ 0���� 1�� �����ϸ� ,������ �����͸� list�� �ִ´�
		}
	}

	// ������ư
	public void handlerBtnColerAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getColer();
		int rowCount = list.size();// �� ������ ���� �� ��ŭ ����� ���Ѵ�

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// �ε����� ������ 0���� 1�� �����ϸ� ,������ �����͸� list�� �ִ´�
		}
	}

	// �� ��ư
	public void handlerBtnPermAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getPurm();
		int rowCount = list.size();// �� ������ ���� �� ��ŭ ����� ���Ѵ�

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// �ε����� ������ 0���� 1�� �����ϸ� ,������ �����͸� list�� �ִ´�
		}
	}

	// ĿƮ��ư
	public void handlerBtnCutAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getCut();
		int rowCount = list.size();// �� ������ ���� �� ��ŭ ����� ���Ѵ�

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// �ε����� ������ 0���� 1�� �����ϸ� ,������ �����͸� list�� �ִ´�
		}
	}

	// ����
	public void handlerBtnPayAction(MouseEvent event) {
		try {
			if (event.getClickCount() != 2) {
				ProductVO pVo = new ProductVO();
				// �ֹ���ȣ ���� ����
				int random = (int) (Math.random() * 9 + 1);// 0~8 +1 = 1~9
				int random2 = (int) (Math.random() * 9 + 1);
				int random3 = (int) (Math.random() * 9 + 1);
				int random4 = (int) (Math.random() * 9 + 1);
				int random5 = (int) (Math.random() * 9 + 1);
				String num = random + "" + random2 + "" + random3 + "" + random4 + "" + random5 + "";
				System.out.println(num);
				// �ֹ���ȣ ���� ��
				pVo.setP_no(Integer.parseInt(num));
				pVo.setM_name(tableView.getSelectionModel().getSelectedItem().getM_name());
				pVo.setM_gender(tableView.getSelectionModel().getSelectedItem().getM_gender());
				for (int i = 0; i < dataO.size(); i++) {
					pVo.setI_name(dataO.get(i).getI_name());
					pVo.setI_price(dataO.get(i).getI_price());

					productDAO pDAO = new productDAO();

					pDAO.getProduct(pVo);
				}
				dataP.removeAll(dataP);// ���̺�� �׸� Ŭ����
				productTotalList();// ���̺�� ���
				totalInfo();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�޴� �����Է�");
				alert.setHeaderText(" �ֹ��� ���������� ..");
				alert.setContentText("���� �޴��� �Է��ϼ���");
				alert.showAndWait();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void totalInfo() {
		productDAO pDao = new productDAO();
		ArrayList<ProductVO> list = new ArrayList();// ��ü �޴��� ���� ����� ����

		list = pDao.getProductTotal();// �迭�� DB���� �ִ´�
		int rowCount = list.size();// �� ������ ���� �� ��ŭ ����� ���Ѵ�
		
		txtVisits.setText(rowCount+"");
	}

	// �׸����
	public void handlerBtnOrderDeleteAction(MouseEvent event) {
		if (event.getClickCount() != 2) {
			OrderTable.getItems().remove(OrderTable.getSelectionModel().getSelectedItem());
			OrderTable.refresh();
			int sum = 0;
			for (int index = 0; index < dataO.size(); index++) {
				sum += Integer.parseInt(dataO.get(index).getI_price());
			}
			txtSum.setText(sum + "");
		}
	}

	// �޴�����
	public void handlerBtnMenuOkAction(MouseEvent event) {
		if (event.getClickCount() != 2) {// 2�� Ŭ������ ������
			boolean menuCheck = false;
			for (int i = 0; i < dataO.size(); i++) {
				// ������ ���̺��� �̸��� �����ͼ� �ٸ� ������ ���Ӱ� �񱳽� ��ġ�°� ������ menuCheck = true�� ���̺�信 �߰����� �ʴ´�
				if (ItemTable.getSelectionModel().getSelectedItem().getI_name().equals(dataO.get(i).getI_name())) {
					menuCheck = true;
					break;
				}
			}
			if (!menuCheck) {
				// !menuCheck�̸� �������� ���̺� �߰��Ѵ�(�ߺ�����)
				ProductVO pVo = new ProductVO();
				pVo.setI_name(ItemTable.getSelectionModel().getSelectedItem().getI_name());
				pVo.setI_price(ItemTable.getSelectionModel().getSelectedItem().getI_price());
				dataO.add(pVo);

				int sum = 0;
				for (int index = 0; index < dataO.size(); index++) {
					sum += Integer.parseInt(dataO.get(index).getI_price());
				}
				txtSum.setText(sum + "");
			}

		}
	}

	// ���̺� �� Ŭ���� Ȯ�ι�ư ������ ȸ�������� ���� ������ ������Ȳ���� �Ѿ��
	/*
	 * public void handlerBtnMemberOkAction(ActionEvent event) { // tableView
	 * MemberVO Member = tableView.getSelectionModel().getSelectedItem(); //
	 * memberSelectedIndex = tableView.getSelectionModel().getSelectedIndex();
	 * txtOrderName.setText(Member.getM_name());
	 * txtOrderGender.setText(Member.getM_gender());
	 * 
	 * }
	 */

	// ���̺�並 Ŭ���� ������ư ������ �ش����� ����
	public void handlerBtnItemDeleteAction(MouseEvent event) {
		try {
			if (event.getClickCount() != 2) {

				String i_no;// �������� ��������
				ItemDAO itDao = new ItemDAO();
				i_no = ItemTable.getSelectionModel().getSelectedItem().getI_no();
				itDao.getItemDelete(i_no);
				dataI.removeAll(dataI);
				totalListItem();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// item ����Ʈ ��ü ���
	public void totalListItem() {
		ArrayList<ItemVO> list = new ArrayList<>();
		ItemVO itVo = null;
		ItemDAO itDao = new ItemDAO();

		// �˻��� ȸ���� ����Ʈ�� ����� �����غ�
		list = itDao.getItemTotal();

		int rowCount = list.size(); // Į���� �����ϳ��� ���ڰ� 1�� ����
		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);

		}
	}

	// �޴��߰�
	public void handlerBtnItemAddAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/ItemAdd.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnItemAdd.getScene().getWindow());
			dialog.setTitle("�޴��߰�");

			Parent parentAdd = (Parent) loader.load();

			Scene scene = new Scene(parentAdd);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			// �����Է�
			// addM_�̸� �������� �ʵ�� ���,(#M_�̸�)���� ��â�� �̸��� �����ؼ� ��������
			// no�� �������̰� ������ �߰������� �Է��� �κ��� �ƴϱ⶧���� ����
			TextField addI_name = (TextField) parentAdd.lookup("#txtI_name");
			TextField addI_price = (TextField) parentAdd.lookup("#txtI_price");
			ComboBox<String> addI_category = (ComboBox) parentAdd.lookup("#cbI_category");
			addI_category.setItems(
					(ObservableList<String>) FXCollections.observableArrayList("ĿƮ", "��", "����", "Ŭ����", "�����", "etc."));// �޴�

			Button btnExit = (Button) parentAdd.lookup("#btnExit");
			Button btnOk = (Button) parentAdd.lookup("#btnOk");

			btnOk.setOnAction(e1 -> {
				try {
					System.out.println("11111111111");
					data.removeAll(data);
					ItemVO itVo = null;
					ItemDAO itDao = new ItemDAO();

					// ������ ���� ����
					System.out.println("22222222222");
					itVo = new ItemVO(addI_name.getText().trim(), addI_price.getText().trim(),
							addI_category.getSelectionModel().getSelectedItem());
					itDao = new ItemDAO();
					System.out.println("333333333");
					itDao.getItemAdd(itVo);
					if (itDao != null) {
						System.out.println("4444444444");
						totalList();
					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("�޴� �����Է�");
					alert.setHeaderText(addI_name.getText() + " �޴� ������ ���������� �߰��Ǿ����ϴ�..");
					//alert.setContentText("���� �޴��� �Է��ϼ���");
					alert.showAndWait();
					dialog.close();
				} catch (Exception ie) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("�޴� ���� �Է�");
					alert.setHeaderText("�޴��� ��Ȯ�� �Է��Ͻÿ�.");
					alert.setContentText("�������� �����ϼ���!");
					alert.showAndWait();
				}
			});// ����

			btnExit.setOnAction(e -> {
				dialog.close();
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// -----------------------------------------------------------------------
	// ����â
	public void handlerBtnEditAction(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Join.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnEdit.getScene().getWindow());
			dialog.setTitle("ȸ������");

			Parent parentEdit = (Parent) loader.load();// ��â ����

			Scene scene = new Scene(parentEdit);// �� â �ȿ� ȭ�� ����
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			// �����Է�
			// EditM_�̸� �������� �ʵ�� ���,(#M_�̸�)���� ��â�� �̸��� �����ؼ� ��������
			ToggleGroup EditM_gender = new ToggleGroup();
			Label joinTitle = (Label) parentEdit.lookup("#lblJoin");
			TextField EditM_name = (TextField) parentEdit.lookup("#txtM_name");
			RadioButton EditrbM_male = (RadioButton) parentEdit.lookup("#rbM_male");
			RadioButton EditrbM_female = (RadioButton) parentEdit.lookup("#rbM_female");
			// ��۱׷쿡 ������ư���� ����
			TextField EditM_hp = (TextField) parentEdit.lookup("#txtM_hp");
			TextField EditM_visitMotivate = (TextField) parentEdit.lookup("#txtM_visitMotivate");
			DatePicker EditdpM_birthday = (DatePicker) parentEdit.lookup("#dpM_birthday");
			TextField EditM_recomm = (TextField) parentEdit.lookup("#txtM_recomm");
			EditrbM_male.setToggleGroup(EditM_gender);
			EditrbM_female.setToggleGroup(EditM_gender);

			Button btnExit = (Button) parentEdit.lookup("#btnExit");
			Button btnOk = (Button) parentEdit.lookup("#btnOk");

			System.out.println(tableView.getSelectionModel().getSelectedItem().getM_gender().toString());
			if (tableView.getSelectionModel().getSelectedItem().getM_gender().toString().equals("����")) {
				EditrbM_female.setSelected(true);
			} else {
				EditrbM_male.setSelected(true);
			}

			joinTitle.setText("*ȸ������*");
			EditrbM_male.setDisable(true);
			EditrbM_female.setDisable(true);

			// ������ ���̺��� �����ͼ� �����Ѵ�
			EditM_name.setText(tableView.getSelectionModel().getSelectedItem().getM_name());
			EditM_hp.setText(tableView.getSelectionModel().getSelectedItem().getM_hp());
			EditdpM_birthday.setValue(tableView.getSelectionModel().getSelectedItem().getM_birthday().toLocalDate());
			EditM_visitMotivate.setText(tableView.getSelectionModel().getSelectedItem().getM_visitMotive());
			EditM_recomm.setText(tableView.getSelectionModel().getSelectedItem().getM_recomm());
			// M_no�� �������� �ʰ� ���̺��� ���� �����´�,Ok��ư �ȿ� ������ �ν��� ����
			String m_Num = tableView.getSelectionModel().getSelectedItem().getM_no();

			btnOk.setOnAction(e1 -> {
				try {
					data.removeAll(data);
					MemberVO mmVo = null;
					MemberDAO mmDao = new MemberDAO();

					// ������ ȸ�� ���� ����
					// m_Num�� ��ư �ۿ� �ʵ�� ������ ���ְ� �״�� ������,����â���� ������ ��ġ�� �ʱ⶧����
					mmVo = new MemberVO(m_Num, EditM_hp.getText().trim(), EditM_name.getText().trim(),
							EditM_gender.getSelectedToggle().getUserData().toString(),
							EditM_visitMotivate.getText().trim(), Date.valueOf(EditdpM_birthday.getValue()),
							EditM_recomm.getText().trim());
					mmDao = new MemberDAO();
					mmDao.getMemberUpdate(mmVo);
					if (mmDao != null) {
						data.removeAll(data);
						totalList();
					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ȸ�� �����Է�");
					alert.setHeaderText(EditM_name.getText() + " ȸ���� ������ ���������� �����Ǿ����ϴ�..");
					alert.setContentText("������ �Ϸ�Ǿ����ϴ�.");
					alert.showAndWait();
					dialog.close();
				} catch (Exception ie) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("ȸ�� ���� �Է�");
					alert.setHeaderText("ȸ�� ������ ��Ȯ�� �Է��Ͻÿ�.");
					alert.setContentText("�������� �����ϼ���!");
					alert.showAndWait();
				}
			});

			btnExit.setOnAction(e -> {
				dialog.close();
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �ݱ� ��ư
	public void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	// ã�� ��ư
	public void handlerBtnSearchAction(ActionEvent event) {
		ArrayList<MemberVO> list = new ArrayList<>();
		MemberVO mmVo = new MemberVO();
		MemberDAO mmDao = null;

		boolean searchResult = false;

		try {
			mmDao = new MemberDAO();// ����

			// ������ư�� ����������
			if (tgSearchMember.getSelectedToggle() != null) {
				if (tgSearchMember.getSelectedToggle().getUserData().toString().equals("�̸�")) {
					list = mmDao.getMemberCheck(txtSearch.getText(), "�̸�");// �̸��� �� �˻�ĭ�� �ҷ��´�
				} else {
					list = mmDao.getMemberCheck(txtSearch.getText(), "HP");// ��ȣ�� �� �˻�ĭ�� �ҷ��´�
				}
			} else {
				// ������ư�� �������� �ʾ����� �˾�
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("ȸ�� ���� �˻�");
				alert.setHeaderText("ȸ���� �̸��� �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");
				alert.showAndWait();
			}
			// �˻��� ȸ���� ����Ʈ�� ����� �����غ�
			int rowCount = list.size(); // Į���� �����ϳ��� ���ڰ� 1�� ����
			data.removeAll(data);
			for (int index = 0; index < rowCount; index++) {
				mmVo = list.get(index);
				data.add(mmVo);
				searchResult = true;
			}

			if (!searchResult) {// ���� ��ġ�ϴ� ���� ������ ���â
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ȸ�� ���� �˻�");
				alert.setHeaderText(txtSearch.getText() + " ȸ���� ����Ʈ�� �����ϴ�.");
				alert.setContentText("�ٽ� �˻��ϼ���.");
				alert.showAndWait();
			}

		} catch (Exception e) {// �� �� ���� �߻���
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ȸ�� ���� �˻� ����");
			alert.setHeaderText("ȸ�� ���� �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�ٽ� �ϼ���.");
			alert.showAndWait();
		}
	}

	// ȸ�� ��ü ����Ʈ
	public void totalList() {
		MemberDAO mmDao = new MemberDAO();
		MemberVO mm = null;
		ArrayList<MemberVO> list = new ArrayList();// ��ü �޴��� ���� ����� ����

		list = mmDao.getMemberTotal();// �迭�� DB���� �ִ´�
		int rowCount = list.size();// �� ������ ���� �� ��ŭ ����� ���Ѵ�

		// TrimToSize(); �뷮�� ArrayList�� ���� ��� ���� �����մϴ�.
		for (int index = 0; index < rowCount; index++) {
			mm = list.get(index);
			data.add(mm);
			// �ε����� ������ 0���� 1�� �����ϸ� ,������ �����͸� list�� �ִ´�
		}
	}

	// ȸ�� ��ü ����Ʈ
	public void productTotalList() {
		productDAO pDao = new productDAO();
		ProductVO p = null;
		ArrayList<ProductVO> list = new ArrayList();// ��ü �޴��� ���� ����� ����

		list = pDao.getProductTotal();// �迭�� DB���� �ִ´�
		int rowCount = list.size();// �� ������ ���� �� ��ŭ ����� ���Ѵ�

		// TrimToSize(); �뷮�� ArrayList�� ���� ��� ���� �����մϴ�.
		for (int index = 0; index < rowCount; index++) {
			p = list.get(index);
			dataP.add(p);
			// �ε����� ������ 0���� 1�� �����ϸ� ,������ �����͸� list�� �ִ´�
		}
	}

	// ȸ������ ��ư ������ ��â����
	// �� ���� �Է��ϰ� DB�� ����
	public void handlerBtnJoinAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Join.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnJoin.getScene().getWindow());
			dialog.setTitle("ȸ������");

			Parent parentInsert = (Parent) loader.load();

			Scene scene = new Scene(parentInsert);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			// �����Է�
			// insertM_�̸� �������� �ʵ�� ���,(#M_�̸�)���� ��â�� �̸��� �����ؼ� ��������
			ToggleGroup insertM_gender = new ToggleGroup();
			TextField insertM_name = (TextField) parentInsert.lookup("#txtM_name");
			RadioButton insertrbM_male = (RadioButton) parentInsert.lookup("#rbM_male");
			RadioButton insertrbM_female = (RadioButton) parentInsert.lookup("#rbM_female");
			// ��۱׷쿡 ������ư���� ����
			TextField insertM_hp = (TextField) parentInsert.lookup("#txtM_hp");
			TextField insertM_visitMotivate = (TextField) parentInsert.lookup("#txtM_visitMotivate");
			DatePicker insertdpM_birthday = (DatePicker) parentInsert.lookup("#dpM_birthday");
			TextField insertM_recomm = (TextField) parentInsert.lookup("#txtM_recomm");
			insertrbM_male.setToggleGroup(insertM_gender);
			insertrbM_female.setToggleGroup(insertM_gender);

			Button btnExit = (Button) parentInsert.lookup("#btnExit");
			Button btnOk = (Button) parentInsert.lookup("#btnOk");

			btnOk.setOnAction(e1 -> {
				try {
					System.out.println("11111111111");
					data.removeAll(data);
					MemberVO mmVo = null;
					MemberDAO mmDao = new MemberDAO();

					// �л� ���� ����
					System.out.println("22222222222");
					mmVo = new MemberVO(insertM_hp.getText().trim(), insertM_name.getText().trim(),
							insertM_gender.getSelectedToggle().getUserData().toString(),
							insertM_visitMotivate.getText().trim(), Date.valueOf(insertdpM_birthday.getValue()),
							insertM_recomm.getText().trim());
					mmDao = new MemberDAO();
					System.out.println("333333333");
					mmDao.getMemberJoin(mmVo);
					if (mmDao != null) {
						System.out.println("4444444444");
						totalList();
					}
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ȸ�� �����Է�");
					alert.setHeaderText(insertM_name.getText() + " ȸ���� ������ ���������� �߰��Ǿ����ϴ�..");
					alert.setContentText("������ �Ϸ�Ǿ����ϴ�.");
					alert.showAndWait();
					dialog.close();
				} catch (Exception ie) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("ȸ�� ���� �Է�");
					alert.setHeaderText("ȸ�� ������ ��Ȯ�� �Է��Ͻÿ�.");
					alert.setContentText("�������� �����ϼ���!");
					alert.showAndWait();
				}
			});// ����

			btnExit.setOnAction(e -> {
				dialog.close();
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}