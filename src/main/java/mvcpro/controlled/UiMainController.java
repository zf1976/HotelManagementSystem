package mvcpro.controlled;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mvcpro.model.dao.*;
import mvcpro.model.entity.*;
import mvcpro.view.AlertDefined;
import mvcpro.view.server.*;
import sun.lwawt.macosx.CThreading;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UiMainController {

    private Stage mainStage;

    @FXML
    private ImageView imageView_one;

    @FXML
    private ImageView imageView_two;

    @FXML
    ImageView iv_picture_user;
    @FXML
    private Button add;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private Button mainMinimize;

    @FXML
    private Button browse;

    @FXML
    private Button mainExit;

    @FXML
    private TextField txf_search;

    @FXML
    private TableView<UserData> mTableUser;

    @FXML
    private TableView<ClientData> mTableClient;

    @FXML
    private TableView<BookRoomData> mTableBookRoom;

    @FXML
    private TableView<InfoRoomData> mTableInfoRoom;

    @FXML
    private TableView<StandardRoomData> mTableStandardRoom;

    //用户表
    @FXML
    private TableColumn<UserData, String> tableColumnPassword;

    @FXML
    private TableColumn<UserData, String> tableColumnType;

    @FXML
    private TableColumn<UserData, String> tableColumnId;

    @FXML
    private TableColumn<UserData, Integer> tableColumnUUID;

    @FXML
    private TableColumn<UserData, String> tableColumnQs_three;

    @FXML
    private TableColumn<UserData, String> tableColumnQs_one;

    @FXML
    private TableColumn<UserData, String> tableColumnQs_two;

    @FXML
    TableColumn<UserData, String> tableColumnPicture;


    @FXML
    private TableColumn<ClientData, String> tableColumnNative_client;

    @FXML
    private TableColumn<ClientData, String> tableColumnIdNumber_client;

    @FXML
    private TableColumn<ClientData, String> tableColumnName_client;

    @FXML
    private TableColumn<ClientData, String> tableColumnSex_client;

    @FXML
    private TableColumn<ClientData, String> tableColumnCard_client;

    @FXML
    private TableColumn<ClientData, String> tableColumnPhone_client;

    //订房信息列
    @FXML
    private TableColumn<BookRoomData, Integer> tableColumnId_number_booking;

    @FXML
    private TableColumn<BookRoomData, String> tableColumnType_booking;

    @FXML
    private TableColumn<BookRoomData, Integer> tableColumnPrice_booking;

    @FXML
    private TableColumn<BookRoomData, String> tableColumnPeople_id_booking;

    @FXML
    private TableColumn<BookRoomData, String> tableColumnPeople_name_booking;
    @FXML
    private TableColumn<BookRoomData, String> tableColumnIn_date_booking;

    @FXML
    private TableColumn<BookRoomData, String> tableColumnDiscount_booking;

    @FXML
    private TableColumn<BookRoomData, String> tableColumnOut_date_booking;

    @FXML
    private TableColumn<BookRoomData, Integer> tableColumnAmout_booking;

    @FXML
    private TableColumn<BookRoomData, String> tableColumnRemark_booking;

    //房间信息列

    @FXML
    private TableColumn<InfoRoomData, String> tableColumnType_Info;

    @FXML
    private TableColumn<InfoRoomData, Integer> tableColumnArea_Info;

    @FXML
    private TableColumn<InfoRoomData, Integer> tableColumnMax_bed_Info;

    @FXML
    private TableColumn<InfoRoomData, Integer> tableColumnMax_people_Info;

    @FXML
    private TableColumn<InfoRoomData, String> tableColumnAir_conditioning_Info;
    @FXML
    private TableColumn<InfoRoomData, String> tableColumnTv_Info;

    @FXML
    private TableColumn<InfoRoomData, String> tableColumnRest_Info;

    @FXML
    private TableColumn<InfoRoomData, String> tableColumnPhone_Info;

    @FXML
    private TableColumn<InfoRoomData, String> tableColumnPs_Info;


    //房间标准列
    @FXML
    private TableColumn<StandardRoomData, Integer> tableColumnIdNumber_standard;

    @FXML
    private TableColumn<StandardRoomData, String> tableColumnType_standard;

    @FXML
    private TableColumn<StandardRoomData, String> tableColumnFloor_standard;

    @FXML
    private TableColumn<StandardRoomData, Integer> tableColumnPrice_standard;

    @FXML
    private TableColumn<StandardRoomData, String> tableColumnRemark_standard;


    //
    // 用户表数据列表，此列表绑定这控件
    //
    private final ObservableList<UserData> userData = FXCollections.observableArrayList();

    //客户数据列表
    private final ObservableList<ClientData> clientData = FXCollections.observableArrayList();

    //订房数据列表
    private final ObservableList<BookRoomData> bookRoomData = FXCollections.observableArrayList();

    //房间信息列表
    private final ObservableList<InfoRoomData> infoRoomData = FXCollections.observableArrayList();

    //房间标准信息
    private final ObservableList<StandardRoomData> standardRoomData = FXCollections.observableArrayList();


    private UserDao userDao;
    private ClientDao clientDao;
    private InfoRoomDao infoRoomDao;
    private BookRoomDao bookRoomDao;
    private StandardRoomDao standardRoomDao;

    @FXML
    void CheckAdd(ActionEvent event) throws Exception {
        System.out.println("add");
    }

    @FXML
    void CheckUadate(ActionEvent event) {
        System.out.println("update!");
    }

    @FXML
    void CheckDelete(ActionEvent event) {
        System.out.println("delete!");
    }

    @FXML
    void CheckBrower(ActionEvent event) {
        System.out.println("brower!");
    }

    @FXML
    void CheckMainExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void CheckMainMinimize(ActionEvent event) {
        mainStage.setIconified(true);
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    void systemClose(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void initialize() throws Exception {
        initDataDao();
        initProprety();
        initUserTable();
        initClientTable();
        initBookRoomTable();
        initInfoRoomTable();
        initStandardRoom();
        initActionEvent();
    }

    private void initActionEvent() {
        //用户列表添加列双击事件
        tableColumnId.setCellFactory(TextFieldTableCell.<UserData>forTableColumn());
        tableColumnId.setOnEditCommit(
                (TableColumn.CellEditEvent<UserData, String> event) -> {
                    try {
                        event.getTableView().getItems().get(event.getTablePosition().getRow()).setId(event.getNewValue());
                        userDao.update(event.getTableView().getItems().get(event.getTablePosition().getRow()).userToEntity());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

        tableColumnPassword.setCellFactory(TextFieldTableCell.<UserData>forTableColumn());
        tableColumnPassword.setOnEditCommit((TableColumn.CellEditEvent<UserData, String> event) -> {
            try {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setPassword(event.getNewValue());
                userDao.update(event.getTableView().getItems().get(event.getTablePosition().getRow()).userToEntity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tableColumnType.setCellFactory(TextFieldTableCell.<UserData>forTableColumn());
        tableColumnType.setOnEditCommit((TableColumn.CellEditEvent<UserData, String> event) -> {
            try {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setUserType(event.getNewValue());
                userDao.update(event.getTableView().getItems().get(event.getTablePosition().getRow()).userToEntity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initDataDao() {
        bookRoomDao = new BookRoomDao();
        clientDao = new ClientDao();
        infoRoomDao = new InfoRoomDao();
        standardRoomDao = new StandardRoomDao();
        userDao = new UserDao();
    }

    private void initProprety() {

        Rectangle clip_one = new Rectangle(imageView_one.getImage().getWidth(), imageView_one.getImage().getHeight());
        clip_one.setArcWidth(15);
        clip_one.setArcHeight(15);
        imageView_one.setClip(clip_one);

        Rectangle clip_two = new Rectangle(361, 30);
        clip_two.setArcWidth(15);
        clip_two.setArcHeight(15);
        imageView_two.setClip(clip_two);


        update.setFont(new Font("宋体", 13));
        delete.setFont(new Font("宋体", 13));
        add.setFont(new Font("宋体", 13));
        browse.setFont(new Font("宋体", 13));
        mainExit.setFont(new Font("宋体", 13));
        mainMinimize.setFont(new Font("宋体", 13));
        iv_picture_user.setImage(new Image("/png/timg.jpeg"));


        mTableUser.getSelectionModel().selectedItemProperty().addListener(// 选中某一行
                new ChangeListener<UserData>() {
                    @Override
                    public void changed(ObservableValue<? extends UserData> observable, UserData oldValue, UserData newValue) {
                        if (newValue.getPicture() == null)
                            iv_picture_user.setImage(new Image("/png/timg.jpeg"));
                        iv_picture_user.setImage(new Image(newValue.getPicture()));


                    }
                });
    }

    private void initUserTable() throws Exception {

        //字段名
        tableColumnId.setCellValueFactory(new PropertyValueFactory<UserData, String>("id"));
        tableColumnPassword.setCellValueFactory(new PropertyValueFactory<UserData, String>("password"));
        tableColumnType.setCellValueFactory(new PropertyValueFactory<UserData, String>("userType"));
        tableColumnUUID.setCellValueFactory(new PropertyValueFactory<UserData, Integer>("UUID"));
        tableColumnQs_one.setCellValueFactory(new PropertyValueFactory<UserData, String>("question_one"));
        tableColumnQs_two.setCellValueFactory(new PropertyValueFactory<UserData, String>("question_two"));
        tableColumnQs_three.setCellValueFactory(new PropertyValueFactory<UserData, String>("question_three"));
        tableColumnPicture.setCellValueFactory(new PropertyValueFactory<UserData, String>("picture"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("用户信息列表初始化线程已启动...");
                try {
                    for (User user : userDao.list())
                        userData.add(new UserData(user));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mTableUser.setItems(userData);
            }
        }).start();
    }

    private void initClientTable() throws Exception {


        tableColumnName_client.setCellValueFactory(new PropertyValueFactory<ClientData, String>("client_name"));
        tableColumnSex_client.setCellValueFactory(new PropertyValueFactory<ClientData, String>("client_sex"));
        tableColumnCard_client.setCellValueFactory(new PropertyValueFactory<ClientData, String>("client_id_card"));
        tableColumnPhone_client.setCellValueFactory(new PropertyValueFactory<ClientData, String>("client_phone"));
        tableColumnNative_client.setCellValueFactory(new PropertyValueFactory<ClientData, String>("client_native"));
        tableColumnIdNumber_client.setCellValueFactory(new PropertyValueFactory<ClientData, String>("client_id_number"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("客户信息列表初始化线程已启动...");
                try {
                    for (Client client : clientDao.list())
                        clientData.add(new ClientData(client));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mTableClient.setItems(clientData);
            }
        }).start();

    }

    private void initBookRoomTable() throws Exception {
        tableColumnRemark_booking.setCellValueFactory(new PropertyValueFactory<BookRoomData, String>("room_remark"));
        tableColumnAmout_booking.setCellValueFactory(new PropertyValueFactory<BookRoomData, Integer>("room_amout"));
        tableColumnPeople_name_booking.setCellValueFactory(new PropertyValueFactory<BookRoomData, String>("room_peple_name"));
        tableColumnDiscount_booking.setCellValueFactory(new PropertyValueFactory<BookRoomData, String>("room_discount"));
        tableColumnId_number_booking.setCellValueFactory(new PropertyValueFactory<BookRoomData, Integer>("room_id_number"));
        tableColumnIn_date_booking.setCellValueFactory(new PropertyValueFactory<BookRoomData, String>("room_in_date"));
        tableColumnOut_date_booking.setCellValueFactory(new PropertyValueFactory<BookRoomData, String>("room_out_date"));
        tableColumnPeople_id_booking.setCellValueFactory(new PropertyValueFactory<BookRoomData, String>("room_peple_id"));
        tableColumnType_booking.setCellValueFactory(new PropertyValueFactory<BookRoomData, String>("room_type"));
        tableColumnPrice_booking.setCellValueFactory(new PropertyValueFactory<BookRoomData, Integer>("room_price"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("房间预定信息列表初始化线程已启动...");
                try {
                    for (BookRoom bookRoom : bookRoomDao.list())
                        bookRoomData.add(new BookRoomData(bookRoom));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mTableBookRoom.setItems(bookRoomData);
            }
        }).start();


    }

    private void initInfoRoomTable() throws Exception {
        tableColumnMax_bed_Info.setCellValueFactory(new PropertyValueFactory<InfoRoomData, Integer>("max_bed"));
        tableColumnAir_conditioning_Info.setCellValueFactory(new PropertyValueFactory<InfoRoomData, String>("air_conditioning"));
        tableColumnPhone_Info.setCellValueFactory(new PropertyValueFactory<InfoRoomData, String>("iphone"));
        tableColumnType_Info.setCellValueFactory(new PropertyValueFactory<InfoRoomData, String>("type"));
        tableColumnArea_Info.setCellValueFactory(new PropertyValueFactory<InfoRoomData, Integer>("area"));
        tableColumnPs_Info.setCellValueFactory(new PropertyValueFactory<InfoRoomData, String>("ps"));
        tableColumnTv_Info.setCellValueFactory(new PropertyValueFactory<InfoRoomData, String>("tv"));
        tableColumnMax_people_Info.setCellValueFactory(new PropertyValueFactory<InfoRoomData, Integer>("max_people"));
        tableColumnRest_Info.setCellValueFactory(new PropertyValueFactory<InfoRoomData, String>("rest"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("房间信息列表初始化线程已启动...");
                try {
                    for (InfoRoom infoRoom : infoRoomDao.list())
                        infoRoomData.add(new InfoRoomData(infoRoom));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mTableInfoRoom.setItems(infoRoomData);
            }
        }).start();

    }

    private void initStandardRoom() throws Exception {
        tableColumnIdNumber_standard.setCellValueFactory(new PropertyValueFactory<StandardRoomData, Integer>("room_id_number"));
        tableColumnFloor_standard.setCellValueFactory(new PropertyValueFactory<StandardRoomData, String>("room_floor"));
        tableColumnPrice_standard.setCellValueFactory(new PropertyValueFactory<StandardRoomData, Integer>("room_price"));
        tableColumnType_standard.setCellValueFactory(new PropertyValueFactory<StandardRoomData, String>("room_type"));
        tableColumnRemark_standard.setCellValueFactory(new PropertyValueFactory<StandardRoomData, String>("room_remark"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("标准房间信息列表初始化线程已启动...");
                try {
                    for (StandardRoom standardRoom : standardRoomDao.list())
                        standardRoomData.add(new StandardRoomData(standardRoom));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mTableStandardRoom.setItems(standardRoomData);
            }
        }).start();
    }


    @FXML
    void ac_refresh_user(ActionEvent event) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("刷新线程已启动...");
                iv_picture_user.setImage(new Image("/png/timg.jpeg"));
                userData.removeAll(userData);
                try {
                    for (User user : userDao.list())
                        userData.add(new UserData(user));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
    void ac_AlterPicture_user(ActionEvent event) throws Exception {
        if (mTableUser.getSelectionModel().getSelectedIndex() == -1) {
            new AlertDefined(Alert.AlertType.INFORMATION, "提示", "当前未选中用户").show();
            return;
        }

        File file = new FileChooser().showOpenDialog(new Stage());
        UserData userSelect = mTableUser.getSelectionModel().getSelectedItem();
        if (file != null) {
            String pictureUrl = new String("/png/" + file.getName());
            iv_picture_user.setImage(new Image(pictureUrl));
            userSelect.setPicture(pictureUrl);
            userDao.update(userSelect.userToEntity());
        }
    }

    @FXML
    void ac_delete_user(ActionEvent event) throws Exception {
        try {
            int index = mTableUser.getSelectionModel().getSelectedIndex();
            if (index == -1) {
                new AlertDefined(Alert.AlertType.INFORMATION, "提示", "当前未选中用户").show();
                return;
            }
            UserData selectUser = mTableUser.getSelectionModel().getSelectedItem();
            AlertDefined dialog = new AlertDefined(Alert.AlertType.INFORMATION, "提示", "你确定要删除用户[ " + selectUser.getId() + " ]吗?");
            Optional result = dialog.showAndWait();
            if (result.get() == ButtonType.OK) {
                if (!userDao.delete(selectUser.userToEntity())) {
                    System.out.println("点击确认");
                    new AlertDefined(Alert.AlertType.ERROR, "提示", "删除用户失败").show();
                    return;
                } else {
                    new AlertDefined(Alert.AlertType.INFORMATION, "提示", "该用户已删除").show();
                    ac_refresh_user(event);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ac_search_check(ActionEvent event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("搜索线程已启动...");
                if (txf_search.getText() != null) {

                    //
                    // 获取搜索内容
                    //

                    String search_text = txf_search.getText();

                    //
                    // 存放搜索到的用户信息
                    //

                    ArrayList<UserData> search_result_list = new ArrayList<>();

                    //
                    // 遍历客户用户数据列表
                    //


                    for (int i = 0; i < userData.size(); i++) {
                        if (search_text.equals(userData.get(i).getId()) ||
                                search_text.equals(userData.get(i).getPassword()) ||
                                search_text.equals(userData.get(i).getUserType()) ||
                                search_text.equals(String.valueOf(userData.get(i).getUUID()))) {

                            //
                            // 存放符合搜索条件的用户信息
                            //

                            search_result_list.add(userData.get(i));

                        }
                    }

                    //
                    // 将符合条件的客户信息，重新插入到客户信息数据列表中，从第0行开始插入
                    //

                    userData.removeAll(userData);
                    for (int j = 0; j < search_result_list.size(); j++) {
                        userData.add(j, search_result_list.get(j));
                    }

                    //
                    // 清空
                    //

                    search_result_list.clear();
                }
            }
        }).start();
    }
}





