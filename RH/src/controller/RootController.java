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
	private Button btnJoin;// 회원가입

	@FXML
	private ToggleGroup tgSearchMember;// 전화번호,이름 토글그룹
	@FXML
	private RadioButton rbM_hp;// 회원번호
	@FXML
	private RadioButton rbM_neme;// 이름

	@FXML
	private TextField txtSearch;// 찾기칸
	@FXML
	private Button btnSearch;// 찾기버튼

	// @FXML
	// private TextField txtM_no;// 회원번호
	@FXML
	private TextField txtM_name;// 회원이름
	@FXML
	private ToggleGroup tgM_gender;// 회원성별
	@FXML
	private RadioButton rbM_male;// 남성
	@FXML
	private RadioButton rbM_female;// 여성
	@FXML
	private TextField txtM_hp;// 회원 전화번호
	@FXML
	private TextField txtM_visitMotive;// 가입동기
	@FXML
	private DatePicker dpM_birthday;// 생년월일
	@FXML
	private TextField txtM_recomm;// 추천인
	@FXML
	private Button btnSave;// 저장버튼
	@FXML
	private Button btnInit;// 초기화 버튼
	@FXML
	private Button btnExit;// 닫기버튼
	@FXML
	private Button btnEdit;// 회원정보 수정
	@FXML
	private Button btnDelete;// 삭제
	@FXML
	private Button btnTotal;// 전체회원정보 조회

	// 메뉴선택
	@FXML
	private Button btnCut;// 커트
	@FXML
	private Button btnPerm;// 펌
	@FXML
	private Button btnColer;// 염색
	@FXML
	private Button btnClinic;// 클리닉
	@FXML
	private Button btnDry;// 드라이
	@FXML
	private Button btnEtc;// 기타
	@FXML
	private Button btnMenuAdd;// 메뉴추가
	@FXML
	private Button btnMenuDelete;// 메뉴삭제
	@FXML
	private Button btnItemAdd;// 메뉴추가버튼
	@FXML
	private Button btnAll;// 전체메뉴

	@FXML
	private Button btnItemDelete;// 삭제
	@FXML
	private TextField txtI_name;// 메뉴이름
	@FXML
	private String txtI_price;// 메뉴가격
	@FXML
	private ComboBox<String> cbI_category;// 카테고리

	@FXML
	private Button btnPay;// 결제 버튼
	@FXML
	private Button btnMemberOk;// 확인버튼
	@FXML
	private Button btnMenuOk;// 확인버튼

	@FXML
	private Button btnOrderDelete;// 항목삭제

	@FXML
	private TextField txtName;// 님의 서비스정보

	@FXML
	private TextField txtSum;// 가격

	@FXML
	private TextField txtVisits;// 매장방문자수
	/*
	 * @FXML private Label lblOrderName;
	 * 
	 * @FXML private TextField lblOrderGender;
	 * 
	 * @FXML private TextField lblOrderMenu;
	 * 
	 * @FXML private TextField lblOrderPrice;
	 */

	// 쓸 변수들 선언 해주삼
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

		// 테이블 칼럼만들기
		tableView.setEditable(false);// 표 수정 불가능->새창띄워서 수정하게 하기위함
		// Member
		TableColumn colM_no = new TableColumn("회원번호");// 테이블 칼럼
		colM_no.setMinWidth(50);
		colM_no.setCellValueFactory(new PropertyValueFactory<>("M_no"));

		TableColumn colM_name = new TableColumn("이름");
		colM_name.setMinWidth(200);
		colM_name.setCellValueFactory(new PropertyValueFactory<>("M_name"));

		TableColumn colM_gender = new TableColumn("성별");
		colM_gender.setMinWidth(50);
		colM_gender.setCellValueFactory(new PropertyValueFactory<>("M_gender"));

		TableColumn colM_hp = new TableColumn("전화번호");
		colM_hp.setMinWidth(200);
		colM_hp.setCellValueFactory(new PropertyValueFactory<>("M_hp"));

		tableView.setItems(data);
		tableView.getColumns().addAll(colM_no, colM_name, colM_gender, colM_hp);

		// Item

		TableColumn colI_name = new TableColumn("메뉴명");
		colI_name.setMinWidth(200);
		colI_name.setCellValueFactory(new PropertyValueFactory<>("I_name"));
		TableColumn colI_price = new TableColumn("가격");
		colI_price.setMinWidth(200);
		colI_price.setCellValueFactory(new PropertyValueFactory<>("I_price"));

		ItemTable.setItems(dataI);
		ItemTable.getColumns().addAll(colI_name, colI_price);

		// product
		TableColumn colProP_no = new TableColumn("주문번호");

		colProP_no.setMinWidth(100);
		colProP_no.setCellValueFactory(new PropertyValueFactory<>("P_no"));

		TableColumn colProM_name = new TableColumn("회원이름");
		colProM_name.setMinWidth(100);
		colProM_name.setCellValueFactory(new PropertyValueFactory<>("M_name"));

		TableColumn colProM_gender = new TableColumn("성별");
		colProM_gender.setMinWidth(50);
		colProM_gender.setCellValueFactory(new PropertyValueFactory<>("M_gender"));

		TableColumn colProI_name = new TableColumn("메뉴명");
		colProI_name.setMinWidth(480);
		colProI_name.setCellValueFactory(new PropertyValueFactory<>("I_name"));

		TableColumn colProP_date = new TableColumn("주문날짜");
		colProP_date.setMinWidth(150);
		colProP_date.setCellValueFactory(new PropertyValueFactory<>("P_date"));

		TableColumn colProI_price = new TableColumn("가격");
		colProI_price.setMinWidth(150);
		colProI_price.setCellValueFactory(new PropertyValueFactory<>("I_price"));

		productTable.setItems(dataP);
		productTable.getColumns().addAll(colProP_no, colProM_name, colProM_gender, colProI_name, colProP_date,
				colProI_price);

		// Order
		TableColumn colOrderI_name = new TableColumn("메뉴명");
		colOrderI_name.setMinWidth(200);
		colOrderI_name.setCellValueFactory(new PropertyValueFactory<>("I_name"));
		TableColumn colOrderI_price1 = new TableColumn("가격");
		colOrderI_price1.setMinWidth(270);
		colOrderI_price1.setCellValueFactory(new PropertyValueFactory<>("I_price"));

		OrderTable.setItems(dataO);
		OrderTable.getColumns().addAll(colOrderI_name, colOrderI_price1);

		// 테이블 뷰를 클릭하기 전까진 수정 비활성화
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
		});// 전체메뉴버튼
		btnItemAdd.setOnAction(event -> handlerBtnItemAddAction(event));

		btnEdit.setOnMouseClicked(event -> handlerBtnEditAction(event));// 수정
		btnJoin.setOnAction(event -> handlerBtnJoinAction(event));// 가입
		btnSearch.setOnAction(event -> handlerBtnSearchAction(event));// 검색
		btnTotal.setOnAction(event -> {
			try {
				data.removeAll(data);
				// 회원 전체 정보
				totalList();
				btnEdit.setDisable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}

		});// 회원 전체리스트

		// 회원 전체 정보
		totalList();
		totalListItem();
		productTotalList();
		
		totalInfo(); // 방문자수 초기화

	}

	// 기타버튼
	public void handlerBtnEtcAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getEtc();
		int rowCount = list.size();// 행 갯수를 세어 그 만큼 사이즈를 정한다

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// 인덱스는 생성시 0부터 1씩 증가하며 ,생성된 데이터를 list에 넣는다
		}
	}

	// 드라이버튼
	public void handlerBtnDryAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getDry();
		int rowCount = list.size();// 행 갯수를 세어 그 만큼 사이즈를 정한다

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// 인덱스는 생성시 0부터 1씩 증가하며 ,생성된 데이터를 list에 넣는다
		}
	}

	// 클리닉버튼
	public void handlerBtnClinicAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getClinic();
		int rowCount = list.size();// 행 갯수를 세어 그 만큼 사이즈를 정한다

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// 인덱스는 생성시 0부터 1씩 증가하며 ,생성된 데이터를 list에 넣는다
		}
	}

	// 염색버튼
	public void handlerBtnColerAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getColer();
		int rowCount = list.size();// 행 갯수를 세어 그 만큼 사이즈를 정한다

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// 인덱스는 생성시 0부터 1씩 증가하며 ,생성된 데이터를 list에 넣는다
		}
	}

	// 펌 버튼
	public void handlerBtnPermAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getPurm();
		int rowCount = list.size();// 행 갯수를 세어 그 만큼 사이즈를 정한다

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// 인덱스는 생성시 0부터 1씩 증가하며 ,생성된 데이터를 list에 넣는다
		}
	}

	// 커트버튼
	public void handlerBtnCutAction(ActionEvent event) {
		ItemDAO itDao = new ItemDAO();
		ItemVO itVo;
		ArrayList<ItemVO> list;
		list = itDao.getCut();
		int rowCount = list.size();// 행 갯수를 세어 그 만큼 사이즈를 정한다

		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);
			// 인덱스는 생성시 0부터 1씩 증가하며 ,생성된 데이터를 list에 넣는다
		}
	}

	// 결제
	public void handlerBtnPayAction(MouseEvent event) {
		try {
			if (event.getClickCount() != 2) {
				ProductVO pVo = new ProductVO();
				// 주문번호 생성 시작
				int random = (int) (Math.random() * 9 + 1);// 0~8 +1 = 1~9
				int random2 = (int) (Math.random() * 9 + 1);
				int random3 = (int) (Math.random() * 9 + 1);
				int random4 = (int) (Math.random() * 9 + 1);
				int random5 = (int) (Math.random() * 9 + 1);
				String num = random + "" + random2 + "" + random3 + "" + random4 + "" + random5 + "";
				System.out.println(num);
				// 주문번호 생성 끝
				pVo.setP_no(Integer.parseInt(num));
				pVo.setM_name(tableView.getSelectionModel().getSelectedItem().getM_name());
				pVo.setM_gender(tableView.getSelectionModel().getSelectedItem().getM_gender());
				for (int i = 0; i < dataO.size(); i++) {
					pVo.setI_name(dataO.get(i).getI_name());
					pVo.setI_price(dataO.get(i).getI_price());

					productDAO pDAO = new productDAO();

					pDAO.getProduct(pVo);
				}
				dataP.removeAll(dataP);// 테이블뷰 항목 클리어
				productTotalList();// 테이블뷰 출력
				totalInfo();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("메뉴 정보입력");
				alert.setHeaderText(" 주문이 성공적으로 ..");
				alert.setContentText("다음 메뉴를 입력하세요");
				alert.showAndWait();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void totalInfo() {
		productDAO pDao = new productDAO();
		ArrayList<ProductVO> list = new ArrayList();// 전체 메뉴를 넣을 방들을 생성

		list = pDao.getProductTotal();// 배열에 DB값을 넣는다
		int rowCount = list.size();// 행 갯수를 세어 그 만큼 사이즈를 정한다
		
		txtVisits.setText(rowCount+"");
	}

	// 항목삭제
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

	// 메뉴선택
	public void handlerBtnMenuOkAction(MouseEvent event) {
		if (event.getClickCount() != 2) {// 2번 클릭하지 않을때
			boolean menuCheck = false;
			for (int i = 0; i < dataO.size(); i++) {
				// 아이템 테이블에서 이름을 가져와서 다른 아이템 네임과 비교시 겹치는게 있으면 menuCheck = true라서 테이블뷰에 추가되지 않는다
				if (ItemTable.getSelectionModel().getSelectedItem().getI_name().equals(dataO.get(i).getI_name())) {
					menuCheck = true;
					break;
				}
			}
			if (!menuCheck) {
				// !menuCheck이면 아이템을 테이블에 추가한다(중복제거)
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

	// 테이블 뷰 클릭후 확인버튼 누르면 회원정보와 서비스 정보가 매장현황으로 넘어옴
	/*
	 * public void handlerBtnMemberOkAction(ActionEvent event) { // tableView
	 * MemberVO Member = tableView.getSelectionModel().getSelectedItem(); //
	 * memberSelectedIndex = tableView.getSelectionModel().getSelectedIndex();
	 * txtOrderName.setText(Member.getM_name());
	 * txtOrderGender.setText(Member.getM_gender());
	 * 
	 * }
	 */

	// 테이블뷰를 클릭후 삭제버튼 누르면 해당정보 삭제
	public void handlerBtnItemDeleteAction(MouseEvent event) {
		try {
			if (event.getClickCount() != 2) {

				String i_no;// 쓰기위해 선언해줌
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

	// item 리스트 전체 출력
	public void totalListItem() {
		ArrayList<ItemVO> list = new ArrayList<>();
		ItemVO itVo = null;
		ItemDAO itDao = new ItemDAO();

		// 검색한 회원과 리스트의 목록을 대조해봄
		list = itDao.getItemTotal();

		int rowCount = list.size(); // 칼럼은 제목하나당 숫자가 1씩 증가
		dataI.removeAll(dataI);
		for (int index = 0; index < rowCount; index++) {
			itVo = list.get(index);
			dataI.add(itVo);

		}
	}

	// 메뉴추가
	public void handlerBtnItemAddAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/ItemAdd.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnItemAdd.getScene().getWindow());
			dialog.setTitle("메뉴추가");

			Parent parentAdd = (Parent) loader.load();

			Scene scene = new Scene(parentAdd);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			// 정보입력
			// addM_이름 형식으로 필드명 사용,(#M_이름)으로 뷰창에 이름과 같게해서 연결해줌
			// no는 시퀀스이고 아이템 추가란에서 입력할 부분이 아니기때문에 없음
			TextField addI_name = (TextField) parentAdd.lookup("#txtI_name");
			TextField addI_price = (TextField) parentAdd.lookup("#txtI_price");
			ComboBox<String> addI_category = (ComboBox) parentAdd.lookup("#cbI_category");
			addI_category.setItems(
					(ObservableList<String>) FXCollections.observableArrayList("커트", "펌", "염색", "클리닉", "드라이", "etc."));// 메뉴

			Button btnExit = (Button) parentAdd.lookup("#btnExit");
			Button btnOk = (Button) parentAdd.lookup("#btnOk");

			btnOk.setOnAction(e1 -> {
				try {
					System.out.println("11111111111");
					data.removeAll(data);
					ItemVO itVo = null;
					ItemDAO itDao = new ItemDAO();

					// 아이템 정보 저장
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
					alert.setTitle("메뉴 정보입력");
					alert.setHeaderText(addI_name.getText() + " 메뉴 정보가 성공적으로 추가되었습니다..");
					//alert.setContentText("다음 메뉴를 입력하세요");
					alert.showAndWait();
					dialog.close();
				} catch (Exception ie) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("메뉴 정보 입력");
					alert.setHeaderText("메뉴를 정확히 입력하시오.");
					alert.setContentText("다음에는 주의하세요!");
					alert.showAndWait();
				}
			});// 저장

			btnExit.setOnAction(e -> {
				dialog.close();
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// -----------------------------------------------------------------------
	// 수정창
	public void handlerBtnEditAction(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Join.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnEdit.getScene().getWindow());
			dialog.setTitle("회원수정");

			Parent parentEdit = (Parent) loader.load();// 새창 생성

			Scene scene = new Scene(parentEdit);// 새 창 안에 화면 생성
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			// 정보입력
			// EditM_이름 형식으로 필드명 사용,(#M_이름)으로 뷰창에 이름과 같게해서 연결해줌
			ToggleGroup EditM_gender = new ToggleGroup();
			Label joinTitle = (Label) parentEdit.lookup("#lblJoin");
			TextField EditM_name = (TextField) parentEdit.lookup("#txtM_name");
			RadioButton EditrbM_male = (RadioButton) parentEdit.lookup("#rbM_male");
			RadioButton EditrbM_female = (RadioButton) parentEdit.lookup("#rbM_female");
			// 토글그룹에 라디오버튼까지 써줌
			TextField EditM_hp = (TextField) parentEdit.lookup("#txtM_hp");
			TextField EditM_visitMotivate = (TextField) parentEdit.lookup("#txtM_visitMotivate");
			DatePicker EditdpM_birthday = (DatePicker) parentEdit.lookup("#dpM_birthday");
			TextField EditM_recomm = (TextField) parentEdit.lookup("#txtM_recomm");
			EditrbM_male.setToggleGroup(EditM_gender);
			EditrbM_female.setToggleGroup(EditM_gender);

			Button btnExit = (Button) parentEdit.lookup("#btnExit");
			Button btnOk = (Button) parentEdit.lookup("#btnOk");

			System.out.println(tableView.getSelectionModel().getSelectedItem().getM_gender().toString());
			if (tableView.getSelectionModel().getSelectedItem().getM_gender().toString().equals("여성")) {
				EditrbM_female.setSelected(true);
			} else {
				EditrbM_male.setSelected(true);
			}

			joinTitle.setText("*회원수정*");
			EditrbM_male.setDisable(true);
			EditrbM_female.setDisable(true);

			// 변수를 테이블에서 가져와서 수정한다
			EditM_name.setText(tableView.getSelectionModel().getSelectedItem().getM_name());
			EditM_hp.setText(tableView.getSelectionModel().getSelectedItem().getM_hp());
			EditdpM_birthday.setValue(tableView.getSelectionModel().getSelectedItem().getM_birthday().toLocalDate());
			EditM_visitMotivate.setText(tableView.getSelectionModel().getSelectedItem().getM_visitMotive());
			EditM_recomm.setText(tableView.getSelectionModel().getSelectedItem().getM_recomm());
			// M_no는 수정하지 않고 테이블에서 값을 가져온다,Ok버튼 안에 넣으면 인식을 못함
			String m_Num = tableView.getSelectionModel().getSelectedItem().getM_no();

			btnOk.setOnAction(e1 -> {
				try {
					data.removeAll(data);
					MemberVO mmVo = null;
					MemberDAO mmDao = new MemberDAO();

					// 수정한 회원 정보 저장
					// m_Num는 버튼 밖에 필드로 선언을 해주고 그대로 가져옴,수정창에서 수정을 거치지 않기때문에
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
					alert.setTitle("회원 정보입력");
					alert.setHeaderText(EditM_name.getText() + " 회원의 정보가 성공적으로 수정되었습니다..");
					alert.setContentText("수정이 완료되었습니다.");
					alert.showAndWait();
					dialog.close();
				} catch (Exception ie) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("회원 정보 입력");
					alert.setHeaderText("회원 정보를 정확히 입력하시오.");
					alert.setContentText("다음에는 주의하세요!");
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

	// 닫기 버튼
	public void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	// 찾기 버튼
	public void handlerBtnSearchAction(ActionEvent event) {
		ArrayList<MemberVO> list = new ArrayList<>();
		MemberVO mmVo = new MemberVO();
		MemberDAO mmDao = null;

		boolean searchResult = false;

		try {
			mmDao = new MemberDAO();// 선언

			// 라디오버튼을 선택했을때
			if (tgSearchMember.getSelectedToggle() != null) {
				if (tgSearchMember.getSelectedToggle().getUserData().toString().equals("이름")) {
					list = mmDao.getMemberCheck(txtSearch.getText(), "이름");// 이름이 들어갈 검색칸을 불러온다
				} else {
					list = mmDao.getMemberCheck(txtSearch.getText(), "HP");// 번호가 들어갈 검색칸을 불러온다
				}
			} else {
				// 라디오버튼을 선택하지 않았을때 팝업
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("회원 정보 검색");
				alert.setHeaderText("회원의 이름을 입력하시오.");
				alert.setContentText("다음에는 주의하세요!");
				alert.showAndWait();
			}
			// 검색한 회원과 리스트의 목록을 대조해봄
			int rowCount = list.size(); // 칼럼은 제목하나당 숫자가 1씩 증가
			data.removeAll(data);
			for (int index = 0; index < rowCount; index++) {
				mmVo = list.get(index);
				data.add(mmVo);
				searchResult = true;
			}

			if (!searchResult) {// 만약 일치하는 값이 없으면 경고창
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("회원 정보 검색");
				alert.setHeaderText(txtSearch.getText() + " 회원이 리스트에 없습니다.");
				alert.setContentText("다시 검색하세요.");
				alert.showAndWait();
			}

		} catch (Exception e) {// 그 외 오류 발생시
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("회원 정보 검색 오류");
			alert.setHeaderText("회원 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 하세요.");
			alert.showAndWait();
		}
	}

	// 회원 전체 리스트
	public void totalList() {
		MemberDAO mmDao = new MemberDAO();
		MemberVO mm = null;
		ArrayList<MemberVO> list = new ArrayList();// 전체 메뉴를 넣을 방들을 생성

		list = mmDao.getMemberTotal();// 배열에 DB값을 넣는다
		int rowCount = list.size();// 행 갯수를 세어 그 만큼 사이즈를 정한다

		// TrimToSize(); 용량을 ArrayList의 실제 요소 수로 설정합니다.
		for (int index = 0; index < rowCount; index++) {
			mm = list.get(index);
			data.add(mm);
			// 인덱스는 생성시 0부터 1씩 증가하며 ,생성된 데이터를 list에 넣는다
		}
	}

	// 회원 전체 리스트
	public void productTotalList() {
		productDAO pDao = new productDAO();
		ProductVO p = null;
		ArrayList<ProductVO> list = new ArrayList();// 전체 메뉴를 넣을 방들을 생성

		list = pDao.getProductTotal();// 배열에 DB값을 넣는다
		int rowCount = list.size();// 행 갯수를 세어 그 만큼 사이즈를 정한다

		// TrimToSize(); 용량을 ArrayList의 실제 요소 수로 설정합니다.
		for (int index = 0; index < rowCount; index++) {
			p = list.get(index);
			dataP.add(p);
			// 인덱스는 생성시 0부터 1씩 증가하며 ,생성된 데이터를 list에 넣는다
		}
	}

	// 회원가입 버튼 누르면 새창열림
	// 새 정보 입력하고 DB에 저장
	public void handlerBtnJoinAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Join.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnJoin.getScene().getWindow());
			dialog.setTitle("회원가입");

			Parent parentInsert = (Parent) loader.load();

			Scene scene = new Scene(parentInsert);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			// 정보입력
			// insertM_이름 형식으로 필드명 사용,(#M_이름)으로 뷰창에 이름과 같게해서 연결해줌
			ToggleGroup insertM_gender = new ToggleGroup();
			TextField insertM_name = (TextField) parentInsert.lookup("#txtM_name");
			RadioButton insertrbM_male = (RadioButton) parentInsert.lookup("#rbM_male");
			RadioButton insertrbM_female = (RadioButton) parentInsert.lookup("#rbM_female");
			// 토글그룹에 라디오버튼까지 써줌
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

					// 학생 정보 저장
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
					alert.setTitle("회원 정보입력");
					alert.setHeaderText(insertM_name.getText() + " 회원의 정보가 성공적으로 추가되었습니다..");
					alert.setContentText("가입이 완료되었습니다.");
					alert.showAndWait();
					dialog.close();
				} catch (Exception ie) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("회원 정보 입력");
					alert.setHeaderText("회원 정보를 정확히 입력하시오.");
					alert.setContentText("다음에는 주의하세요!");
					alert.showAndWait();
				}
			});// 저장

			btnExit.setOnAction(e -> {
				dialog.close();
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}